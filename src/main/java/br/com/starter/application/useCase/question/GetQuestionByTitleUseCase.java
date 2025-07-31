package br.com.starter.application.useCase.question;

import br.com.starter.domain.question.Question;

public class GetQuestionByTitleUseCase {
    private final QuestionService questionService;

    public Question execute(String title) {
        return questionService.findByTitleContainingIgnoreCase(titles);
    }
}
