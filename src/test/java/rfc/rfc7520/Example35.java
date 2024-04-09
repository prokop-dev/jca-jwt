package rfc.rfc7520;

import dev.prokop.jwt.Jwa;
import dev.prokop.jwt.jwk.Jwk;
import org.junit.Test;
import rfc.RfcUtils;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 */
public class Example35 {

    public static Jwk publicSymmetricKey() {
        return Jwk.fromJson(RfcUtils.example("/rfc/rfc7520/example35.jwk"));
    }

    @Test
    public void example35() {
        final Jwk jwk = publicSymmetricKey();
        assertEquals(Jwk.KeyType.oct, jwk.getKty());
        assertEquals("018c0ae5-4d9b-471b-bfd6-eef314bc7037", jwk.getKid());
        assertEquals(Jwk.PublicKeyUse.sig, jwk.getUse());
//        assertEquals(Jwa.HS256, jwk.getAlg());

        assertEquals("JWK", jwk.getFormat());
        assertEquals("HMAC", jwk.getAlgorithm());
        String encoded = new String(jwk.getEncoded(), StandardCharsets.UTF_8);
        assertTrue(encoded.contains("\"hJtXIZ2uSN5kbQfbtTNWbpdmhkV8FJG-Onbc6mxCcYg\""));
    }

}