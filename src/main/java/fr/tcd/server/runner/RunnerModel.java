package fr.tcd.server.runner;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;
import java.util.List;

@Document(collection="Runners")
@Data
@Accessors(chain = true)
public class RunnerModel {
    @Id
    private String id;

    @Indexed(unique = true)
    private String runnername;

    private String key;

    private List<String> roles = List.of("RUNNER"); // TODO: Try to see if it cannot just be of string type

    private boolean isUp;

    public RunnerDTO toDTO() {
        RunnerDTO runnerDTO = new RunnerDTO();
        runnerDTO.setRunnername(runnername);
        runnerDTO.setKey(key);
        return runnerDTO;
    }
}
