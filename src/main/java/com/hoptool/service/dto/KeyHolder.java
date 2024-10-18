/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.service.dto;

/**
 *
 * @author taysayshaguy
 */
public class KeyHolder {
    
    private String iv;
    private String key;

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

    public KeyHolder() {
    }

    public KeyHolder(String iv, String key) {
        this.iv = iv;
        this.key = key;
    }

    @Override
    public String toString() {
        return "KeyHolder{" + "iv=" + iv + ", key=" + key + '}';
    }
    
    
}
