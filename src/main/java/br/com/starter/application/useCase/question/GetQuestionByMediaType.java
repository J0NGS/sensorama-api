package br.com.starter.application.useCase.question;

import br.com.starter.domain.question.MediaType;
import br.com.starter.domain.question.Question;
import lombok.RequiredArgsConstructor;
import br.com.starter.domain.question.QuestionService;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
public class GetQuestionByMediaType {
    private final QuestionService questionService;

    public List<Question> execute(MediaType media) {
        return questionService.findByMediaType(media);
    }

}
