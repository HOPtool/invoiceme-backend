package com.hoptool.invoiceme.boundary;

import com.hoptool.invoiceme.controllers.CryptoHelper;
import com.hoptool.invoiceme.controllers.InvoiceMeRequestController;
import com.hoptool.invoiceme.controllers.UserController;
import com.hoptool.eivc.response.dto.SignInvoiceResponse;
import com.hoptool.eivc.response.dto.DownloadInvoiceClearResponse;
import com.hoptool.eivc.response.dto.IRNGenerationResponse;
import com.hoptool.eivc.response.dto.UpdatedInvoiceResponse;
import com.hoptool.invoice.dto.EntityRequest;
import com.hoptool.invoice.dto.InvoiceValidationResponse;
import com.hoptool.invoice.dto.SignInvoice;
import com.hoptool.invoice.dto.SignInvoiceValidation;
import com.hoptool.invoice.dto.UpdateInvoice;
import com.hoptool.invoice.dto.ValidateIRNRequest;
import com.hoptool.invoiceme.dto.InvoiceCategoriesResponse;
import com.hoptool.invoiceme.dto.InvoiceTypesResponse;
import com.hoptool.invoiceme.dto.UserLogResponse;
import com.hoptool.invoiceme.dto.UserLogin;
import com.hoptool.invoiceme.enumz.InvoiceTypesEnum;
import com.hoptool.service.InvoiceService;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Path("/v1/xxx")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvoicesResource {
    
  
    @Inject
    CryptoHelper cryptoHelper;
    
    
    @Inject
    UserController userController;
    
    
   
    final InvoiceService invoiceService;
    final InvoiceMeRequestController invoiceController;

    public InvoicesResource(InvoiceService invoiceService, InvoiceMeRequestController invoiceController) {
      
        this.invoiceService = invoiceService;
        this.invoiceController = invoiceController;
    }
    
    
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public Response doPing() {
       
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("responseDesc", "I am alive and well (Authentication Service..)");
        return Response.ok(job.build(), MediaType.APPLICATION_JSON).build();
    }
    
    
    
    @POST
    @Path("aboutme")
    public Response doAboutMe() {
        return Response.fromResponse(cryptoHelper.aboutME()).build();
    }

    
    @POST
    @Path("user-login")
    public UserLogResponse doInvoiceMeUserLogin(@Valid final UserLogin request) {
        
      return userController.doLogin(request);
    }
    
    
    @POST
    @Path("confirm-invoice")
    public InvoiceValidationResponse doConfirmInvoice(@Valid final ValidateIRNRequest request) {
        
      return invoiceController.doProcessConfirmIRNRequest(request);
    }
    
    @POST
    @Path("invoice-types")
    public InvoiceTypesResponse doListInvoiceTypes() {
        
        
         List<String> enumNames = Stream.of(InvoiceTypesEnum.values()).map(Enum::name).collect(Collectors.toList());
          
         Collections.sort(enumNames);
      
        return new InvoiceTypesResponse (enumNames);
    }
    
    
    @POST
    @Path("invoice-categories")
    public InvoiceCategoriesResponse doListInvoiceCategories() {
     
         List<String> enumNames = Stream.of(InvoiceTypesEnum.values()).map(Enum::name).collect(Collectors.toList());
          
           Collections.sort(enumNames);
      
        return new InvoiceCategoriesResponse(enumNames);
    }
    
    
    
    @POST
    @Path("download-invoice")
    public DownloadInvoiceClearResponse doDownloadInvoice(@Valid final ValidateIRNRequest request) {
        
      return invoiceController.doDownloadInvoice(request);
    }
    
    
    @POST
    @Path("search-invoice")
    public InvoiceValidationResponse doSearchInvoice(@Valid final ValidateIRNRequest request) {
        
      return invoiceService.doSearchInvoice(request);
    }
   
    
    @POST
    @Path("validate-invoice")
    //@Metered(name = "reset_metered")
    public InvoiceValidationResponse doValidateInvoiceRequest(@Valid final SignInvoiceValidation request) {
        
        return invoiceController.doProcessInvoiceValidationRequest(request);// invoiceService.doValidateInvoice(request);
    }
    
    
    @POST
    @Path("generate-irn")
    public IRNGenerationResponse doGenerateIRN(@Valid final EntityRequest request) {
        
        return invoiceController.doGenerateIRN(request);
    }
    
//    
//    @POST
//    @Path("get-invoice")
//    public GenerateIRNResponse doGetInvoice(@Valid final GetInvoiceRequest request) {
//        
//        return invoiceService.doGetInvoice(request);
//    }
    
    
    @POST
    @Path("sign-invoice")
    public SignInvoiceResponse doSignInvoice(@Valid final SignInvoice request) {
        
        return invoiceController.doSignInvoice(request);
    }
    
    
    @POST
    @Path("update-invoice")
    public UpdatedInvoiceResponse doUpdateInvoice(@Valid final UpdateInvoice request) {
        
        return invoiceController.doUpdateInvoice(request);
    }
   
   
    
    

}
