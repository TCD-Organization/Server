package fr.tcd.server.user.dto;

import fr.tcd.server.user.UserModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public abstract class IUserDTO {
    @NotEmpty(message = "username must not be empty")
    private String username;

    @NotEmpty(message = "password must not be empty")
    private String password;

    public UserModel toUserModel(String password) {
        return new UserModel(this.getUsername(), password, List.of("USER"));
    }
}
