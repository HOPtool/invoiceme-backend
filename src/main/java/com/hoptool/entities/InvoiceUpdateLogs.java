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
@Table(name="invoice_update_logs")
@Slf4j
@ToString()
public class InvoiceUpdateLogs extends PanacheEntityBase // implements Serializable
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
    @Column(name="reference")
    public String reference;
    
    @NotNull
    @Column(name="request_id")
    public String requestId;
    
    @Column(name="duplicate_count")
    public Integer duplicateCount;
    
    
    @Column(name="update_count")
    public Integer updateCount;
    
    @NotNull
    @Column(name="request_date")
    public LocalDateTime requestDate;
    
    //@NotNull
    @Column(name="response_date")
    public LocalDateTime responseDate;
    
    @NotNull
    @Column(name="status")
    public int status;
    
    
//    public  static InvoiceConfirmationLogs doLookUpInvoiceConfirmationCount(ValidateIRNRequestObj obj) {
//     log.info("obj = " + obj.irn);
//     return InvoiceConfirmationLogs.find("irn = ?1  and status = 200 and max(tid)", obj.irn).firstResult();
//    //List<InvoiceDownloadsLogs>
//    }
//    
//    @Transactional
//    public static InvoiceConfirmationLogs doLog(ValidateIRNRequestObj obj, LocalDateTime ldt) throws Exception {
//        
//        try 
//        {
//            
//        
//            InvoiceConfirmationLogs objx = new InvoiceConfirmationLogs();
//            objx.businessId = obj.business_id;
//            objx.irn = obj.irn;
//            objx.duplicateCount = 0;
//            objx.requestDate = ldt;// LocalDateTime.now();
//            objx.requestId = obj.request_id;
//            objx.status = 0;
//            return Panache.getEntityManager().merge(objx);
//        
//        } 
//        catch (Exception e) {
//        
//        
//            throw new Exception(e);
//        }
//    }
//    
//    
//    @Transactional
//    public static  InvoiceConfirmationLogs doSync(ValidateIRNRequestObj request, int code) throws Exception {
//        InvoiceConfirmationLogs obj = new InvoiceConfirmationLogs();
//        try 
//        {
//           InvoiceConfirmationLogs objx = doLookUp(request);
//           log.info("objx = " + objx+" : : code "+code);
//           if(objx != null)
//           {
//            objx.responseDate = LocalDateTime.now();
//            objx.status = code;
//            if(objx.status == 913)
//            {
//                objx.duplicateCount = (objx.duplicateCount == null?0 +1:objx.duplicateCount+1);
//            }
//           }
//           
//           return PanacheEntity.getEntityManager().merge(objx);
//        } 
//        catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception(e);
//        }
//     
//    }
//    
//    
//    @Transactional
//    public static  InvoiceConfirmationLogs doSyncConfirmationCount(InvoiceConfirmationLogs objx) throws Exception {
//        InvoiceConfirmationLogs obj = new InvoiceConfirmationLogs();
//        try 
//        {
//         
//           log.info(" doSyncConfirmationCount objx = " + objx);
//           if(objx != null)
//           {
//          
//                objx.confirmationCount = objx.confirmationCount == null?0:objx.confirmationCount+1;
//           }
//           
//           
//           return PanacheEntity.getEntityManager().merge(objx);
//        } 
//        catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception(e);
//        }
//     
//    }
//    
//    public static InvoiceConfirmationLogs doLookUp(ValidateIRNRequestObj obj) {
//     log.info("obj = " + obj.request_id);
//     return InvoiceConfirmationLogs.find("requestId = ?1", obj.request_id).firstResult();
//    
//    }
    
}
