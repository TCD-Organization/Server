package fr.tcd.server.runner;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RunnerRepository extends MongoRepository<RunnerModel, Long> {
    boolean existsByRunnername(String token);
    RunnerModel findByRunnername(String runnername);
}
