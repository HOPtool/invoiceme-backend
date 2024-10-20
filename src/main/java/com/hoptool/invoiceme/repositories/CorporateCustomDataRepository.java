/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.repositories;



import com.hoptool.invoiceme.dto.CorporateCustomDataRequestObj;
import com.hoptool.invoiceme.dto.UserSignUpStepOneObj;
import com.hoptool.invoiceme.entities.CorporateCustomData;
import com.hoptool.invoiceme.entities.EmailVerificationLog;
import io.quarkus.hibernate.orm.panache.Panache;
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
public class CorporateCustomDataRepository implements  PanacheRepository<CorporateCustomData> {
    
    
    public CorporateCustomData doLookUpById(long tid) {
    // log.info("obj = " + obj.reference);
     return find("tid = ?1", tid).firstResult();
    
    }
    
    public CorporateCustomData doLookUpOTP(CorporateCustomDataRequestObj obj) {
     log.info("obj = " + obj);
     return find("domainType = ?1 and corporateId =?2 ", obj.domainType, obj.corporateId).firstResult();
    
    }
   
    
    /*

     public PanacheQuery<InvoiceUpdateLogs> doList(LocalDateTime start, LocalDateTime end) {
       
        return find("requestDate between ?1 and ?2 order by requestDate desc", start, end);
    
    }
     
     
    public PanacheQuery<InvoiceUpdateLogs> doList(LocalDateTime start, LocalDateTime end, int status) {
       
        return find("requestDate between ?1 and ?2 and status = ?3 order by requestDate desc", start, end, status);
    
    }
    
    */
    
    
    @Transactional
    public CorporateCustomData doLog(UserSignUpStepOneObj request, String otp) throws Exception {
        CorporateCustomData obj = null;
        String firstname = "", lastname ="";
        try 
        {
            

                obj = new CorporateCustomData();
          
                
                obj.colorCode = otp;
                obj.colorCode2 = otp;
                obj.corporateId = request.corporateId;
                obj.domain = request.companyEmail;
                obj.domainType = request.companyEmail;
                obj.createdDate = LocalDateTime.now();
                obj = Panache.getEntityManager().merge(obj);
          
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
    }
    
    @Transactional
    public CorporateCustomData doSync(EmailVerificationLog request, String status) throws Exception {
       
        CorporateCustomData resp = null;
        try 
        {
            

               // obj = new EmailVerificationLog();
          
                 if(request !=null)
                 {
                
                    request.usedDate = LocalDateTime.now();
                    request.status = status;
                    request = Panache.getEntityManager().merge(request);
                 }
          
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return resp;
    }
    
    
    
    
    /*
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
   

  */
    
}
