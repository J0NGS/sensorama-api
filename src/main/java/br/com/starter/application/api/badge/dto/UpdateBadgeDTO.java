package br.com.starter.application.api.badge.dto;


import lombok.Data;

import java.util.UUID;

@Data

public class UpdateBadgeDTO {
    String name;
    String description;
    String imageUrl;
    UUID categoryId;
}
