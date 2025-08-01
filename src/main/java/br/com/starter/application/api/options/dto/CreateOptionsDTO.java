package br.com.starter.application.api.options.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateOptionsDTO {
    private UUID questionId;
    private String text;
    private String imageUrl;
    private boolean isCorrect;
}
