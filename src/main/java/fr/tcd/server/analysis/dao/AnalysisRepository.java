package fr.tcd.server.analysis.dao;

import fr.tcd.server.analysis.model.AnalysisModel;
import fr.tcd.server.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalysisRepository extends MongoRepository<AnalysisModel, Long> {
}
