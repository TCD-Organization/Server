package fr.tcd.server.analysis;

import fr.tcd.server.document.DocumentModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentAnalysisContainer {
    private final DocumentModel document;
    private final AnalysisModel analysis;
}
