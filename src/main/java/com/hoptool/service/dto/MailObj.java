/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.service.dto;

/**
 *
 * @author tshaguy
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author taysayshaguy
 */
public class MailObj {
    
    
    private String recipientName;
    private String receipientEmail;
    private String subject;
    private String username;
    private String token;
    private int tryCount;
    private String ourMail;
    private String notificationDate;
    private String inhouseMail = "";//iawojuola@nibss-plc.com.ng";
    private String mailPassword;
    private String smtpHost;
    private String smtpPort;
    private String enableTls;
    private String extraInfo;
    private String extraInfo2;
    private String amount;
    private String ccMail;
    private String ccMail2;
    private String otherInfo;
    private String otherInfo1;
    private String otherInfo2;

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

   
    

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReceipientEmail() {
        return receipientEmail;
    }

    public void setReceipientEmail(String receipientEmail) {
        this.receipientEmail = receipientEmail;
    }

    
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

   

    public String getOurMail() {
        return ourMail;
    }

    public void setOurMail(String ourMail) {
        this.ourMail = ourMail;
    }


    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getInhouseMail() {
        return inhouseMail;
    }

    public void setInhouseMail(String inhouseMail) {
        this.inhouseMail = inhouseMail;
    }

    public int getTryCount() {
        return tryCount;
    }

    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }
    

    public String getEnableTls() {
        return enableTls;
    }

    public void setEnableTls(String enableTls) {
        this.enableTls = enableTls;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCcMail() {
        return ccMail;
    }

    public void setCcMail(String ccMail) {
        this.ccMail = ccMail;
    }

    public String getCcMail2() {
        return ccMail2;
    }

    public void setCcMail2(String ccMail2) {
        this.ccMail2 = ccMail2;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getOtherInfo1() {
        return otherInfo1;
    }

    public void setOtherInfo1(String otherInfo1) {
        this.otherInfo1 = otherInfo1;
    }

    public String getOtherInfo2() {
        return otherInfo2;
    }

    public void setOtherInfo2(String otherInfo2) {
        this.otherInfo2 = otherInfo2;
    }

    public String getExtraInfo2() {
        return extraInfo2;
    }

    public void setExtraInfo2(String extraInfo2) {
        this.extraInfo2 = extraInfo2;
    }

    @Override
    public String toString() {
        return "MailObj{" + "recipientName=" + recipientName + ", receipientEmail=" + receipientEmail + ", subject=" + subject + ", username=" + username + ", token=" + token + ", tryCount=" + tryCount + ", ourMail=" + ourMail + ", notificationDate=" + notificationDate + ", inhouseMail=" + inhouseMail + ", mailPassword=" + mailPassword + ", smtpHost=" + smtpHost + ", smtpPort=" + smtpPort + ", enableTls=" + enableTls + ", extraInfo=" + extraInfo + ", extraInfo2=" + extraInfo2 + ", amount=" + amount + ", ccMail=" + ccMail + ", ccMail2=" + ccMail2 + ", otherInfo=" + otherInfo + ", otherInfo1=" + otherInfo1 + ", otherInfo2=" + otherInfo2 + '}';
    }

    
}
