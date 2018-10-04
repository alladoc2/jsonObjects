package com.gfs.projects.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;



import org.apache.commons.codec.binary.Hex;

public abstract class Encryption {
    public static final String REVISION = "$";
    protected String encoding = "UTF-8";
    protected byte[] encryptedBytes = null;
    protected byte[] decryptedBytes = null;

    protected abstract void encrypt(String clearString)
            throws Exception;

    protected abstract void decrypt(byte[] encryptedBytes)
            throws Exception;

    public byte[] getEncryptedBytes(String clearString)
            throws Exception {

        this.encrypt(clearString);
        return this.encryptedBytes;
    }

    public String getEncryptedString(String clearString) throws Exception {

        this.encrypt(clearString);
        try {
            return (new String(this.encryptedBytes, this.encoding));
        } catch (UnsupportedEncodingException e) {
            System.out.println(".getEncryptedString " +  e);
            throw new Exception(e);
        }
    }

    public String getEncryptedHex(String clearString) throws Exception {
        this.encrypt(clearString);
        char[] hexChars          = Hex.encodeHex(this.encryptedBytes);
        return (new String(hexChars));
    }

    public String getEncryptedBase64(String clearString) throws Exception {
        this.encrypt(clearString);
        return (new Base64()).encodeToString(this.encryptedBytes);
    }

    public byte[] getDecryptedBytes(String encryptedString) throws Exception {
        try {
            this.decrypt(encryptedString.getBytes(this.encoding));
            return this.decryptedBytes;
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }

    public String getDecryptedString(String encryptedString) throws Exception {
        try {
            this.decrypt(encryptedString.getBytes(this.encoding));
            return new String(this.decryptedBytes, this.encoding);
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }

    public String getDecryptedFromHex(String encryptedHex) throws Exception {

        try {
            byte[] bytes              = Hex.decodeHex(encryptedHex.toCharArray());
            this.decrypt(bytes);
            return new String(this.decryptedBytes, this.encoding);
            
        } catch (DecoderException e) {
            throw new Exception(e);
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }

    public String getDecryptedFromBase64(String encryptedBase64) throws Exception {

        try {
            byte[] bytes = new Base64().decode(encryptedBase64);
            this.decrypt(bytes);
            return new String(this.decryptedBytes, this.encoding);
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

}
