package dev.prokop.jwt.jwa;

import java.security.GeneralSecurityException;
import java.security.Key;

public interface JwaCryptoHelper {

    boolean isKeySuitableForVerification(Key keyInstance);
    boolean verify(Key key, byte[] verifiablePayload, byte[] signature) throws GeneralSecurityException; //                return (T) Mac.getInstance(jcaName);


    static JwaCryptoHelper resolve(String jwaAlgorithm) {
        switch (jwaAlgorithm) {
            case "HS256":
                return new MacCryptoHelper("HS256", "HmacSHA256");
            case "HS384":
                return new MacCryptoHelper("HS384", "HmacSHA384");
            case "HS512":
                return new MacCryptoHelper("HS512", "HmacSHA512");
            case "RS256":
                return new SignatureCryptoHelper("RS256", "SHA256withRSA");
            case "RS384":
                return new SignatureCryptoHelper("RS384", "SHA384withRSA");
            case "RS512":
                return new SignatureCryptoHelper("RS512", "SHA512withRSA");
            case "ES256":
                return new SignatureCryptoHelper("ES256", "SHA256withECDSA");
            case "ES384":
                return new SignatureCryptoHelper("ES384", "SHA384withECDSA");
            case "ES512":
                return new SignatureCryptoHelper("ES512", "SHA512withECDSA");
            case "PS256":
                return new SignatureCryptoHelper("PS256", "SHA256withRSAandMGF1");
            case "PS384":
                return new SignatureCryptoHelper("PS384", "SHA384withRSAandMGF1");
            case "PS512":
                return new SignatureCryptoHelper("PS512", "SHA512withRSAandMGF1");
            case "RSA1_5":
                return new SignatureCryptoHelper("RSA1_5", "RSA/ECB/PKCS1Padding");
            case "RSA-OAEP":
                return new SignatureCryptoHelper("RSA-OAEP", "RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
            case "RSA-OAEP-256":
                return new SignatureCryptoHelper("RSA-OAEP-256", "RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            case "ECDH-ES":
                return new SignatureCryptoHelper("ECDH-ES", "ECDH");
            case "A128KW":
                return new SignatureCryptoHelper("A128KW", "AESWrap");
            case "A192KW":
                return new SignatureCryptoHelper("A192KW", "AESWrap");
            case "A256KW":
                return new SignatureCryptoHelper("A256KW", "AESWrap");
            case "A128CBC-HS256":
                return new SignatureCryptoHelper("A128CBC-HS256", "AES/CBC/PKCS5Padding");
            case "A192CBC-HS384":
                return new SignatureCryptoHelper("A192CBC-HS384", "AES/CBC/PKCS5Padding");
            case "A256CBC-HS512":
                return new SignatureCryptoHelper("A256CBC-HS512", "AES/CBC/PKCS5Padding");
            case "A128GCM":
                return new SignatureCryptoHelper("A128GCM", "AES/GCM/NoPadding");
            case "A192GCM":
                return new SignatureCryptoHelper("A192GCM", "AES/GCM/NoPadding");
            case "A256GCM":
                return new SignatureCryptoHelper("A256GCM", "AES/GCM/NoPadding");
            default:
                throw new IllegalArgumentException("not supported yet: " + jwaAlgorithm);
        }
    }

}
