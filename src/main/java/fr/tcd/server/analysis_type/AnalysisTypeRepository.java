package fr.tcd.server.analysis_type;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisTypeRepository extends MongoRepository<AnalysisTypeModel, Long> {
    List<AnalysisTypeModel> findAll();
    Boolean existsByName(String typeName);
    void deleteById(String id);
}


