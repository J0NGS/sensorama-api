package br.com.starter.domain.question;

public enum MediaType {
    IMAGE("Image"),
    VIDEO("Video"),
    AUDIO("Audio");

    private final String description;

    MediaType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
