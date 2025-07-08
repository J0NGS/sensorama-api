package br.com.starter.domain.game;

public enum Mode {
    SINGLE_PLAYER("SinglePlayer"),
    MULTI_PLAYER("MultiPlayer");

    private final String description;

    Mode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
