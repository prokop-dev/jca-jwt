package dev.prokop.jwt.jwk;

import dev.prokop.jwt.tools.Json;

import java.math.BigInteger;
import java.util.Base64;

public final class JsonJwkParser {

    final static Base64.Decoder B64_URL_DEC = Base64.getUrlDecoder();

    static Jwk fromJson(String jsonAsString) {
        final Json json = Json.read(jsonAsString);
        if (!json.has("kty")) throw new IllegalArgumentException("Provided JSON does not contain kty field.");

        switch (Jwk.KeyType.valueOf(json.at("kty").asString())) {
            case EC:
//                if (json.has("x") && json.has("y")) return JwkEcPublicKey.fromJson(json);
                break;
            case RSA:
//                if (json.has("n") && json.has("e")
//                        && json.has("d") && json.has("p") && json.has("q")
//                        && json.has("dp") && json.has("dq") && json.has("qi"))
//                    return JwkRsaCrtKey.fromJson(json);
                if (json.has("n") && json.has("e"))
                    return parseRsaPublicKey(json);
            case oct:
//                return JwkOctSecretKey.fromJson(json);
            default:
                throw new IllegalArgumentException("Unknown kty value - cannot determine key type.");
        }

        throw new IllegalArgumentException("Cannot determine key type.");
    }

    static Jwk parseRsa(Json json) {
        if (json.has("n") && json.has("e")) {
            return parseRsaPublicKey(json);
        }
        throw new IllegalArgumentException("Cannot determine RSA key details.");
    }

    static JwkRsaPublicKey parseRsaPublicKey(Json json) {
        final String kid = json.at("kid").asString();
        final String n = json.at("n").asString();
        final String e = json.at("e").asString();

        final BigInteger N = new BigInteger(1, B64_URL_DEC.decode(n));
        final BigInteger E = new BigInteger(1, B64_URL_DEC.decode(e));

//        if (json.has("use")) retVal.setUse(PublicKeyUse.valueOf(json.at("use").asString()));
        return new JwkRsaPublicKey(kid, N, E);
    }

}
