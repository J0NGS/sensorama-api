package br.com.starter.domain.badge;

import br.com.starter.application.api.badge.dto.UpdateBadgeDTO;
import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final CategoryService categoryService;

    public Badge createBadge(Badge badge) {
        if (badge.getCategory() != null && badge.getCategory().getId() != null) {
            throw new FrontDisplayableException(HttpStatus.BAD_REQUEST, "Categoria não pode ser nula");
        }
        return badgeRepository.save(badge);
    }

    public Badge updateBadge(UUID badgeId, UpdateBadgeDTO updateBadgeDTO) {
        Badge badge = findBadgeById(badgeId);
        if(updateBadgeDTO.getName() != null || updateBadgeDTO.getDescription() != null
            || updateBadgeDTO.getImageUrl() != null || updateBadgeDTO.getCategoryId() != null) {
            throw new FrontDisplayableException(HttpStatus.BAD_REQUEST, "Campo para atualização foi fornecido como nulo");
        } else {
            badge.setName(updateBadgeDTO.getName());
            badge.setDescription(updateBadgeDTO.getDescription());
            Category category = categoryService.findCategoryById(updateBadgeDTO.getCategoryId());
            badge.setCategory(category);
            badge.setImageUrl(updateBadgeDTO.getImageUrl());
            return badgeRepository.save(badge);
        }
    }

    public Badge findBadgeById(UUID id) {
        return badgeRepository.findById(id)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Badge não encontrado"));
    }

    public List<Badge> findBadgesByNameContainingIgnoreCase(String name) {
        return badgeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Badge> findBadgesByDescriptionContainingIgnoreCase(String description) {
        return badgeRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public List<Badge> findBadgesByCategoryId(UUID categoryId) {
        return badgeRepository.findByCategoryId(categoryId);
    }

    public List<Badge> findBadgesByCategoryIdOrderByName(UUID categoryId) {
        return badgeRepository.findByCategoryIdOrderByName(categoryId);
    }

    public Long countBadgesByCategoryId(UUID categoryId) {
        return badgeRepository.countByCategoryId(categoryId);
    }

    public Page<Badge> getAllBadges(Pageable pageable) {
        return badgeRepository.findAllPageable(pageable);
    }

    public List<Badge> getAllBadgesList() {
        return badgeRepository.findAllList();
    }

    public List<Badge> getAllBadgesOrderByName() {
        return badgeRepository.findAllOrderByNameAsc();
    }

    public boolean deleteBadgeById(UUID id) {
        if (!badgeRepository.existsById(id)) {
            throw new FrontDisplayableException(HttpStatus.NOT_FOUND, "Badge não encontrado");
        }
        try {
            badgeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new FrontDisplayableException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar badge");
        }
    }

    public boolean existsById(UUID id) {
        return badgeRepository.existsById(id);
    }

    public long countAllBadges() {
        return badgeRepository.count();
    }
}
