package fr.tcd.server.user.dto;

import fr.tcd.server.user.model.UserModel;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class AdminDTO implements IUserDTO {
    private String username;

    @NotEmpty(message = "password must not be empty")
    private String password;

    @Email
    private String email;

    @Override
    public UserModel toUserModel(PasswordEncoder passwordEncoder) {
        return new UserModel()
                .setUsername(this.getUsername())
                .setPassword(passwordEncoder.encode(this.getPassword()))
                .setEmail(this.getEmail())
                .setRoles(List.of("USER", "ADMIN"));
    }
}
