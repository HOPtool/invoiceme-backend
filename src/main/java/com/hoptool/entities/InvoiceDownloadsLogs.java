/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.entities;

import com.hoptool.invoice.dto.ValidateIRNRequest;
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
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@Entity
@Table(name="invoice_downloads_logs")
@Slf4j
public class InvoiceDownloadsLogs extends PanacheEntityBase // implements Serializable
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
    
     @Column(name="downoad_count")
    public Integer downloadCount;
    
    @NotNull
    @Column(name="request_date")
    public LocalDateTime requestDate;
    
    //@NotNull
    @Column(name="response_date")
    public LocalDateTime responseDate;
    
    @NotNull
    @Column(name="status")
    public int status;
    
    
    @Transactional
    public static InvoiceDownloadsLogs doLog(ValidateIRNRequestObj obj, LocalDateTime ldt) throws Exception {
        
        try 
        {
            
        
            InvoiceDownloadsLogs objx = new InvoiceDownloadsLogs();
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
    public static  InvoiceDownloadsLogs doSync(ValidateIRNRequestObj request, int code) throws Exception {
        InvoiceDownloadsLogs obj = new InvoiceDownloadsLogs();
        try 
        {
           InvoiceDownloadsLogs objx = doLookUp(request);
           log.info("objx = " + objx+" : : code "+code);
           if(objx != null)
           {
            objx.responseDate = LocalDateTime.now();
            objx.status = code;
            if(objx.status == 913)
            {
                objx.duplicateCount = (objx.duplicateCount == null?0 +1:objx.duplicateCount+1);
            }
            if(objx.status == 200)
            {
                objx.downloadCount = (objx.downloadCount == null?0 +1:objx.downloadCount+1);
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
    public static  InvoiceDownloadsLogs doSyncDownloadCount(InvoiceDownloadsLogs objx) throws Exception {
        InvoiceDownloadsLogs obj = new InvoiceDownloadsLogs();
        try 
        {
            
          // InvoiceDownloadsLogs objx = doLookUpInvoiceDownload(request);
           log.info(" doSyncDownloadCount objx = " + objx);
           if(objx != null)
           {
          
                objx.downloadCount = (objx.downloadCount == null?0 +1:objx.downloadCount+1);
           }
           
           
           return PanacheEntity.getEntityManager().merge(objx);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }
    
    
    public static InvoiceDownloadsLogs doLookUp(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.request_id);
     return InvoiceDownloadsLogs.find("requestId = ?1", obj.request_id).firstResult();
    
    }
    
    public  static InvoiceDownloadsLogs doLookUpInvoiceDownload(ValidateIRNRequestObj obj) {
     log.info("obj = " + obj.irn);
     return find("irn = ?1  and status = 200  order by tid desc limit 1", obj.irn).firstResult();
   //return find("irn = ?1  and status = 200 order by tid desc limit 1", obj.irn).firstResult();


    //List<InvoiceDownloadsLogs>
    }
    
}
