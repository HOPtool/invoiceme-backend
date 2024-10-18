/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.service;

import com.hoptool.exceptions.InvalidRequestException;
import com.hoptool.exceptions.ProcessingException;
import com.hoptool.invoiceme.auth.dto.ForceSyncProfilePasswordRequest;
import com.hoptool.invoiceme.auth.dto.ForceSyncProfilePasswordRequestObj;
import com.hoptool.invoiceme.auth.dto.ForceSyncResponse;
import com.hoptool.invoiceme.auth.dto.ProfileSyncResponse;
import com.hoptool.invoiceme.auth.dto.ResetKeysRequest;
import com.hoptool.invoiceme.auth.dto.ResetRequest;
import com.hoptool.invoiceme.auth.dto.ResetResponse;
import com.hoptool.invoiceme.auth.dto.SyncProfile;
import com.hoptool.invoiceme.auth.dto.SyncProfileObj;
import com.hoptool.invoiceme.auth.dto.SysLoginRequest;
import com.hoptool.invoiceme.auth.dto.SysLoginRequestObj;
import com.hoptool.invoiceme.auth.dto.SysLoginResponse;
import com.hoptool.invoiceme.auth.dto.UserLoginRequest;
import com.hoptool.invoiceme.auth.dto.UserLoginRequestObj;
import com.hoptool.invoiceme.dto.InitForcePasswordChangeResponse;
import com.hoptool.invoiceme.dto.InitForcePasswordReset;
import com.hoptool.invoiceme.dto.InitForcePasswordResetObj;
import com.hoptool.resources.ResourceHelper;
import com.hoptool.service.mongo.MongoService;
import com.hoptool.service.redis.RedisClient;
import com.hoptool.service.redis.RedisConnectors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author taysayshaguy
 */

@ApplicationScoped
@Slf4j
public class AuthService {
    
    
    @ConfigProperty(name = "auth.service.base.url")
    String authServiceBaseUrl;
    
//    @ConfigProperty(name = "firs.eivc.api.key")
//    String firstEIVCAPIKey;
//    
//    @ConfigProperty(name = "firs.eivc.api.secret")
//    String firstEIVCAPISecret;
    
    @Context
    private UriInfo uriInfo;
   
    ResourceHelper rh = new ResourceHelper();
    
    @Inject
    RedisClient redisClient;
    
    @Inject
    MongoService mongo;
    
    @Inject
    RedisConnectors redisConnectors;
    
    public @NotNull ProfileSyncResponse doSyncProfile(SyncProfile request) {
        log.info("-- ProfileSyncResponse --"+request);
        ProfileSyncResponse requestResponse;
          
        try (var client = ClientBuilder.newClient()) {
           
            SyncProfileObj irnDTO = new SyncProfileObj(request);
            log.info("@@--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/sync-profile",authServiceBaseUrl));
            var requestBuilder = target.request();
            //requestBuilder.header("x-api-key", firstEIVCAPIKey);
            //requestBuilder.header("x-api-secret", firstEIVCAPISecret); 

            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new SyncProfileObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid  sync profile response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid sync profile {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid sync profile  exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while  syncing profile {%s}  {%s} : {%s}",
                            request.msisdn(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(ProfileSyncResponse.class);
        }

        return  requestResponse;//new ProfileSyncResponse(requestResponse.code(), requestResponse.data(), (requestResponse.code() !=null && requestResponse.code().equals("200")|| requestResponse.equals("202")?"Successful":"Error"));
    }
    
    
    
     public @NotNull ProfileSyncResponse doUserLogin(UserLoginRequest request) {
        log.info("-- ProfileSyncResponse --"+request);
        ProfileSyncResponse requestResponse;
          
        try (var client = ClientBuilder.newClient()) {
           
            UserLoginRequestObj irnDTO = new UserLoginRequestObj(request);
            log.info("@@--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/verify-user",authServiceBaseUrl));
            var requestBuilder = target.request();
            //requestBuilder.header("x-api-key", firstEIVCAPIKey);
            //requestBuilder.header("x-api-secret", firstEIVCAPISecret); 

            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new UserLoginRequestObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid  sync profile response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid sync profile {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid sync profile  exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while  syncing profile {%s}  {%s} : {%s}",
                            request.code(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(ProfileSyncResponse.class);
        }

        return  requestResponse;//new ProfileSyncResponse(requestResponse.code(), requestResponse.data(), (requestResponse.code() !=null && requestResponse.code().equals("200")|| requestResponse.equals("202")?"Successful":"Error"));
    }
     
    public @NotNull InitForcePasswordChangeResponse doInitForcePasswordChange(InitForcePasswordReset request) {
        log.info("-- ProfileSyncResponse --"+request);
        InitForcePasswordChangeResponse requestResponse;
          
        try (var client = ClientBuilder.newClient()) {
           
            InitForcePasswordResetObj irnDTO = new InitForcePasswordResetObj(request);
            log.info("@@--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/init-force-password-change",authServiceBaseUrl));
            var requestBuilder = target.request();
            
            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new InitForcePasswordResetObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid  force password change response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid force password change {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid force password change exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while  force password change {%s}  {%s} : {%s}",
                            request.code(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(InitForcePasswordChangeResponse.class);
        }

        return  requestResponse;//new ProfileSyncResponse(requestResponse.code(), requestResponse.data(), (requestResponse.code() !=null && requestResponse.code().equals("200")|| requestResponse.equals("202")?"Successful":"Error"));
    }
    
    
    
    public @NotNull ForceSyncResponse doCompleteForcePasswordChange(ForceSyncProfilePasswordRequest request) {
        log.info("-- doCompleteForcePasswordChange --"+request);
        ForceSyncResponse requestResponse;
          
        try (var client = ClientBuilder.newClient()) {
           
            ForceSyncProfilePasswordRequestObj irnDTO = new ForceSyncProfilePasswordRequestObj(request);
            log.info("@@--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/force-sync-password",authServiceBaseUrl));
            var requestBuilder = target.request();
            
            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new ForceSyncProfilePasswordRequestObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid  force password change response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid force password change {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid force password change exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while  force password change {%s}  {%s} : {%s}",
                            request.code(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(ForceSyncResponse.class);
        }

        return  requestResponse;//new ProfileSyncResponse(requestResponse.code(), requestResponse.data(), (requestResponse.code() !=null && requestResponse.code().equals("200")|| requestResponse.equals("202")?"Successful":"Error"));
    }
    
    
    
    public @NotNull SysLoginResponse doSysLogin(SysLoginRequest request) {
     
        SysLoginResponse requestResponse;
          
        try (var client = ClientBuilder.newClient()) {
           
            SysLoginRequestObj irnDTO = new SysLoginRequestObj(request);
            log.info("--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/sys-login",authServiceBaseUrl));
            var requestBuilder = target.request();
            
            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new SysLoginRequestObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid sys login response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid sys login request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid validation  exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while sys login {%s}  {%s} : {%s}",
                            request.ux(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(SysLoginResponse.class);
        
        }

        return requestResponse;//new SysLoginResponse(requestResponse.code(), requestResponse.data(), (requestResponse.code() !=null && requestResponse.code().equals("200")|| requestResponse.equals("202")?"Successful":"Error"));
    }
    
    public @NotNull ResetResponse doResend(ResetKeysRequest request) {
     
        ResetResponse requestResponse;
          
        try (var client = ClientBuilder.newClient()) {
           
            ResetRequest irnDTO = new ResetRequest(request);
            log.info("--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/resend",authServiceBaseUrl));
            var requestBuilder = target.request();
            
            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new ResetRequest(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid resend response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid resend request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid resend  exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while sys login {%s}  {%s} : {%s}",
                            request.ux(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(ResetResponse.class);
        
        }

        return requestResponse;//new SysLoginResponse(requestResponse.code(), requestResponse.data(), (requestResponse.code() !=null && requestResponse.code().equals("200")|| requestResponse.equals("202")?"Successful":"Error"));
    }
    
    
    public @NotNull ResetResponse doReset(ResetKeysRequest request) {
     
        ResetResponse requestResponse;
          
        try (var client = ClientBuilder.newClient()) {
           
            ResetRequest irnDTO = new ResetRequest(request);
            log.info("--reset irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/reset",authServiceBaseUrl));
            var requestBuilder = target.request();
            
            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new ResetRequest(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid reset response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid reset request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid reset  exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while sys login {%s}  {%s} : {%s}",
                            request.ux(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(ResetResponse.class);
        
        }

        return requestResponse;//new SysLoginResponse(requestResponse.code(), requestResponse.data(), (requestResponse.code() !=null && requestResponse.code().equals("200")|| requestResponse.equals("202")?"Successful":"Error"));
    }
    
    
    
    //
     /*
     
    
    
    public @NotNull InvoiceValidationResponse doConfirmIRN(ValidateIRNRequest request) {
     
        InvoiceValidationResponse requestResponse;
          
        try (var client = ClientBuilder.newClient()) {
           
            ValidateIRNRequestObj irnDTO = new ValidateIRNRequestObj(request);
            log.info("--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/api/v1/invoice/confirm/%s",firstEIVCBaseUrl, irnDTO.irn));
            var requestBuilder = target.request();
            requestBuilder.header("x-api-key", firstEIVCAPIKey);
            requestBuilder.header("x-api-secret", firstEIVCAPISecret); 

            var httpResponse = requestBuilder.get();//.post(jakarta.ws.rs.client.Entity.json(new ValidateIRNRequestObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid bvn retrieval response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid doValidateInvoiceIRN request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Bvn retrieval request exception {} {}", httpResponse.getStatus(), body);
                    log.info(" -- error details "+new ErrorDataObj(body));
                    throw new ProcessingException(String.format("Error occurred while validating the invoice with irn {%s}  {%s} : {%s}",
                            request.irn(),httpResponse.getStatus(), new ErrorDataObj(body).error.public_message()));
                }
            }

            requestResponse = httpResponse.readEntity(InvoiceValidationResponse.class);
        }

        return requestResponse;
    }
    
    public @NotNull DownloadInvoiceResponse doDownloadInvoice(ValidateIRNRequest request) {
     
        DownloadInvoiceResponse requestResponse =null;
        String downloaded = "";
          
        try (var client = ClientBuilder.newClient()) {
           
            ValidateIRNRequestObj irnDTO = new ValidateIRNRequestObj(request);
            log.info("--download-- "+irnDTO);
            var target = client.target(String.format("%s/api/v1/invoice/download/%s",firstEIVCBaseUrl, irnDTO.irn));
            var requestBuilder = target.request();
            requestBuilder.header("x-api-key", firstEIVCAPIKey);
            requestBuilder.header("x-api-secret", firstEIVCAPISecret); 

            var httpResponse = requestBuilder.get();//.post(jakarta.ws.rs.client.Entity.json(new ValidateIRNRequestObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid download response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid download invoice request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid download request exception {} {}", httpResponse.getStatus(), body);
                    log.info(" -- error details "+new ErrorDataObj(body));
                    throw new ProcessingException(String.format("Error occurred while downloding the invoice with irn {%s}  {%s} : {%s}",
                            request.irn(),httpResponse.getStatus(), new ErrorDataObj(body).error.public_message()));
                }
            }
           requestResponse = httpResponse.readEntity(DownloadInvoiceResponse.class);
            //log.info("-***- readEntity -- "+readEntity);
            //downloaded = httpResponse.readEntity(String.class);
            log.info("-- downloaded -- "+downloaded);
        }

        return requestResponse;
    }
    
    public @NotNull InvoiceValidationResponse doSearchInvoice(ValidateIRNRequest request) {
     
        InvoiceValidationResponse requestResponse = null;
          
        try (var client = ClientBuilder.newClient()) {
           
            ValidateIRNRequestObj irnDTO = new ValidateIRNRequestObj(request);
            log.info("@@--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/api/v1/invoice/%s",firstEIVCBaseUrl, irnDTO.business_id));
            var requestBuilder = target.request();
            requestBuilder.header("x-api-key", firstEIVCAPIKey);
            requestBuilder.header("x-api-secret", firstEIVCAPISecret); 
            
            log.info("--key --  "+firstEIVCAPIKey);
            log.info("--secret --  "+firstEIVCAPISecret);

            var httpResponse = requestBuilder.get();//.post(jakarta.ws.rs.client.Entity.json(new ValidateIRNRequestObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid Search invoice response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid search invoice request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.info("--  error public message "+new ErrorDataObj(body).error.public_message());
                    log.warn("search invoice request exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while searching the invoices with business id {%s}  {%s} : {%s}",
                            request.business_id(),httpResponse.getStatus(), new ErrorDataObj(body).error.public_message()));
                }
            }

            //log.info("-- httpResponse.readEntity(InvoiceValidationResponse.class)-- "+httpResponse.readEntity(String.class));
            requestResponse = httpResponse.readEntity(InvoiceValidationResponse.class);
        }

        return requestResponse;
    }
   
        public @NotNull GenerateIRNResponse doGetInvoice(GetInvoiceRequest request) {
        log.info("-- request --  "+request);
        GenerateIRNResponse requestResponse;
        // log.info("@@@--- "+firstEIVCBaseUrl+"/api/v1/invoice/irn");
        try (var client = ClientBuilder.newClient()) {
          
            var target = client.target(String.format("%s/api/v1/invoice/%s",firstEIVCBaseUrl,request.irn() ));
            var requestBuilder = target.request();
            requestBuilder.header("x-api-key", firstEIVCAPIKey);
            requestBuilder.header("x-api-secret", firstEIVCAPISecret); 
            //requestBuilder.header("invoice_id", request.invoiceId()); 

            var httpResponse = requestBuilder.get();//.post(jakarta.ws.rs.client.Entity.json(null));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid IRN response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("error occured retreiving invoice with irn {%s} request {%s} : {%s}",
                            request.irn(),httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid generate IRN  response {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while retreiving the invoice with irn {%s}  {%s} : {%s}",
                            request.irn(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(GenerateIRNResponse.class);
        }

        return requestResponse;
    }
    
    public @NotNull SignInvoiceServiceResponse doSignInvoice(SignInvoice request) {
        log.info("-- doSignInvoice request --  "+request);
      
            SignInvoiceServiceResponse requestResponse;
        
            LocalDateTime start = LocalDateTime.now();
           // SignInvoice doFindDocumentx = redisConnectors.getInvoiceObject(request.business_id()+request.irn());//.doFindDocument(toJson, request.business_id()+request.irn());
        
        log.info("@@@--- "+firstEIVCBaseUrl+"/api/v1/invoice/sign");
        try (var client = ClientBuilder.newClient()) {
            SignInvoiceObj signInvoiceObj = new SignInvoiceObj(request);
            log.info("@@@-- signInvoiceObj to pass -- "+signInvoiceObj);
            var target = client.target(String.format("%s/api/v1/invoice/sign",firstEIVCBaseUrl));
            var requestBuilder = target.request();
            requestBuilder.header("x-api-key", firstEIVCAPIKey);
            requestBuilder.header("x-api-secret", firstEIVCAPISecret); 
            
            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new SignInvoiceObj(request)));
            log.error("-- httpResponse.getStatus() -- "+httpResponse.getStatus());
            switch (httpResponse.getStatus()) {
                case 200,202,201 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                   
                    log.warn("Invalid sign invoice response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("error occured signing invoice with business_id : {%s} irn : {%s} status {%s} : error message : {%s}",
                            request.business_id(), request.irn(),httpResponse.getStatus(), new ErrorDataObj(body).error.public_message()));
                    
                    
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid sign Invoice  response {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred signing the invoice with business id {%s}  {%s} : {%s}",
                            request.business_id(),httpResponse.getStatus(), body));
                }
            }

             requestResponse = httpResponse.readEntity(SignInvoiceServiceResponse.class);
        }
        

        return requestResponse;
    }
    
    public @NotNull UpdatednvoiceServiceResponse doUpdateInvoice(UpdateInvoice request) {
        log.info("-- doUpdateInvoice request --  "+request); 
       
         UpdatednvoiceServiceResponse requestResponse;
         int code = 0;
           
         log.info("###--- "+firstEIVCBaseUrl+"/api/v1/invoice/update");
        try (var client = ClientBuilder.newClient()) {
            UpdateInvoiceObj signInvoiceObj = new UpdateInvoiceObj(request);
            log.info("-- doUpdateInvoice to pass -- "+signInvoiceObj);
            var target = client.target(String.format("%s/api/v1/invoice/update/%s",firstEIVCBaseUrl, request.irn()));
            var requestBuilder = target.request();
            requestBuilder.header("x-api-key", firstEIVCAPIKey);
            requestBuilder.header("x-api-secret", firstEIVCAPISecret); 
            log.info("--firstEIVCAPIKey --  "+firstEIVCAPIKey);
            log.info("--firstEIVCAPISecret --  "+firstEIVCAPISecret);
            var httpResponse = requestBuilder.method("PATCH", jakarta.ws.rs.client.Entity.json(new UpdateInvoiceObj(request)));
            code = httpResponse.getStatus();
            log.error("-- httpResponse.getStatus() -- "+code);
            switch (httpResponse.getStatus()) {
                case 200,202 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                   
                    log.warn("Invalid update  invoice response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("error occured update invoice with payment_status : {%s} reference : {%s} status {%s} : error message : {%s}",
                            request.payment_status(), request.reference(),httpResponse.getStatus(), new ErrorDataObj(body).error.public_message()));
                    
                    
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid sign Invoice  response {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred updating invoice with  payment_status  {%s} : status {%s} : {%s}",
                            request.payment_status(),httpResponse.getStatus(), new ErrorDataObj(body).error.public_message()));
                }
            }

             requestResponse = httpResponse.readEntity(UpdatednvoiceServiceResponse.class);
        }
        
        return  requestResponse;//new UpdatedInvoiceResponse(requestResponse.code(), requestResponse.data(), (requestResponse.code() !=null && requestResponse.code().equals("200")|| requestResponse.equals("202")?"Successful":"Error"));
    }
        
    public @NotNull EntityResponse doPullEntity(EntityRequestObj request) {
        log.info("--doPullEntity  request --  "+request);
        EntityResponse requestResponse;
        try (var client = ClientBuilder.newClient()) {
          
            var target = client.target(String.format("%s/api/v1/entity/%s",firstEIVCBaseUrl,request.entity_id));
           
            var requestBuilder = target.request();
            requestBuilder.header("x-api-key", firstEIVCAPIKey);
            requestBuilder.header("x-api-secret", firstEIVCAPISecret); 
//          
            var httpResponse = requestBuilder.get();//.post(jakarta.ws.rs.client.Entity.json(null));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid IRN response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid doGenerateIRN request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid generate IRN  response {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while validating the invoice with entity_id {%s}  {%s} : {%s}",
                            request.entity_id,httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(EntityResponse.class);
        }

        return requestResponse;
    }
        
        
    public @NotNull InvoiceValidationResponse doValidateInvoice(SignInvoiceValidation request) {
     
        log.info("InvoiceValidationResponse doValidateInvoice= " + new SignInvoiceValidationObj(request));
        InvoiceValidationResponse requestResponse;// = null;
          
        try (var client = ClientBuilder.newClient()) {
          
            var target = client.target(String.format("%s/api/v1/invoice/validate",firstEIVCBaseUrl));
            var requestBuilder = target.request();
            requestBuilder.header("x-api-key", firstEIVCAPIKey);
            requestBuilder.header("x-api-secret", firstEIVCAPISecret); 

            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new SignInvoiceValidationObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> 
                {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid validate invoice response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid doValidateInvoiceIRN request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid validate invoice response {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while validating the invoice with irn {%s}  {%s} : {%s}",
                            request.irn(),httpResponse.getStatus(), body));
                }
            }
            //log.info("-- xxx "+httpResponse.readEntity(InvoiceValidationResponse.class));
            requestResponse = httpResponse.readEntity(InvoiceValidationResponse.class);
        }

        return requestResponse;
    }
        
        
//    public @NotNull InvoiceValidationResponse doCreateParty(CreateParty request) {
//     
//        InvoiceValidationResponse requestResponse;
//          
//        try (var client = ClientBuilder.newClient()) {
//          
//            var target = client.target(String.format("%s/api/v1/invoice/irn/validate",firsEIVCBaseUrl));
//            var requestBuilder = target.request();
//            requestBuilder.header("x-api-key", firsEIVCAPIKey);
//            requestBuilder.header("x-api-secret", firsEIVCAPISecret); 
//
//            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new CreatePartyObj(request)));
//            
//            switch (httpResponse.getStatus()) {
//                case 200 -> {
//                }
//                case 400, 401, 403,404,405,500,504 -> {
//                    var body = httpResponse.readEntity(String.class);
//                    log.warn("Invalid validate invoice response {} {}", httpResponse.getStatus(), body);
//                    throw new InvalidRequestException(String.format("Invalid doCreateParty request {%s} : {%s} : {%s}",
//                            request.business_id(),httpResponse.getStatus(), body));
//                }
//                default -> {
//                    var body = httpResponse.readEntity(String.class);
//                    log.warn("Invalid validate invoice response {} {}", httpResponse.getStatus(), body);
//                    throw new ProcessingException(String.format("Error occurred while creating  party {%s}   {%s} : {%s}",
//                            request.business_id(),httpResponse.getStatus(), body));
//                }
//            }
//
//            requestResponse = httpResponse.readEntity(InvoiceValidationResponse.class);
//        }
//
//        return requestResponse;
//    }
  
   
*/
 
    
}
