package dev.prokop.jwt.jwk;

import dev.prokop.jwt.tools.Json;

import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

import static dev.prokop.jwt.tools.IOUtils.removeHeadZeros;

public final class JwkOctSecretKey extends JwkBase {

    @Override
    public KeyType getKty() {
        return KeyType.oct;
    }

    private final byte[] key;

    public JwkOctSecretKey(byte[] key) {
        this.key = key;
    }

    public static JwkOctSecretKey fromJson(Json json) {
        PublicKey publicKey;

        final String k = json.at("k").asString();
        final byte[] K = DECODER.decode(k);

        JwkOctSecretKey retVal = new JwkOctSecretKey(K);
        if (json.has("kid")) retVal.setKid(json.at("kid").asString());
        if (json.has("use")) retVal.setUse(PublicKeyUse.valueOf(json.at("use").asString()));
        return retVal;
    }

    @Override
    public String getAlgorithm() {
        return "HMAC"; //TODO: unhappy with that choice
    }

    @Override
    public String getFormat() {
        return "raw";
    }

    @Override
    public byte[] getEncoded() {
        return key;
    }

    protected Json asJson() {
        Json json = super.asJson();
        json.set("k", ENCODER.encodeToString(key));
        return json;
    }

    @Override
    public PublicKey derivePublicKey() {
        throw new AssertionError();
    }
}
