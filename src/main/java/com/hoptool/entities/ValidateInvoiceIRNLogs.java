/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.entities;

import com.hoptool.invoice.dto.ValidateIRNRequestObj;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@Entity
@Table(name="irn_validation_logs")
@Slf4j
@ToString()
public class ValidateInvoiceIRNLogs extends PanacheEntityBase // implements Serializable
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
    
    @Column(name="duplicate_count")
    public Integer duplicateCount;
    
    @Column(name="validation_count")
    public Integer validationCount;
    
    
    @NotNull
    @Column(name="request_date")
    public LocalDateTime requestDate;
    
    //@NotNull
    @Column(name="response_date")
    public LocalDateTime responseDate;
    
    @NotNull
    @Column(name="status")
    public int status;
    
    
     public  static ValidateInvoiceIRNLogs doLookUpInvoiceValidationCount(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.irn);
        //SELECT e FROM YourEntity e WHERE e.id = (SELECT MAX(e2.id) FROM YourEntity e2)
        return ValidateInvoiceIRNLogs.find("max(tid)").firstResult();
     
        // System.out.println("firstResult = " + firstResult);
        
        //return IRNValidationLogs.find("select e from IRNValidationLogs where e.tid = (select max(x.tid) from IRNValidationLogs x)  and e.irn = ?1  and e.status = 200 ", obj.irn).firstResult();
    //List<InvoiceDownloadsLogs>
    
    //IRNValidationLogs.find("select e from IRNValidationLogs where e.tid = (select max(x.tid) from IRNValidationLogs x)  and e.irn = ?1  and e.status = 200 ", obj.irn).firstResult();
    }
    
    
    @Transactional
    public static ValidateInvoiceIRNLogs doLog(ValidateIRNRequestObj obj, LocalDateTime ldt) throws Exception {
        
        try 
        {
            
        
            ValidateInvoiceIRNLogs objx = new ValidateInvoiceIRNLogs();
            objx.businessId = obj.business_id;
            objx.irn = obj.irn;
            objx.duplicateCount = 0;
            objx.requestDate = ldt;// LocalDateTime.now();
            objx.requestId = obj.request_id;
            objx.status = 0;
            return Panache.getEntityManager().merge(objx);
        
        } 
        catch (Exception e) {
        
        
            throw new Exception(e);
        }
    }
    
    
    @Transactional
    public static  ValidateInvoiceIRNLogs doSync(ValidateIRNRequestObj request, int code) throws Exception {
        ValidateInvoiceIRNLogs obj = new ValidateInvoiceIRNLogs();
        try 
        {
           ValidateInvoiceIRNLogs objx = doLookUp(request);
           log.info("objx = " + objx+" : : code "+code);
           if(objx != null)
           {
            objx.responseDate = LocalDateTime.now();
            objx.status = code;
            if(objx.status == 913)
            {
                objx.duplicateCount = (objx.duplicateCount == null?0 +1:objx.duplicateCount+1);
            }
           }
           
           return Panache.getEntityManager().merge(objx);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }
    
    @Transactional
    public static  ValidateInvoiceIRNLogs doSyncValidationCount(ValidateInvoiceIRNLogs objx) throws Exception {
        ValidateInvoiceIRNLogs obj = new ValidateInvoiceIRNLogs();
        try 
        {
         
           log.info(" doSyncValidationCount objx = " + objx);
           if(objx != null)
           {
          
                objx.validationCount = objx.validationCount == null?0:objx.validationCount+1;
           }
           
           
           return Panache.getEntityManager().merge(objx);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }
    
    
    public static ValidateInvoiceIRNLogs doLookUp(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.request_id);
     return ValidateInvoiceIRNLogs.find("requestId = ?1", obj.request_id).firstResult();
    
    }
    
}
