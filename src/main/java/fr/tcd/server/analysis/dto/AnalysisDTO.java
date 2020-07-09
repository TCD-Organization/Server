package fr.tcd.server.analysis.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnalysisDTO {
    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotEmpty(message = "doc_id must not be empty")
    private String doc_id;
}
