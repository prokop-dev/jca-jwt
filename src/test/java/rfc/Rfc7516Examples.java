package rfc;

import dev.prokop.jwt.Jwe;
import dev.prokop.jwt.jwk.Jwk;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static rfc.RfcUtils.example;

public class Rfc7516Examples {

    @Test
    public void example1() throws Exception {
        final Jwk jwk = Jwk.fromJson(example("/rfc/rfc7516/example1.jwk"));
        final Jwe jwe = Jwe.parse(example("/rfc/rfc7516/example1.jwe"), id -> jwk);

        System.out.println(jwe.getHeader());
        assertEquals("RSA-OAEP", jwe.getHeader().getAlg().toString());
        assertEquals("A256GCM", jwe.getHeader().getEnc().toString());

        System.out.println(jwe.getContent().length);
        assertEquals("The true sign of intelligence is not knowledge but imagination.",
                jwe.content().asString());
    }

}