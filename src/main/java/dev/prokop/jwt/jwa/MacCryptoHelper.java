package dev.prokop.jwt.jwa;

import dev.prokop.jwt.jwk.JwkOctSecretKey;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Arrays;

public class MacCryptoHelper implements JwaCryptoHelper {

    private final String jcaName;
    public MacCryptoHelper(final String jwa, final String jcaName) {
        this.jcaName = jcaName;
    }

    @Override
    public boolean isAlgorithmSuitableForContentDecryption() {
        return false;
    }

    @Override
    public boolean isKeySuitableForKekDecryption(Key keyInstance) {
        return false;
    }

    @Override
    public boolean isKeySuitableForVerification(Key keyInstance) {
        if (keyInstance instanceof JwkOctSecretKey) return true;
        return false;
    }

    @Override
    public byte[] decrypt(byte[] key, byte[] aad, byte[] iv, byte[] cipherText, byte[] authTag) throws GeneralSecurityException {
        throw new AssertionError();
    }

    @Override
    public byte[] decrypt(Key key, byte[] cipherText) throws GeneralSecurityException {
        throw new AssertionError();
    }

    @Override
    public boolean verify(Key key, final byte[] verifiablePayload, final byte[] signature) throws GeneralSecurityException {
        if (key instanceof JwkOctSecretKey) {
            key = new SecretKeySpec(key.getEncoded(), jcaName);
        }
        Mac mac = Mac.getInstance(jcaName);
        mac.init(key);
        return Arrays.equals(signature, mac.doFinal(verifiablePayload));
    }

}
