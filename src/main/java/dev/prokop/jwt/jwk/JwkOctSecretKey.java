//package dev.prokop.jwt.jwk;
//
//import dev.prokop.jwt.Jwa;
//import dev.prokop.jwt.tools.Json;
//
//import java.security.PublicKey;
//
//public final class JwkOctSecretKey extends JwkExBase {
//
//    @Override
//    public KeyType getKty() {
//        return KeyType.oct;
//    }
//
//    private final byte[] key;
//
//    public JwkOctSecretKey(byte[] key) {
//        this.key = key;
//    }
//
//    public static JwkOctSecretKey fromJson(Json json) {
//        final String k = json.at("k").asString();
//        final byte[] K = DECODER.decode(k);
//
//        JwkOctSecretKey retVal = new JwkOctSecretKey(K);
//        if (json.has("kid")) retVal.setKid(json.at("kid").asString());
//        if (json.has("use")) retVal.setUse(PublicKeyUse.valueOf(json.at("use").asString()));
//        if (json.has("alg")) retVal.setAlg(Jwa.parse(json.at("alg").asString()));
//        return retVal;
//    }
//
//    @Override
//    public String getAlgorithm() {
//        return "HMAC"; //TODO: unhappy with that choice
//    }
//
//    protected Json asJson() {
//        Json json = super.asJson();
//        json.set("k", ENCODER.encodeToString(key));
//        return json;
//    }
//
//    @Override
//    public PublicKey derivePublicKey() {
//        throw new AssertionError();
//    }
//}
