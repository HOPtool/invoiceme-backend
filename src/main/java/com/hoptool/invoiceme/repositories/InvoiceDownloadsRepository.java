/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.repositories;



import com.hoptool.entities.InvoiceConfirmationLogs;
import com.hoptool.entities.InvoiceDownloadsLogs;
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
public class InvoiceDownloadsRepository implements  PanacheRepository<InvoiceDownloadsLogs>, Serializable {
    
    
    
    
    public InvoiceDownloadsLogs doLookUp(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.request_id);
     return find("requestId = ?1", obj.request_id).firstResult();
    
    }
    
    
    public InvoiceDownloadsLogs doLookUpInvoiceDownload(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.irn);
     return find("irn = ?1", obj.irn).firstResult();
    
    }
    
    public InvoiceDownloadsLogs doLookUpInvoiceDownloadByParams(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.irn);
     return find("irn = ?1 and  requestId = ?2 ", obj.irn, obj.request_id).firstResult();
    
    }

     public PanacheQuery<InvoiceDownloadsLogs> doList(LocalDateTime start, LocalDateTime end) {
       
        return find("requestDate between ?1 and ?2 order by requestDate desc", start, end);
    
    }
     
     
    public PanacheQuery<InvoiceDownloadsLogs> doList(LocalDateTime start, LocalDateTime end, int status) {
       
        return find("requestDate between ?1 and ?2 and status = ?3 order by requestDate desc", start, end, status);
    
    }
    
    public InvoiceDownloadsLogs doLookUpInvoiceDownloadCount(ValidateIRNRequestObj obj) {
      log.info("obj = " + obj.irn);
      return find("irn = ?1  and status = 200 order by tid desc  limit 1", obj.irn).firstResult();

    }
    
    
    @Transactional
    public InvoiceDownloadsLogs doLog(ValidateIRNRequestObj request, LocalDateTime requestDate) throws Exception {
        InvoiceDownloadsLogs obj = null;
        try 
        {
           obj = new InvoiceDownloadsLogs();
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
    public InvoiceDownloadsLogs doSync(ValidateIRNRequestObj request, int code) throws Exception {
        InvoiceDownloadsLogs obj = new InvoiceDownloadsLogs();
        try 
        {
           InvoiceDownloadsLogs objx = doLookUp(request);
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
    
    
     @Transactional
    public InvoiceDownloadsLogs doSync(ValidateIRNRequestObj request, int code, InvoiceDownloadsLogs maxCountObj) throws Exception {
        InvoiceDownloadsLogs obj = new InvoiceDownloadsLogs();
        try 
        {
           InvoiceDownloadsLogs objx = doLookUpInvoiceDownloadByParams(request);
           
           log.info("-- do sync objx = " + objx+" : : code "+code);
           if(objx != null)
           {
             System.out.println(" got inside objx = " + objx);
             System.out.println("max count objx = " + maxCountObj);
             objx.responseDate = LocalDateTime.now();
             objx.status = code;
                if(maxCountObj !=null)
                {
                    log.info(" inside maxCountObj = " +maxCountObj);
                    if(objx.status ==200)
                    {
                        objx.downloadCount = maxCountObj.downloadCount==null?0+1:(maxCountObj.downloadCount==null?1:maxCountObj.downloadCount+1);
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
                         
                         objx.downloadCount = objx.downloadCount==null?0+1:(objx.downloadCount+1);
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
    
    
}
