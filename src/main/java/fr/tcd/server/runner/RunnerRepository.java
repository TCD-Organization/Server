package fr.tcd.server.runner;

import fr.tcd.server.runner.status.RunnerStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RunnerRepository extends MongoRepository<RunnerModel, Long> {
    boolean existsByRunnername(String token);
    Optional<RunnerModel> findByRunnername(String runnername);
    List<RunnerModel> findByStatus(RunnerStatus status);
}
