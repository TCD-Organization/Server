package fr.tcd.server.analysis.service;

import fr.tcd.server.analysis.dao.AnalysisRepository;
import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.model.AnalysisModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AnalysisService extends ITaskService {

    private final PasswordEncoder passwordEncoder;
    private final AnalysisRepository analysisRepository;

    public AnalysisService(AnalysisRepository analysisRepository, PasswordEncoder passwordEncoder) {
        this.analysisRepository = analysisRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AnalysisModel processNewAnalysis(AnalysisDTO analysisDTO) {
        //TODO: Create AnalysisModel();
        AnalysisModel analysisModel = new AnalysisModel();
                /*.setUsername(analysisDTO.getUsername())
                .setPassword(passwordEncoder.encode(analysisDTO.getPassword()))
                .setEmail(analysisDTO.getEmail())
                .setRoles(List.of("USER"));*/

        return analysisRepository.save(analysisModel);
    }
}
