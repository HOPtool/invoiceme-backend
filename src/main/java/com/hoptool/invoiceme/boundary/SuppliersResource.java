package com.hoptool.invoiceme.boundary;

import com.hoptool.invoiceme.controllers.CryptoHelper;
import com.hoptool.invoiceme.controllers.InvoiceMeRequestController;
import com.hoptool.invoiceme.controllers.SuppliersController;
import com.hoptool.invoiceme.dto.ApproveOrDeleteSupplierRequest;
import com.hoptool.invoiceme.dto.FilterSuppliers;
import com.hoptool.invoiceme.dto.SupplierRequest;
import com.hoptool.invoiceme.dto.SupplierResponse;
import com.hoptool.invoiceme.dto.SuppliersSearchResponse;
import com.hoptool.invoiceme.dto.SyncSupplierRequest;
import com.hoptool.service.InvoiceService;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/v1/supplier-management")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SuppliersResource {
    
  
    @Inject
    CryptoHelper cryptoHelper;
    
    
    @Inject
    SuppliersController suppliersController;
    
    
   
    final InvoiceService invoiceService;
    final InvoiceMeRequestController invoiceController;

    public SuppliersResource(InvoiceService invoiceService, InvoiceMeRequestController invoiceController) {
      
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
    @Path("create")
    public SupplierResponse doCreateSupplier(@Valid final SupplierRequest request) {
        System.out.println("doCreateSupplier request = " +  request);
       return suppliersController.doCreateSupplier(request);
    }
    
    @PUT
    @Path("edit")
    public SupplierResponse doSyncSupplier(@Valid final SyncSupplierRequest request) {
        System.out.println("doInitForcePasswordReset request = " +  request);
       return suppliersController.doSyncSupplier(request);
    }
    
    
    @PUT
    @Path("delete")
    public SupplierResponse doDeleteSupplier(@Valid final ApproveOrDeleteSupplierRequest request) {
        System.out.println("doDeleteSupplier request = " +  request);
       return suppliersController.doDeleteSupplier(request);
    }
    
    @PUT
    @Path("approve")
    public SupplierResponse doApproveSupplier(@Valid final ApproveOrDeleteSupplierRequest request) {
        System.out.println("doApproveSupplier request = " +  request);
       return suppliersController.doApproveSupplier(request);
    }
    
   
    
    
    
    @GET
    @Path("list-suppliers")
    public SuppliersSearchResponse doListSuppliers(@Valid final FilterSuppliers request) {
        
      return suppliersController.doFilterSuppliers(request);
    }
    
    @GET
    @Path("list-suppliers-for-business")
    public SuppliersSearchResponse doListSuppliersForBusiness(@Valid final FilterSuppliers request) {
        
      return suppliersController.doFilterSuppliersForBusiness(request);
    }
   
    /*
    @GET
    @Path("list-suppliers-for-business")
    public SuppliersSearchResponse doListSuppliers(@Valid final FilterSuppliers request) {
        
      return suppliersController.doFilterSuppliers(request);
    }
    */

}
