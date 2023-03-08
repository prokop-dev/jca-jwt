package rfc.rfc7520;

import dev.prokop.jwt.Jwk;
import dev.prokop.jwt.jwk.JwkEcPublicKey;
import dev.prokop.jwt.jwk.JwkKeystore;
import dev.prokop.jwt.jwk.JwkRsaPublicKey;
import org.junit.Test;
import rfc.RfcUtils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class Example31 {

//    @Test
    public void example1() {
        final Jwk jwk = Jwk.fromJson(RfcUtils.example("/rfc/rfc7520/example31.jwk"));
        System.out.println(jwk);
    }

}