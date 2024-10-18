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
public class LoginObj {
    
    
    public String ux;
    public String iv;
    public String key;
    public int clientCategory;
    public long providerId;
    public String providerStr;
    public String customerCode;
    public String flwSecKey;
    public String flwPubKey;
    public String flwEncKey;
    public String apiUserCustomerName;

    @Override
    public String toString() {
        return "LoginObj{" + "ux=" + ux + ", iv=" + iv + ", key=" + key + ", clientCategory=" + clientCategory + ", providerId=" + providerId + ", providerStr=" + providerStr + ", customerCode=" + customerCode + ", flwSecKey=" + flwSecKey + ", flwPubKey=" + flwPubKey + ", flwEncKey=" + flwEncKey + ", apiUserCustomerName=" + apiUserCustomerName + '}';
    }

   
    

}
