package dev.prokop.jwt.tools;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public final class IOUtils {

    private IOUtils() {throw new AssertionError();}

    public static String readAllInputStream(InputStream inputStream) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, StandardCharsets.UTF_8))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }

    public static List<byte[]> split(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }
        // TODO: add length check to prevent DoS CVE on large payloads.

        String[] tokenParts = token.split("\\.");
        if (tokenParts.length != 3 && tokenParts.length != 5) {
            throw new IllegalArgumentException(String.format("Expected three or five parts separated by dot."));
        }

        List<byte[]> decodedTokenParts = new ArrayList<>();
        for (String part : tokenParts) {
            decodedTokenParts.add(B64_URL_DECODER.decode(part));
        }

        return decodedTokenParts;
    }

    public static final Base64.Decoder B64_URL_DECODER = Base64.getUrlDecoder();

    public static byte[] removeHeadZeros(byte[] bytes) {
        while(bytes.length > 0 && bytes[0] == 0) {
            bytes = Arrays.copyOfRange(bytes, 1, bytes.length);
        }
        return bytes;
    }

}
