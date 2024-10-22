/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;


import jakarta.json.Json;

/**
 *
 * @author paycraftsystems-i
 */
public class UserSecObject {
    
    
   public long tid;
   public long pid;
   public String code;
   public String userCode;
   public String msisdn;
   public String codeLink;
   public String vHash1;
   public String vHash;
   public String syncDate;
   public String controlCode;
   public String lastLoginDate;
   public String modifiedDate;
   
   /*
   public String toObj() {
    
      ValidationHelper rh = new ValidationHelper();
      return Json.createObjectBuilder().
                add("tid", this.tid).
                add("pid", this.pid).
                add("code", this.code).
                add("userCode", rh.toDefault(this.userCode)).
                add("msisdn", rh.toDefault(this.msisdn)).
                add("codeLink",rh.toDefault( this.codeLink)).
                add("vHash1", rh.toDefault(this.vHash1)).  
                add("vHash",rh.toDefault( this.vHash)).
                add("syncDate", rh.toDefault(this.syncDate)).
                add("controlCode", rh.toDefault(this.controlCode)).
                add("lastLoginDate", rh.toDefault(this.lastLoginDate)).
                add("modifiedDate", rh.toDefault(this.modifiedDate)).build().toString();
   }
   */
     
    @Override
    public String toString() {
        return "UserSecObject{" + "tid=" + tid + ", pid=" + pid + ", code=" + code + ", userCode=" + userCode + ", msisdn=" + msisdn + ", codeLink=" + codeLink + ", vHash1=" + vHash1 + ", vHash=" + vHash + ", syncDate=" + syncDate + ", controlCode=" + controlCode + ", lastLoginDate=" + lastLoginDate + ", modifiedDate=" + modifiedDate + '}';
    }
    
    
    
}
