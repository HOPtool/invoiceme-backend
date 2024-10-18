package com.hoptool.invoiceme.boundary;

import com.hoptool.eivc.response.dto.InvoiceTypeResponse;
import com.hoptool.eivc.response.dto.PaymentMeansDataResponse;
import com.hoptool.eivc.response.dto.VATExemptionDataResponse;
import com.hoptool.invoice.dto.CountriesResponse;
import com.hoptool.invoice.dto.CurrenciesResponse;
import com.hoptool.invoice.dto.TaxCategoryResponse;
import com.hoptool.service.ResourcesService;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/v1/resources-xx")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OtherResources {
   
    @Inject
    ResourcesService resourceService;
    
    
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public Response doPing() {
       
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("responseDesc", "I am alive and well (Authentication Service..)");
        return Response.ok(job.build(), MediaType.APPLICATION_JSON).build();
    }
    

    /*
    public OtherResources(ResourcesService resourceService) {
      
        this.resourceService = resourceService;
    }
    */
    
    @POST
    @Path("invoice-types")
    //@Metered(name = "reset_metered")
    public InvoiceTypeResponse doGetInvoiceTypes() {
        
        return resourceService.doInvoiceTypes();
    }
    
    
    @POST
    @Path("list-countries")
    public CountriesResponse doListCountries() {
        
        return resourceService.doGetCountries();
    }
    
    
    @POST
    @Path("list-currencies")
    public CurrenciesResponse doListCurrencies() {
        
        return resourceService.doGetCurrencies();
    }
    
    @POST
    @Path("list-tax-categories")
    public TaxCategoryResponse doListTaxCategorie() {
        
        return resourceService.doListTaxCategories();
    }
    
    
    @POST
    @Path("list-vat-exemptions")
    public VATExemptionDataResponse doListVatExemtions() {
        
        return resourceService.doListVATExemptions();
    }
    
    @POST
    @Path("list-payment-means")
    public PaymentMeansDataResponse doListPaymentMeans() {
        
        return resourceService.doListPaymentMeans();
    }
    
   
    
    

}
