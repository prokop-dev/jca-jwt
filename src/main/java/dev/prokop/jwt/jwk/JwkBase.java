package dev.prokop.jwt.jwk;

import dev.prokop.jwt.Jwa;
import dev.prokop.jwt.Jwk;
import dev.prokop.jwt.tools.Json;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public abstract class JwkBase implements Jwk {

    private PublicKeyUse use;
    private Jwa alg;
    private String kid;

    @Override
    public PublicKeyUse getUse() {
        return use;
    }

    @Override
    public Jwk setUse(PublicKeyUse use) {
        this.use = use;
        return this;
    }

    @Override
    public Jwa getAlg() {
        return alg;
    }

    @Override
    public Jwk setAlg(Jwa alg) {
        this.alg = alg;
        return this;
    }

    @Override
    public String getKid() {
        return kid;
    }

    @Override
    public Jwk setKid(String kid) {
        this.kid = kid;
        return this;
    }

    @Override
    final public String getFormat() {
        return "JWK";
    }

    @Override
    final public byte[] getEncoded() {
        return asJson().toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public final String toString() {
        return "JWK(kty=" + getKty() + ",kid=" + getKid() + ")";
    }

    protected Json asJson() {
        Json json = Json.object();
        json.set("kty", getKty().name());
        if (kid != null) json.set("kid", kid);
        if (use != null) json.set("use", use.toString());
        return json;
    }

    protected final static Base64.Decoder DECODER = Base64.getUrlDecoder();
    protected final static Base64.Encoder ENCODER = Base64.getUrlEncoder().withoutPadding();

}
