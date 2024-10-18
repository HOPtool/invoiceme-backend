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
@Table(name="email_verification_log")
@ToString
@Slf4j
public class EmailVerificationLog extends PanacheEntityBase implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tid")
    public long tid;
    
    @Column(name="user_email")
    public String userEmail;
    
    @Column(name="otp")
    public String otp;
    
    @Column(name="create_date")
    public LocalDateTime createDate;
    
    @Column(name="used_date")
    public LocalDateTime usedDate;
    
    @Column(name="corporate_id")
    public String corporateId;
    
    @Column(name="status")
    public String status;
    

}
