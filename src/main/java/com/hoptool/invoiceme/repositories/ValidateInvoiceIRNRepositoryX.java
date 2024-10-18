/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.repositories;

//import com.hoptool.entities.IRNValidateLogs;
//import com.hoptool.entities.IRNValidationLogs;//
import com.hoptool.entities.ValidateInvoiceIRNLogs;
import com.hoptool.invoice.dto.ValidateIRNRequestObj;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
@Slf4j
public class ValidateInvoiceIRNRepositoryX implements  PanacheRepository<ValidateInvoiceIRNLogs>, Serializable {
    
    
    
    
    public ValidateInvoiceIRNLogs doLookUp(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.request_id);
     return find("requestId = ?1", obj.request_id).firstResult();
    
    }

    
     public PanacheQuery<ValidateInvoiceIRNLogs> doList(LocalDateTime start, LocalDateTime end) {
       
        return find("requestDate between ?1 and ?2 order by requestDate desc", start, end);
    
    }
     
     
    public PanacheQuery<ValidateInvoiceIRNLogs> doList(LocalDateTime start, LocalDateTime end, int status) {
       
        return find("requestDate between ?1 and ?2 and status = ?3 order by requestDate desc", start, end, status);
    
    }
    
    
    
    @Transactional
    public ValidateInvoiceIRNLogs doLog(ValidateIRNRequestObj request, LocalDateTime requestDate) throws Exception {
        ValidateInvoiceIRNLogs obj = null;
        try 
        {
           obj = new ValidateInvoiceIRNLogs();
           obj.businessId = request.business_id;
           obj.irn = request.irn;
           obj.requestDate = requestDate;
           obj.requestId = request.request_id;
           obj.status = 0;
           obj = Panache.getEntityManager().merge(obj);
          // return obj;
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
    }
    
    
    @Transactional
    public ValidateInvoiceIRNLogs doSync(ValidateIRNRequestObj request, int code) throws Exception {
        ValidateInvoiceIRNLogs obj = new ValidateInvoiceIRNLogs();
        try 
        {
           ValidateInvoiceIRNLogs objx = doLookUp(request);
           log.info("objx = " + objx+" : : code "+code);
           if(objx != null)
           {
            objx.responseDate = LocalDateTime.now();
            objx.status = code;
           }
           
           return Panache.getEntityManager().merge(objx);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }
    
    
}
