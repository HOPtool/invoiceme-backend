/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.service;

import com.hoptool.eivc.response.dto.DownloadInvoiceResponse;
import com.hoptool.eivc.response.dto.EntityResponse;
import com.hoptool.eivc.response.dto.SignInvoiceServiceResponse;
import com.hoptool.eivc.response.dto.UpdatedInvoiceResponse;
import com.hoptool.eivc.response.dto.UpdatednvoiceServiceResponse;
import com.hoptool.exceptions.InvalidRequestException;
import com.hoptool.exceptions.ProcessingException;
import com.hoptool.invoice.dto.EntityRequestObj;
import com.hoptool.invoice.dto.ErrorDataObj;
import com.hoptool.invoice.dto.GenerateIRNRequest;
import com.hoptool.invoice.dto.GenerateIRNResponse;
import com.hoptool.invoice.dto.GetInvoiceRequest;
import com.hoptool.invoice.dto.InvoiceValidationData;
import com.hoptool.invoice.dto.InvoiceValidationResponse;
import com.hoptool.invoice.dto.InvoiceValidationServiceResponse;
import com.hoptool.invoice.dto.SignInvoice;
import com.hoptool.invoice.dto.SignInvoiceObj;
import com.hoptool.invoice.dto.SignInvoiceValidation;
import com.hoptool.invoice.dto.SignInvoiceValidationObj;
import com.hoptool.invoice.dto.UpdateInvoice;
import com.hoptool.invoice.dto.UpdateInvoiceObj;
import com.hoptool.invoice.dto.ValidateIRNRequest;
import com.hoptool.invoice.dto.ValidateIRNRequestObj;
import com.hoptool.invoiceme.auth.dto.FIRSMBSLogin;
import com.hoptool.invoiceme.auth.dto.FIRSMBSLoginObj;
import com.hoptool.invoiceme.auth.dto.FirsLoginResponse;
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
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author taysayshaguy
 */

@ApplicationScoped
@Slf4j
public class InvoiceService {
    
    
    @ConfigProperty(name = "firs.eivc.base.url")
    String firstEIVCBaseUrl;
    
    @ConfigProperty(name = "firs.eivc.api.key")
    String firstEIVCAPIKey;
    
    @ConfigProperty(name = "firs.eivc.api.secret")
    String firstEIVCAPISecret;
    
    @Context
    private UriInfo uriInfo;
   
    ResourceHelper rh = new ResourceHelper();
    
    @Inject
    RedisClient redisClient;
    
    @Inject
    MongoService mongo;
    
    @Inject
    RedisConnectors redisConnectors;
    
    
    //tax-categories
    
    public @NotNull InvoiceValidationResponse doValidateInvoiceIRN(ValidateIRNRequest request) {
        log.info("-- doValidateInvoiceIRN --"+request);
        InvoiceValidationServiceResponse requestResponse;
          
        try (var client = ClientBuilder.newClient()) {
           
            ValidateIRNRequestObj irnDTO = new ValidateIRNRequestObj(request);
            log.info("@@--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/api/v1/invoice/irn/validate",firstEIVCBaseUrl));
            var requestBuilder = target.request();
            requestBuilder.header("x-api-key", firstEIVCAPIKey);
            requestBuilder.header("x-api-secret", firstEIVCAPISecret); 

            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new ValidateIRNRequestObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid  invoice irn validation response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid invoice irn validation request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid invoice irn validation  exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while validating the invoice with irn {%s}  {%s} : {%s}",
                            request.irn(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(InvoiceValidationServiceResponse.class);
        }

        return new InvoiceValidationResponse(requestResponse.code(), requestResponse.data(), (requestResponse.code() !=null && requestResponse.code().equals("200")|| requestResponse.equals("202")?"Successful":"Error"));
    }
    
     public @NotNull InvoiceValidationResponse doValidateInvoiceIRNTest(ValidateIRNRequest request) {
     
        InvoiceValidationServiceResponse requestResponse;
          
        try (var client = ClientBuilder.newClient()) {
           
            ValidateIRNRequestObj irnDTO = new ValidateIRNRequestObj(request);
            log.info("--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/api/v1/invoice/irn/validate",firstEIVCBaseUrl));
            var requestBuilder = target.request();
            requestBuilder.header("x-api-key", firstEIVCAPIKey);
            requestBuilder.header("x-api-secret", firstEIVCAPISecret); 

            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new ValidateIRNRequestObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid validate invoice response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid Validate Invoice request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid validation  exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while validating the invoice with irn {%s}  {%s} : {%s}",
                            request.irn(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(InvoiceValidationServiceResponse.class);
        
        }

        return new InvoiceValidationResponse(requestResponse.code(), requestResponse.data(), (requestResponse.code() !=null && requestResponse.code().equals("200")|| requestResponse.equals("202")?"Successful":"Error"));
    }
    
    
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
    
     public @NotNull FirsLoginResponse doFirsMBSLogin(FIRSMBSLogin request) {
        log.info("-- doFirsMBSLogin --"+request);
        FirsLoginResponse requestResponse;
        
        try (var client = ClientBuilder.newClient()) {
           
            FIRSMBSLoginObj irnDTO = new FIRSMBSLoginObj(request);
            log.info("@@ doChangePassword--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/api/v1/utilities/authenticate",firstEIVCBaseUrl)); 
            var requestBuilder = target.request();
            requestBuilder.header("x-api-key", firstEIVCAPIKey);
            requestBuilder.header("x-api-secret", firstEIVCAPISecret);
            
            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new FIRSMBSLoginObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid firs mbs login response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid firs mbs login {%s} : # %s",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid  firs mbs login exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while  calling firs mbs login {%s}  {%s} : {%s}",
                            request.email(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(FirsLoginResponse.class);
        }

        return  requestResponse;//new ProfileSyncResponse(requestResponse.code(), requestResponse.data(), (requestResponse.code() !=null && requestResponse.code().equals("200")|| requestResponse.equals("202")?"Successful":"Error"));
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
  
   
 
    
}
