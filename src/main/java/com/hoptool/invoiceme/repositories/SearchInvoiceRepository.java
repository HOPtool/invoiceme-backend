/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.repositories;


import com.hoptool.entities.InvoiceConfirmationLogs;
import com.hoptool.entities.SearchInvoiceLogs;
import com.hoptool.invoice.dto.ValidateIRNRequestObj;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
@Slf4j
public class SearchInvoiceRepository implements  PanacheRepository<SearchInvoiceLogs> {
    
    
    
    
    public SearchInvoiceLogs doLookUp(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.request_id);
     return find("requestId = ?1", obj.request_id).firstResult();
    
    }
    
    public SearchInvoiceLogs doLookUpByIRN(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.irn);
     return find("irn = ?1", obj.irn).firstResult();
    
    }
    
    public SearchInvoiceLogs doLookUpByIRNAndRequestId(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.irn+" : "+obj.request_id);
     return find("irn = ?1 and requestId = ?2 ", obj.irn, obj.request_id).firstResult();
    
    }
    
    
    public SearchInvoiceLogs doLookUpInvoiceDownload(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.request_id);
     return find("requestId = ?1", obj.request_id).firstResult();
    
    }

     public PanacheQuery<SearchInvoiceLogs> doList(LocalDateTime start, LocalDateTime end) {
       
        return find("requestDate between ?1 and ?2 order by requestDate desc", start, end);
    
    }
     
     
    public PanacheQuery<SearchInvoiceLogs> doList(LocalDateTime start, LocalDateTime end, int status) {
       
        return find("requestDate between ?1 and ?2 and status = ?3 order by requestDate desc", start, end, status);
    
    }
    
    
    
    @Transactional
    public SearchInvoiceLogs doLog(ValidateIRNRequestObj request, LocalDateTime requestDate) throws Exception {
        SearchInvoiceLogs obj = null;
        try 
        {
           obj = new SearchInvoiceLogs();
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
    public SearchInvoiceLogs doSync(ValidateIRNRequestObj request, int code, InvoiceConfirmationLogs maxCountObj) throws Exception {
        SearchInvoiceLogs obj = new SearchInvoiceLogs();
        try 
        {
           SearchInvoiceLogs objx = doLookUpByIRNAndRequestId(request);
           
           log.info("-- do sync objx = " + objx+" : : code "+code);
           if(objx != null)
           {
             System.out.println(" got inside objx = " + objx);
             System.out.println("max count objx = " + maxCountObj);
             objx.responseDate = LocalDateTime.now();
             objx.status = code;
                if(maxCountObj !=null)
                {
                    System.out.println(" inside maxCountObj = " +maxCountObj);
                    if(objx.status ==200)
                    {
                        objx.confirmationCount = maxCountObj.confirmationCount==null?0+1:(maxCountObj.confirmationCount==null?1:maxCountObj.confirmationCount+1);
                    }
                    else if(objx.status == 913)
                    {
                         objx.duplicateCount = objx.duplicateCount==null?0+1: objx.duplicateCount+1;//.(maxCountObj.duplicateCount==null?1:maxCountObj.confirmationCount+1);
                   
                    }
                    return Panache.getEntityManager().merge(objx);
                }
                else
                {
                    if(objx.status ==200)
                    {
                         
                         objx.confirmationCount = objx.confirmationCount==null?0+1:(objx.confirmationCount+1);
                    }
                    else if(objx.status == 913)
                    {
                         objx.duplicateCount = objx.duplicateCount==null?0+1: objx.duplicateCount+1;
                    }
                            
                     return Panache.getEntityManager().merge(objx);
                }
             
           }
           else
           {
               return null;
           }
          
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }
    
    @Transactional
    public static  SearchInvoiceLogs doSyncSearchCount(InvoiceConfirmationLogs objx, SearchInvoiceLogs existing) throws Exception {
       SearchInvoiceLogs obj = new SearchInvoiceLogs();
       try 
        {
         
           log.info(" doSyncConfirmationCount objx = " + objx);
           log.info(" existing "+existing);
           
           //doLookUpInvoiceConfirmationCount
           if(objx == null && existing !=null)
           {
          
               existing.responseDate  = existing.responseDate;
               existing.status = existing.status;
               existing.confirmationCount = existing.confirmationCount == null?1:existing.confirmationCount+1;
              
               return Panache.getEntityManager().merge(existing);
           }
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
           else
           {
               return null;
           }
           
           
          
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }

    
    public   SearchInvoiceLogs doLookUpInvoiceSearchCount(ValidateIRNRequestObj obj) {
      log.info("obj = " + obj.irn);
      return find("irn = ?1  and status = 200 order by tid desc limit 1", obj.irn).firstResult();

    }
   
    
}
