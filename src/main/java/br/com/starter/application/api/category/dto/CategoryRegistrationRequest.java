package br.com.starter.application.api.category.dto;

public record CategoryRegistrationRequest(
    String name,
    String description,
    String iconUrl) {


     public CategoryRegistrationRequest(String name, String description, String iconUrl) {
        this.name = name;
        this.description = description;
        this.iconUrl = iconUrl;
    }
}
