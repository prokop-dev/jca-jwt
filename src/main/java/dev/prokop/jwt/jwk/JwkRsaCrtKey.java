//package dev.prokop.jwt.jwk;
//
//import dev.prokop.jwt.tools.Json;
//
//import java.math.BigInteger;
//import java.security.GeneralSecurityException;
//import java.security.KeyFactory;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.interfaces.RSAPrivateCrtKey;
//import java.security.spec.RSAPrivateCrtKeySpec;
//
//import static dev.prokop.jwt.tools.IOUtils.removeHeadZeros;
//
///**
// * RSA keypair build from Chinese Remainder Theorem JWK source.
// */
//public final class JwkRsaCrtKey extends JwkExBase implements RSAPrivateCrtKey {
//
//    @Override
//    public KeyType getKty() {
//        return KeyType.RSA;
//    }
//
//    private final RSAPrivateCrtKey rsaPrivateCrtKey;
//
//    public JwkRsaCrtKey(RSAPrivateCrtKey rsaPrivateCrtKey) {
//        this.rsaPrivateCrtKey = rsaPrivateCrtKey;
//    }
//
//    public static JwkRsaCrtKey fromJson(Json json) {
//        PrivateKey privateKey;
//
//        final String n = json.at("n").asString();
//        final String e = json.at("e").asString();
//        final String d = json.at("d").asString();
//        final String p = json.at("p").asString();
//        final String q = json.at("q").asString();
//        final String dp = json.at("dp").asString();
//        final String dq = json.at("dq").asString();
//        final String qi = json.at("qi").asString();
//
//        final BigInteger N = new BigInteger(1, DECODER.decode(n));
//        final BigInteger E = new BigInteger(1, DECODER.decode(e));
//        final BigInteger D = new BigInteger(1, DECODER.decode(d));
//        final BigInteger P = new BigInteger(1, DECODER.decode(p));
//        final BigInteger Q = new BigInteger(1, DECODER.decode(q));
//        final BigInteger DP = new BigInteger(1, DECODER.decode(dp));
//        final BigInteger DQ = new BigInteger(1, DECODER.decode(dq));
//        final BigInteger QI = new BigInteger(1, DECODER.decode(qi));
//
//        try {
//            final RSAPrivateCrtKeySpec privateKeySpec = new RSAPrivateCrtKeySpec(N, E, D, P, Q, DP, DQ, QI);
//            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            privateKey = keyFactory.generatePrivate(privateKeySpec);
//        } catch (GeneralSecurityException gse) {
//            throw new IllegalArgumentException("Can't create a key from provided CRT parameters", gse);
//        }
//
//        JwkRsaCrtKey retVal = new JwkRsaCrtKey((RSAPrivateCrtKey) privateKey);
//        if (json.has("kid")) retVal.setKid(json.at("kid").asString());
//        if (json.has("use")) retVal.setUse(PublicKeyUse.valueOf(json.at("use").asString()));
//        return retVal;
//    }
//
//    @Override
//    public PublicKey derivePublicKey() {
//        return new RSAPublicKeyDelegate(rsaPrivateCrtKey);
//    }
//
//    protected Json asJson() {
//        Json json = super.asJson();
//        json.set("n", ENCODER.encodeToString(removeHeadZeros(getModulus().toByteArray())));
//        json.set("e", ENCODER.encodeToString(removeHeadZeros(getPublicExponent().toByteArray())));
//        return json;
//    }
//
//    @Override
//    public String getAlgorithm() {
//        return rsaPrivateCrtKey.getAlgorithm();
//    }
//
//    @Override
//    public BigInteger getModulus() {
//        return rsaPrivateCrtKey.getModulus();
//    }
//
//    @Override
//    public BigInteger getPublicExponent() {
//        return rsaPrivateCrtKey.getPublicExponent();
//    }
//
//    @Override
//    public BigInteger getPrimeP() {
//        return rsaPrivateCrtKey.getPrimeP();
//    }
//
//    @Override
//    public BigInteger getPrimeQ() {
//        return rsaPrivateCrtKey.getPrimeQ();
//    }
//
//    @Override
//    public BigInteger getPrimeExponentP() {
//        return rsaPrivateCrtKey.getPrimeExponentP();
//    }
//
//    @Override
//    public BigInteger getPrimeExponentQ() {
//        return rsaPrivateCrtKey.getPrimeExponentQ();
//    }
//
//    @Override
//    public BigInteger getCrtCoefficient() {
//        return rsaPrivateCrtKey.getCrtCoefficient();
//    }
//
//    @Override
//    public BigInteger getPrivateExponent() {
//        return rsaPrivateCrtKey.getPrivateExponent();
//    }
//
//}
