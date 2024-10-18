/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@Entity
@Table(name="irn_generation_logs")
@Slf4j
@ToString()
public class IRNGenerationLogs extends PanacheEntityBase // implements Serializable
{
    
    @Id
    @GeneratedValue
    @Column(name="tid")
    public long tid;
    
    @NotNull
    @Column(name="business_id")
    public String businessId;
    
    @NotNull
    @Column(name="irn")
    public String irn;
    
    @NotNull
    @Column(name="request_id")
    public String requestId;
    
    @Column(name="tin")
    public String tin;
    
    @Column(name="irn_template")
    public String irnTemplate;
    
    @Column(name="invoice_id")
    public String invoiceId;
    
    
    @Column(name="reference")
    public String reference;
    
    @Column(name="sector")
    public String sector;
    
    @Column(name="annual_turnover")
    public String annualTurnover;
    
    @Column(name="support_peppol")
    public boolean supportPeppol;
    
    @Column(name="is_active")
    public boolean isActive;
    
    @NotNull
    @Column(name="request_date")
    public LocalDateTime requestDate;
    
    //@NotNull
    @Column(name="response_date")
    public LocalDateTime responseDate;
    
    @NotNull
    @Column(name="status")
    public int status;
    
    
}
