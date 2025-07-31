package br.com.starter.application.useCase.question;

import br.com.starter.domain.question.Question;

import java.util.UUID;

public class GetQuestionByCategoryIdUseCase {
    private final QuestionService questionService;
    public Question execute(UUID categoryId) {
        return questionService.findByCategoryId(categoryId);
    }
}
