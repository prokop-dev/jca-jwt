package dev.prokop.jwt.jwa;

import java.security.*;
import java.security.interfaces.RSAPublicKey;

public class SignatureCryptoHelper implements JwaCryptoHelper {
    private final String jcaName;
    public SignatureCryptoHelper(final String jwa, final String jcaName) {
        this.jcaName = jcaName;
    }

    @Override
    public boolean isKeySuitableForVerification(Key keyInstance) {
        if (keyInstance instanceof RSAPublicKey) return true;
        return false;
    }

    @Override
    public boolean verify(final Key key, final byte[] verifiablePayload, final byte[] signature) throws GeneralSecurityException {
        final Signature signInstance = Signature.getInstance(jcaName);
        signInstance.initVerify((PublicKey) key);
        signInstance.update(verifiablePayload);
        return signInstance.verify(signature);

    }

}
