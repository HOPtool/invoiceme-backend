/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec; 
import javax.crypto.spec.SecretKeySpec; 
import java.util.Base64;

/**
 *
 * @author paycraftsystems-i
 */
@ApplicationScoped
public class CryptoController {
    
//public static void main(String[] args) {
//String ciphertext = ""; // Base64 URL encoded ciphertext String key = ""; // Key as a plain string (not in hex) String ivHex = ""; // IV in hex
//try {
//byte[] iv = hexStringToByteArray(ivHex); // Convert IV from hex to bytes String decryptedText = decrypt(key, iv, ciphertext); System.out.println("Decrypted Text: " + decryptedText);
//} catch (Exception e) {
//System.out.println("Decryption error: " + e.getMessage()); }
//}
    public  String decrypt(String key, byte[] iv, String ciphertext) throws Exception { // Decode Base64 URL encoded ciphertext
    byte[] ciphertextBytes = Base64.getUrlDecoder().decode(ciphertext);
    // Create AES cipher instance with AES-256-CFB algorithm
    Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
    // Initialize cipher with key and IV
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
    IvParameterSpec ivSpec = new IvParameterSpec(iv); cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);


    byte[] decryptedBytes = cipher.doFinal(ciphertextBytes); return new String(decryptedBytes, "UTF-8");
    }
// Utility function to convert hex string to byte array
    public static byte[] hexStringToByteArray(String s) { int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
    data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
    }
    return data; 
    }
    
}
