# Java Compact JWT library

I wrote that library to work with JWT, JTS and JWE structures.
I wanted to achieve the following:

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
