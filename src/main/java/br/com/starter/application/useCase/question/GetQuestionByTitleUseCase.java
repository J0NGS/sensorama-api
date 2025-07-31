package br.com.starter.application.useCase.question;

import br.com.starter.domain.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetQuestionByTitleUseCase {
    private final QuestionService questionService;

    public Question execute(String title) {
        return questionService.findByTitleContainingIgnoreCase(titles);
    }
}
