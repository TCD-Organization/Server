package fr.tcd.server.analysis_type;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="AnalysisTypes")
@Data
@Accessors(chain = true)
public class AnalysisTypeModel {
    @Id
    private String id;
    private String name;
}
