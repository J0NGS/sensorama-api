package br.com.starter.application.api.category.dto;

import lombok.Data;

@Data
public class UpdateCategoryDTO {
    String name;
    String description;
    String iconUrl;
}
