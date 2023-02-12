package dev.prokop.jwt;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.HashMap;
import java.util.Map;

/**
 * Java Enum type to represent JSON Web Algorithm (JWA)
 * https://www.rfc-editor.org/rfc/rfc7518.txt
 */
public enum Jwa {

    // Digital Signature/MAC Algorithm Identifiers
    HS256(Type.mac, null, "HmacSHA256"),
    HS384(Type.mac, null, "HmacSHA384"),
    HS512(Type.mac, null, "HmacSHA512"),
    RS256(Type.sig, null, "SHA256withRSA"),
    RS384(Type.sig, null, "SHA384withRSA"),
    RS512(Type.sig, null, "SHA512withRSA"),
    ES256(Type.sig, null, "SHA256withECDSA"),
    ES384(Type.sig, null, "SHA384withECDSA"),
    ES512(Type.sig, null, "SHA512withECDSA"),
    PS256(Type.sig, null, "SHA256withRSAandMGF1"),
    PS384(Type.sig, null, "SHA384withRSAandMGF1"),
    PS512(Type.sig, null, "SHA512withRSAandMGF1"),

    // Key Management Algorithm Identifiers
    RSA15(Type.km, "RSA1_5", "RSA/ECB/PKCS1Padding"),
    RSA_OAEP(Type.km, "RSA-OAEP", "RSA/ECB/OAEPWithSHA-1AndMGF1Padding"),
    RSA_OAEP_256(Type.km, "RSA-OAEP-256", "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"), // & MGF1ParameterSpec.SHA256
    ECDH_ES(Type.km, "ECDH-ES", "ECDH"),
    A128KW(Type.km, null, "AESWrap"),
    A192KW(Type.km, null, "AESWrap"),
    A256KW(Type.km, null, "AESWrap"),

    // Content Encryption Algorithm Identifiers
    A128CBC_HS256(Type.ce, "A128CBC-HS256", "AES/CBC/PKCS5Padding"),
    A192CBC_HS384(Type.ce, "A192CBC-HS384", "AES/CBC/PKCS5Padding"),
    A256CBC_HS512(Type.ce, "A256CBC-HS512", "AES/CBC/PKCS5Padding"),
    A128GCM(Type.ce, null, "AES/GCM/NoPadding"),
    A192GCM(Type.ce, null, "AES/GCM/NoPadding"),
    A256GCM(Type.ce, null, "AES/GCM/NoPadding");

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

    private enum Type {mac, sig, km, ce};

    private final Type type;
    private final String jwaName;
    private final String jcaName;

    Jwa(Type type, String jwaName, String jcaName) {
        this.type = type;
        this.jwaName = jwaName == null ? name() : jwaName;
        this.jcaName = jcaName;
    }

    @Override
    public String toString() {
        return jwaName;
    }

    public <T> T cryptoPrimitive() throws NoSuchAlgorithmException, NoSuchPaddingException {
        switch (type) {
            case mac:
                return (T) Mac.getInstance(jcaName);
            case sig:
                return (T) Signature.getInstance(jcaName);
            case km:
            case ce:
                return (T) Cipher.getInstance(jcaName);
            default:
                throw new AssertionError("unknown type");
        }
    }

}
