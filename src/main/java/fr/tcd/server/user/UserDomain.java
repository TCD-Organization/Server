package fr.tcd.server.user;

import java.util.List;

public interface UserDomain {
    String id = null;

    String getId();
    String getUsername();
    String getPassword();
    List<String> getRoles();
}
