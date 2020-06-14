package fr.tcd.server.utils.mime;

import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

public class MimeUtils {
    public static String getExtension(String mimeType) throws MimeTypeException {
        MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
        MimeType fileType = allTypes.forName(mimeType);
        return fileType.getExtension();
    }
}
