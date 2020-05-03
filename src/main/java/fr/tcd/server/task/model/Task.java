package fr.tcd.server.task.model;

import fr.tcd.server.task.dto.TaskDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="Task")
@Data
@Accessors(chain = true)
public class Task {
    @Id
    private String uuid;
    private String name;
    private String content;
    private Date timestamp;

    public TaskDTO toDTO() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(name);
        taskDTO.setContent(content);
        return taskDTO;
    }
}
