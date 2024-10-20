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
import java.time.LocalDateTime;

/**
 *
 * @author paycraftsystems-i
 */

@Entity
@Table(name="corporate_custom_data")
public class CorporateCustomData extends PanacheEntityBase {
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="tid")
    public long tid;
    
    @Column(name="corporate_id")
    public String corporateId;
    
    @Column(name="domain")
    public String domain;
    
    @Column(name="color_code")
    public String colorCode;
    
    @Column(name="color_code_2")
    public String colorCode2;
    
    @Column(name="domain_type")
    public String domainType;
    
    @Column(name="created_date")
    public LocalDateTime createdDate;

    @Column(name="upateed_date")
    public LocalDateTime updatedDate;





}
