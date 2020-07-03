package fr.tcd.server.amqp.runner_analyses;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RunnerAnalysisDTO {
    private String content;
    private String analysis_id;
}
