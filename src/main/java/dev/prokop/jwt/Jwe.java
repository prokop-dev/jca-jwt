package dev.prokop.jwt;

import dev.prokop.jwt.jwe.ContentAdapter;
import dev.prokop.jwt.jwe.JweHeader;
import dev.prokop.jwt.jwe.JweImpl;
import dev.prokop.jwt.tools.Json;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.List;

import static dev.prokop.jwt.tools.IOUtils.split;

/**
 * JSON Web Encryption (JWE)
 * https://www.rfc-editor.org/rfc/rfc7516.txt
 *
 * Java Interface
 */
public interface Jwe {

    JweHeader getHeader();
    byte[] getContent();
    ContentAdapter content();

    static Jwe parse(String token, KeyProvider keyProvider) throws GeneralSecurityException {
        final List<byte[]> parts = split(token);
        if (parts.size() != 5)
            throw new IllegalArgumentException(String.format("Expected five parts separated by dot."));

        // First part is JTW header, which is just base64 encoded JSON structure
        final Json header = Json.read(new String(parts.get(0), StandardCharsets.UTF_8));
        final JweHeader joseHeader = JweHeader.parse(header);

        // Key id is optional header parameter, so null is fine here.
        final Key key = keyProvider.retrieveKey(joseHeader.getKid());
        if (key == null) throw new IllegalArgumentException("Key not found");

        if (!joseHeader.getAlg().getCryptoHelper().isKeySuitableForKekDecryption(key))
            throw new IllegalArgumentException("Key " + key.getClass() + " not suitable for " + joseHeader.getAlg() + " algorithm.");
        final byte[] cek = joseHeader.getAlg().getCryptoHelper().decrypt(key, parts.get(1));

        if (!joseHeader.getEnc().getCryptoHelper().isAlgorithmSuitableForContentDecryption())
            throw new IllegalArgumentException("Algorithm " + joseHeader.getEnc() + " not suitable for content decryption.");
        byte[] plainText = joseHeader.getEnc().getCryptoHelper()
                .decrypt(cek, parts.get(0), parts.get(2), parts.get(3), parts.get(4));

        return new JweImpl(joseHeader, plainText);
    }

}
