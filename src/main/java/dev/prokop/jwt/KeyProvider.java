package dev.prokop.jwt;

import java.security.Key;

@FunctionalInterface
public interface KeyProvider {
    Key retrieveKey(String kid);
}
