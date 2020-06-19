package fr.tcd.server.document;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class DocumentDTO {
    @NotEmpty(message = "name must not be empty")
    private String name;

    @NotEmpty(message = "genre must not be empty")
    private String genre;

    @NotEmpty(message = "content_type must not be empty")
    private String content_type;

    @NotEmpty(message = "content must not be empty")
    private String content;

}
