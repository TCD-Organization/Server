package fr.tcd.server.analysis;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisRepository extends MongoRepository<AnalysisModel, Long> {
    List<AnalysisModel> findByOwner(String owner);
}

interface AnalysisOperations {
    List<AnalysisModel> findByDocumentOwnerAndByAnalysisInDocumentAnalyses(String owner);
}