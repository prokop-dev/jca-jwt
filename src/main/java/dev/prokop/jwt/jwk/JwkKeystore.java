package dev.prokop.jwt.jwk;

import dev.prokop.jwt.Jwk;
import dev.prokop.jwt.KeyProvider;
import dev.prokop.jwt.tools.Json;

import java.security.Key;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwkKeystore implements KeyProvider {

    private final Map<String, Jwk> keys;

    public JwkKeystore(String keystore) {
        keys = Collections.unmodifiableMap(parse(Json.read(keystore)));
    }

    @Override
    public Key retrieveKey(String kid) {
        return keys.get(kid);
    }

    private static Map<String, Jwk> parse(Json keystore) {
        final List<Json> keys = keystore.at("keys").asJsonList();
        return keys.stream()
                .map(json->Jwk.fromJson(json.toString()))
                .collect(Collectors.toMap(k->k.getKid(), k->k));
    }

}
