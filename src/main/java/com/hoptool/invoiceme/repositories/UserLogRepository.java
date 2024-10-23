/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.repositories;



import com.hoptool.invoiceme.dto.UserSignUpStepOneObj;
import com.hoptool.invoiceme.entities.EmailVerificationLog;
import com.hoptool.invoiceme.entities.UserLog;
import com.hoptool.invoiceme.enumz.SignUpStage;
import com.hoptool.invoiceme.enumz.UserStatus;
import com.hoptool.resources.AESCrypter;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
@Slf4j
public class UserLogRepository implements  PanacheRepository<UserLog> {
    
    
    @Inject
    SysDataRepository sysDataRepo;
    
    public UserLog doLookUpById(long tid) {
    // log.info("obj = " + obj.reference);
     return find("tid = ?1", tid).firstResult();
    
    }
    
    public UserLog doLookUpByCredentials(UserSignUpStepOneObj obj) {
     log.info("obj = " + obj);
     return find("userEmail = ?1 and corporateId =?2 ", obj.companyEmail, obj.corporateId).firstResult();
    
    }
    
    public UserLog doLookUp(String companyEmail,  String corporateId) {
     
        return find("userEmail = ?1 and corporateId =?2 ", companyEmail, corporateId).firstResult();
    
    }
    /*
        verify the stage  the user is before allowing the onboarding completion
    */
    public UserLog doVerifyActionStage(String companyEmail,  String corporateId, String stage) {
        log.info("--  doVerifyActionStage-- "+companyEmail+" - corporateId -- "+corporateId+" --stage "+stage);
        return find("userEmail = ?1 and corporateId =?2  and signUpStage = ?3 ", companyEmail, corporateId, stage).firstResult();
    
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
    public UserLog doLog(UserSignUpStepOneObj request) throws Exception {
        UserLog obj = null;
        String firstname = "", lastname ="";
        try 
        {
    
                obj = new UserLog();
          
                String[] split = request.fullname.split(" ");
           
                if(split.length == 2)
                {
                    firstname = split[0];
                    lastname = split[1];
                }
                else if(split.length == 1)
                {
                    firstname = "NA";
                    lastname = split[0];
                }
           
                obj.firstName = firstname;
                obj.lastName = lastname;
                obj.passwordHash = new AESCrypter(sysDataRepo.doLookUpByNameStr("SYS_KEY", "XXX"), sysDataRepo.doLookUpByNameStr("SYS_IV", "")).encrypt(request.password); 
                obj.corporateId = request.corporateId;
                obj.userEmail = request.companyEmail;
                obj.createDate = LocalDateTime.now();
                obj.signUpStage = SignUpStage.OTP_VERIFICATION.name();
                obj.status = UserStatus.PENDING_OTP_OVERIFICATION.name();
                
                log.info("-- obj -- "+obj);
                obj = Panache.getEntityManager().merge(obj);
          
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
    }
    
    @Transactional
    public UserLog doSyncStepChange(EmailVerificationLog request, String step) throws Exception {
        UserLog obj = null;
        String firstname = "", lastname ="";
        try 
        {
 
            UserLog doLookUp = doLookUp(request.userEmail,  request.corporateId);
                 
                 if(doLookUp !=null)
                 {
                     doLookUp.signUpStage = step;
                 }
           
                obj = Panache.getEntityManager().merge(doLookUp);
          
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
    }
    
    
    @Transactional
    public UserLog doSyncProfileSyncStatus(UserLog request, long step, String channel, String clientId, String controlCode) throws Exception {
        UserLog obj = null;
        String firstname = "", lastname ="";
        try 
        {
 
            UserLog doLookUp = doLookUp(request.userEmail,  request.corporateId);
                 
                 if(doLookUp !=null && step == 200)
                 {
                     doLookUp.syncStatus = step;
                     doLookUp.signUpStage = SignUpStage.COMPLETED.name();
                     doLookUp.passwordHash = "NA";
                     doLookUp.channel = channel;
                     doLookUp.clientId = clientId;
                     doLookUp.controlCode = controlCode;
                 }
                 else
                 {
                      doLookUp.syncStatus = step; 
                 }
           
                obj = Panache.getEntityManager().merge(doLookUp);
          
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
    }
    
    @Transactional
    public UserLog doSyncInitForcePasswordReset(UserLog request, long step) throws Exception {
        UserLog obj = null;
        String firstname = "", lastname ="";
        try 
        {
 
            UserLog doLookUp = doLookUp(request.userEmail,  request.corporateId);
                 
                 if(doLookUp !=null)
                 {
                     doLookUp.initResetPassword = "YES";
                     doLookUp.initResetPasswordDate = LocalDateTime.now();
                     
                 }
                 else
                 {
                      //doLookUp.syncStatus = step; 
                 }
           
                obj = Panache.getEntityManager().merge(doLookUp);
          
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
    }
    
    
    @Transactional
    public UserLog doSyncUser(UserLog request) throws Exception {
        UserLog obj = null;
        String firstname = "", lastname ="";
        try 
        {
 
            
                obj = Panache.getEntityManager().merge(request);
          
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
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
