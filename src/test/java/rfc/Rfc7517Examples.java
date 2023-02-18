package rfc;

import dev.prokop.jwt.Jwk;
import dev.prokop.jwt.jwk.JwkEcPublicKey;
import dev.prokop.jwt.jwk.JwkKeystore;
import dev.prokop.jwt.jwk.JwkRsaPublicKey;
import org.junit.Test;

import java.io.IOException;
import java.security.Key;

import static org.junit.Assert.*;

public class Rfc7517Examples {

    @Test
    public void example1() {
        final Jwk jwk = Jwk.fromJson(RfcUtils.example("/rfc/rfc7517/example1.json"));
        System.out.println(jwk);
    }

    @Test
    public void example2() {
        final JwkKeystore example2 = new JwkKeystore(RfcUtils.example("/rfc/rfc7517/example2.json"));
        assertNotNull(example2.retrieveKey("1"));
        assertNotNull(example2.retrieveKey("2011-04-29"));

        // key 1
        final JwkEcPublicKey key1 = (JwkEcPublicKey) example2.retrieveKey("1");
        assertSame(Jwk.KeyType.EC, key1.getKty());

        // key 2
        final JwkRsaPublicKey key2 = (JwkRsaPublicKey) example2.retrieveKey("2011-04-29");
        assertSame(Jwk.KeyType.RSA, key2.getKty());
    }

}