package rfc.rfc7520;

import dev.prokop.jwt.Jwa;
import dev.prokop.jwt.Jwk;
import org.junit.Test;
import rfc.RfcUtils;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 */
public class Example36 {

    public static Jwk publicSymmetricKey() {
        return Jwk.fromJson(RfcUtils.example("/rfc/rfc7520/example36.jwk"));
    }

    @Test
    public void example36() {
        final Jwk jwk = publicSymmetricKey();
        assertEquals(Jwk.KeyType.oct, jwk.getKty());
        assertEquals("1e571774-2e08-40da-8308-e8d68773842d", jwk.getKid());
        assertEquals(Jwk.PublicKeyUse.enc, jwk.getUse());
        assertEquals(Jwa.A256GCM, jwk.getAlg());
//
        assertEquals("JWK", jwk.getFormat());
        assertEquals("HMAC", jwk.getAlgorithm());
        String encoded = new String(jwk.getEncoded(), StandardCharsets.UTF_8);
        assertTrue(encoded.contains("\"AAPapAv4LbFbiVawEjagUBluYqN5rhna-8nuldDvOx8\""));
        System.out.println(encoded);
//
//        RSAKey rsaKey = (RSAKey) jwk;
//        assertEquals(256 * 2, rsaKey.getModulus().toString(16).length());
    }

}