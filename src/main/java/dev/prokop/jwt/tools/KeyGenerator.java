package dev.prokop.jwt.tools;

import dev.prokop.jwt.Jwk;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class KeyGenerator {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair kp = keyGen.generateKeyPair();
        System.out.println(kp);
        System.out.println();

//        System.out.println("-- public key --");
//        PublicKey publicKey = kp.getPublic();
//        Jwk pubJwk = Jwk.fromKey(publicKey);
//        System.out.println(pubJwk.pem());
//        System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
//        System.out.println();

        System.out.println("-- private key --");
        PrivateKey privateKey = kp.getPrivate();
        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        System.out.println();
    }

}
