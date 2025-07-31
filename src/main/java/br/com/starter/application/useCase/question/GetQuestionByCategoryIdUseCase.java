package br.com.starter.application.useCase.question;

import br.com.starter.domain.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GetQuestionByCategoryIdUseCase {
    private final QuestionService questionService;
    public Question execute(UUID categoryId) {
        return questionService.findByCategoryId(categoryId);
    }
}
