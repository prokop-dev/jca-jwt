package dev.prokop.jwt.jwa;

import dev.prokop.jwt.Jwk;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class AsymmetricCryptoHelper implements JwaCryptoHelper {
    private final String jcaName;
    public AsymmetricCryptoHelper(final String jwa, final String jcaName) {
        this.jcaName = jcaName;
    }

    @Override
    public boolean isAlgorithmSuitableForContentDecryption() {
        return false;
    }

    @Override
    public boolean isKeySuitableForKekDecryption(Key keyInstance) {
        if (keyInstance instanceof RSAPrivateKey) return true;
        return false;
    }

    @Override
    public boolean isKeySuitableForVerification(Key keyInstance) {
        if (keyInstance instanceof RSAPrivateKey) return true; // TODO: consider unwrapped key as CRT is needed
        if (keyInstance instanceof RSAPublicKey) return true;
        return false;
    }

    @Override
    public byte[] decrypt(byte[] key, byte[] aad, byte[] iv, byte[] cipherText, byte[] authTag) throws GeneralSecurityException {
        throw new AssertionError();
    }

    @Override
    public byte[] decrypt(Key key, byte[] cipherText) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(jcaName);
//        OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), PSource.PSpecified.DEFAULT);
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
