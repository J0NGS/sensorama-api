package br.com.starter.application.api.badge.dto;

import java.util.UUID;

public record BadgeRegistrationRequest(
        String name,
        String description,
        String imageUrl,
        UUID categoryId) {

    public BadgeRegistrationRequest(String name, String description, String imageUrl, UUID categoryId) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }
}
