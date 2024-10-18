/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.resources;


import java.security.Key;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author Antonio Goncalves  http://www.antoniogoncalves.org
 * @author taysayshaguy        --
 */


public interface KeyGenerator {

    Key generateKey();
    
    Key generateKey(String key);
    
    Key generateKey(String userid, String password);
    
    Key generateKey(String userid, String password, String random);
}
