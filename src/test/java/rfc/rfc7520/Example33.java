package rfc.rfc7520;

import dev.prokop.jwt.Jwk;
import org.junit.Test;
import rfc.RfcUtils;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This example illustrates an RSA public key. This example is the
 * public key corresponding to the private key in Example34.
 *
 * The field "kty" value of "RSA" identifies this as an RSA key. The
 * fields "n" and "e" values are the modulus and (public) exponent
 * (respectively) using the minimum octets necessary.
 *
 * For a 2048-bit key, the field "n" value is 256 octets in length when
 * decoded.
 */
public class Example33 {

    public static Jwk publicRsaKey() {
        return Jwk.fromJson(RfcUtils.example("/rfc/rfc7520/example33.jwk"));
    }

    @Test
    public void example33() {
        final Jwk jwk = publicRsaKey();
        assertEquals(Jwk.KeyType.RSA, jwk.getKty());
        assertEquals("bilbo.baggins@hobbiton.example", jwk.getKid());
        assertEquals(Jwk.PublicKeyUse.sig, jwk.getUse());

        assertEquals("JWK", jwk.getFormat());
        assertEquals("RSA", jwk.getAlgorithm());
        String encoded = new String(jwk.getEncoded(), StandardCharsets.UTF_8);
        assertTrue(encoded.contains("\"AQAB\""));
        assertTrue(encoded.contains("\"n4EPtAOCc9AlkeQHPzHStgAbgs7bTZLwUBZdR8_KuKPEHLd4rHVTeT-O-XV2jRojdNhxJWTDvNd7nqQ0VEiZQHz_AJmSCpMaJMRBSFKrKb2wqVwGU_NsYOYL-QtiWN2lbzcEe6XC0dApr5ydQLrHqkHHig3RBordaZ6Aj-oBHqFEHYpPe7Tpe-OfVfHd1E6cS6M1FZcD1NNLYD5lFHpPI9bTwJlsde3uhGqC0ZCuEHg8lhzwOHrtIQbS0FVbb9k3-tVTU4fg_3L_vniUFAKwuCLqKnS2BYwdq_mzSnbLY7h_qixoR7jig3__kRhuaxwUkRz5iaiQkqgc5gHdrNP5zw\""));

        RSAKey rsaKey = (RSAKey) jwk;
        assertEquals(256 * 2, rsaKey.getModulus().toString(16).length());
    }

}