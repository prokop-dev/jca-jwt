package rfc;

import dev.prokop.jwt.Jwk;
import dev.prokop.jwt.Jws;
import dev.prokop.jwt.jwk.JwkEcPublicKey;
import dev.prokop.jwt.jwk.JwkKeystore;
import dev.prokop.jwt.jwk.JwkRsaPublicKey;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static rfc.RfcUtils.example;

public class Rfc7515Examples {

    @Test
    public void example1() throws Exception {
        final Jwk jwk = Jwk.fromJson(example("/rfc/rfc7515/example1.jwk"));
        final Jws jws = Jws.parse(example("/rfc/rfc7515/example1.jws"), id -> jwk);
        System.out.println(jws);
    }

}