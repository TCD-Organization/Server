package fr.tcd.server.user.dao;

import fr.tcd.server.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private final Map<String, User> db = new HashMap<>();

    public Optional<User> findBy(String username) {
        return Optional.ofNullable(db.get(username));
    }

    public User save(User user) {
        db.put(user.getUsername(), user);
        return user;
    }
}
