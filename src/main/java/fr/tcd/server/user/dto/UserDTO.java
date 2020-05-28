package fr.tcd.server.user.dto;

import fr.tcd.server.user.model.UserModel;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Data
public class UserDTO implements IUserDTO {
    @Override
    public UserModel toUserModel(PasswordEncoder passwordEncoder) {
        return new UserModel()
                .setUsername(this.getUsername())
                .setPassword(passwordEncoder.encode(this.getPassword()))
                .setEmail(this.getEmail())
                .setRoles(List.of("USER"));
    }
}
