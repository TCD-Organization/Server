package fr.tcd.server.user.dto;

import fr.tcd.server.user.model.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public interface IUserDTO {
    @NotEmpty(message = "username must not be empty")
    String username = null;

    @NotEmpty(message = "password must not be empty")
    String password = null;

    @Email
    String email = null;

    UserModel toUserModel(PasswordEncoder passwordEncoder);
}
