/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.repositories;


import com.hoptool.eivc.response.dto.EntityBusinessObject;
import com.hoptool.eivc.response.dto.EntityDataResponse;
import com.hoptool.eivc.response.dto.EntityResponse;
import com.hoptool.entities.IRNGenerationLogs;
import com.hoptool.entities.InvoiceValidationRequestLog;
import com.hoptool.invoice.dto.EntityRequestObj;
import com.hoptool.invoice.dto.SignInvoiceValidationObj;
import com.hoptool.resources.ResourceHelper;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
@Slf4j
public class IRNGenerationRepository implements  PanacheRepository<IRNGenerationLogs>, Serializable{
    
    
     
    public IRNGenerationLogs doLookUp(EntityRequestObj obj) {
     log.info("obj = " + obj.requestId);
     return find("requestId = ?1", obj.requestId).firstResult();
    
    }
    
    
    public IRNGenerationLogs doLookUpIRNGenerationRequest(EntityRequestObj obj) {
     log.info("obj = " + obj.requestId);
     return find("requestId = ?1 and businessId = ?2 and invoiceId = ?3 ", obj.requestId, obj.entity_id, obj.invoiceId).firstResult();
    
    }

     public PanacheQuery<IRNGenerationLogs> doList(LocalDateTime start, LocalDateTime end) {
       
        return find("requestDate between ?1 and ?2 order by requestDate desc", start, end);
    
    }
     
     
    public PanacheQuery<IRNGenerationLogs> doList(LocalDateTime start, LocalDateTime end, int status) {
       
        return find("requestDate between ?1 and ?2 and status = ?3 order by requestDate desc", start, end, status);
    
    }
   
    @Transactional
    public IRNGenerationLogs doLog(EntityRequestObj request) throws Exception {
        log.info("request IRNGenerationLogs = " + request);
        IRNGenerationLogs obj = null;
        ResourceHelper rs = new ResourceHelper();
        String productCategory ="";
        JsonArrayBuilder json = Json.createArrayBuilder();
        try 
        {
          
          
           //log.info(" -- productCategory -- "+productCategory+" json : ");
           obj = new IRNGenerationLogs();
           obj.businessId = request.entity_id;
           obj.requestDate = LocalDateTime.now();
           obj.requestId = request.requestId;
           obj.invoiceId = request.invoiceId;
           obj.status = 0;
           obj.irn = "XX";
           obj = Panache.getEntityManager().merge(obj);
          // return obj;
        } 
        catch (Exception e) {
            e.printStackTrace();
            log.error("--Exception- IRNGenerationLogs - ",e);
            throw new Exception(e);
        }
     return obj;
    }
    
    
    @Transactional
    public IRNGenerationLogs doSync(EntityRequestObj request, int code, EntityResponse response, String doGenerateIRN) throws Exception {
        System.out.println("IRNGenerationLogs doSync  = " + doGenerateIRN+" -- request -- "+request+" doGenerateIRN "+doGenerateIRN);
        IRNGenerationLogs obj = new IRNGenerationLogs();
        EntityBusinessObject business = null;
        IRNGenerationLogs objx = null;
        try 
        {
           objx = doLookUpIRNGenerationRequest(request);
           
           log.info("objx = " + objx+" : : code "+code+"  doGenerateIRN : "+doGenerateIRN);
           if(objx != null)
           {
            objx.responseDate = LocalDateTime.now();
            objx.status = code;
            objx.irn = doGenerateIRN;
            
             EntityBusinessObject buzi = (response !=null && response.data() !=null && response.data().businesses() !=null && response.data().businesses().size() >-1)? response.data().businesses().get(0):null;
            
             log.info("-- buzi -- "+buzi);
             if(buzi !=null)
             {
                objx.annualTurnover = buzi.annual_turnover();
                objx.irnTemplate = buzi.irn_template();
                objx.isActive = buzi.is_active();
                objx.reference = buzi.reference();


                objx.sector = buzi.sector();
                objx.supportPeppol = buzi.support_peppol();
                objx.tin = buzi.tin();
             }
             
           
              objx =  Panache.getEntityManager().merge(objx);
           }
           
           
        } 
        catch (Exception e) {
            log.error("--Exception- IRNGenerationLogs  doSync- ",e);
            e.printStackTrace();
            throw new Exception(e);
        }
     
       return objx;
    }
    
    
    public IRNGenerationLogs doLookUpByIRNAndRequestId(SignInvoiceValidationObj obj) {
      log.info("obj = " + obj.irn+" : "+obj.requestId);
     return find("irn = ?1 and requestId = ?2 ", obj.irn, obj.requestId).firstResult();
    
    }
    
    
    
    @Transactional
    public static  IRNGenerationLogs doSyncValidationCount(InvoiceValidationRequestLog objx, InvoiceValidationRequestLog existing) throws Exception {
       IRNGenerationLogs obj = new IRNGenerationLogs();
       try 
        {
         
           log.info(" doSyncConfirmationCount objx = " + objx);
           log.info(" existing "+existing);
           
           //doLookUpInvoiceConfirmationCount
           if(objx == null && existing !=null)
           {
          
               existing.responseDate  = existing.responseDate;
               existing.status = existing.status;
               existing.validationCount = existing.validationCount == null?1:existing.validationCount+1;
              
               //return Panache.getEntityManager().merge(existing);
           }
           else if(objx != null && existing !=null)
           {
                 log.info(" existing  = " +existing);
                 log.info(" Here  = " +objx);
                 existing.responseDate  = objx.responseDate;
                 existing.status = objx.status;
                 existing.validationCount = objx.validationCount==null?0+1:objx.validationCount+1;
                 //objx
              // return Panache.getEntityManager().merge(existing);
               
           }
           else
           {
               return null;
           }
           
           
          
        } 
        catch (Exception e) {
            log.info(" Exception objx = IRNGenerationLogs doSyncValidationCount " + objx);
            e.printStackTrace();
            throw new Exception(e);
        }
      return null;
    }
    
    
    @Transactional
    public IRNGenerationLogs doSync(SignInvoiceValidationObj request, int code, InvoiceValidationRequestLog maxCountObj) throws Exception {
        IRNGenerationLogs obj = new IRNGenerationLogs();
        try 
        {
           IRNGenerationLogs objx = doLookUpByIRNAndRequestId(request);
           
           log.info("-- do sync objx = " + objx+" : : code "+code);
           if(objx != null)
           {
             System.out.println(" got inside objx = " + objx);
             System.out.println("max count objx = " + maxCountObj);
             objx.responseDate = LocalDateTime.now();
             objx.status = code;
//                if(maxCountObj !=null)
//                {
//                    System.out.println(" inside maxCountObj = " +maxCountObj);
//                    if(objx.status ==200)
//                    {
//                        objx.validationCount = maxCountObj.validationCount ==null?0+1:(maxCountObj.validationCount==null?1:maxCountObj.validationCount+1);
//                    }
//                    else if(objx.status == 913)
//                    {
//                         objx.duplicateCount = objx.duplicateCount==null?0+1: objx.duplicateCount+1;//.(maxCountObj.duplicateCount==null?1:maxCountObj.confirmationCount+1);
//                   
//                    }
//                    return Panache.getEntityManager().merge(objx);
//                }
//                else
//                {
//                    if(objx.status ==200)
//                    {
//                         
//                         objx.validationCount = objx.validationCount==null?0+1:(objx.validationCount+1);
//                    }
//                    else if(objx.status == 913)
//                    {
//                         objx.duplicateCount = objx.duplicateCount==null?0+1: objx.duplicateCount+1;
//                    }
//                            
//                     return Panache.getEntityManager().merge(objx);
//                }
             
           }
           else
           {
               return null;
           }
           /*
           else if(objx != null && existing !=null)
           {
                 System.out.println(" existing  = " +existing);
                 System.out.println(" Here  = " +objx);
                 existing.responseDate  = objx.responseDate;
                 existing.status = objx.status;
                 existing.confirmationCount = objx.confirmationCount==null?0+1:objx.confirmationCount+1;
                 //objx
               return Panache.getEntityManager().merge(existing);
               
           }
           */
           // System.out.println("about to persist objx = " + objx);
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
      return null;
    }

   
    
    
    
}
