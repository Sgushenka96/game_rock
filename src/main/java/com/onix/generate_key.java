package com.onix;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class generate_key {
    /**Генерация ключа и его использование
     * 1. Генерация ключа
     * 2. Ход Компьютера
     * 3. Вычисление HMAC от хода Компьютера и ключа
     * 4. Отображение HMAC
     * 5. Ход Игрока
     * 6. Отображение ключа после завершения игры
     */
    private static final String HMAC_ALGO = "HmacSHA256";
    private static String step_PC_mes;

    public generate_key() {
    }
    public byte[] key_gen(){
        // 1. Генерация ключа
        SecureRandom secureRandom = new SecureRandom();
        byte []bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        return bytes;
    }
    public String bytesToNext(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder(bytes.length*2);
        for(byte b: bytes){
            stringBuilder.append(String.format("%02x",b));
        }
        return stringBuilder.toString();
    }
    public Integer digestToHMAC(String step_PC_mes,byte[] bytes) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac signer = Mac.getInstance(HMAC_ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(bytes,HMAC_ALGO);
        signer.init(keySpec);

        byte[] digest = signer.doFinal(step_PC_mes.getBytes(StandardCharsets.UTF_8));

        System.out.println("HMAC: " + bytesToNext(digest));
        return 0;
    }
}
