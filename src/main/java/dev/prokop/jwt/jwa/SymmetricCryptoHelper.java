package dev.prokop.jwt.jwa;

import dev.prokop.jwt.Jwk;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class SymmetricCryptoHelper implements JwaCryptoHelper {
    private final String jcaName;
    public SymmetricCryptoHelper(final String jwa, final String jcaName) {
        this.jcaName = jcaName;
    }

    @Override
    public boolean isAlgorithmSuitableForContentDecryption() {
        return true;
    }

    @Override
    public boolean isKeySuitableForKekDecryption(Key keyInstance) {
        return false;
    }

    @Override
    public boolean isKeySuitableForVerification(Key keyInstance) {
        return false;
    }

    @Override
    public byte[] decrypt(byte[] key, byte[] aad, byte[] iv, byte[] cipherText, byte[] authTag) throws GeneralSecurityException {
        final SecretKey secretKey = new SecretKeySpec(key, "AES");
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        final GCMParameterSpec parameterSpec = new GCMParameterSpec(authTag.length*8, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);
        cipher.updateAAD(Base64.getUrlEncoder().withoutPadding().encode(aad));
        cipher.update(cipherText); // returned empty array
        return cipher.doFinal(authTag); // returns ciphertext
    }

    @Override
    public byte[] decrypt(Key key, byte[] cipherText) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(jcaName);
        //OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), PSource.PSpecified.DEFAULT);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    @Override
    public boolean verify(Key key, final byte[] verifiablePayload, final byte[] signature) throws GeneralSecurityException {
        final Signature signInstance = Signature.getInstance(jcaName);
        if (key instanceof Jwk) key = ((Jwk) key).derivePublicKey();
        signInstance.initVerify((PublicKey) key);
        signInstance.update(verifiablePayload);
        return signInstance.verify(signature);
    }

}
