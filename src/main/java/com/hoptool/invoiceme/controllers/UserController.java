/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.controllers;

import com.google.gson.Gson;
import com.hoptool.exceptions.InvalidRequestException;
import com.hoptool.invoiceme.auth.dto.FIRSMBSLogin;
import com.hoptool.invoiceme.auth.dto.FirsLoginResponse;
import com.hoptool.invoiceme.dto.ChangeProfilePasswordRequest;
import com.hoptool.invoiceme.dto.ChangeProfilePasswordRequestObj;
import com.hoptool.invoiceme.auth.dto.ForceSyncProfilePasswordRequest;
import com.hoptool.invoiceme.auth.dto.ForceSyncResponse;
import com.hoptool.invoiceme.auth.dto.ProfileSyncResponse;
import com.hoptool.invoiceme.auth.dto.SyncProfile;
import com.hoptool.invoiceme.auth.dto.UserLoginRequest;
import com.hoptool.invoiceme.dto.CompleteBusinessOnboarding;
import com.hoptool.invoiceme.dto.CompleteBusinessOnboardingObj;
import com.hoptool.invoiceme.dto.ForceSyncProfilePasswordRx;
import com.hoptool.invoiceme.dto.ForceSyncProfilePasswordRxObj;
import com.hoptool.invoiceme.dto.InitForcePasswordChangeResponse;
import com.hoptool.invoiceme.dto.InitForcePasswordReset;
import com.hoptool.invoiceme.dto.InitForceUserPasswordReset;
import com.hoptool.invoiceme.dto.InitForceUserPasswordResetObj;
import com.hoptool.invoiceme.dto.InitUserCreationResponse;
import com.hoptool.invoiceme.dto.OTPValidationRequest;
import com.hoptool.invoiceme.dto.OTPValidationRequestObj;
import com.hoptool.invoiceme.dto.OTPVerificationResponse;
import com.hoptool.invoiceme.dto.OnboardingCompletionResponse;
import com.hoptool.invoiceme.dto.ResponseStatusHeaders;
import com.hoptool.invoiceme.dto.UserLogResponse;
import com.hoptool.invoiceme.dto.UserLogin;
import com.hoptool.invoiceme.dto.UserLoginObj;
import com.hoptool.invoiceme.dto.UserSignUpStepOne;
import com.hoptool.invoiceme.dto.UserSignUpStepOneObj;
import com.hoptool.invoiceme.entities.BusinessInfo;
import com.hoptool.invoiceme.entities.EmailVerificationLog;
import com.hoptool.invoiceme.entities.UserLog;
import com.hoptool.invoiceme.enumz.OTPStatus;
import com.hoptool.invoiceme.enumz.SignUpStage;
import com.hoptool.invoiceme.repositories.BusinessInfoRepository;
import com.hoptool.invoiceme.repositories.EmailValidationRepository;
import com.hoptool.invoiceme.repositories.SysDataRepository;
import com.hoptool.invoiceme.repositories.UserLogRepository;
import com.hoptool.resources.AESCrypter;
import com.hoptool.resources.ErrorCodes;
import com.hoptool.resources.RandomCharacter;
import com.hoptool.service.AuthService;
import com.hoptool.service.InvoiceService;
import io.quarkus.hibernate.orm.panache.Panache;
import static io.quarkus.logging.Log.info;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
@Slf4j
public class UserController {
    
    
    @Inject
    UserLogRepository userLogRepo;
    
    @Inject
    SysDataRepository sysDataRepo;
    
    @Inject
    EmailValidationRepository emailValidationRepo;
    
    @Inject
    AuthService authService;
    
    @Inject
    InvoiceService invoiceService;
    
    @Inject
    ESEQRepository eseqRepository;
    
    @Inject
    BusinessInfoRepository businessInfoRepo;
    
    
    public UserLogResponse doLogin(@Valid UserLogin request) {
        
        UserLogResponse response = null;
        try 
        {
            UserLoginObj userLoginObj = new UserLoginObj(request);
            
            if(userLoginObj !=null && userLoginObj.email !=null && userLoginObj.password !=null && userLoginObj.corporateId !=null)
            {

                    UserLog doLookUp = userLogRepo.doLookUp(userLoginObj.email, userLoginObj.corporateId);
                    log.info("--- doLookUp -- "+doLookUp);
                    if(doLookUp != null && doLookUp.signUpStage !=null &&  doLookUp.signUpStage.equals(SignUpStage.COMPLETED.name()))
                    {
                        // UserLoginRequest ( String channel, long pid,String controlCode, String password,  String code, String codeLink, String clientId
                        UserLoginRequest userLoginRequest = new  UserLoginRequest(doLookUp.channel, doLookUp.tid, doLookUp.controlCode, userLoginObj.password, doLookUp.businessMobileNo, userLoginObj.email, doLookUp.clientId);

                        log.info("-- userLoginRequest -- "+userLoginRequest);


                        ProfileSyncResponse doUserLogin = authService.doUserLogin(userLoginRequest);

                        System.out.println("doUserLogin = " + doUserLogin);
                        
                        return new UserLogResponse(Integer.parseInt(""+doUserLogin.statusHeaders().statusCode), ErrorCodes.doErroDesc(Integer.parseInt(""+doUserLogin.statusHeaders().statusCode)), doUserLogin.bearerToken());
                 


                    }
                    else if(doLookUp != null && doLookUp.signUpStage !=null &&  !doLookUp.signUpStage.equals(SignUpStage.COMPLETED.name()))
                    {

                          return new UserLogResponse(ErrorCodes.TRANSACTION_FORBIDDEN, ErrorCodes.doErroDesc(ErrorCodes.TRANSACTION_FORBIDDEN), null);
                    }
                    else
                    {
                        return new UserLogResponse(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR), null);
                    }
            }
            else
            {
                
               return new UserLogResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR), null);
                         
            }
           
            
        } catch (Exception e) {
            
            
            log.info("Exception @ doLogin ",e);
            
             return new UserLogResponse(ErrorCodes.SYSTEM_ERROR, e.getMessage(), null);
         
        }
    
      //return response;  
    }
    
    
    public ForceSyncResponse doForcePasswordReset(@Valid ForceSyncProfilePasswordRx request) {
        
        UserLogResponse response = null;
        ForceSyncResponse doUserLogin = null;
        try 
        {
            ForceSyncProfilePasswordRxObj userLoginObj = new ForceSyncProfilePasswordRxObj(request);
            
            if(userLoginObj !=null)
            {

                    UserLog doLookUp = userLogRepo.doLookUp(userLoginObj.email, userLoginObj.corporateId);
                    log.info("--- doLookUp -- "+doLookUp);
                    if(doLookUp != null && "YES".equals(doLookUp.initResetPassword))
                    {
                        
                        ForceSyncProfilePasswordRequest forceSyncProfilePasswordRequest = new ForceSyncProfilePasswordRequest(userLoginObj.password, doLookUp.businessMobileNo,doLookUp.userEmail, userLoginObj.verifyPassword, doLookUp.tid, doLookUp.controlCode, doLookUp.businessMobileNo, doLookUp.clientId, doLookUp.channel, userLoginObj.otp);
       
                        System.out.println("--> forceSyncProfilePasswordRequest = " +forceSyncProfilePasswordRequest);
                        
                        doUserLogin = authService.doCompleteForcePasswordChange(forceSyncProfilePasswordRequest);
 
                        if(doUserLogin !=null && doUserLogin.statusHeaders() !=null && doUserLogin.statusHeaders().statusCode == ErrorCodes.SUCCESSFUL)
                        {
                            
                             doLookUp.initResetPassword = "NO";
                             doLookUp.initResetPasswordDate = LocalDateTime.now();
                        
                             UserLog doSyncUser = userLogRepo.doSyncUser(doLookUp);
                           
                             System.out.println("doUserLogin = " + doUserLogin);
              
                        
                        
                        }
                     
                        //ResponseStatusHeaders
                        
                        return new ForceSyncResponse( doUserLogin.statusHeaders());
                 


                    }
                    
            }
            else
            {
                
               return new ForceSyncResponse( new com.hoptool.invoiceme.auth.dto.ResponseStatusHeaders(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR)));
                         
            }
           
            
        } catch (Exception e) {
            
            
             log.info("Exception @ doInitForcePasswordReset ",e);
            
             return new ForceSyncResponse( new com.hoptool.invoiceme.auth.dto.ResponseStatusHeaders(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)));
               
         
        }
    
      return null;  
    }
    
     public ProfileSyncResponse doChangePassword(@Valid ChangeProfilePasswordRequest request) {
        
        UserLogResponse response = null;
        ProfileSyncResponse doChangePassword = null;
        try 
        {
            ChangeProfilePasswordRequestObj userLoginObj = new ChangeProfilePasswordRequestObj(request);
            
            if(userLoginObj !=null)
            {

                    UserLog doLookUp = userLogRepo.doLookUp(userLoginObj.email, userLoginObj.corporateId);
                    log.info("--- doLookUp -- "+doLookUp);
                    if(doLookUp != null )
                    {
                        com.hoptool.invoiceme.auth.dto.ChangeProfilePasswordRequest changePasswordRequest = new com.hoptool.invoiceme.auth.dto.ChangeProfilePasswordRequest(doLookUp.businessMobileNo, doLookUp.userEmail, doLookUp.channel, doLookUp.tid, doLookUp.controlCode, userLoginObj.password, userLoginObj.newPassword, doLookUp.clientId);
   
                        System.out.println("--> chan password = " +changePasswordRequest);
                        
                        
                        doChangePassword = authService.doChangePassword(changePasswordRequest);
 
                        if(doChangePassword !=null && doChangePassword.statusHeaders() !=null && doChangePassword.statusHeaders().statusCode == ErrorCodes.SUCCESSFUL)
                        {
                           
                            return doChangePassword;
                        }
                     
                    }
                    
            }
            else
            {
                
               return  new ProfileSyncResponse(new com.hoptool.invoiceme.auth.dto.ResponseStatusHeaders(ErrorCodes.FORMAT_ERROR,ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR)), 0, "", "", "", 0, "","", "","","","",null,null,null,null,"");
                   
            }
           
            
        } catch (Exception e) {
            
            
             log.info("Exception @ doChangePassword ",e);
            
             //return new ForceSyncResponse( new com.hoptool.invoiceme.auth.dto.ResponseStatusHeaders(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)));
               
         
              return  new ProfileSyncResponse(new com.hoptool.invoiceme.auth.dto.ResponseStatusHeaders(ErrorCodes.SYSTEM_ERROR,ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)), 0, "", "", "", 0, "","", "","","","",null,null,null,null,"");
              
        }
    
      return null;  
    }
    
    public InitForcePasswordChangeResponse doInitForcePasswordReset(@Valid InitForceUserPasswordReset request) {
        
        UserLogResponse response = null;
        InitForcePasswordChangeResponse doUserLogin = null;
        try 
        {
            InitForceUserPasswordResetObj userLoginObj = new InitForceUserPasswordResetObj(request);
            
            if(userLoginObj !=null)
            {

                    UserLog doLookUp = userLogRepo.doLookUp(userLoginObj.email, userLoginObj.corporateId);
                    log.info("--- doLookUp -- "+doLookUp);
                    if(doLookUp != null)
                    {
                        
                         doLookUp.initResetPassword = "YES";
                         doLookUp.initResetPasswordDate = LocalDateTime.now();
                        
                         UserLog doSyncUser = userLogRepo.doSyncUser(doLookUp);
                       
                        if(doSyncUser !=null)
                        {
                           
                            // UserLoginRequest ( String channel, long pid,String controlCode, String password,  String code, String codeLink, String clientId
                           InitForcePasswordReset resetUserRequest = new  InitForcePasswordReset(doLookUp.businessMobileNo,doLookUp.channel, doLookUp.clientId);

                        
                           doUserLogin = authService.doInitForcePasswordChange(resetUserRequest);

                           System.out.println("doUserLogin = " + doUserLogin);
              
                        
                        
                        }
                     
                        
                        return new InitForcePasswordChangeResponse(doUserLogin.status(), doUserLogin.message());
                 


                    }
                    
            }
            else
            {
                
               return new InitForcePasswordChangeResponse(new ResponseStatusHeaders(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR)), ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR));
                         
            }
           
            
        } catch (Exception e) {
            
            
             log.info("Exception @ doInitForcePasswordReset ",e);
             
             return new InitForcePasswordChangeResponse(new ResponseStatusHeaders(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)), e.getMessage());

         
        }
    
      return null;  
    }
    
    
    public FirsLoginResponse doFIRSMBSLogin(@Valid FIRSMBSLogin request) {
        
        FirsLoginResponse doFirsMBSLogin = null;
        try 
        {
           
            if(request !=null)
            {

                   
                 return  invoiceService.doFirsMBSLogin(request);

            }
            else
            {
                        
              return new FirsLoginResponse(ErrorCodes.FORMAT_ERROR, null);
                 
            }
           
           
            
        }
        catch(com.hoptool.exceptions.InvalidRequestException ex)
        {
            System.out.println("ex-- = " + ex.getMessage());
            FirsLoginResponse fromJson = new Gson().fromJson(ex.getMessage().split("#")[1], FirsLoginResponse.class);
            System.out.println("fromJson = " + fromJson);
            log.info("Exception @ InvalidRequestException ",ex);
            
            return fromJson;
        }
        catch (Exception e) {
            
            
             log.info("Exception @ doFIRSMBSLogin ",e);
            
             return new FirsLoginResponse(ErrorCodes.SYSTEM_ERROR, null);
         
        }
    
    }
    
    public UserLogResponse doForcePasswordReset(@Valid UserLogin request) {
        
        UserLogResponse response = null;
        try 
        {
            UserLoginObj userLoginObj = new UserLoginObj(request);
            
            if(userLoginObj !=null && userLoginObj.email !=null && userLoginObj.password !=null && userLoginObj.corporateId !=null)
            {

                    UserLog doLookUp = userLogRepo.doLookUp(userLoginObj.email, userLoginObj.corporateId);
                    log.info("--- doLookUp -- "+doLookUp);
                    if(doLookUp != null && doLookUp.signUpStage !=null &&  doLookUp.signUpStage.equals(SignUpStage.COMPLETED.name()))
                    {
                        // UserLoginRequest ( String channel, long pid,String controlCode, String password,  String code, String codeLink, String clientId
                        UserLoginRequest userLoginRequest = new  UserLoginRequest(doLookUp.channel, doLookUp.tid, doLookUp.controlCode, userLoginObj.password, doLookUp.businessMobileNo, userLoginObj.email, doLookUp.clientId);

                        log.info("-- userLoginRequest -- "+userLoginRequest);


                        ProfileSyncResponse doUserLogin = authService.doUserLogin(userLoginRequest);

                        System.out.println("doUserLogin = " + doUserLogin);
                        
                        return new UserLogResponse(Integer.parseInt(""+doUserLogin.statusHeaders().statusCode), ErrorCodes.doErroDesc(Integer.parseInt(""+doUserLogin.statusHeaders().statusCode)), doUserLogin.bearerToken());
                 


                    }
                    else if(doLookUp != null && doLookUp.signUpStage !=null &&  !doLookUp.signUpStage.equals(SignUpStage.COMPLETED.name()))
                    {

                          return new UserLogResponse(ErrorCodes.TRANSACTION_FORBIDDEN, ErrorCodes.doErroDesc(ErrorCodes.TRANSACTION_FORBIDDEN), null);
                    }
                    else
                    {
                        return new UserLogResponse(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR), null);
                    }
            }
            else
            {
                
               return new UserLogResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR), null);
                         
            }
           
            
        } catch (Exception e) {
            
            
            log.info("Exception @ doLogin ",e);
            
             return new UserLogResponse(ErrorCodes.SYSTEM_ERROR, e.getMessage(), null);
         
        }
    
      //return response;  
    }
    
    
    
    public InitUserCreationResponse doInitOnboarding(@Valid UserSignUpStepOne request) {
        
        InitUserCreationResponse response = null;
        String otp = "";
        String password = "";
        try 
        {
            if(request ==null)
            {
                throw new InvalidRequestException(String.format("Invalid  sign up request {%s} : {%s}",
                            400, request));
            }
            
            if(!request.password().equals(request.verifyPassword()))
            {
                throw new InvalidRequestException(String.format(" Password Mismatch {%s}",
                            400));
            }
            
            UserSignUpStepOneObj userLoginObj = new UserSignUpStepOneObj(request);
            
            UserLog doLookUpByCredentials = userLogRepo.doLookUpByCredentials(userLoginObj);
            
            if(doLookUpByCredentials == null)
            {
                UserLog doLog = userLogRepo.doLog(userLoginObj);
            
                if(doLog != null)
                {
                    
                    
                    String doDemo = sysDataRepo.doLookUpByNameStr("DO-DEMO", "0");
                    
                    if("1".equals(doDemo))
                    {
                       otp = sysDataRepo.doLookUpByNameStr("TEST-PIN", "XXXX");
                    }
                    else
                    {
                      otp = RandomCharacter.doRandomPass(Integer.parseInt(sysDataRepo.doLookUpByNameStr("PIN-LENGTH", "4")));
  
                    }
                    
                    
                    EmailVerificationLog doLog1 = emailValidationRepo.doLog(userLoginObj, otp);
                    
                    if(doLog1 !=null)
                    {
                      
                        return  new InitUserCreationResponse(""+ErrorCodes.ACCEPTED, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL_PENDING_OTP_VERIFICATION));
                    }
                    
                   
                }
                else
                {
                   return new InitUserCreationResponse(""+ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR));
                 
                }
            
            }
            else
            {
                 return new InitUserCreationResponse(""+ErrorCodes.USER_ALREADY_EXIST, ErrorCodes.doErroDesc(ErrorCodes.USER_ALREADY_EXIST));
                 
            }
           
            
        } 
        catch (Exception e) {
            
            
             return new InitUserCreationResponse(""+ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)+" - "+e.getMessage());
                 
        }
    
      return response;  
    }
    
    
    public OTPVerificationResponse doValidateOTP(@Valid OTPValidationRequest request) {
        
        OTPVerificationResponse response = null;
        String otp = "";
        try 
        {
            if(request ==null)
            {
                throw new InvalidRequestException(String.format("Invalid  OTP validation request {%s} : {%s}",
                            400, request));
            }
            
            OTPValidationRequestObj otpVerification = new OTPValidationRequestObj(request);
            
            EmailVerificationLog doLookUpOTP = emailValidationRepo.doLookUpOTP(otpVerification);
            
            if (doLookUpOTP !=null && "ACTIVE".equals(doLookUpOTP.status)) {
                   
               LocalDateTime otpDate = (doLookUpOTP.createDate !=null)?doLookUpOTP.createDate:LocalDateTime.now();// && doLookUpOTP.updatedDate== null)?doVerifyOTP.createdDate:doVerifyOTP.updatedDate;
                                     
               log.info("+ OTP CHECK otpDate  "+otpDate);
                                     
               long until = (otpDate !=null)? otpDate.until(LocalDateTime.now(), ChronoUnit.MINUTES):0;
                
               log.info("OTP TIME DIFF "+until+"  : OTP-TIME-OUT "+sysDataRepo.doLookUpByNameStr("OTP-TIME-OUT", "0"));
                                      // OTP-TIME-OUT
               if(until > Integer.parseInt(sysDataRepo.doLookUpByNameStr("OTP-TIME-OUT", "0")))
               {
                         emailValidationRepo.doSync(doLookUpOTP, OTPStatus.EXPIRED.name());
                   return new OTPVerificationResponse(ErrorCodes.OTP_EXPIRED, ErrorCodes.doErroDesc(ErrorCodes.OTP_EXPIRED));
            
               }
               else
               {
                         emailValidationRepo.doSync(doLookUpOTP, OTPStatus.USED.name());
                         //userLogRepo.doSyncStepChange(doLookUpOTP);
                   return new OTPVerificationResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL));
            
               }
               
            }
            else if (doLookUpOTP !=null && !"ACTIVE".equals(doLookUpOTP.status)) {
                
                return new OTPVerificationResponse(ErrorCodes.OTP_USED, ErrorCodes.doErroDesc(ErrorCodes.OTP_USED));
            
            }
            else
            {
                //emailValidationRepo.doSync(doLookUpOTP, OTPStatus.USED.name());
                return new OTPVerificationResponse(ErrorCodes.OTP_INVALID, ErrorCodes.doErroDesc(ErrorCodes.OTP_INVALID));
            
            }
            
            
        } 
        catch (Exception e) {
            
              log.error("Exception -- ",e);
             return new OTPVerificationResponse(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR));
            
        }
    
      //return response;  
    }
    
    @Transactional
    public OnboardingCompletionResponse doCompleteOnboarding(CompleteBusinessOnboarding request) {
        
        OnboardingCompletionResponse response = null;
        String password = "";
        try 
        {
            
            CompleteBusinessOnboardingObj onboardingObj = new CompleteBusinessOnboardingObj(request);
            
            //verify onboarding stage
            UserLog doVerifyActionStage = userLogRepo.doVerifyActionStage(onboardingObj.companyEmail, onboardingObj.corporateId, SignUpStage.OTP_VERIFICATION.name());
            
            if(doVerifyActionStage != null)
            {
                       doVerifyActionStage.businessLogoUrl = onboardingObj.logoUrl;
                       doVerifyActionStage.firsMBSNumber = onboardingObj.firsMBSNo;
                       doVerifyActionStage.businessNo = onboardingObj.businessNo;
                       doVerifyActionStage.businessMobileNo = onboardingObj.msisdn;
                       doVerifyActionStage.userEmail = onboardingObj.companyEmail;
                       doVerifyActionStage.businessName = onboardingObj.businessName;
                       doVerifyActionStage.verificationType = onboardingObj.businessVerificationType;
                       
                UserLog merge = Panache.getEntityManager().merge(doVerifyActionStage);
                
                if(merge !=null)
                {
                   
                    password = new AESCrypter(sysDataRepo.doLookUpByNameStr("SYS_KEY", "XXX"), sysDataRepo.doLookUpByNameStr("SYS_IV", "")).decrypt(merge.passwordHash);
                    //SyncProfile(String msisdn, String channel, String controlCode, String codeLink, int pid, String verifyPassword, String password, String clientId, String code)
                    SyncProfile syncProfile = new SyncProfile(merge.businessMobileNo, sysDataRepo.doLookUpByNameStr("DEFAULT-AUTH-CHANNEL", "XXX"), sysDataRepo.doLookUpByNameStr("DEFAULT-USER-ROLE", "XXX"), merge.userEmail, merge.tid, password,  password, sysDataRepo.doLookUpByNameStr("AUTH-CLIENT-ID", "UNKNOWN"), merge.businessMobileNo);
                    
                    log.info("-- sync profile request == "+syncProfile);
                    
                    ProfileSyncResponse doSyncProfile = authService.doSyncProfile(syncProfile);
                    
                    log.info("-- doSyncProfile -- "+doSyncProfile);
                    
                    if(doSyncProfile !=null && doSyncProfile.statusHeaders() !=null && doSyncProfile.statusHeaders().statusCode == ErrorCodes.SUCCESSFUL)
                    {
                      
                        UserLog doSyncProfileSyncStatus = userLogRepo.doSyncProfileSyncStatus(merge, doSyncProfile.statusHeaders().statusCode,doSyncProfile.channel(), doSyncProfile.clientId(), doSyncProfile.controlCode());
                        
                        String genCode = eseqRepository.genCode(sysDataRepo.doLookUpByNameStr("BUSSINESS-ID", "XXX"), Integer.parseInt(sysDataRepo.doLookUpByNameStr("BUSSINESS-ID-LEN", "5")));
                        log.info("-- genCode -- "+genCode);
                        BusinessInfo doLog = businessInfoRepo.doLog(doSyncProfileSyncStatus,genCode);
                        log.info("-- BusinessInfo doLog -- "+doLog);
                        log.info(" -- doSyncProfileSyncStatus --"+doSyncProfileSyncStatus);
                        return  new OnboardingCompletionResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL));
                    }
                    else
                    {
                        
                        return  new OnboardingCompletionResponse(Integer.parseInt(""+doSyncProfile.statusHeaders().statusCode), ErrorCodes.doErroDesc(Integer.parseInt(""+doSyncProfile.statusHeaders().statusCode)));
                   
                        
                    }
                    
                    
                }
                else
                {
                    return  new OnboardingCompletionResponse(ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR));
            
                }
                       
            }
            else
            {
                throw new InvalidRequestException(String.format("Prohibited action {%s} : {%s} {%s}",
                            400, request, onboardingObj.toString()));
            }
        
        
        } 
        catch (Exception e) {
            
            
            log.error("Exception -@ doCompleteOnboarding- ", e);
            
            
              return  new OnboardingCompletionResponse(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)+" - "+e.getMessage());
            
        
        }
        
    }
    
    
    



}
