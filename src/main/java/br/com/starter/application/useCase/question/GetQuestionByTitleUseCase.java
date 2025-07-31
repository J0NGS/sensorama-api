package br.com.starter.application.useCase.question;

import br.com.starter.domain.question.Question;
import lombok.RequiredArgsConstructor;
import br.com.starter.domain.question.QuestionService;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetQuestionByTitleUseCase {
    private final QuestionService questionService;

    public List<Question> execute(String title) {
        return questionService.findByTitleContainingIgnoreCase(title);
    }
}
