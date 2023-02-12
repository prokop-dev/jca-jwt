package dev.prokop.jwt.jwe;

import dev.prokop.jwt.Jwe;

public class JweImpl implements Jwe {

    private final JweHeader header;

    public JweImpl(JweHeader header) {
        this.header = header;
    }

    @Override
    public JweHeader getHeader() {
        return header;
    }

}
