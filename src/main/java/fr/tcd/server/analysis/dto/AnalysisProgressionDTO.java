package fr.tcd.server.analysis.dto;

import fr.tcd.server.analysis.status.AnalysisStatus;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnalysisProgressionDTO {
    // TODO: @ValidStatus cannot be "TO_START"
    private AnalysisStatus status;

    // TODO: @ValidStatus cannot be above total_steps
    // TODO: @ValidStatus cannot be different than total_steps if status is "FINISHED"
    // TODO: @ValidStatus cannot be total_steps or above if status is not "FINISHED"
    @NotNull(message = "step_number must not be null")
    @Range(min = 1)
    private int step_number;

    // TODO: @ValidStatus cannot be below step_number
    @NotNull(message = "total_steps must not be null")
    @Range(min = 1)
    private int total_steps;

    @NotEmpty(message = "step_name must not be empty")
    private String step_name;

    @NotEmpty(message = "lasting_time must not be empty")
    private Long lasting_time;

    // TODO: @ValidStatus cannot be empty if status is "FINISHED"
    private String result;
}
