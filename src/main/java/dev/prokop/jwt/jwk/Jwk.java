package dev.prokop.jwt.jwk;

import java.security.Key;

/**
 * JSON Web Key (JWK) Java Interface
 * https://www.rfc-editor.org/rfc/rfc7517.txt
 *
 * This class extends java.security.Key - a top-level interface for opaque keys.
 */
public interface Jwk extends Key {

    static Jwk fromJson(String jsonAsString) {
        return JsonJwkParser.fromJson(jsonAsString);
    }

    /**
     * The "kty" (key type) parameter identifies the cryptographic algorithm
     * family used with the key, such as "RSA" or "EC".
     * The "kty" value is a case-sensitive string.
     * This member MUST be present in a JWK.
     *
     * @return Key Type
     */
    KeyType getKty();

    String getKid();
    PublicKeyUse getUse();

    /**
     * "kty" (Key Type) Parameter
     * A list of defined "kty" values can be found in the IANA "JSON Web Key
     * Types" registry established by [JWA]; the initial contents of this
     * registry are the values defined in Section 6.1 of [JWA].
     */
    enum KeyType {RSA, EC, oct}

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

}