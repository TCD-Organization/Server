package fr.tcd.server.utils.file_content;

import fr.tcd.server.utils.file_content.exception.FileNotSpecifiedException;
import fr.tcd.server.utils.file_content.exception.FileUnknownExtensionException;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.mime.MimeTypeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static fr.tcd.server.utils.file.FileUtils.downloadFileFromHttpEntity;
import static fr.tcd.server.utils.http_entity.HttpEntityUtils.getHttpEntity;

public class FileContentUtils {

    public static String getContentFromLink(String url) throws MimeTypeException, IOException {
        String content;
        HttpEntity fileResponse = getHttpEntity(url);
        File tmpFile = downloadFileFromHttpEntity(fileResponse);
        content = getContentFromFile(tmpFile);
        tmpFile.deleteOnExit();
        return content;
    }

    public static String getContentFromFile(File file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getName());
        switch (extension) {
            case "txt":
                return scanTxtFile(file);
            case "pdf":
                return generateTxtFromPDF(file);
            default:
                throw new FileUnknownExtensionException();
        }
    }

    private static String generateTxtFromPDF(File file) throws IOException {
        System.out.println("File is :" + file.getName());

        String parsedText;
        //PDFParser parser = new PDFParser(fileInputStream);
        PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
        parser.parse();

        COSDocument cosDoc = parser.getDocument();
        PDFTextStripper pdfStripper = new PDFTextStripper();
        PDDocument pdDoc = new PDDocument(cosDoc);
        parsedText = pdfStripper.getText(pdDoc);
        System.out.println(parsedText);
        return parsedText;
    }

    private static String scanTxtFile(File file) {
        StringBuilder result = new StringBuilder();
        try {

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine())
                result.append(sc.nextLine());
        } catch (FileNotFoundException e) {
            throw new FileNotSpecifiedException();
        }

        return String.valueOf(result);
    }
}
