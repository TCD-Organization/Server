package fr.tcd.server.document;

import fr.tcd.server.document.exception.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public DocumentModel createDocument(DocumentDTO documentDTO, String owner) {
        String hash = hashText(documentDTO.getContent());
        if (documentAlreadyExists(hash, owner)) {
            throw new DocumentAlreadyExistsException();
        }

            DocumentModel documentModel = new DocumentModel()
                    .setName(documentDTO.getName())
                    .setHash(hash)
                    .setGenre(documentDTO.getGenre())
                    .setContent(documentDTO.getContent())
                    //.setSize(size)
                    .setOwner(owner);

        return Optional.of(documentRepository.save(documentModel)).orElseThrow(DocumentNotCreatedException::new);
    }

    public String generateTxtFromPDF(File file) {
        InputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);

            String parsedText;
            PDFParser parser = new PDFParser(fileInputStream);
            parser.parse();

            COSDocument cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
            return parsedText;
        } catch (IOException e) {
            throw new DocumentPDFNotReadException();
        }
    }

    public File convertMultiPartToFile(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = null;
            fos = new FileOutputStream(convFile);

            fos.write(file.getBytes());
            fos.close();

        } catch (IOException e) {
            throw new DocumentPDFNotDownloadedException();
        }
        return convFile;
    }


    public List<DocumentModel> getMyDocuments(String name) {
        return documentRepository.findByOwner(name);
    }

    public DocumentModel getDocument(String id, String owner) {
        return documentRepository.findByIdAndOwner(id, owner).orElseThrow(DocumentNotFoundException::new);
    }

    private boolean documentAlreadyExists(String hash, String owner) {
        return documentRepository.existsByHashAndOwner(hash, owner);
    }

    private String hashText(String text) {
        return DigestUtils.md5Hex(text).toUpperCase();
    }
}
