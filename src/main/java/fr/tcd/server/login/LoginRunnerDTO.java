package fr.tcd.server.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRunnerDTO {

    @NotEmpty(message = "runnername must not be empty")
    private String runnername;

    @NotEmpty(message = "key must not be empty")
    private String key;

    @NotEmpty(message = "port must not be empty")
    private int port;
}
