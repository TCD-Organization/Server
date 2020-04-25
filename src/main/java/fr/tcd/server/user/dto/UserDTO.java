package fr.tcd.server.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {
    @NotEmpty(message = "username must not be empty")
    private String username;

    @NotEmpty(message = "password must not be empty")
    private String password;

    @Email
    private String email;
}
