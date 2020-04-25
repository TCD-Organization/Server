package fr.tcd.server.user.dao;

import fr.tcd.server.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
