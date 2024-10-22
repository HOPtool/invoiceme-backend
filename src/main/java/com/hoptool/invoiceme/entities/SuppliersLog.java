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
@Table(name="suppliers_log")
@Slf4j
@ToString
public class SuppliersLog extends PanacheEntityBase implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="tid")
    public long tid;
    @Column(name="company_name")
    public String companyName;
    @Column(name="cac_number")
    public String cacNumber;
    @Column(name="tin_number")
    public String tinNumber;
    @Column(name="contact_email")
    public String contactEmail;
    @Column(name="contact_phone_number")
    public String contactPhoneNumber;
    @Column(name="bank_name")
    public String bankName;
    @Column(name="bank_code")
    public String bankCode;
    @Column(name="bank_sort_code")
    public String bankSortCode;
    @Column(name="bank_account_number")
    public String bankAccountNumber;
    @Column(name="company_address")
    public String companyAddress;
    
    @Column(name="supplier_business_id")
    public String supplierBusinessId;
    
    @Column(name="supplier_code")
    public String supplierCode;
    
    @Column(name="status")
    public String status;
    
    @Column(name="created_date")
    public LocalDateTime createdDate;
    
    @Column(name="created_by")
    public long createdBy;
    
    @Column(name="updated_by")
    public long updatedBy;
    
    @Column(name="created_by_str")
    public String createdByStr;
    
    @Column(name="updated_by_str")
    public String updatedByStr;
    
    
    @Column(name="updated_date")
    public LocalDateTime updatedDate;
        
        
        
}
