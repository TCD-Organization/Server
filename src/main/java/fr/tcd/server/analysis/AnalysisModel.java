package fr.tcd.server.analysis;

import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.exception.AnalysisAlreadyFinishedException;
import fr.tcd.server.analysis.exception.AnalysisProgressionFieldsInvalidException;
import fr.tcd.server.analysis.status.AnalysisStatus;
import fr.tcd.server.document.DocumentModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import static fr.tcd.server.analysis.status.AnalysisStatus.FINISHED;
import static fr.tcd.server.analysis.status.AnalysisStatus.TO_START;

@Document(collection="Analysis")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisModel {
    @Id
    private String id;
    private String name;
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
        this.status = TO_START;
        this.document_id = document.getId();
        this.document_name = document.getName();
        this.owner = document.getOwner();
    }

    public void updateProgress(AnalysisStatus status, String runner, int stepNumber, int totalSteps, String stepName,
                               Long lastingTime, String result) {
        updateIsValid(status, stepNumber, totalSteps, result);

        if (this.status == FINISHED) {
            throw new AnalysisAlreadyFinishedException();
        }

        if (this.status == TO_START) {
            this.start_time = new Date();
        }

        this.status = status;
        this.runner = runner;
        this.step_number = stepNumber;
        this.total_steps = totalSteps;
        this.step_name = stepName;
        this.lasting_time = lastingTime;

        if (status == FINISHED) {
            this.step_number = totalSteps;
            this.end_time = new Date();
            this.lasting_time = 0L;
            this.result = result;
        }
    }

    private void updateIsValid(AnalysisStatus status, int stepNumber, int totalSteps, String result) {
        if ((result == null || result.isEmpty()) && status == FINISHED) {
            throw new AnalysisProgressionFieldsInvalidException("Cannot have status FINISHED and result empty");
        }

        if(stepNumber <= this.step_number) {
            throw new AnalysisProgressionFieldsInvalidException("stepNumber cannot be same or lower than previous step number");
        }

        if (stepNumber == totalSteps && status != FINISHED) {
            throw new AnalysisProgressionFieldsInvalidException("StepNumber cannot equal totalSteps when status is not FINISHED");
        }
    }
}
