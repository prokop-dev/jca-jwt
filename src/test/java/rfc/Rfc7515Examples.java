package rfc;

import dev.prokop.jwt.Jwk;
import dev.prokop.jwt.Jws;
import org.junit.Test;

import static org.junit.Assert.*;
import static rfc.RfcUtils.example;

public class Rfc7515Examples {

    @Test
    public void example1() throws Exception {
        final Jwk jwk = Jwk.fromJson(example("/rfc/rfc7515/example1.jwk"));
        final Jws jws = Jws.parse(example("/rfc/rfc7515/example1.jws"), id -> jwk);

        System.out.println(jws.getHeader());
        assertEquals("HS256", jws.getHeader().getAlg().toString());

        System.out.println(jws.getClaims());
    }

    @Test
    public void example2() throws Exception {
        final Jwk jwk = Jwk.fromJson(example("/rfc/rfc7515/example2.jwk"));
        final Jws jws = Jws.parse(example("/rfc/rfc7515/example2.jws"), id -> jwk);
        System.out.println(jws.getHeader());
        System.out.println(jws.getClaims());
    }

}