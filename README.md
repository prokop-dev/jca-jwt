# Java Compact JWT (JSON Web Token) library

Clean room implementation of JWT ecosystem for Java and Android:

- [RFC 7515](http://www.rfc-editor.org/info/rfc7515) - JSON Web Signature (JWS)
- [RFC 7516](http://www.rfc-editor.org/info/rfc7516) - JSON Web Encryption (JWE)
- [RFC 7517](http://www.rfc-editor.org/info/rfc7517) - JSON Web Key (JWK)
- [RFC 7518](http://www.rfc-editor.org/info/rfc7518) - JSON Web Algorithms (JWA)
- [RFC 7519](http://www.rfc-editor.org/info/rfc7519) - JSON Web Token (JWT)
- [RFC 7520](http://www.rfc-editor.org/info/rfc7520) - Examples of Protecting Content Using JSON Object Signing and Encryption (JOSE)

I wrote that library to faciliatte work with JWT (JTS and JWE) structures.
Primary reason was that I was not fully none of the libraries I was able to find and wanted the following features:

- **Maximum compatibility with Java JCA** and standard Java classpath API.
For example JWK constructed with this library must be JCA class and must be usable with Java crypto.
- **Minimalism**, ...

## Usage

Use the following Maven Central coordinates:

```xml
<dependency>
    <groupId>dev.prokop</groupId>
    <artifactId>jca-jwt</artifactId>
    <version>0.0.1</version>
</dependency>
```

This library is written with "zero transitive dependencies" in mind.
It will not create classpath problems.

## Sample use

### JWK

To use JWT (signed JWS or encrypted JWE) one must have cryptographic keys.
Here is where `dev.prokop.jwt.Jwk` interface comes handy.

## Technical notes for myself

Release:

```bash
mvn clean deploy
git tag -s v0.0.1 -m 'Maven Central 0.0.1'
git push --tags
```
