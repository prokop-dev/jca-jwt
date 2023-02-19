package dev.prokop.jwt.jwe;

import dev.prokop.jwt.Jwe;

public class JweImpl implements Jwe {

    private final JweHeader header;
    private final byte[] content;
    private final ContentAdapter contentAdapter;

    public JweImpl(JweHeader header, byte[] content) {
        this.header = header;
        this.content = content;
        this.contentAdapter = new ContentAdapter(this);
    }

    @Override
    public JweHeader getHeader() {
        return header;
    }

    @Override
    public byte[] getContent() {
        return content;
    }

    @Override
    public ContentAdapter content() {
        return contentAdapter;
    }
}
