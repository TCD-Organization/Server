package fr.tcd.server.user.dto;

import fr.tcd.server.user.UserModel;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public abstract class IUserDTO {
    @NotEmpty(message = "username must not be empty")
    private String username;

    @NotEmpty(message = "password must not be empty")
    private String password;

    @Email
    private String email;

    public UserModel toUserModel(String password) {
        return new UserModel(this.getUsername(), password, this.getEmail(), List.of("USER"));
    }
}
