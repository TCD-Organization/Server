package fr.tcd.server.authentication.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO {

    @NotEmpty(message = "username must not be empty")
    private String username;

    @NotEmpty(message = "password must not be empty")
    private String password;
}
