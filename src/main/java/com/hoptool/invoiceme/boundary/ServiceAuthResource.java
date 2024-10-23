package com.hoptool.invoiceme.boundary;

import com.hoptool.invoiceme.auth.dto.ResetKeysRequest;
import com.hoptool.invoiceme.auth.dto.ResetResponse;
import com.hoptool.invoiceme.auth.dto.SysLoginRequest;
import com.hoptool.invoiceme.auth.dto.SysLoginResponse;
import com.hoptool.invoiceme.controllers.CryptoHelper;
import com.hoptool.invoiceme.controllers.InvoiceMeRequestController;
import com.hoptool.invoiceme.controllers.ServiceAuthController;
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


@Path("/v1/service-auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceAuthResource {
    
  
    @Inject
    CryptoHelper cryptoHelper;
    
    
    @Inject
    ServiceAuthController serviceAuthController;
    
    
   
    final InvoiceService invoiceService;
    final InvoiceMeRequestController invoiceController;

    public ServiceAuthResource(InvoiceService invoiceService, InvoiceMeRequestController invoiceController) {
      
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
    @Path("sys-login")
    public SysLoginResponse doInvoiceMeUserLogin(@Valid final SysLoginRequest request) {
        System.out.println("doInvoiceMeUserLogin request = " +  request);
       return serviceAuthController.doSysLogin(request);
    }
    
    
    @POST
    @Path("reset")
    public ResetResponse doReset(@Valid final ResetKeysRequest request) {
        System.out.println("doReset request = " +  request);
       return serviceAuthController.doReset(request);
    }
    
    @POST
    @Path("resend")
    public ResetResponse doResend(@Valid final ResetKeysRequest request) {
        System.out.println("doResend request = " +  request);
       return serviceAuthController.doResend(request);
    }
    
    
    

}
