package fr.tcd.server.analysis;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalysisRepository extends MongoRepository<AnalysisModel, Long> {
}
