package fr.tcd.server.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, Long> {
    UserModel findByUsername(String username);
    boolean existsByUsername(String username);
}
