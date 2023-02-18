package dev.prokop.jwt;

import dev.prokop.jwt.jws.JwsHeader;
import dev.prokop.jwt.jws.JwsImpl;
import dev.prokop.jwt.tools.Json;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.List;

import static dev.prokop.jwt.tools.IOUtils.split;

/**
 * JSON Web Signature (JWS)
 * https://www.rfc-editor.org/rfc/rfc7516.txt
 *
 * Java Interface
 */
public interface Jws {

    JwsHeader getHeader();
    Json getClaims();

    static Jws parse(String token, KeyProvider keyProvider) throws GeneralSecurityException {
        final List<byte[]> parts = split(token);

        // First part is JTW header, which is just base64 encoded JSON structure
        final Json header = Json.read(new String(parts.get(0), StandardCharsets.UTF_8));
        final JwsHeader joseHeader = JwsHeader.parse(header);

        // Key id is optional header parameter, so null is fine here.
        final Key key = keyProvider.retrieveKey(joseHeader.getKid());
        if (key == null) throw new IllegalArgumentException("Key not found");
        if (!joseHeader.getAlg().getCryptoHelper().isKeySuitableForVerification(key))
            throw new IllegalArgumentException("Key " + key.getClass() + " not suitable for " + joseHeader.getAlg() + " algorithm.");

        final byte[] verifiablePayload = token
                .substring(0, token.lastIndexOf('.'))
                .getBytes(StandardCharsets.US_ASCII);
        if (!joseHeader.getAlg().getCryptoHelper().verify(key, verifiablePayload, parts.get(2))) {
            throw new SignatureException("JWS signature validation has failed.");
        }

        final Json claimSet = Json.read(new String(parts.get(1), StandardCharsets.UTF_8));
        return new JwsImpl(joseHeader, claimSet);
    }

}
