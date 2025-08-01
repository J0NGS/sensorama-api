package br.com.starter.application.api.question.dto;

import br.com.starter.domain.question.MediaType;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateQuestionDTO {
    private UUID categoryId;
    private String title;
    private String description;
    private String mediaUrl;
    private MediaType mediaType;
}
