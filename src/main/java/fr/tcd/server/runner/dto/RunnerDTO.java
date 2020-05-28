package fr.tcd.server.runner.dto;

import fr.tcd.server.runner.model.RunnerModel;
import fr.tcd.server.user.model.UserModel;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;

@Data
public class RunnerDTO {
    @NotEmpty(message = "runnername must not be empty")
    private String runnername;

    @NotEmpty(message = "key must not be empty")
    private String key;

    public RunnerModel toRunnerModel(PasswordEncoder passwordEncoder) {
        return new RunnerModel()
                .setRunnername(this.getRunnername())
                .setKey(passwordEncoder.encode(this.getKey()));
    }
}
