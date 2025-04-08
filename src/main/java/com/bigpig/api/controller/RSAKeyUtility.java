package com.bigpig.api.controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class RSAKeyUtility {

    // Funzione per generare la coppia di chiavi
    public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize); // Imposta la dimensione della chiave
        return keyPairGenerator.generateKeyPair();
    }

    public static String toBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
