package com.gfs.projects.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionAES extends Encryption {
    public static final String CVS_REVISION = "$";
    public static  String defaultKey       = "BipetUqoJ2LHEmYW";
    private String algorithm      = "AES";
    private SecretKey secretKey = null;

    public EncryptionAES() throws Exception {
        this(EncryptionAES.defaultKey);
    }

    public EncryptionAES(String keyString) throws Exception {
        try {
            byte[] sharedKey = keyString.getBytes( super.encoding );
            this.secretKey = new SecretKeySpec(sharedKey, this.algorithm);

        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }

    protected void encrypt(String clearString)
            throws Exception {
        try {
            byte[] clearBytes = clearString.getBytes  (super.encoding);

            Cipher encryptCipher = Cipher.getInstance ( this.algorithm);
            encryptCipher.init     (Cipher.ENCRYPT_MODE, this.secretKey);

            super.encryptedBytes = encryptCipher.doFinal(clearBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e);
        } catch (InvalidKeyException e) {
            throw new Exception(e);
        } catch (IllegalBlockSizeException e) {
            throw new Exception(e);
        } catch (BadPaddingException e) {
            throw new Exception(e);
        } catch (NoSuchPaddingException e) {
            throw new Exception(e);
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }


    protected void decrypt(byte[] encryptedBytes)
            throws Exception {
        try {
            Cipher decryptCipher = Cipher.getInstance(this.algorithm);
            decryptCipher.init(Cipher.DECRYPT_MODE, this.secretKey);
            super.decryptedBytes  = decryptCipher.doFinal(encryptedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e);
        } catch (InvalidKeyException e) {
            throw new Exception(e);
        } catch (IllegalBlockSizeException e) {
            throw new Exception(e);
        } catch (BadPaddingException e) {
            throw new Exception(e);
        } catch (NoSuchPaddingException e) {
            throw new Exception(e);
        }
    }
}
