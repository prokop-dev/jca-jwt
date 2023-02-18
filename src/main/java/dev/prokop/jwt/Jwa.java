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
    HS256(null, "HmacSHA256"),
    HS384(null, "HmacSHA384"),
    HS512(null, "HmacSHA512"),
    RS256(null, "SHA256withRSA"),
    RS384(null, "SHA384withRSA"),
    RS512(null, "SHA512withRSA"),
    ES256(null, "SHA256withECDSA"),
    ES384(null, "SHA384withECDSA"),
    ES512(null, "SHA512withECDSA"),
    PS256(null, "SHA256withRSAandMGF1"),
    PS384(null, "SHA384withRSAandMGF1"),
    PS512(null, "SHA512withRSAandMGF1"),

    // Key Management Algorithm Identifiers
    RSA15("RSA1_5", "RSA/ECB/PKCS1Padding"),
    RSA_OAEP("RSA-OAEP", "RSA/ECB/OAEPWithSHA-1AndMGF1Padding"),
    RSA_OAEP_256("RSA-OAEP-256", "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"), // & MGF1ParameterSpec.SHA256
    ECDH_ES("ECDH-ES", "ECDH"),
    A128KW(null, "AESWrap"),
    A192KW(null, "AESWrap"),
    A256KW(null, "AESWrap"),

    // Content Encryption Algorithm Identifiers
    A128CBC_HS256("A128CBC-HS256", "AES/CBC/PKCS5Padding"),
    A192CBC_HS384("A192CBC-HS384", "AES/CBC/PKCS5Padding"),
    A256CBC_HS512("A256CBC-HS512", "AES/CBC/PKCS5Padding"),
    A128GCM(null, "AES/GCM/NoPadding"),
    A192GCM(null, "AES/GCM/NoPadding"),
    A256GCM(null, "AES/GCM/NoPadding");

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
    private final String jcaName;
    private JwaCryptoHelper cryptoHelper;

    Jwa(String jwaName, final String jcaName) {
        this.jwaName = jwaName == null ? name() : jwaName;
        this.jcaName = jcaName;
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
