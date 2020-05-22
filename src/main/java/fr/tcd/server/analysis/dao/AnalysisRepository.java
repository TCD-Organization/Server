package fr.tcd.server.analysis.dao;

import fr.tcd.server.analysis.model.AnalysisModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalysisRepository extends MongoRepository<AnalysisModel, Long> {
}
