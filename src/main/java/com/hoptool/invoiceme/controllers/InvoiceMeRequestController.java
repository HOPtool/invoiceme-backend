/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.controllers;


import com.google.gson.Gson;
import com.hoptool.eivc.response.dto.DownloadInvoiceClearResponse;
import com.hoptool.eivc.response.dto.DownloadInvoiceResponse;
import com.hoptool.eivc.response.dto.EntityBusinessObject;
import com.hoptool.eivc.response.dto.EntityResponse;
import com.hoptool.eivc.response.dto.IRNGenerationResponse;
import com.hoptool.eivc.response.dto.SignInvoiceResponse;
import com.hoptool.eivc.response.dto.SignInvoiceServiceResponse;
import com.hoptool.eivc.response.dto.UpdatedInvoiceResponse;
import com.hoptool.eivc.response.dto.UpdatednvoiceServiceResponse;
import com.hoptool.entities.IRNGenerationLogs;
import com.hoptool.entities.InvoiceConfirmationLogs;
import com.hoptool.entities.InvoiceDownloadsLogs;
import com.hoptool.entities.InvoiceUpdateLogs;
import com.hoptool.entities.InvoiceValidationRequestLog;
import com.hoptool.entities.SignInvoiceRequestLog;
import com.hoptool.entities.ValidateInvoiceIRNLogs;
import com.hoptool.invoice.dto.EntityRequest;
import com.hoptool.invoice.dto.EntityRequestObj;
import com.hoptool.invoice.dto.InvoiceValidationData;
import com.hoptool.invoice.dto.InvoiceValidationResponse;
import com.hoptool.invoice.dto.SignInvoice;
import com.hoptool.invoice.dto.SignInvoiceObj;
import com.hoptool.invoice.dto.SignInvoiceValidation;
import com.hoptool.invoice.dto.SignInvoiceValidationObj;
import com.hoptool.invoice.dto.UpdateInvoice;
import com.hoptool.invoice.dto.UpdateInvoiceObj;
import com.hoptool.invoice.dto.ValidateIRNRequest;
import com.hoptool.invoice.dto.ValidateIRNRequestObj;
import com.hoptool.invoiceme.repositories.ConfirmInvoiceRepository;
import com.hoptool.invoiceme.repositories.IRNGenerationRepository;
import com.hoptool.invoiceme.repositories.InvoiceDownloadsRepository;
import com.hoptool.invoiceme.repositories.InvoiceValidationRepository;
import com.hoptool.invoiceme.repositories.SignInvoiceRepository;
import com.hoptool.invoiceme.repositories.UpdateInvoiceRepository;
import com.hoptool.invoiceme.repositories.ValidateInvoiceIRNRepository;
import com.hoptool.resources.ErrorCodes;
import com.hoptool.resources.ResourceHelper;
import com.hoptool.service.InvoiceService;
import com.hoptool.service.mongo.MongoService;
import com.hoptool.service.redis.RedisClient;
import com.hoptool.service.redis.RedisConnectors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
@Slf4j
public class InvoiceMeRequestController {
    
    
    
    @Inject
    InvoiceService invoiceService;
    
    
    @Inject
    ValidateInvoiceIRNRepository validateInvoiceIRNRepository;
    
    
    @Inject
    ConfirmInvoiceRepository confirmInvoiceRepository;
    
    @Inject
    InvoiceDownloadsRepository invoiceDownloadRepository;
    
    @Inject
    InvoiceValidationRepository invoiceValidationRepository;
    
    
    @Inject
    UpdateInvoiceRepository updatedInvoiceRepository;
    
    @Inject
    IRNGenerationRepository irnGenerationRepository;
    
    @Inject
    CryptoController cryptoController;
    
    @Inject
    SignInvoiceRepository signInvoiceRepository;
    
    @Inject
    RedisClient redisClient;
    
    @Inject
    MongoService mongo;
    
    @Inject
    RedisConnectors redisConnectors;
    
    
    public SignInvoiceResponse doSignInvoice(SignInvoice request) {
       
        LocalDateTime requestDate = LocalDateTime.now();
        String code = "";
        SignInvoiceObj signInvoiceObj = null;
        InvoiceValidationRequestLog invoiceVerificationCount = null;
        SignInvoiceRequestLog signInvoiceLog = null;
        String toJson = new Gson().toJson(request);
        log.info(" @@-- json--@* "+toJson);
        try 
        {
              signInvoiceObj = new SignInvoiceObj(request); 
       
              SignInvoiceRequestLog doLookUp = signInvoiceRepository.doLookUp(signInvoiceObj); 
              
              log.info("-- doLookUp = " + doLookUp);
              if(doLookUp == null)
              {
                 
                 signInvoiceLog = SignInvoiceRequestLog.doLog(signInvoiceObj);
                 log.info("@@##** resp **##" +  signInvoiceLog);
                 if(signInvoiceLog != null)
                 {
                   
                    //   SignInvoice doFindDocumentx = redisConnectors.getInvoiceObject(request.business_id()+request.irn());//.doFindDocument(toJson, request.business_id()+request.irn());
        
                     redisConnectors.doSave(request.business_id()+request.irn(), toJson);
                     mongo.add(request, requestDate);
                     SignInvoiceServiceResponse signInvoiceResponse = invoiceService.doSignInvoice(request); //.doSignInvoice(request); //.doValidateInvoiceIRN(request);
                     code = ""+signInvoiceResponse.code();
                     log.info(" -- signInvoiceResponse --  "+signInvoiceResponse);
                    
                     return new  SignInvoiceResponse(code, new InvoiceValidationData(code.equals("201")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
         
                 }
                 else
                 {
                     code = ""+ErrorCodes.DATABASE_ERROR;// database error
                   
                   return new  SignInvoiceResponse(code, new InvoiceValidationData(code.equals("201")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
         
                 }
            
           
           }
           else
           {
                 code = ""+ErrorCodes.DUPLICATE_TRANSACTION;
               
                 return new  SignInvoiceResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
         
           
           }
     
       }
       catch (java.sql.SQLSyntaxErrorException | org.hibernate.exception.SQLGrammarException e) {
            e.printStackTrace();
            try 
            {
                     code = ""+ErrorCodes.DATABASE_ERROR;
                    
                     return new  SignInvoiceResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
         
            } 
            catch (Exception ex) {
            
                ex.printStackTrace();
                
            }
         
       }
       catch (Exception e) {
             //e.printStackTrace();
            log.info("---- doSignInvoice doSync = ",e);
            try 
            {
                     code = ""+ErrorCodes.SYSTEM_ERROR;
                    
                     return new  SignInvoiceResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
         
            } 
            catch (Exception ex) {
            
                ex.printStackTrace();
                log.info("---- doSignInvoice doSync = ",e);
                
            }
         
       }
       finally 
        {
            
            try 
            {
                
                 // InvoiceUpdateLogs doLookUpInvoiceUpdateCount = updatedInvoiceRepository.doLookUpInvoiceUpdateCount(request);
                  //log.info("logged = " + logged);
                  SignInvoiceRequestLog doSync = signInvoiceRepository.doSync(signInvoiceObj, Integer.parseInt(code));
                  log.info("---- doSignInvoice doSync = " +  doSync);
               // SignInvoiceRequestLog.doSync(signInvoiceLog, Integer.parseInt(code));
                
                
                //createdDate
            } catch (Exception e) {
                
                e.printStackTrace();
                
                log.info("---- doSignInvoice finally doSync = ",e);
            }
            
        }
    
        return new  SignInvoiceResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
         
       // return new InvoiceValidationResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
    
    }
     
    
    public InvoiceValidationResponse doProcessIRNValidationRequest(ValidateIRNRequest request) {
        log.info("doProcessIRNValidationRequest. request = " + request);
        LocalDateTime requestDate = LocalDateTime.now();
        String code = "";
        ValidateIRNRequestObj validateIRNRequestObj = null;
        ValidateInvoiceIRNLogs invoiceValidationCount = null;
        ValidateInvoiceIRNLogs logged = null;
        try 
        {
            validateIRNRequestObj = new ValidateIRNRequestObj(request); 
       
            ValidateInvoiceIRNLogs doLookUp = validateInvoiceIRNRepository.doLookUp(validateIRNRequestObj);
            log.info("--doLookUp = " + doLookUp);
            if(doLookUp == null)
            {//IRNValidationLogs.doLog(request);// 
                // invoiceValidationCount = validateInvoiceIRNRepository.doLookUpInvoiceValidationCount(validateIRNRequestObj);//.doLookUpInvoiceValidationCount(validateIRNRequestObj);
                 
                 logged = validateInvoiceIRNRepository.doLog(validateIRNRequestObj, requestDate);
                 log.info("** resp ** " +  logged);
                 if(logged != null)
                 {
                     InvoiceValidationResponse doValidateInvoiceIRN = invoiceService.doValidateInvoiceIRN(request);
                     code = doValidateInvoiceIRN.code();
                     log.info(" -- doProcessIRNValidationRequest --  "+doValidateInvoiceIRN);
                     
                     return doValidateInvoiceIRN;
                 }
                 else
                 {
                    code = ""+ErrorCodes.DATABASE_ERROR;// database error
                    return new InvoiceValidationResponse(""+code, new InvoiceValidationData(false), ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" "+request.request_id());
           
                 }
            
            
            }
            else
            {
                code = ""+ErrorCodes.DUPLICATE_TRANSACTION;
                return new InvoiceValidationResponse(code, new InvoiceValidationData(false),  ErrorCodes.doErroDesc(ErrorCodes.DUPLICATE_TRANSACTION)+" "+request.request_id());
            }
     
       } 
       catch (Exception e) {
           // e.printStackTrace();
            
            log.info("---- doProcessIRNValidationRequest doSync = ",e);
            try 
            {
                     code = ""+ErrorCodes.SYSTEM_ERROR;
                     return new InvoiceValidationResponse("500", new InvoiceValidationData(false), ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)+" - "+request.request_id()+e.getMessage());
      
            } 
            catch (Exception ex) {
            
                ex.printStackTrace();
                
                log.info("---- doProcessIRNValidationRequest Exception = ",e);
                
            }
        
       }
       finally 
        {
            
            try 
            {
                
                  invoiceValidationCount = validateInvoiceIRNRepository.doLookUpInvoiceValidationCount(validateIRNRequestObj);
                  log.info("logged = " + logged);
                  ValidateInvoiceIRNLogs doSync = validateInvoiceIRNRepository.doSync(validateIRNRequestObj, Integer.parseInt(code),invoiceValidationCount);
                  log.info("---- doProcessIRNValidationRequest doSync = " +  doSync);

               
                
            } catch (Exception e) {
                
                e.printStackTrace();
                
                log.info("---- doProcessIRNValidationRequest doSync = ",e);
              
            }
            
        }
    
        return new InvoiceValidationResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
     
      
    }
    
    
    public InvoiceValidationResponse doProcessConfirmIRNRequest(ValidateIRNRequest request) {
        log.info("doProcessConfirmIRNRequest. request = " + request);
        LocalDateTime requestDate = LocalDateTime.now();
        String code = "";
        ValidateIRNRequestObj validateIRNRequestObj = null;
        InvoiceConfirmationLogs doLookUpInvoiceConfirmationCount =  null;
        InvoiceConfirmationLogs logged = null;
        try 
        {
            //
            validateIRNRequestObj = new ValidateIRNRequestObj(request); 
       
              InvoiceConfirmationLogs doLookUp = confirmInvoiceRepository.doLookUp(validateIRNRequestObj);
              log.info("doLookUp = " + doLookUp);
              if(doLookUp == null)
              {
                 //IRNValidationLogs.doLog(request);//
                 logged = confirmInvoiceRepository.doLog(validateIRNRequestObj, requestDate);
                 log.info("** resp ** " +  logged);
                 if(logged != null)
                 {
                      
                     System.out.println("logged = " + logged);
                     InvoiceValidationResponse doValidateInvoiceIRN = invoiceService.doValidateInvoiceIRN(request);
                     code = doValidateInvoiceIRN.code();
                     log.info(" -- doProcessIRNValidationRequest --  "+doValidateInvoiceIRN);
                     //validateIRNRepository.doSync(request, Integer.parseInt(doValidateInvoiceIRN.code()));
                     return doValidateInvoiceIRN;
                 }
                 else
                 {
                    code = ""+ErrorCodes.DATABASE_ERROR;// database error
                    return new InvoiceValidationResponse(""+code, new InvoiceValidationData(false), ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" "+request.request_id());
           
                 }
            
           
           }
           else
           {
                 code = ""+ErrorCodes.DUPLICATE_TRANSACTION;
                 return new InvoiceValidationResponse(code, new InvoiceValidationData(false),  ErrorCodes.doErroDesc(ErrorCodes.DUPLICATE_TRANSACTION)+" "+request.request_id());
           }
     
       } 
       catch (Exception e) {
             //e.printStackTrace();
             
            log.info("--Exception -- doProcessConfirmIRNRequest in Exception @  ",e);
            try 
            {
                     code = ""+ErrorCodes.SYSTEM_ERROR;
                     return new InvoiceValidationResponse("500", new InvoiceValidationData(false), ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)+" - "+request.request_id()+e.getMessage());
      
            } 
            catch (Exception ex) {
            
                ex.printStackTrace();
                
                log.info("--Exception -- doProcessConfirmIRNRequest in Exception @  ",e);
                
            }
         
       }
       finally 
        {
            
            try 
            {
                  doLookUpInvoiceConfirmationCount = confirmInvoiceRepository.doLookUpInvoiceConfirmationCount(validateIRNRequestObj);
                  log.info("logged = " + logged);
                  InvoiceConfirmationLogs doSync = confirmInvoiceRepository.doSync(validateIRNRequestObj, Integer.parseInt(code),doLookUpInvoiceConfirmationCount);
                  log.info("---- InvoiceConfirmationLogs doSync = " +  doSync);
//                if(Integer.parseInt(code) == 200)
//                {
//                    //nvoiceDownloadsLogs.doSyncDownloadCount(invoiceDownloadCount); invoiceVerificationCount
//                    confirmInvoiceRepository.doSyncConfirmationCount(doLookUpInvoiceConfirmationCount, logged);
//                   
//                            
//                }
                
            } catch (Exception e) {
                
                e.printStackTrace();
                
                log.info("--Exception -- doProcessConfirmIRNRequest in Exception @  ",e);
            }
            
        }
    
        return new InvoiceValidationResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
     
    }
    
    public InvoiceValidationResponse doProcessInvoiceValidationRequest(SignInvoiceValidation request) {
        log.info("doProcessInvoiceValidationRequest. request = " + request);
        LocalDateTime requestDate = LocalDateTime.now();
        String code = "";
        SignInvoiceValidationObj signInvoiceObj = null;
        InvoiceValidationRequestLog invoiceVerificationCount = null;
        InvoiceValidationRequestLog logged = null;
        try 
        {
              signInvoiceObj = new SignInvoiceValidationObj(request); 
       
              InvoiceValidationRequestLog doLookUp = invoiceValidationRepository.doLookUpValidationInvoiceRequest(signInvoiceObj);
              
              log.info("doLookUp = " + doLookUp);
              if(doLookUp == null)
              {//IRNValidationLogs.doLog(request);// SignInvoiceValidation
                 
                  logged = invoiceValidationRepository.doLog(signInvoiceObj);
                 log.info("** resp  logged ** " +  logged);
                 if(logged != null)
                 {
                     InvoiceValidationResponse doValidateInvoice = invoiceService.doValidateInvoice(request); //.doSignInvoice(request); //.doValidateInvoiceIRN(request);
                     code = ""+doValidateInvoice.code();
                     log.info(" -- InvoiceValidationRequestLog --  "+doValidateInvoice);
                    
                     return new  InvoiceValidationResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
         
                 }
                 else
                 {
                     code = ""+ErrorCodes.DATABASE_ERROR;// database error
                   
                    return new  InvoiceValidationResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
         
                 }
            
           
           }
           else
           {
                 code = ""+ErrorCodes.DUPLICATE_TRANSACTION;
                // return new SignInvoiceResponse(Integer.parseInt(code), new InvoiceValidationData(false),  ErrorCodes.doErroDesc(ErrorCodes.DUPLICATE_TRANSACTION)+" "+request.irn());
           
                 return new  InvoiceValidationResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
           }
     
       } 
       catch (Exception e) {
            e.printStackTrace();
            
             log.info("--Exception -- InvoiceValidationLogs in Exception @  ",e);
            try 
            {
                     code = ""+ErrorCodes.SYSTEM_ERROR;
                     
                     //return new SignInvoiceResponse(Integer.parseInt("500"), new InvoiceValidationData(false), ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)+" - "+request.irn()+e.getMessage());
      
                     return new  InvoiceValidationResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
         
            } 
            catch (Exception ex) {
            
                ex.printStackTrace();
                
            }
         
       }
       finally 
        {
            
            try 
            {
                  invoiceVerificationCount = InvoiceValidationRequestLog.doLookUpInvoiceValidationCount(signInvoiceObj);
                  log.info("logged = " + logged);
                  InvoiceValidationRequestLog doSync = invoiceValidationRepository.doSync(signInvoiceObj, Integer.parseInt(code),invoiceVerificationCount);
                  log.info("---- InvoiceValidationLogs doSync = " +  doSync);
                //createdDate
            } catch (Exception e) {
                
                e.printStackTrace();
                
                 log.info("--Exception -- InvoiceValidationLogs in finally @ Inner Catch ",e);
            }
            
        }
    
        return new InvoiceValidationResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
     
      
    }
    
    /*
     public SignInvoiceResponse doProcessInvoiceValidationRequest(SignInvoice request) {
        log.info("doProcessInvoiceValidationRequest. request = " + request);
        LocalDateTime requestDate = LocalDateTime.now();
        String code = "";
        SignInvoiceObj signInvoiceObj = null;
        try 
        {
              signInvoiceObj = new SignInvoiceObj(request); 
       
              InvoiceConfirmationLogs doLookUp = null;// confirmInvoiceRepository.doLookUp(signInvoiceObj);
              
              log.info("doLookUp = " + doLookUp);
              if(doLookUp == null)
              {//IRNValidationLogs.doLog(request);// 
                 InvoiceConfirmationLogs resp = null;// confirmInvoiceRepository.doLog(validateIRNRequestObj, requestDate);
                 log.info("** resp ** " +  resp);
                 if(resp != null)
                 {
                     SignInvoiceServiceResponse doSignInvoice = invoiceService.doSignInvoice(request); //.doValidateInvoiceIRN(request);
                     code = ""+doSignInvoice.code();
                     log.info(" -- doProcessIRNValidationRequest --  "+doSignInvoice);
                     //validateIRNRepository.doSync(request, Integer.parseInt(doValidateInvoiceIRN.code()));
                     return doSignInvoice;
                 }
                 else
                 {
                     code = ""+ErrorCodes.DATABASE_ERROR;// database error
                    // validateIRNRepository.doSync(request, Integer.parseInt("905"));
                    return new SignInvoiceResponse(""+code, new InvoiceValidationData(false), ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" "+request.irn());
           
                 }
            
           
           }
           else
           {
                 code = ""+ErrorCodes.DUPLICATE_TRANSACTION;
                 return new SignInvoiceResponse(code, new InvoiceValidationData(false),  ErrorCodes.doErroDesc(ErrorCodes.DUPLICATE_TRANSACTION)+" "+request.request_id());
           }
     
       } 
       catch (Exception e) {
             e.printStackTrace();
            try 
            {
                     code = ""+ErrorCodes.SYSTEM_ERROR;
                     return new SignInvoiceResponse("500", new InvoiceValidationData(false), ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)+" - "+request.request_id()+e.getMessage());
      
            } 
            catch (Exception ex) {
            
                ex.printStackTrace();
                
            }
         
       }
       finally 
        {
            
            try 
            {
                InvoiceConfirmationLogs.doSync(validateIRNRequestObj, Integer.parseInt(code));
                
            } catch (Exception e) {
                
                e.printStackTrace();
            }
            
        }
    
        return new InvoiceValidationResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
     
      
    }
    */
    
    public DownloadInvoiceClearResponse doDownloadInvoice(ValidateIRNRequest request) {
        log.info("doDownloadInvoice. request = " + request);
        LocalDateTime requestDate = LocalDateTime.now();
        String code = "";
        ValidateIRNRequestObj downlodInvoiceObjObj = null;
        InvoiceDownloadsLogs invoiceDownloadCount = null;
        DownloadInvoiceResponse doValidateInvoiceIRN = null;
        String decrypt = null;
        try 
        {
              downlodInvoiceObjObj = new ValidateIRNRequestObj(request); 
       
              InvoiceDownloadsLogs doLookUp = invoiceDownloadRepository.doLookUp(downlodInvoiceObjObj);
              
              log.info("doLookUp = " + doLookUp);
              if(doLookUp == null)
              {
                 invoiceDownloadCount = InvoiceDownloadsLogs.doLookUpInvoiceDownload(downlodInvoiceObjObj);
                 
                 InvoiceDownloadsLogs resp = invoiceDownloadRepository.doLog(downlodInvoiceObjObj, requestDate);
                 log.info("** resp ** " +  resp);
                 if(resp != null)
                 {
                     
                     doValidateInvoiceIRN = invoiceService.doDownloadInvoice(request);
                     
                     code = (doValidateInvoiceIRN !=null)?doValidateInvoiceIRN.code():"500";
                     log.info(" -- doProcessIRNValidationRequest --  "+doValidateInvoiceIRN);
                     //return doValidateInvoiceIRN;
                 }
                 else
                 {
                     code = ""+ErrorCodes.DATABASE_ERROR;// database error
                    
                    return new DownloadInvoiceClearResponse(""+code, null, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" "+request.request_id());
           
                 }
            
           
           }
           else
           {
                 code = ""+ErrorCodes.DUPLICATE_TRANSACTION;
                 return new DownloadInvoiceClearResponse(code, null,  ErrorCodes.doErroDesc(ErrorCodes.DUPLICATE_TRANSACTION)+" "+request.request_id());
           }
     
       } 
       catch (Exception e) {
             e.printStackTrace();
            try 
            {
                     code = ""+ErrorCodes.SYSTEM_ERROR;
                     return new DownloadInvoiceClearResponse("500", null, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)+" - "+request.request_id()+e.getMessage());
      
            } 
            catch (Exception ex) {
            
                ex.printStackTrace();
                
            }
         
       }
       finally 
        {
            
            try 
            {
                InvoiceDownloadsLogs doLookUpInvoiceDownloadCount = invoiceDownloadRepository.doLookUpInvoiceDownloadCount(downlodInvoiceObjObj);
                 
                InvoiceDownloadsLogs doSync = invoiceDownloadRepository.doSync(downlodInvoiceObjObj, Integer.parseInt(code), doLookUpInvoiceDownloadCount);
                
                log.info("doSync = " + doSync);
                
            } catch (Exception e) {
                
                e.printStackTrace();
                
                 log.info("--Exception -- doDownloadInvoice in finally @ Inner Catch ",e);
            }
            
        }
    
        try 
        {
            
       
            if(doValidateInvoiceIRN !=null && "200".equals(doValidateInvoiceIRN.code()))
            {
                    decrypt = cryptoController.decrypt(doValidateInvoiceIRN.data().pub(), doValidateInvoiceIRN.data().iv_hex().getBytes(), doValidateInvoiceIRN.data().data());
            }
            else
            {
                   decrypt = "NO-DATA";
            }
        
            
        } catch (Exception e) {
        
            
            e.printStackTrace();
            
             log.info("--Exception -- doDownloadInvoice in finally @ Inner Catch ",e);
        
        }
                
                
        return new DownloadInvoiceClearResponse(code, decrypt ,ErrorCodes.doErroDesc(Integer.parseInt(code)));
     
      
    }
    
    
    
    public UpdatedInvoiceResponse doUpdateInvoice(UpdateInvoice request) {
        log.info("doUpdateInvoice. request = " + request);
        LocalDateTime requestDate = LocalDateTime.now();
        String code = "";
        UpdateInvoiceObj updateInvoiceRequestObj = null;
        InvoiceDownloadsLogs invoiceDownloadCount = null;
        InvoiceUpdateLogs logged = null;
        try 
        {
            updateInvoiceRequestObj = new UpdateInvoiceObj(request); 
       
            InvoiceUpdateLogs doLookUpByRequestId = updatedInvoiceRepository.doLookUpByRequestId(updateInvoiceRequestObj);
              
              log.info("doLookUp = " + doLookUpByRequestId);
              if(doLookUpByRequestId == null)
              {
                 
                 logged = updatedInvoiceRepository.doLog(updateInvoiceRequestObj, requestDate);//.doLog(validateIRNRequestObj, requestDate);
                 log.info("** resp ** " +  logged);
                 if(logged != null)
                 {
                     
                     UpdatednvoiceServiceResponse doUpdateInvoice = invoiceService.doUpdateInvoice(request);
                     code = doUpdateInvoice.code();
                     log.info(" -- doProcessIRNValidationRequest --  "+doUpdateInvoice);
                     //validateIRNRepository.doSync(request, Integer.parseInt(doValidateInvoiceIRN.code()));
                     return new UpdatedInvoiceResponse(""+code, doUpdateInvoice.data(), ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" "+request.requestId());
            
                 }
                 else
                 {
                     code = ""+ErrorCodes.DATABASE_ERROR;// database error
                    // validateIRNRepository.doSync(request, Integer.parseInt("905"));
                    return new UpdatedInvoiceResponse(""+code, new InvoiceValidationData(false), ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" "+request.requestId());
           
                 }
            
           
           }
           else
           {
                 code = ""+ErrorCodes.DUPLICATE_TRANSACTION;
                 return new UpdatedInvoiceResponse(code, new InvoiceValidationData(false),  ErrorCodes.doErroDesc(ErrorCodes.DUPLICATE_TRANSACTION)+" "+request.requestId());
           }
     
       } 
       catch (Exception e) {
             e.printStackTrace();
            try 
            {
                     code = ""+ErrorCodes.SYSTEM_ERROR;
                     return new UpdatedInvoiceResponse("500", new InvoiceValidationData(false), ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)+" - "+request.requestId()+e.getMessage());
      
            } 
            catch (Exception ex) {
            
                ex.printStackTrace();
                 log.info("--Exception -- doUpdateInvoice in finally @ Inner Catch ",e);
                
            }
         
       }
       finally 
        {
            
            try 
            {
                
                  InvoiceUpdateLogs doLookUpInvoiceUpdateCount = updatedInvoiceRepository.doLookUpInvoiceUpdateCount(request);
                  log.info("logged = " + logged);
                  InvoiceUpdateLogs doSync = updatedInvoiceRepository.doSync(updateInvoiceRequestObj, Integer.parseInt(code),doLookUpInvoiceUpdateCount);
                  log.info("---- ValidateInvoiceIRNLogs doSync = " +  doSync);

                
            } catch (Exception e) {
                
                e.printStackTrace();
                
                 log.info("--Exception -- doUpdateInvoice in finally @ Inner Catch ",e);
            }
            
        }
    
    return new UpdatedInvoiceResponse(code, new InvoiceValidationData(code.equals("200")?true:false),ErrorCodes.doErroDesc(Integer.parseInt(code)));
     
      
    }
    
    public IRNGenerationResponse doGenerateIRN(EntityRequest request) {
        log.info("@ doGenerateIRN. request = " + request);
        LocalDateTime requestDate = LocalDateTime.now();
        int code = 0;
        EntityRequestObj generateIRN = null;
        IRNGenerationLogs logged = null;
        String doGenerateIRN = "";
        EntityResponse entityResponse = null;
        String irn = "";
        try 
        {
              generateIRN = new EntityRequestObj(request); 
       
              IRNGenerationLogs doLookUpIRNGenerationRequest = irnGenerationRepository.doLookUpIRNGenerationRequest(generateIRN);
              
              log.info("@ doLookUp = " + doLookUpIRNGenerationRequest);
              if(doLookUpIRNGenerationRequest == null)
              {
                 
                 logged = irnGenerationRepository.doLog(generateIRN);
                 log.info("** resp ** " +  logged);
                 if(logged != null)
                 {
                     
                     entityResponse = invoiceService.doPullEntity(generateIRN);
                     code = entityResponse.code();
                     
                     EntityBusinessObject entityObi = (entityResponse.data().businesses() !=null && entityResponse.data().businesses() .size() > 0)?entityResponse.data().businesses().get(0): null;
                     
                     String irnTemp = (entityObi !=null)? entityObi.irn_template():"0000-XXXX-0000";
                     
                     doGenerateIRN = ResourceHelper.doGenerateIRN(generateIRN.invoiceId, irnTemp);
                     
                     log.info(" -- doProcessIRNValidationRequest --  "+entityResponse);
                     
                 }
                 else
                 {
                     code = ErrorCodes.DATABASE_ERROR;// database error
                   
                    return new IRNGenerationResponse(code, "NA", ErrorCodes.doErroDesc(code)+" "+request.requestId());
                 }
            
           
           }
           else
           {
                 code = ErrorCodes.DUPLICATE_TRANSACTION;
                
                 return new IRNGenerationResponse(code, "NA", ErrorCodes.doErroDesc(code)+" "+request.requestId());
                
           
           }
     
       } 
       catch (Exception e) {
             //e.printStackTrace();
            try 
            {
                      log.info("--Exception -- doGenerateIRN @ ",e);
                      code = ErrorCodes.SYSTEM_ERROR;
                    
                      return new IRNGenerationResponse(code, "NA", ErrorCodes.doErroDesc(code)+" "+request.requestId());
                
            } 
            catch (Exception ex) {
            
                ex.printStackTrace();
                
                log.info("--Exception -- doGenerateIRN @ Inner Catch ",e);
                
            }
         
       }
       finally 
        {
            
            try 
            {
              
                  log.info("logged = " + logged);
                  IRNGenerationLogs doSync = irnGenerationRepository.doSync(generateIRN ,code, entityResponse, doGenerateIRN);
                  log.info("---- IRNGenerationLogs doSync = " +  doSync);

                
            } catch (Exception e) {
                
                e.printStackTrace();
                
                 log.info("--Exception -- doGenerateIRN in finally @ Inner Catch ",e);
            }
            
        }
    
       return new IRNGenerationResponse(code, doGenerateIRN,ErrorCodes.doErroDesc(code));
     
      
    }
 
   
   
}
