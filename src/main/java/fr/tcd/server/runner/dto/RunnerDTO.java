package fr.tcd.server.runner.dto;

import fr.tcd.server.runner.RunnerModel;
import fr.tcd.server.runner.status.RunnerStatus;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;

@Data
public class RunnerDTO {
    @NotEmpty(message = "runnername must not be empty")
    private String runnername;

    @NotEmpty(message = "key must not be empty")
    private String key;

    @NotEmpty(message = "ip must not be empty")
    private String ip;

    @NotEmpty(message = "port must not be empty")
    private String port;

    public RunnerModel toRunnerModel(PasswordEncoder passwordEncoder) {
        return new RunnerModel()
                .setRunnername(this.getRunnername())
                .setKey(passwordEncoder.encode(this.getKey()))
                .setStatus(RunnerStatus.DOWN)
                .setIp(ip)
                .setPort(port);
    }
}
