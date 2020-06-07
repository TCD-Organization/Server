package fr.tcd.server.analysis.repository;

import fr.tcd.server.analysis.AnalysisModel;
import fr.tcd.server.analysis.repository.AnalysisOperations;
import fr.tcd.server.document.DocumentModel;
import fr.tcd.server.document.DocumentRepository;
import fr.tcd.server.document.exception.DocumentNotFoundException;
import fr.tcd.server.user.UserModel;
import fr.tcd.server.user.UserRepository;
import fr.tcd.server.user.exception.UserNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AnalysisRepository extends MongoRepository<AnalysisModel, Long>, AnalysisOperations {
}


