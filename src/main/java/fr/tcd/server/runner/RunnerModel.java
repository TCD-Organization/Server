package fr.tcd.server.runner;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection="Users")
@Data
@Accessors(chain = true)
public class RunnerModel {
    @Id
    private String id;

    @Indexed(unique = true)
    @Field(value = "Username")
    private String runnername;

    @Field(value = "Password")
    private String key;

    private final List<String> roles = List.of("RUNNER"); // TODO: Try to see if it cannot just be of string type


    public RunnerDTO toDTO() {
        RunnerDTO runnerDTO = new RunnerDTO();
        runnerDTO.setRunnername(runnername);
        runnerDTO.setKey(key);
        return runnerDTO;
    }
}
