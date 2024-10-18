/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.service.dto;

/**
 *
 * @author root
 */
public class CryptoObj {
    
     private String iv;
     private String key;
     private String toJibrish;
     private String toPlain;

    public CryptoObj() {
    }

    public CryptoObj(String iv, String key, String toJibrish, String toPlain) {
        this.iv = iv;
        this.key = key;
        this.toJibrish = toJibrish;
        this.toPlain = toPlain;
    }
    
    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToJibrish() {
        return toJibrish;
    }

    public void setToJibrish(String toJibrish) {
        this.toJibrish = toJibrish;
    }

    public String getToPlain() {
        return toPlain;
    }

    public void setToPlain(String toPlain) {
        this.toPlain = toPlain;
    }
    
    

    @Override
    public String toString() {
        return "CryptoObj{" + "iv=" + iv + ", key=" + key + ", toJibrish=" + toJibrish + ", toPlain = "+toPlain+'}';
    }
   
}
