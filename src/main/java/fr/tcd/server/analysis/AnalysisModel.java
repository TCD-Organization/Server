package fr.tcd.server.analysis;

import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.status.AnalysisStatus;
import fr.tcd.server.document.DocumentModel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="Analysis")
@Data
@Accessors(chain = true)
public class AnalysisModel {
    @Id
    private String id;
    private String name;
    private String type;
    private AnalysisStatus status;
    private String document_id;
    private String document_name;
    private String owner;
    private String runner;
    private int step_number;
    private int total_steps;
    private String step_name;
    private Date start_time;
    private Long lasting_time;
    private Date end_time;
    private String result;

    public AnalysisModel(AnalysisDTO analysisDTO, DocumentModel document) {
        this.name = analysisDTO.getName();
        this.type = analysisDTO.getType();
        this.status = AnalysisStatus.TO_START;
        this.document_id = document.getId();
        this.document_name = document.getName();
        this.owner = document.getOwner();
        this.runner = null;
    }
}
