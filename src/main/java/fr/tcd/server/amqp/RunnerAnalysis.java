package fr.tcd.server.amqp;

import fr.tcd.server.analysis.model.AnalysisModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RunnerAnalysis {
    private String Id;
    private String genre;
    private String content;
    private AnalysisModel analyse;
}
