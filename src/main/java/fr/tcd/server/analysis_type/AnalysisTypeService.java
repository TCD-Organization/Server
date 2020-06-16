package fr.tcd.server.analysis_type;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalysisTypeService {

    private final AnalysisTypeRepository analysisTypeRepository;

    public AnalysisTypeService(AnalysisTypeRepository analysisTypeRepository) {
        this.analysisTypeRepository = analysisTypeRepository;
    }

    public List<AnalysisTypeModel> getAnalysisTypes() {
        return analysisTypeRepository.findAll();
    }

    public AnalysisTypeModel createAnalysisType(String name) {
        AnalysisTypeModel analysisType = new AnalysisTypeModel();
        analysisType.setName(name);
        return Optional.of(analysisTypeRepository.save(analysisType)).orElseThrow(AnalysisTypeNotCreatedException::new);
    }

    public void deleteAnalysisType(String id) {
        analysisTypeRepository.deleteById(id);
    }

}
