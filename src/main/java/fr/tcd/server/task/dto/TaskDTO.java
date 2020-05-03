package fr.tcd.server.task.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TaskDTO {
    @NotEmpty(message = "processType must not be empty")
    private String processType;

    @NotEmpty(message = "content must not be empty")
    private String content;

}
