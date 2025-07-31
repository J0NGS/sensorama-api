package br.com.starter.domain.option;

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
public class OptionService {
    private final OptionRepository optionRepository;

    public Option createOption(Option option) {
        return optionRepository.save(option);
    }

    public Option findById(UUID id) {
        return optionRepository.findById(id)
                .orElseThrow(() -> new FrontDisplayableException(HttpStatus.NOT_FOUND, "Opção não encontrada"));
    }

    public List<Option> findByQuestionId(UUID questionId) {
        return optionRepository.findByQuestionId(questionId);
    }

    public List<Option> findByIsCorrect(boolean isCorrect) {
        return optionRepository.findByIsCorrect(isCorrect);
    }

    public Option findCorrectOptionsByQuestionId(UUID questionId) {
        return optionRepository.findCorrectOptionsByQuestionId(questionId);
    }

    public List<Option> findByTextContainingIgnoreCase(String text) {
        return optionRepository.findByTextContainingIgnoreCase(text);
    }

    public Page<Option> getAllOptions(Pageable pageable) {
        return optionRepository.findAllPageable(pageable);
    }

    public List<Option> getAllOptionsList() {
        return optionRepository.findAllList();
    }
}
