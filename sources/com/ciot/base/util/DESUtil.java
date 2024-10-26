package com.ciot.base.util;

import android.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESUtil {
    private static final String ALGORITHM = "DES";
    private static final String CHARSET = "utf-8";
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";
    private static final String IV_PARAMETER = "12345678";

    private static Key generateKey(String str) throws Exception {
        return SecretKeyFactory.getInstance(ALGORITHM).generateSecret(new DESKeySpec(str.getBytes("utf-8")));
    }

    public static String encrypt(String str, String str2) {
        if (str == null || str.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        } else if (str2 == null) {
            return null;
        } else {
            try {
                Key generateKey = generateKey(str);
                Cipher instance = Cipher.getInstance(CIPHER_ALGORITHM);
                instance.init(1, generateKey, new IvParameterSpec(IV_PARAMETER.getBytes("utf-8")));
                return new String(Base64.encode(instance.doFinal(str2.getBytes("utf-8")), 0));
            } catch (Exception e) {
                e.printStackTrace();
                return str2;
            }
        }
    }

    public static String decrypt(String str, String str2) {
        if (str == null || str.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        } else if (str2 == null) {
            return null;
        } else {
            try {
                Key generateKey = generateKey(str);
                Cipher instance = Cipher.getInstance(CIPHER_ALGORITHM);
                instance.init(2, generateKey, new IvParameterSpec(IV_PARAMETER.getBytes("utf-8")));
                return new String(instance.doFinal(Base64.decode(str2.getBytes("utf-8"), 0)), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
                return str2;
            }
        }
    }

    public static String encryptFile(String str, String str2, String str3, String str4) {
        if (str == null || str.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_PARAMETER.getBytes("utf-8"));
            Cipher instance = Cipher.getInstance(CIPHER_ALGORITHM);
            instance.init(1, generateKey(str4), ivParameterSpec);
            FileInputStream fileInputStream = new FileInputStream(str2);
            FileOutputStream fileOutputStream = new FileOutputStream(str3);
            CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, instance);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = cipherInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    cipherInputStream.close();
                    fileInputStream.close();
                    fileOutputStream.close();
                    return str3;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptFile(String str, String str2, String str3, String str4) {
        if (str == null || str.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        try {
            File file = new File(str3);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_PARAMETER.getBytes("utf-8"));
            Cipher instance = Cipher.getInstance(CIPHER_ALGORITHM);
            instance.init(2, generateKey(str4), ivParameterSpec);
            FileInputStream fileInputStream = new FileInputStream(str2);
            FileOutputStream fileOutputStream = new FileOutputStream(str3);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, instance);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read >= 0) {
                    cipherOutputStream.write(bArr, 0, read);
                } else {
                    cipherOutputStream.close();
                    fileInputStream.close();
                    fileOutputStream.close();
                    return str3;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
