/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.resources;


import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author Antonio Goncalves   http://www.antoniogoncalves.org
 * @author taysayshaguy
 *         --
 */

@ApplicationScoped
public class SysKeyGenerator implements KeyGenerator 
{

    //@Override
    public  Key generateKey() {
        String keyString = "simplekey";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "AES");
        return key;
    }
    
    @Override
    public  Key generateKey(String keyString) 
    {
        
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "AES");
        return key;
    }
    
    @Override
    public  Key generateKey(String userid, String keyString) 
    {
        if(userid == null) userid = "";
        if(keyString == null) keyString = "";
        String tohash = userid.trim()+keyString.trim();
        Key key = new SecretKeySpec(tohash.getBytes(), 0, tohash.getBytes().length, "AES");
        return key;
    }
    
    @Override
    public  Key generateKey(String userid, String password, String random) 
    {
        
        String tohash = userid.trim()+password.trim()+random.trim();
        Key key = new SecretKeySpec(tohash.getBytes(), 0, tohash.getBytes().length, "AES");
        return key;
    }
}
