package com.example.demo.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SHA256 {

    public static String hash(String text) throws UtilException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(text.getBytes(StandardCharsets.UTF_8));
            return Util.convertToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new UtilException(e);
        }
    }

    public static String hash(String[] text) throws UtilException {
        try {
            Arrays.sort(text, Comparator.naturalOrder());
            StringBuilder sb = new StringBuilder();
            for (String s : text) {
                sb.append(s);
            }
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(sb.toString().getBytes(StandardCharsets.UTF_8));
            return Util.convertToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new UtilException(e);
        }
    }

    public static String hash(List<String> text) throws UtilException {
        return hash(text.toArray(new String[0]));
    }
}