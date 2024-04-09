//package dev.prokop.jwt.jwk;
//
//import dev.prokop.jwt.tools.Json;
//
//import java.math.BigInteger;
//import java.security.AlgorithmParameters;
//import java.security.GeneralSecurityException;
//import java.security.KeyFactory;
//import java.security.PublicKey;
//import java.security.interfaces.ECPublicKey;
//import java.security.spec.ECGenParameterSpec;
//import java.security.spec.ECParameterSpec;
//import java.security.spec.ECPoint;
//import java.security.spec.ECPublicKeySpec;
//
//public class JwkEcPublicKey extends JwkExBase implements ECPublicKey {
//
//    private final ECPublicKey ecPublicKey;
//
//    public JwkEcPublicKey(ECPublicKey ecPublicKey) {
//        this.ecPublicKey = ecPublicKey;
//    }
//
//    public static JwkEcPublicKey fromJson(Json json) throws IllegalArgumentException {
//        PublicKey publicKey;
//
//        final String x = json.at("x").asString();
//        final String y = json.at("y").asString();
//
//        final BigInteger X = new BigInteger(DECODER.decode(x));
//        final BigInteger Y = new BigInteger(DECODER.decode(y));
//
//        try {
//            final AlgorithmParameters parameters = AlgorithmParameters.getInstance("EC");
//            parameters.init(new ECGenParameterSpec("secp256r1"));
//            final ECParameterSpec spec = parameters.getParameterSpec(ECParameterSpec.class);
//
//            final ECPoint ecPoint = new ECPoint(X, Y);
//            final ECPublicKeySpec keySpec = new ECPublicKeySpec(ecPoint, spec);
//
//            KeyFactory keyFactory = KeyFactory.getInstance("EC");
//            publicKey = keyFactory.generatePublic(keySpec);
//        } catch (GeneralSecurityException ges) {
//            throw new IllegalArgumentException(ges);
//        }
//        JwkEcPublicKey retVal = new JwkEcPublicKey((ECPublicKey) publicKey);
//        if (json.has("kid")) retVal.setKid(json.at("kid").asString());
//        if (json.has("use")) retVal.setUse(PublicKeyUse.valueOf(json.at("use").asString()));
//        return retVal;
//    }
//
//    @Override
//    public PublicKey derivePublicKey() {
//        return this;
//    }
//
//    @Override
//    public KeyType getKty() {
//        return KeyType.EC;
//    }
//
//    @Override
//    public ECPoint getW() {
//        return ecPublicKey.getW();
//    }
//
//    @Override
//    public String getAlgorithm() {
//        return ecPublicKey.getAlgorithm();
//    }
//
//    @Override
//    public ECParameterSpec getParams() {
//        return ecPublicKey.getParams();
//    }
//
//}
