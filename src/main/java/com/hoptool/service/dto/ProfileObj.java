/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.service.dto;

import java.math.BigDecimal;
import jakarta.json.Json;

/**
 *
 * @author taysayshaguy
 */
public class ProfileObj {
    
     private String accountId;
     private String accountNo;
     private String alias;
     private String mobile;
     private String email;
     private boolean doFt;
     private boolean doBillpay;
     private double bpLimit;
     private double ftLimit;
     private int status;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDoFt() {
        return doFt;
    }

    public void setDoFt(boolean doFt) {
        this.doFt = doFt;
    }

    public boolean isDoBillpay() {
        return doBillpay;
    }

    public void setDoBillpay(boolean doBillpay) {
        this.doBillpay = doBillpay;
    }

    public double getBpLimit() {
        return bpLimit;
    }

    public void setBpLimit(double bpLimit) {
        this.bpLimit = bpLimit;
    }

    public double getFtLimit() {
        return ftLimit;
    }

    public void setFtLimit(double ftLimit) {
        this.ftLimit = ftLimit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String toJson() {
   
     return  Json.createObjectBuilder().
             add("accountId", toDefault(accountId)).
             add("accountNo", toDefault(accountNo)).
             add("alias", toDefault(alias)).
             add("mobile", toDefault(mobile)).
             add("email", toDefault(email)).
             add("doFt", doFt).
             add("doBillpay", doBillpay).
             add("bpLimit", bpLimit).
             add("ftLimit", ftLimit).
             add("status", status).
             build().toString();
        
    }
    
    private  String toDefault(String value) {
        
        return (value ==null || value.trim().equals(""))?"NA": value;
    }
    

    @Override
    public String toString() {
        return "ProfileObj{" + "accountId=" + accountId + ", accountNo=" + accountNo + ", alias=" + alias + ", mobile=" + mobile + ", email=" + email + ", doFt=" + doFt + ", doBillpay=" + doBillpay + ", bpLimit=" + bpLimit + ", ftLimit=" + ftLimit + ", status=" + status + '}';
    }
   
    
}
