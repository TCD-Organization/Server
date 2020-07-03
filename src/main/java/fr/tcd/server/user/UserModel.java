package fr.tcd.server.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="Users")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements UserDomain {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;

    private List<String> roles;

    public UserModel(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }
}
