package dev.prokop.jwt.jwa;

import java.security.GeneralSecurityException;
import java.security.Key;

public interface JwaCryptoHelper {

    boolean isAlgorithmSuitableForContentDecryption();
    boolean isKeySuitableForKekDecryption(Key keyInstance);
    boolean isKeySuitableForVerification(Key keyInstance);

    byte[] decrypt(byte[] key, byte[] aad, byte[] iv, byte[] cipherText, byte[] authTag) throws GeneralSecurityException;
    byte[] decrypt(Key key, byte[] cipherText) throws GeneralSecurityException;
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
                return new AsymmetricCryptoHelper("RS256", "SHA256withRSA");
            case "RS384":
                return new AsymmetricCryptoHelper("RS384", "SHA384withRSA");
            case "RS512":
                return new AsymmetricCryptoHelper("RS512", "SHA512withRSA");
            case "ES256":
                return new AsymmetricCryptoHelper("ES256", "SHA256withECDSA");
            case "ES384":
                return new AsymmetricCryptoHelper("ES384", "SHA384withECDSA");
            case "ES512":
                return new AsymmetricCryptoHelper("ES512", "SHA512withECDSA");
            case "PS256":
                return new AsymmetricCryptoHelper("PS256", "SHA256withRSAandMGF1");
            case "PS384":
                return new AsymmetricCryptoHelper("PS384", "SHA384withRSAandMGF1");
            case "PS512":
                return new AsymmetricCryptoHelper("PS512", "SHA512withRSAandMGF1");
            case "RSA1_5":
                return new AsymmetricCryptoHelper("RSA1_5", "RSA/ECB/PKCS1Padding");
            case "RSA-OAEP":
                return new AsymmetricCryptoHelper("RSA-OAEP", "RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
            case "RSA-OAEP-256":
                return new AsymmetricCryptoHelper("RSA-OAEP-256", "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"); // with MGF1ParameterSpec.SHA256
            case "ECDH-ES":
                return new AsymmetricCryptoHelper("ECDH-ES", "ECDH");
            case "A128KW":
                return new SymmetricCryptoHelper("A128KW", "AESWrap");
            case "A192KW":
                return new SymmetricCryptoHelper("A192KW", "AESWrap");
            case "A256KW":
                return new SymmetricCryptoHelper("A256KW", "AESWrap");
            case "A128CBC-HS256":
                return new SymmetricCryptoHelper("A128CBC-HS256", "AES/CBC/PKCS5Padding");
            case "A192CBC-HS384":
                return new SymmetricCryptoHelper("A192CBC-HS384", "AES/CBC/PKCS5Padding");
            case "A256CBC-HS512":
                return new SymmetricCryptoHelper("A256CBC-HS512", "AES/CBC/PKCS5Padding");
            case "A128GCM":
                return new SymmetricCryptoHelper("A128GCM", "AES/GCM/NoPadding");
            case "A192GCM":
                return new SymmetricCryptoHelper("A192GCM", "AES/GCM/NoPadding");
            case "A256GCM":
                return new SymmetricCryptoHelper("A256GCM", "AES/GCM/NoPadding");
            default:
                throw new IllegalArgumentException("not supported yet: " + jwaAlgorithm);
        }
    }

}
