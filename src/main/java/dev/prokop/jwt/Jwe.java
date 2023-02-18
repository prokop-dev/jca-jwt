package dev.prokop.jwt;

import dev.prokop.jwt.jwe.JweHeader;

/**
 * JSON Web Encryption (JWE)
 * https://www.rfc-editor.org/rfc/rfc7516.txt
 *
 * Java Interface
 */
public interface Jwe {

    JweHeader getHeader();

}
