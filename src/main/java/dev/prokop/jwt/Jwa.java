package dev.prokop.jwt;

import dev.prokop.jwt.jwa.JwaCryptoHelper;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON Web Algorithms (JWA)
 * https://www.rfc-editor.org/rfc/rfc7518.txt
 *
 * Java Enum type to represent JSON Web Algorithm (JWA)
 */
public enum Jwa {

    // Digital Signature/MAC Algorithm Identifiers
    HS256(),
    HS384(),
    HS512(),
    RS256(),
    RS384(),
    RS512(),
    ES256(),
    ES384(),
    ES512(),
    PS256(),
    PS384(),
    PS512(),

    // Key Management Algorithm Identifiers
    RSA15("RSA1_5"),
    RSA_OAEP("RSA-OAEP"),
    RSA_OAEP_256("RSA-OAEP-256"),
    ECDH_ES("ECDH-ES"),
    A128KW(),
    A192KW(),
    A256KW(),

    // Content Encryption Algorithm Identifiers
    A128CBC_HS256("A128CBC-HS256"),
    A192CBC_HS384("A192CBC-HS384"),
    A256CBC_HS512("A256CBC-HS512"),
    A128GCM(),
    A192GCM(),
    A256GCM();

    private final static Map<String, Jwa> jwaNameToEnum = new HashMap<>();
    static {
        for (Jwa jwa : values())
            jwaNameToEnum.put(jwa.toString(), jwa);
    }

    public static Jwa parse(String jwaAlgo) throws NoSuchAlgorithmException {
        if (jwaNameToEnum.containsKey(jwaAlgo)) {
            return jwaNameToEnum.get(jwaAlgo);
        } else {
            throw new NoSuchAlgorithmException("Unknown algo name: " + jwaAlgo + ". Should be one from RFC 7518.");
        }
    }

    private final String jwaName;
    private JwaCryptoHelper cryptoHelper;

    Jwa() {
        this(null);
    }

    Jwa(String jwaName) {
        this.jwaName = jwaName == null ? name() : jwaName;
        this.cryptoHelper = JwaCryptoHelper.resolve(this.jwaName);
    }

    public JwaCryptoHelper getCryptoHelper() {
        return cryptoHelper;
    }

    @Override
    public String toString() {
        return jwaName;
    }

}
