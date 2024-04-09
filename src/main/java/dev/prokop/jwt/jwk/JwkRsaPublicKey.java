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

    private final BigInteger modulus;
    private final BigInteger publicExponent;

    JwkRsaPublicKey(String kid, BigInteger modulus, BigInteger publicExponent) {
        super(KeyType.RSA, kid);
        this.modulus = modulus;
        this.publicExponent = publicExponent;
    }

    @Override
    public BigInteger getModulus() {
        return modulus;
    }

    @Override
    public BigInteger getPublicExponent() {
        return publicExponent;
    }

    protected Json asJson() {
        Json json = super.asJson();
        json.set("n", B64_URL_ENC.encodeToString(removeHeadZeros(getModulus().toByteArray())));
        json.set("e", B64_URL_ENC.encodeToString(removeHeadZeros(getPublicExponent().toByteArray())));
        return json;
    }

}