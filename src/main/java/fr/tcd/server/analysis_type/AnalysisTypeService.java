package fr.tcd.server.analysis_type;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisTypeService {

    private final AnalysisTypeRepository analysisTypeRepository;

    public AnalysisTypeService(AnalysisTypeRepository analysisTypeRepository) {
        this.analysisTypeRepository = analysisTypeRepository;
    }

    public List<AnalysisTypeModel> getAnalysisTypes() {
        return analysisTypeRepository.findAll();
    }

    public void deleteAnalysisType(String id) {
        analysisTypeRepository.deleteById(id);
    }

}
