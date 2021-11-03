package com.onix;

import java.security.SecureRandom;

public class generate_key {
    public generate_key(){
        SecureRandom secureRandom = new SecureRandom();
        byte []bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        System.out.println(secureRandom);
        System.out.println(bytesToNext(bytes));
    }
    public static String bytesToNext(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder(bytes.length*2);
        for(byte b: bytes){
            stringBuilder.append(String.format("%02x",b));
        }
        return stringBuilder.toString();
    }

}
