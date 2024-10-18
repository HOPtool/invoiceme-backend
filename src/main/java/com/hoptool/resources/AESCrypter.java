/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.resources;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author aakintola
 */
public class AESCrypter {
   
    //private String iv              = "uE:Fh8jToN186*fY";//  bytesToHex("uE:Fh8jToN186*fY".getBytes()); //"uE:Fh8jToN186*fY"//1234567890123456";
    private String iv ;//             =   "V@f@3v1lk4Op*7ID";
    private String secretkey  ;//     =   "j0sr%Zk6^JB4v~Kc";//     !654XYZ_#9abcdEF";
    private IvParameterSpec ivspec;
    private SecretKeySpec keyspec;
    private Cipher cipher;
    //a45020b63a38a92ab702b69c1b41e32a
    //d4b3e9f4b7b3a294a35b890d64a1819d
    //7778280145c9d0b14ba0f5102ae96aa8
    public static void main(String args[]) throws UnsupportedEncodingException, Exception
    {
        //af0fbb97b45cd873aee4c4e81af858aa
        AESCrypter  crypta = new AESCrypter();
        String toenc = "1";
        String enc = "";//d4b3e9f4b7b3a294a35b890d64a1819d";
        String dec = "";
        //7ef8f476ff84428d013e777b5afd5a0e
        
        enc = crypta.encrypt(toenc);
        
        System.out.println(" ENCED : "+enc);
        
        
        //dec = crypta.decrypt(toenc);
        
        
        dec = crypta.decrypt(enc);
	//dec = URLDecoder.decode(dec,"UTF-8");
        System.out.println(" DECED : "+dec);
    }

    public AESCrypter(String keyz, String ivStr) throws SecException
    {
        if(ivStr == null || ivStr.isEmpty() || keyz == null || keyz.isEmpty()) throw new SecException("Empty IV or Key passed ");
        ivspec = new IvParameterSpec(ivStr.getBytes());
        keyspec = new SecretKeySpec(keyz.getBytes(), "AES");

        try 
        {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        } 
        catch (NoSuchPaddingException e) 
        {
            e.printStackTrace();
        }
    }
    
    public AESCrypter()
    {
        ivspec = new IvParameterSpec(iv.getBytes());
        keyspec = new SecretKeySpec(secretkey.getBytes(), "AES");
        
        
        System.out.println("this ivspec = " + ivspec);

        try 
        {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        } 
        catch (NoSuchPaddingException e) 
        {
            e.printStackTrace();
        }
    }

    public String encrypt(String text) throws SecException
    {
         
       // System.err.println("text ss= " + text);
        
        if(text == null || text.length() == 0) {
            throw new SecException("Empty string");
        }
        byte[] encrypted = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        }
        catch (Exception e) {
            throw new SecException("[encrypt] " + e.getMessage());
        }
        return bytesToHex(encrypted);
    }

    public String decrypt(String code) throws SecException, UnsupportedEncodingException
    {
        if(code == null || code.length() == 0) {
            throw new SecException("Empty string");
        }
        byte[] decrypted = null;
        try 
        {
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            decrypted = cipher.doFinal(hexToBytes(code));
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            throw new SecException("[decrypt] " + e.getMessage());
        }
        return new String(decrypted, "UTF-8" );
    }

    public static String bytesToHex(byte[] data)
    {
        if (data==null) {
            return null;
        }
        int len = data.length;
        String str = "";
        for (int i=0; i<len; i++) 
        {
            if ((data[i]&0xFF)<16) {
                str = str + "0" + java.lang.Integer.toHexString(data[i]&0xFF);
            }
            else {
                str = str + java.lang.Integer.toHexString(data[i]&0xFF);
            }
        }
        return str;
    }

    public static byte[] hexToBytes(String str) {
        if (str==null) {
            return null;
        }
        else if (str.length() < 2) {
            return null;
        }
        else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i=0; i<len; i++) {
                buffer[i] = (byte) Integer.parseInt(str.substring(i*2,i*2+2),16);
            }
            return buffer;
        }
    } 
}
