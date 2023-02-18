package rfc;

import dev.prokop.jwt.tools.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class RfcUtils {

    public static String example(String path) {
        try {
            InputStream resourceAsStream = RfcUtils.class.getResourceAsStream(path);
            String content = IOUtils.readAllInputStream(resourceAsStream);
            System.out.println("Successfully read: " + path);
            return content;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}
