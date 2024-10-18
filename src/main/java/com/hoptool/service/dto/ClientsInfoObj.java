/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.service.dto;


import com.hoptool.resources.ResourceHelper;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import jakarta.json.Json;

/**
 *
 * @author root
 */

public class ClientsInfoObj extends ResourceHelper implements Serializable {
    

    private static final long serialVersionUID = 1L;
    public long tid;
    public String ipAddress;
    public boolean enforceIp;
    public BigInteger status;
    public long crBy;
    public String code;
    public String iv;
    public String cKey;
    public String clientName;
    public long upBy;
    public int tokenLifespanDays;
    public String partnerID;
    public String partnerCode;
    public Date lastresetDT;
    public int clientCategory;
    public boolean isSuperAgent;

    public ClientsInfoObj() {
    }

    public ClientsInfoObj(Long tid) {
        this.tid = tid;
    }

    public ClientsInfoObj(Long tid, long crBy, long upBy, int tokenLifespanDays, String partnerID, String partnerCode) {
        this.tid = tid;
        this.crBy = crBy;
        this.upBy = upBy;
        this.tokenLifespanDays = tokenLifespanDays;
        this.partnerID = partnerID;
        this.partnerCode = partnerCode;
    }

   
    
    public static String toJson(ClientsInfoObj  obj)
    {
        
        return Json.createObjectBuilder()
                .add("tid",obj.tid)
                .add("ux",toDefault(obj.clientName))
                .add("ip", toDefault(obj.ipAddress))
                .add("enforceIp", obj.enforceIp)
                .add("tokenLifespanDays", obj.tokenLifespanDays)
                .add("iv", toDefault(obj.iv))
                .add("key", toDefault(obj.cKey))
                .add("code", toDefault(obj.code))
                .add("isSuperAgent", obj.isSuperAgent)
                .add("partnerCode", toDefault(obj.partnerCode))
                .add("partnerId", toDefault(obj.partnerID))
                
                
       .build().toString();
       
    }
    
     public String toJson()
    {
        
        return Json.createObjectBuilder()
                .add("tid",this.tid)
                .add("ux",toDefault(this.clientName))
                .add("ip", toDefault(this.ipAddress))
                .add("enforceIp", this.enforceIp)
                .add("tokenLifespanDays", this.tokenLifespanDays)
                .add("iv", toDefault(this.iv))
                .add("key", toDefault(this.cKey))
                .add("code", toDefault(this.code))
                .add("partnerCode", toDefault(this.partnerCode))
                .add("partnerId", toDefault(this.partnerID))
                
       .build().toString();
       
    }

    @Override
    public String toString() {
        return "ClientsInfoObj{" + "tid=" + tid + ", ipAddress=" + ipAddress + ", enforceIp=" + enforceIp + ", status=" + status + ", crBy=" + crBy + ", code=" + code + ", iv=" + iv + ", cKey=" + cKey + ", clientName=" + clientName + ", upBy=" + upBy + ", tokenLifespanDays=" + tokenLifespanDays + ", partnerID=" + partnerID + ", partnerCode=" + partnerCode + ", lastresetDT=" + lastresetDT + ", clientCategory=" + clientCategory + '}';
    }


}
