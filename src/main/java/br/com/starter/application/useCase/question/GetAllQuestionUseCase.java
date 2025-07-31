package br.com.starter.application.useCase.question;

import br.com.starter.domain.category.Category;
import br.com.starter.domain.category.CategoryService;
import br.com.starter.domain.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAllQuestionUseCase {
    private final QuestionService questionService;

    public Page<Question> execute(Pageable pageable) {
        return questionService.getAllQuestions(pageable);
    }

}
