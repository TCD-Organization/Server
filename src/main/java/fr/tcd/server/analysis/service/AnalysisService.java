package fr.tcd.server.analysis.service;

import fr.tcd.server.amqp.AmqpConfig;
import fr.tcd.server.amqp.RunnerAnalysis;
import fr.tcd.server.analysis.dao.AnalysisRepository;
import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.exception.AnalysisNotCreatedException;
import fr.tcd.server.analysis.model.AnalysisModel;
import fr.tcd.server.analysis.status.AnalysisStatus;
import fr.tcd.server.document.dao.DocumentRepository;
import fr.tcd.server.document.exception.DocumentNotCreatedException;
import fr.tcd.server.document.model.DocumentModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalysisService extends IAnalysisService {

    private final AnalysisRepository analysisRepository;
    private final DocumentRepository documentRepository;
    private final RabbitTemplate rabbitTemplate;

    public AnalysisService(AnalysisRepository analysisRepository, DocumentRepository documentRepository, RabbitTemplate rabbitTemplate) {
        this.analysisRepository = analysisRepository;
        this.documentRepository = documentRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public boolean processNewAnalysis(Long docID, AnalysisDTO analysisDTO) {
        Optional<DocumentModel> document = documentRepository.findById(docID);
        if(document.isEmpty()) {
            throw new DocumentNotCreatedException("Document not found");
        }

        Optional<AnalysisModel> newAnalysis = createAnalysis(analysisDTO);
        if(newAnalysis.isEmpty()) {
            throw new AnalysisNotCreatedException("Analysis not created");
        }

        List<AnalysisModel> analyses = document.getAnalyses();
        document.setAnalyses(analyses.add(Optional.of(newAnalysis)));
        return true;
    }

    @Override
    protected Optional<AnalysisModel> createAnalysis(AnalysisDTO analysisDTO) {

        AnalysisModel analysisModel = new AnalysisModel()
                .setName(analysisDTO.getName())
                .setType(analysisDTO.getType())
                .setStatus(AnalysisStatus.TO_START);

        return Optional.ofNullable(analysisRepository.save(analysisModel));

    }

    @Override
    boolean formAndSendRunnerAnalysis(DocumentModel document, AnalysisModel analysis) {
        RunnerAnalysis runnerAnalysis = new RunnerAnalysis()
                .setId(document.getId())
                .setGenre(document.getGenre())
                .setContent(document.getContent())
                .setAnalyse(analysis);

        rabbitTemplate.convertAndSend(AmqpConfig.AUTH_EXCHANGE, "tcd.analysis.new", runnerAnalysis);


    }
    //public boolean sendRunnerAnalysis()



}
