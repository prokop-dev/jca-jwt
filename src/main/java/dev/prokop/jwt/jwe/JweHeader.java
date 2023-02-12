package dev.prokop.jwt.jwe;

import dev.prokop.jwt.Jwa;

public class JweHeader {

    /**
     * This "alg" Header Parameter identifies the cryptographic algorithm used
     * to encrypt or determine the value of the CEK. The encrypted content
     * is not usable if the "alg" value does not represent a supported
     * algorithm, or if the recipient does not have a key that can be used
     * with that algorithm.
     */
    private Jwa alg;

    /**
     * The "enc" (encryption algorithm) Header Parameter identifies the
     * content encryption algorithm used to perform authenticated encryption
     * on the plaintext to produce the ciphertext and the Authentication Tag.
     * This algorithm MUST be an AEAD algorithm with a specified key length.
     * The encrypted content is not usable if the "enc" value does
     * not represent a supported algorithm.  "enc" values should either be
     * registered in the IANA "JSON Web Signature and Encryption Algorithms"
     * registry established by [JWA] or be a value that contains a
     * Collision-Resistant Name.  The "enc" value is a case-sensitive ASCII
     * string containing a StringOrURI value.  This Header Parameter MUST be
     * present and MUST be understood and processed by implementations.
     */
    private Jwa enc;

    /**
     * The "zip" (compression algorithm) applied to the plaintext before
     * encryption, if any.  The "zip" value defined by this specification is:
     *
     *    o  "DEF" - Compression with the DEFLATE [RFC1951] algorithm
     *
     * Other values MAY be used. The "zip" value is a case-sensitive
     * string.  If no "zip" parameter is present, no compression is applied
     * to the plaintext before encryption.
     */
    public enum CompressionAlgorithm {DEF}
    private CompressionAlgorithm zip = null;

    // TODO: 4.1.4.  "jku" (JWK Set URL) Header Parameter
    // TODO: 4.1.5.  "jwk" (JSON Web Key) Header Parameter

    /**
     * "kid" (Key ID) Header Parameter
     *
     * The "kid" Header Parameter references the public key to which the JWE was
     * encrypted; this can be used to determine the private key needed to
     * decrypt the JWE.
     */
    private String kid;

    // TODO: 4.1.7.  "x5u" (X.509 URL) Header Parameter
    // TODO: 4.1.8.  "x5c" (X.509 Certificate Chain) Header Parameter
    // TODO: 4.1.9.  "x5t" (X.509 Certificate SHA-1 Thumbprint) Header Parameter
    // TODO: 4.1.10.  "x5t#S256" (X.509 Certificate SHA-256 Thumbprint) Header Parameter

    // 4.1.11.  "typ" (Type) Header Parameter
    // 4.1.12.  "cty" (Content Type) Header Parameter
    // 4.1.13.  "crit" (Critical) Header Parameter

    public Jwa getAlg() {
        return alg;
    }

    public JweHeader setAlg(Jwa alg) {
        this.alg = alg;
        return this;
    }

    public Jwa getEnc() {
        return enc;
    }

    public JweHeader setEnc(Jwa enc) {
        this.enc = enc;
        return this;
    }

    public CompressionAlgorithm getZip() {
        return zip;
    }

    public JweHeader setZip(CompressionAlgorithm zip) {
        this.zip = zip;
        return this;
    }

    public String getKid() {
        return kid;
    }

    public JweHeader setKid(String kid) {
        this.kid = kid;
        return this;
    }
}
