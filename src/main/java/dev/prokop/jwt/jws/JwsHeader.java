package dev.prokop.jwt.jws;

import dev.prokop.jwt.Jwa;

/**
 * For a JWS, the members of the JSON object(s) representing the JOSE
 * Header describe the digital signature or MAC applied to the JWS
 * Protected Header and the JWS Payload and optionally additional
 * properties of the JWS.
 */
public class JwsHeader {

    /**
     * The "alg" (algorithm) Header Parameter identifies the cryptographic
     * algorithm used to secure the JWS. The JWS Signature value is not
     * valid if the "alg" value does not represent a supported algorithm or
     * if there is not a key for use with that algorithm associated with the
     * party that digitally signed or MACed the content.
     * The "alg" value is a case-sensitive ASCII string containing a StringOrURI value.
     * This Header Parameter MUST be present and MUST be understood and processed by implementations.
     */
    private Jwa alg;

    // TODO: 4.1.2.  "jku" (JWK Set URL) Header Parameter
    // TODO: 4.1.3.  "jwk" (JSON Web Key) Header Parameter

    /**
     * The "kid" (key ID) Header Parameter is a hint indicating which key
     * was used to secure the JWS.  This parameter allows originators to
     * explicitly signal a change of key to recipients. The structure of
     * the "kid" value is unspecified.  Its value MUST be a case-sensitive
     * string. Use of this Header Parameter is OPTIONAL.
     */
    private String kid;

    // TODO: 4.1.5.  "x5u" (X.509 URL) Header Parameter
    // TODO: 4.1.6.  "x5c" (X.509 Certificate Chain) Header Parameter
    // TODO: 4.1.7.  "x5t" (X.509 Certificate SHA-1 Thumbprint) Header Parameter
    // TODO: 4.1.8.  "x5t#S256" (X.509 Certificate SHA-256 Thumbprint) Header Parameter
    // TODO: 4.1.9.  "typ" (Type) Header Parameter
    // TODO: 4.1.10.  "cty" (Content Type) Header Parameter
    // TODO: 4.1.11.  "crit" (Critical) Header Parameter

    public Jwa getAlg() {
        return alg;
    }

    public JwsHeader setAlg(Jwa alg) {
        this.alg = alg;
        return this;
    }

    public String getKid() {
        return kid;
    }

    public JwsHeader setKid(String kid) {
        this.kid = kid;
        return this;
    }
}
