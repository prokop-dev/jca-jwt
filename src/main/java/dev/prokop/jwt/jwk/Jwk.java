package dev.prokop.jwt.jwk;

import java.security.Key;

/**
 * This class implements java.security.Key - a top-level interface for opaque keys.
 */
public abstract class Jwk implements Key {

    private final String kid;

    public Jwk(String kid) {
        this.kid = kid;
    }

    public static Jwk create(String kid, Key key) {
        throw new IllegalArgumentException();
    }

}
