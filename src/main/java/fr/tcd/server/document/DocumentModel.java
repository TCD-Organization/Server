package fr.tcd.server.document;

import fr.tcd.server.analysis.AnalysisModel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection="Document")
@Data
@Accessors(chain = true)
public class DocumentModel {
    @Id
    private String id;
    private String name;
    private String checksum;
    private String genre;
    private String content;
    private Double size;
    private List<AnalysisModel> analyses;

    @Field("user_id")
    private String userId;

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
