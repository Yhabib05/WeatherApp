package com.weatherApp.weatherApp.Dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateUsernameRequest {
    @NotBlank
    private String newUsername;

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }
}
