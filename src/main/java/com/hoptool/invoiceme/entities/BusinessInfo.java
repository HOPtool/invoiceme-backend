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

/**
 *
 * @author paycraftsystems-i
 */
@Entity
@Table(name="business_logs")
public class BusinessInfo extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="tid")
    public long tid;
    
    @Column(name="business_name")
    public String businessName;
    
    @Column(name="business_type")
    public String businessType;
    
    @Column(name="business_no")
    public String businessNo;
    
    @Column(name="invoiceme_id")
    public String invoiceMeId;

    @Column(name="conpact_person_first_name")
    public String contactPersonfirstName;
    
    @Column(name="contact_person_email")
    public String contact_PersonEmail;
    
    @Column(name="contact_last_name")
    public String contactPersonLastName;
   
    @Column(name="business_logo_url")
    public String businessLogoUrl;
    
    @Column(name="verification_type")
    public String verificationType;
    
    @Column(name="business_mobile_no")
    public String businessMobileNo;
    
    @Column(name="status")
    public String status;
    



}
