package dev.prokop.jwt;

import dev.prokop.jwt.jwk.JwkRSAPublicKey;
import dev.prokop.jwt.tools.Json;

import java.security.Key;

/**
 * JSON Web Key (JWK)
 * https://www.rfc-editor.org/rfc/rfc7517.txt
 */
public interface Jwk extends Key {

    /**
     * "kty" (Key Type) Parameter
     *
     * The "kty" (key type) parameter identifies the cryptographic algorithm
     * family used with the key, such as "RSA" or "EC".
     * The "kty" value is a case-sensitive string.
     * This member MUST be present in a JWK.
     */
    enum KeyType {RSA, EC}

    /**
     * "use" (Public Key Use) Parameter
     *
     * The "use" (public key use) parameter identifies the intended use of
     * the public key.  The "use" parameter is employed to indicate whether
     * a public key is used for encrypting data or verifying the signature
     * on data.
     *
     * Values defined by this specification are:
     *    o  "sig" (signature)
     *    o  "enc" (encryption)
     *
     * When a key is used to wrap another key and a public key use
     * designation for the first key is desired, the "enc" (encryption) key
     * use value is used, since key wrapping is a kind of encryption.
     * The "enc" value is also to be used for public keys used for key
     * agreement operations.
     */
    enum PublicKeyUse {sig, enc}

    KeyType getKty();

    PublicKeyUse getUse();
    Jwk setUse(PublicKeyUse use);

    String getKid();
    Jwk setKid(String kid);

    static Jwk fromJson(String jsonAsString) {
        final Json json = Json.read(jsonAsString);

        if (json.has("n") && json.has("e")) {
            return JwkRSAPublicKey.fromJson(json);
        }

        if (json.has("x") && json.has("y")) {
//            return new JwkEcImpl(json);
        }

        throw new IllegalArgumentException("Cannot determine key type");
    }

}
