package br.com.starter.application.useCase.question;

import br.com.starter.domain.question.Question;

import java.util.UUID;

public class GetQuestionByIdUseCase {
    private final QuestionService questionService;

    public Question execute(UUID questionId) {
        return questionService.findById(questionId);
    }
}
