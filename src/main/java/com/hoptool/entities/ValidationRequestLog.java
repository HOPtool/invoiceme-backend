/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author paycraftsystems-i
 */

@Entity
@Table(name="invoice_validation_log")
public class ValidationRequestLog extends PanacheEntityBase implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TID")
    private Long tid;
    
    @Column(name="invoice_reference")
    public String invoiceReference;
    
    @Column(name="business_id")
    public String businessId;
    
    @Column(name="irn")
    public String irn;
    
    @Column(name="request_date")
    public String requestDate;
    
    
    
    
}
