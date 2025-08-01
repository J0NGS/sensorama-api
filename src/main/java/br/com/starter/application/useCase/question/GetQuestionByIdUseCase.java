package br.com.starter.application.useCase.question;

import br.com.starter.domain.question.Question;
import lombok.RequiredArgsConstructor;
import br.com.starter.domain.question.QuestionService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetQuestionByIdUseCase {
    private final QuestionService questionService;

    public Question execute(UUID questionId) {
        return questionService.findById(questionId);
    }
}
