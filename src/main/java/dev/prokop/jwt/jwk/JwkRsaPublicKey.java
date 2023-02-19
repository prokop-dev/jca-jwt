package dev.prokop.jwt.jwk;

import dev.prokop.jwt.tools.Json;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

import static dev.prokop.jwt.tools.IOUtils.removeHeadZeros;

public final class JwkRsaPublicKey extends JwkBase implements RSAPublicKey {

    @Override
    public KeyType getKty() {
        return KeyType.RSA;
    }

    private final RSAPublicKey rsaPublicKey;

    public JwkRsaPublicKey(RSAPublicKey rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public static JwkRsaPublicKey fromJson(Json json) {
        PublicKey publicKey;

        final String n = json.at("n").asString();
        final String e = json.at("e").asString();

        final BigInteger N = new BigInteger(1, DECODER.decode(n));
        final BigInteger E = new BigInteger(1, DECODER.decode(e));

        try {
            final RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(N, E);
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
        } catch (GeneralSecurityException gse) {
            throw new IllegalArgumentException("Can't create a key from provided modulus and exponent", gse);
        }

        JwkRsaPublicKey retVal = new JwkRsaPublicKey((RSAPublicKey) publicKey);
        if (json.has("kid")) retVal.setKid(json.at("kid").asString());
        if (json.has("use")) retVal.setUse(PublicKeyUse.valueOf(json.at("use").asString()));
        return retVal;
    }

    @Override
    public String getAlgorithm() {
        return rsaPublicKey.getAlgorithm();
    }

    @Override
    public BigInteger getPublicExponent() {
        return rsaPublicKey.getPublicExponent();
    }

    @Override
    public BigInteger getModulus() {
        return rsaPublicKey.getModulus();
    }

    protected Json asJson() {
        Json json = super.asJson();
        json.set("n", ENCODER.encodeToString(removeHeadZeros(getModulus().toByteArray())));
        json.set("e", ENCODER.encodeToString(removeHeadZeros(getPublicExponent().toByteArray())));
        return json;
    }

    @Override
    public PublicKey derivePublicKey() {
        return this;
    }
}
