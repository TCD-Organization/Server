package fr.tcd.server.analysis;

import fr.tcd.server.analysis.status.AnalysisStatus;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="Analysis")
@Data
@Accessors(chain = true)
public class AnalysisModel {
    @Id
    private String id;
    private String name;
    private String type; // ObjectID of Analysis_Type
    private AnalysisStatus status;
    private String document_id;
    private String owner;
    private String result;
    private Date startTime;
    private Date endTime;

    /*
    public AnalysisDTO toDTO() {
        AnalysisDTO analysisDTO = new AnalysisDTO();
        analysisDTO.setName(name);
        analysisDTO.setData(data);
        analysisDTO.setOperation(operation);
        analysisDTO.setOption(option);
        return analysisDTO;
    }
    */
}
