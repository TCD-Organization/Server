package fr.tcd.server.amqp.runner_analyses;

import fr.tcd.server.analysis.AnalysisModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RunnerAnalysis {
    private String Id;
    private String genre;
    private String content;
    private AnalysisModel analysis;
}
