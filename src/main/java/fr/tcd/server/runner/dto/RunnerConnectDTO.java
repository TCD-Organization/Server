package fr.tcd.server.runner.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RunnerConnectDTO {

    @NotEmpty(message = "runnername must not be empty")
    private String runnername;

    @NotEmpty(message = "key must not be empty")
    private String key;
}
