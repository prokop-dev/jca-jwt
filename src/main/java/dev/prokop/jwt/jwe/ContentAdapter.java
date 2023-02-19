package dev.prokop.jwt.jwe;

import dev.prokop.jwt.Jwe;

import java.nio.charset.StandardCharsets;

public class ContentAdapter {
    private final Jwe source;

    public ContentAdapter(Jwe source) {
        this.source = source;
    }

    public String asString() {
        return new String(source.getContent(), StandardCharsets.UTF_8);
    }
}
