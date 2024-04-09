package dev.prokop.jwt.jwk;

import dev.prokop.jwt.tools.Json;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 */
public abstract class JwkBase implements Jwk {

    private final KeyType kty;
    private final String kid;
    private PublicKeyUse use = null;

    JwkBase(KeyType kty, String kid) {
        if (kty == null) throw new IllegalArgumentException("kty must not be null");
        if (kid == null) throw new IllegalArgumentException("kid must not be null");
        this.kty = kty;
        this.kid = kid;
    }

    @Override
    public final KeyType getKty() {
        return kty;
    }

    @Override
    public final String getKid() {
        return kid;
    }

    @Override
    public final PublicKeyUse getUse() {
        return use;
    }

    public void setUse(PublicKeyUse use) {
        this.use = use;
    }

    protected final static Base64.Encoder B64_URL_ENC = Base64.getUrlEncoder().withoutPadding();

    protected Json asJson() {
        Json json = Json.object();
        json.set("kty", getKty().name());
        json.set("kid", kid);
        if (use != null) json.set("use", use.toString());
        return json;
    }

    @Override
    public String getAlgorithm() {
        return kty.name();
    }

    @Override
    final public byte[] getEncoded() {
        return asJson().toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public final String getFormat() {
        return "JWK";
    }

}
