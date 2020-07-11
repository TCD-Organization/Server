package fr.tcd.server.analysis;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnalysisRepository extends MongoRepository<AnalysisModel, Long> {
    Optional<AnalysisModel> findByIdAndOwner(String id, String owner);
    Optional<AnalysisModel> findById(String id);
    List<AnalysisModel> findByOwner(String owner);
    void deleteByIdAndOwner(String id, String owner);
    boolean existsById(String id);
    List<AnalysisModel> findByRunner(String runner);
}


