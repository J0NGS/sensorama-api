package br.com.starter.application.useCase.options;

import br.com.starter.application.api.options.dto.CreateOptionsDTO;
import br.com.starter.domain.option.Option;
import br.com.starter.domain.option.OptionService;
import br.com.starter.domain.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateOptionsUseCase {
    private final OptionService optionService;
    private final QuestionService questionService;

    public Option execute(CreateOptionsDTO createOptionsDTO) {
        Option option = new Option();

        // Valida se a questão existe
        option.setQuestion(questionService.findById(createOptionsDTO.getQuestionId()));
        option.setText(createOptionsDTO.getText());
        option.setImageUrl(createOptionsDTO.getImageUrl());
        option.setCorrect(createOptionsDTO.isCorrect());

        return optionService.createOption(option);
    }
}
