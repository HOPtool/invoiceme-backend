/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */
@Entity
@Table(name="user_log")
@ToString
@Slf4j
public class UserLog extends PanacheEntityBase implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tid")
    public long tid;
    
    
    @Column(name="first_name")
    public String firstName;
    
    @Column(name="user_email")
    public String userEmail;
    
    @Column(name="last_name")
    public String lastName;
    
    @Column(name="channel")
    public String channel;
    
    @Column(name="client_id")
    public String clientId;
    
    @Column(name="control_code")
    public String controlCode;
    
    
    
    @Column(name="sync_status")
    public long syncStatus;
    
    @Column(name="business_logo_url")
    public String businessLogoUrl;
    
    @Column(name="verification_type")
    public String verificationType;
    

    @Column(name="firs_mbs_number")
    public String firsMBSNumber;
    
    @Column(name="business_no")
    public String businessNo;
    
    @Column(name="business_name")
    public String businessName;
    
    @Column(name="business_mobile_no")
    public String businessMobileNo;
    
    
    @Column(name="status")
    public String status;
    
    @Column(name="corporate_id")
    public String corporateId;
    
    @Column(name="password_hash")
    public String passwordHash;
    
    
    @Column(name="create_date")
    public LocalDateTime createDate;
    
    @Column(name="update_date")
    public LocalDateTime upateDate;
    
    
    @Column(name="sign_up_stage")
    public String signUpStage;
    
    
    @Column(name="init_reset_password")
    public String initResetPassword;
    
    @Column(name="init_reset_password_date")
    public LocalDateTime initResetPasswordDate;
    

}
