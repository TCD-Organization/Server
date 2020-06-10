package fr.tcd.server.amqp.runner_analyses;

import fr.tcd.server.analysis.AnalysisModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RunnerAnalysis {
    private String Id;
    private String genre;
    private String content;
    private AnalysisModel analysis;
}
