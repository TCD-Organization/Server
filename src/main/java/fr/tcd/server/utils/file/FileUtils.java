package fr.tcd.server.utils.file;

import org.apache.http.HttpEntity;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static fr.tcd.server.utils.mime.MimeUtils.getExtension;

public class FileUtils {
    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = null;
        fos = new FileOutputStream(convFile);

        fos.write(file.getBytes());
        fos.close();

        return convFile;
    }

    public static File downloadFileFromHttpEntity(HttpEntity httpEntity) throws MimeTypeException, IOException {
        String contentType = httpEntity.getContentType().getValue();
        String extension = getExtension(contentType);

        InputStream fileContent = httpEntity.getContent();
        Path filePath = Paths.get("tmp"+extension);
        Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);

        return new File(filePath.toString());
    }
}
