package br.com.starter.domain.game;

import br.com.starter.application.api.game.dto.UpdateGameDTO;
import br.com.starter.domain.gamePlayer.GamePlayer;
import br.com.starter.domain.profile.Profile;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public Game createGame(Profile player) {
        // cria uma nova partida single player
        Game newGame = new Game();
        newGame.setMode(Mode.SINGLE_PLAYER);

        // Cria o jogador
        GamePlayer singlePlayer = new GamePlayer();
        singlePlayer.setGame(newGame);
        singlePlayer.setProfile(player);
        singlePlayer.setScore(0);
        singlePlayer.setTurnOrder(1);
        singlePlayer.setCurrentTurn(true);
        List<GamePlayer> players = new ArrayList<>();
        players.add(singlePlayer);
        newGame.setPlayers(players);

        newGame.setStatus(Status.IN_PROGRESS); // Single player já inicia
        newGame.setStartTime(LocalDateTime.now());

        return gameRepository.save(newGame);
    }

    public Game createGameOnline(Profile player) {
        // Primeiro, tenta buscar uma partida disponível
        Game availableGame = searchGame(player);

        if (availableGame != null) {
            // Adiciona o jogador à partida encontrada
            GamePlayer newPlayer = new GamePlayer();
            newPlayer.setGame(availableGame);
            newPlayer.setProfile(player);
            newPlayer.setScore(0);
            newPlayer.setTurnOrder(2); // Segunda posição
            newPlayer.setCurrentTurn(false); // Segundo jogador não começa jogando

            // Adiciona o novo jogador à lista
            availableGame.getPlayers().add(newPlayer);

            return gameRepository.save(availableGame);
        } else {
            // Cria uma nova partida se não encontrar uma disponível
            Game newGame = new Game();
            newGame.setMode(Mode.MULTI_PLAYER);

            // Cria o primeiro jogador
            GamePlayer firstPlayer = new GamePlayer();
            firstPlayer.setGame(newGame);
            firstPlayer.setProfile(player);
            firstPlayer.setScore(0);
            firstPlayer.setTurnOrder(1);
            firstPlayer.setCurrentTurn(true); // Primeiro jogador começa

            List<GamePlayer> players = new ArrayList<>();
            players.add(firstPlayer);
            newGame.setPlayers(players);

            newGame.setStatus(Status.IN_PROGRESS); // Status IN_PROGRESS = disponível para jogar
            newGame.setStartTime(LocalDateTime.now());

            return gameRepository.save(newGame);
        }
    }

    public Game searchGame(Profile player) {
        List<Game> availableGames = gameRepository.searchOnlineGameDisponible(Status.IN_PROGRESS, Mode.MULTI_PLAYER);

        for (Game game : availableGames) {
            GamePlayer existingPlayer = game.getPlayers().get(0);

            // Verifica se não é o mesmo jogador tentando entrar na própria partida
            if (!existingPlayer.getProfile().getId().equals(player.getId())) {
                return game; // Partida disponível encontrada
            }
        }
        return null; // Não encontrou partida disponível
    }

    public Game updateGame(Game game) {
        if (!gameRepository.existsById(game.getId())) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Jogo não encontrado");
        }
        return gameRepository.save(game);
    }

    public Game updateGame(UUID gameId, UpdateGameDTO updateGameDTO) {
        Game game = findGameById(gameId);

        if (updateGameDTO.mode != null) {
            game.setMode(updateGameDTO.mode);
        }
        if (updateGameDTO.status != null) {
            game.setStatus(updateGameDTO.status);
        }
        if (updateGameDTO.playerIds != null) {
            game.setPlayers(updateGameDTO.playerIds);
        }
        if (updateGameDTO.startTime != null) {
            game.setStartTime(updateGameDTO.startTime);
        }
        if (updateGameDTO.endTime != null) {
            game.setEndTime(updateGameDTO.endTime);
        }

        return gameRepository.save(game);
    }

    public Game findGameById(UUID id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Jogo não encontrado"));
    }

    public Game switchTurn(UUID gameId) {
        Game game = findGameById(gameId);

        if (game.getPlayers() != null && game.getPlayers().size() == 2) {
            GamePlayer player1 = game.getPlayers().get(0);
            GamePlayer player2 = game.getPlayers().get(1);

            // Troca os turnos
            player1.setCurrentTurn(!player1.isCurrentTurn());
            player2.setCurrentTurn(!player2.isCurrentTurn());

            // Atualiza a ordem dos turnos
            int tempTurnOrder = player1.getTurnOrder();
            player1.setTurnOrder(player2.getTurnOrder());
            player2.setTurnOrder(tempTurnOrder);

            return gameRepository.save(game);
        } else {
            throw new FrontDisplayableException(HttpStatus.BAD_REQUEST, "Partida deve ter exatamente 2 jogadores");
        }
    }

    public Game endGame(UUID gameId) {
        Game game = findGameById(gameId);
        game.setStatus(Status.COMPLETED);
        game.setEndTime(LocalDateTime.now());

        // Remove os turnos ativos
        if (game.getPlayers() != null) {
            game.getPlayers().forEach(player -> player.setCurrentTurn(false));
        }

        return gameRepository.save(game);
    }

    public Page<Game> getAllGames(Pageable pageable) {
        return gameRepository.findAllPageable(pageable);
    }

    public List<Game> getAllGamesList() {
        return gameRepository.findAllList();
    }

    public List<Game> findByStatus(Status status) {
        return gameRepository.findByStatus(status);
    }

    public List<Game> findByMode(Mode mode) {
        return gameRepository.findByMode(mode);
    }

    public List<Game> findByStatusAndMode(Status status, Mode mode) {
        return gameRepository.findByStatusAndMode(status, mode);
    }

    public List<Game> findByStartTimeBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return gameRepository.findByStartTimeBetween(startDate, endDate);
    }

    public List<Game> findActiveGames() {
        return gameRepository.findActiveGames();
    }

    public List<Game> findCompletedGames() {
        return gameRepository.findCompletedGames();
    }

    public List<Game> findAllOrderByStartTimeDesc() {
        return gameRepository.findAllOrderByStartTimeDesc();
    }

    public List<Game> findGamesByPlayerId(UUID profileId) {
        return gameRepository.findGamesByPlayerId(profileId);
    }

    public Long countByStatus(Status status) {
        return gameRepository.countByStatus(status);
    }

    public boolean deleteGameById(UUID id) {
        if (!gameRepository.existsById(id)) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Jogo não encontrado");
        }
        try {
            gameRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new FrontDisplayableException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar jogo");
        }
    }

    public boolean existsById(UUID id) {
        return gameRepository.existsById(id);
    }

    public long countAllGames() {
        return gameRepository.count();
    }
}
