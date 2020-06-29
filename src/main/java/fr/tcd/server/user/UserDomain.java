package fr.tcd.server.user;

import java.util.List;

public interface UserDomain {
    String id = null;

    String getId();
    void setId(String id);
    String getUsername();
    void setUsername(String username);
    String getPassword();
    void setPassword(String password);
    List<String> getRoles();
    void setRoles(List<String> roles);
}
