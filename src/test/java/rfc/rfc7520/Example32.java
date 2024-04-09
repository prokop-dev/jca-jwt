package rfc.rfc7520;

import dev.prokop.jwt.jwk.Jwk;
import org.junit.Test;
import rfc.RfcUtils;

public class Example32 {

//    @Test
    public void example1() {
        final Jwk jwk = Jwk.fromJson(RfcUtils.example("/rfc/rfc7520/example32.jwk"));
        System.out.println(jwk);
    }

}