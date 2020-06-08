package fr.tcd.server.document;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Document")
@Data
@Accessors(chain = true)
public class DocumentModel {
    @Id
    private String id;
    private String name;
    private String hash;
    private String genre;
    private String content;
    private Double size;
    //private List<AnalysisModel> analyses; // TODO : Only have the 10 latest for memory opt?
    private String owner;

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
