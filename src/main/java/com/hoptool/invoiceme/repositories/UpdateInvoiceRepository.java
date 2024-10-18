/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.repositories;



import com.hoptool.entities.InvoiceUpdateLogs;
import com.hoptool.invoice.dto.UpdateInvoice;
import com.hoptool.invoice.dto.UpdateInvoiceObj;
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
public class UpdateInvoiceRepository implements  PanacheRepository<InvoiceUpdateLogs> {
    
    
    
    
    public InvoiceUpdateLogs doLookUp(UpdateInvoiceObj obj) {
     log.info("obj = " + obj.reference);
     return find("irn = ?1", obj.irn).firstResult();
    
    }
    
    public InvoiceUpdateLogs doLookUpByRequestId(UpdateInvoiceObj obj) {
     log.info("obj = " + obj.requestId);
     return find("requestId = ?1", obj.requestId).firstResult();
    
    }
    
    public InvoiceUpdateLogs doLookUpByIRNAndRequestId(UpdateInvoiceObj obj) {
     log.info("obj = " + obj.irn+" : "+obj.requestId);
     return find("irn = ?1 and requestId = ?2 ", obj.irn, obj.requestId).firstResult();
    
    }
    
    

     public PanacheQuery<InvoiceUpdateLogs> doList(LocalDateTime start, LocalDateTime end) {
       
        return find("requestDate between ?1 and ?2 order by requestDate desc", start, end);
    
    }
     
     
    public PanacheQuery<InvoiceUpdateLogs> doList(LocalDateTime start, LocalDateTime end, int status) {
       
        return find("requestDate between ?1 and ?2 and status = ?3 order by requestDate desc", start, end, status);
    
    }
    
    
    
    @Transactional
    public InvoiceUpdateLogs doLog(UpdateInvoiceObj request, LocalDateTime requestDate) throws Exception {
        InvoiceUpdateLogs obj = null;
        try 
        {
           obj = new InvoiceUpdateLogs();
           //obj.businessId = request.business_id;
           obj.irn = request.irn;
           obj.businessId = request.business_id;
           obj.reference = request.reference;
           obj.requestDate = requestDate;
           obj.requestId = request.requestId;
           obj.status = 0;
           obj = Panache.getEntityManager().merge(obj);
         
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
    }
    
    
    @Transactional
    public InvoiceUpdateLogs doSync(UpdateInvoiceObj request, int code, InvoiceUpdateLogs maxCountObj) throws Exception {
        InvoiceUpdateLogs obj = new InvoiceUpdateLogs();
        try 
        {
           InvoiceUpdateLogs objx = doLookUpByIRNAndRequestId(request);
           
           log.info("-- do sync objx = " + objx+" : : code "+code);
           if(objx != null)
           {
             log.info(" got inside objx = " + objx);
             System.out.println("max count objx = " + maxCountObj);
             objx.responseDate = LocalDateTime.now();
             objx.status = code;
                if(maxCountObj !=null)
                {
                    log.info(" inside maxCountObj = " +maxCountObj);
                    
                    if(objx.duplicateCount == null)
                    {
                        objx.duplicateCount = 0;
                    }
                    if(objx.status ==200)
                    {
                        objx.updateCount = maxCountObj.updateCount==null?0+1:(maxCountObj.updateCount==null?1:maxCountObj.updateCount+1);
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
                         
                         objx.updateCount = objx.updateCount==null?0+1:(objx.updateCount+1);
                    }
                    else if(objx.status == 913)
                    {
                         objx.duplicateCount = objx.duplicateCount==null?0+1: objx.duplicateCount+1;
                    }
                    
                    if(objx.duplicateCount == null)
                    {
                        objx.duplicateCount = 0;
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
    public static  InvoiceUpdateLogs doSyncUpdateCount(InvoiceUpdateLogs objx, InvoiceUpdateLogs existing) throws Exception {
       InvoiceUpdateLogs obj = new InvoiceUpdateLogs();
       try 
        {
         
           log.info(" doSyncUpdateCount objx = " + objx);
           log.info(" existing "+existing);
           
           //doLookUpInvoiceConfirmationCount
           if(objx == null && existing !=null)
           {
          
               existing.responseDate  = existing.responseDate;
               existing.status = existing.status;
               existing.updateCount = existing.updateCount == null?1:existing.updateCount+1;
              
               return Panache.getEntityManager().merge(existing);
           }
           else if(objx != null && existing !=null)
           {
                 System.out.println(" existing  = " +existing);
                 System.out.println(" Here  = " +objx);
                 existing.responseDate  = objx.responseDate;
                 existing.status = objx.status;
                 existing.updateCount = objx.updateCount==null?0+1:objx.updateCount+1;
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

    
    public InvoiceUpdateLogs doLookUpInvoiceUpdateCount(UpdateInvoice obj) {
      log.info("obj = " + obj.reference());
      return find("irn = ?1  and status = 200 order by tid desc  limit 1", obj.reference()).firstResult();

    }
   
    
}
