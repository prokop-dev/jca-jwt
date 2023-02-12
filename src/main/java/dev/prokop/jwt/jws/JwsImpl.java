package dev.prokop.jwt.jws;

import dev.prokop.jwt.Jws;
import dev.prokop.jwt.tools.Json;

public class JwsImpl implements Jws {

    private final JwsHeader header;
    private final Json claims;

    public JwsImpl(JwsHeader header, Json claims) {
        this.header = header;
        this.claims = claims;
    }

    @Override
    public JwsHeader getHeader() {
        return header;
    }

    @Override
    public Json getClaims() {
        return claims;
    }

}
