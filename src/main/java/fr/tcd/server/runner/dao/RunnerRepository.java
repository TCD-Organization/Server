package fr.tcd.server.runner.dao;

import fr.tcd.server.runner.model.RunnerModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RunnerRepository extends MongoRepository<RunnerModel, Long> {
    boolean existsByRunnername(String token);
}
