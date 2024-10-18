/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.service.dto;

import jakarta.json.Json;


/**
 *
 * @author taysayshaguy
 */
public class HeaderClientObj 
{
    
    
    private String ux;
    private String ip;
    private String exip;
    private boolean forceIp;
    private String code;
    private String px;
    private String rx;
    private int tid;
    private String partnercode;
    private int tokenExpiryDays;
    private String emailAddress;

    public HeaderClientObj() {
    }

    public HeaderClientObj(String user, String ip, String exip, boolean forceIp, String code, String px, String rx, int tid, String partnercode, int tokenExpiryMins, String emailAddress) {
        this.ux = user;
        this.ip = ip;
        this.exip = exip;
        this.forceIp = forceIp;
        this.code = code;
        this.px = px;
        this.rx = rx;
        this.tid = tid;
        this.partnercode = partnercode;
        this.tokenExpiryDays = tokenExpiryMins;
        this.emailAddress = emailAddress;
    }
    
    
    
 
    public String getUx() {
        return ux;
    }

    public void setUx(String user) {
        this.ux = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isForceIp() {
        return forceIp;
    }

    public void setForceIp(boolean forceIp) {
        this.forceIp = forceIp;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPx() {
        return px;
    }

    public void setPx(String px) {
        this.px = px;
    }

    public String getRx() {
        return rx;
    }

    public void setRx(String rx) {
        this.rx = rx;
    }
    
    private String toDefault(String value) {
        
        return (value ==null)?"":value.trim();
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getExip() {
        return exip;
    }

    public void setExip(String exip) {
        this.exip = exip;
    }

    public String getPartnercode() {
        return partnercode;
    }

    public void setPartnercode(String partnercode) {
        this.partnercode = partnercode;
    }



    public int getTokenExpiryDays() {
        return tokenExpiryDays;
    }

    public void setTokenExpiryDays(int tokenExpiryDays) {
        this.tokenExpiryDays = tokenExpiryDays;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    
    public String toObj() {
    
        
      return Json.createObjectBuilder().
                add("tid", this.tid).
                add("user", toDefault(this.ux)).
                add("ip", toDefault(this.ip)).
                add("exip", toDefault(this.exip)).
                add("forceIp", this.forceIp).
                add("code",toDefault( this.code)).
                add("partnercode", toDefault(this.partnercode)).  
                add("tokenExpiryDays", this.tokenExpiryDays).
                add("px", toDefault(this.px)).
                add("emailAddress", toDefault(this.emailAddress)).
                add("rx", toDefault(this.rx)).build().toString();
      
    }

    @Override
    public String toString() {
        return "HeaderClientObj{" + "user=" + ux + ", ip=" + ip + ", exip=" + exip + ", forceIp=" + forceIp + ", code=" + code + ", px=" + px + ", rx=" + rx + ", tid=" + tid +  ", partnercode=" + partnercode + ", tokenExpiryDays=" + tokenExpiryDays +" , emailAddress = "+emailAddress+ '}';
    }
    

   
    
}
