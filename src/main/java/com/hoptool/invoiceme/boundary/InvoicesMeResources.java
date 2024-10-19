package com.hoptool.invoiceme.boundary;

import com.hoptool.exceptions.InvalidRequestException;
import com.hoptool.invoiceme.controllers.CryptoHelper;
import com.hoptool.invoiceme.controllers.InvoiceMeRequestController;
import com.hoptool.invoiceme.controllers.UserController;
import com.hoptool.invoiceme.dto.BusinessNameResponse;
import com.hoptool.invoiceme.dto.InvoiceCategoriesResponse;
import com.hoptool.invoiceme.dto.InvoiceTypesResponse;
import com.hoptool.invoiceme.dto.TinOrCACLookup;
import com.hoptool.invoiceme.dto.TinOrCACLookupObj;
import com.hoptool.invoiceme.enumz.DummyCorporatesLookUp;
import com.hoptool.invoiceme.enumz.InvoiceTypesEnum;
import com.hoptool.resources.ErrorCodes;
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
import lombok.extern.slf4j.Slf4j;


@Path("/v1/resources")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class InvoicesMeResources {
    
    
  
    @Inject
    CryptoHelper cryptoHelper;
    
    
    @Inject
    UserController userController;
    
    
   
    final InvoiceService invoiceService;
    final InvoiceMeRequestController invoiceController;

    public InvoicesMeResources(InvoiceService invoiceService, InvoiceMeRequestController invoiceController) {
      
        this.invoiceService = invoiceService;
        this.invoiceController = invoiceController;
    }
    
    
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public Response doPing() {
       
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("responseDesc", "I am alive and well (resources Service..)");
        return Response.ok(job.build(), MediaType.APPLICATION_JSON).build();
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
    @Path("lookup-tin-or-cac")
    public BusinessNameResponse doLookupTinOrCAC(@Valid TinOrCACLookup lookUp) {
        System.out.println("@-- doLookupTinOrCAC lookUp = " +  lookUp);
        List<String> enumNames =  null;
        String businessName = "";
        BusinessNameResponse business = null;
        try 
        {
             if(lookUp == null)
             {
                  throw new InvalidRequestException(String.format("Invalid business no validation request {%s} : {%s}",
                            400, lookUp));
             }
             
             TinOrCACLookupObj tInOrCACLookupObj = new TinOrCACLookupObj(lookUp);
             log.info("--doLookupTinOrCAC -- "+tInOrCACLookupObj);
             enumNames = Stream.of(DummyCorporatesLookUp.values()).map(Enum::name).collect(Collectors.toList());
             System.out.println("-- tInOrCACLookupObj = " + enumNames);
             if(enumNames !=null)
             {
             
                for (String enumName : enumNames) {

                    if(enumName.startsWith(lookUp.validationType()))
                    {
                        businessName = enumName.split(lookUp.validationType()+"_")[1];

                        business = new BusinessNameResponse(ErrorCodes.SUCCESSFUL, businessName.replaceAll("_", " "));
                    }

                }
             }
             else
             {
                 business = new BusinessNameResponse(ErrorCodes.SYSTEM_ERROR, businessName); 
             }
            
            
        } catch (Exception e) {
        
            e.printStackTrace();
            business = new BusinessNameResponse(ErrorCodes.SYSTEM_ERROR, e.getMessage()); 
            log.error("Exception @ doLookupTinOrCAC ",e);
        
        }
        
        
      
        return business;
    }
    
    

}
