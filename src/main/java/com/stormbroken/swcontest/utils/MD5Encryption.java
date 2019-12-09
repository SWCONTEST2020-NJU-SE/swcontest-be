package com.stormbroken.swcontest.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryption {
    //修改密码需要加密！
    public static String encrypt(String word) throws NoSuchAlgorithmException {
        //用于加密密码
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] input = word.getBytes();
        byte[] output = md.digest(input);
        return Base64.encodeBase64String(output);
    }
}
