package br.com.starter.application.useCase.question;

import br.com.starter.domain.question.MediaType;
import br.com.starter.domain.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class GetQuestionByMediaType {
    private final QuestionService questionService;

    public Question execute(MediaType media) {
        return questionService.findByMediaType(media);
    }

}
