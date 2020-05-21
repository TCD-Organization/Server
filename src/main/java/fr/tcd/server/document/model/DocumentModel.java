package fr.tcd.server.document.model;

import fr.tcd.server.analysis.model.AnalysisModel;
import fr.tcd.server.document.dto.DocumentDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="Document")
@Data
@Accessors(chain = true)
public class DocumentModel {
    @Id
    private String uuid;
    private String name;
    private String checksum;
    private String content;
    private Double size;
    private List<AnalysisModel> analyses;
    private String user_id;

    /*
    public DocumentDTO toDTO() {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setName(name);
        documentDTO.setData(data);
        documentDTO.setOperation(operation);
        documentDTO.setOption(option);
        return documentDTO;
    }
    */
}
