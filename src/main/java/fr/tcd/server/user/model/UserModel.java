package fr.tcd.server.user.model;

import fr.tcd.server.user.dto.UserDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection="UserModel")
@Data
@Accessors(chain = true)
public class UserModel {
    @Id
    private String id;

    @Indexed(unique = true)
    @Field(value = "Username")
    private String username;

    @Field(value = "Password")
    private String password;

    @Field(value = "Email")
    private String email;

    private List<String> roles;

    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setEmail(email);
        return userDTO;
    }
}
