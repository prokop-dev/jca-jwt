package dev.prokop.jwt.jwk;

import java.math.BigInteger;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

public class RSAPublicKeyDelegate implements RSAPublicKey {

    private final RSAPrivateCrtKey rsaPrivateCrtKey;

    public RSAPublicKeyDelegate(RSAPrivateCrtKey rsaPrivateCrtKey) {
        this.rsaPrivateCrtKey = rsaPrivateCrtKey;
    }

    @Override
    public BigInteger getPublicExponent() {
        return rsaPrivateCrtKey.getPublicExponent();
    }

    @Override
    public String getAlgorithm() {
        return rsaPrivateCrtKey.getAlgorithm();
    }

    @Override
    public String getFormat() {
        return rsaPrivateCrtKey.getFormat();
    }

    @Override
    public byte[] getEncoded() {
        return rsaPrivateCrtKey.getEncoded();
    }

    @Override
    public BigInteger getModulus() {
        return rsaPrivateCrtKey.getModulus();
    }

}
