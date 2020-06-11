package fr.tcd.server.analysis.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnalysisDTO {
    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotEmpty(message = "type must not be empty")
    //TODO : @ValidType that verifies that this type exists
    private String type; // ObjectID of Analysis_Type

    @NotEmpty(message = "doc_id must not be empty")
    private String doc_id;
}
