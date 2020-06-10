package fr.tcd.server.analysis.dto;

import fr.tcd.server.analysis.status.AnalysisStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnalysisProgressionDTO {
    // TODO : @ValidStatus that verifies if it is not "TO_START"
    @NotEmpty(message = "status must not be empty")
    private AnalysisStatus status;

    @NotEmpty(message = "step_number must not be empty")
    private int step_number;

    @NotEmpty(message = "total_steps must not be empty")
    private int total_steps;

    @NotEmpty(message = "step_name must not be empty")
    private String step_name;

    @NotEmpty(message = "lasting_time must not be empty")
    private String lasting_time;
}
