package br.com.starter.application.useCase.question;

import br.com.starter.application.api.question.dto.CreateQuestionDTO;
import br.com.starter.domain.category.CategoryService;
import br.com.starter.domain.question.Question;
import br.com.starter.domain.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateQuestionUseCase {
    private final QuestionService questionService;
    private final CategoryService categoryService;

    public Question execute(CreateQuestionDTO createQuestionDTO) {
        Question question = new Question();

        // Valida se a categoria existe
        question.setCategory(categoryService.findCategoryById(createQuestionDTO.getCategoryId()));
        question.setTitle(createQuestionDTO.getTitle());
        question.setDescription(createQuestionDTO.getDescription());
        question.setMediaUrl(createQuestionDTO.getMediaUrl());
        question.setMediaType(createQuestionDTO.getMediaType());

        return questionService.createQuestion(question);
    }
}
