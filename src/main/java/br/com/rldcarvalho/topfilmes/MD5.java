package br.com.rldcarvalho.topfilmes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String encryptToMD5(String ts, String privateKey, String publicKey) {
        String text = ts + privateKey + publicKey;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes(),0,text.length());
            return new BigInteger(1,md.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
