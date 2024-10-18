/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.service;

import com.hoptool.eivc.response.dto.CountryDataResponse;
import com.hoptool.eivc.response.dto.CurrenciesDataResponse;
import com.hoptool.eivc.response.dto.InvoiceTypeResponse;
import com.hoptool.eivc.response.dto.PaymentMeansDataResponse;
import com.hoptool.eivc.response.dto.VATExemptionDataResponse;
import com.hoptool.exceptions.InvalidRequestException;
import com.hoptool.exceptions.ProcessingException;
import com.hoptool.invoice.dto.CountriesResponse;
import com.hoptool.invoice.dto.CurrenciesResponse;
import com.hoptool.invoice.dto.TaxCategoryResponse;
import io.smallrye.common.constraint.NotNull;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
@Slf4j
public class ResourcesService {
    
    
     
     @ConfigProperty(name="firs.eivc.base.url")
     String firsBaseUrl;
    
    
     public @NotNull CountryDataResponse doListCountries() {
       
        CountryDataResponse response;// == null;
        
        try (var client = ClientBuilder.newClient()) {
            var target = client.target(String.format("%s/invoice/resources/countries", firsBaseUrl));
            var requestBuilder = target.request();
           
            var httpResponse = requestBuilder.get();
            switch (httpResponse.getStatus()) {
                case 200 -> 
                {
                }
                case 400, 401, 403 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid token error {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid list countries request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Token Exchange request exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while processing list countries {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
            }
            
            response = httpResponse.readEntity(CountryDataResponse.class);
            
        }
       
        return  response;//GrantTokenXchangeResponse(tokenResponse.access_token(), tokenResponse.token_type(), tokenResponse.expires_in());
    }
     
     
     
    public @NotNull TaxCategoryResponse doListTaxCategories() {
       
        TaxCategoryResponse response;// == null;
        
        try (var client = ClientBuilder.newClient()) {
            var target = client.target(String.format("%s/api/v1/invoice/resources/tax-categories", firsBaseUrl));
            var requestBuilder = target.request();
           
            var httpResponse = requestBuilder.get();
            switch (httpResponse.getStatus()) {
                case 200 -> 
                {
                }
                case 400, 401, 403 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid tax-categories {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid list tax-categories request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("tax-categories request exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while processing list tax-categories {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
            }
            
            response = httpResponse.readEntity(TaxCategoryResponse.class);
            
        }
       
        return  response;//GrantTokenXchangeResponse(tokenResponse.access_token(), tokenResponse.token_type(), tokenResponse.expires_in());
    }
    
    
    public @NotNull VATExemptionDataResponse doListVATExemptions() {
       
        VATExemptionDataResponse response;// == null;
        
        try (var client = ClientBuilder.newClient()) {
            var target = client.target(String.format("%s/api/v1/invoice/resources/vat-exemptions", firsBaseUrl));
            var requestBuilder = target.request();
           
            var httpResponse = requestBuilder.get();
            switch (httpResponse.getStatus()) {
                case 200 -> 
                {
                }
                case 400, 401, 403 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid list vat exemptions error {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid list list vat exemptions request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("list vat exemptions request exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while processing list vat exemptions {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
            }
            
            response = httpResponse.readEntity(VATExemptionDataResponse.class);
            
        }
       
        return  response;//GrantTokenXchangeResponse(tokenResponse.access_token(), tokenResponse.token_type(), tokenResponse.expires_in());
    }
    
    public @NotNull PaymentMeansDataResponse doListPaymentMeans() {
       
        PaymentMeansDataResponse response;// == null;
        
        try (var client = ClientBuilder.newClient()) {
            var target = client.target(String.format("%s/api/v1/invoice/resources/payment-means", firsBaseUrl));
            var requestBuilder = target.request();
           
            var httpResponse = requestBuilder.get();
            switch (httpResponse.getStatus()) {
                case 200 -> 
                {
                }
                case 400, 401, 403 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid list payment means error {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid list payment means request {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("list payment means exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while processing list payment means {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
            }
            
            response = httpResponse.readEntity(PaymentMeansDataResponse.class);
            
        }
       
        return  response;
    }
    
    
    public @NotNull InvoiceTypeResponse doInvoiceTypes() {
       
        InvoiceTypeResponse response;// == null;
        
        try (var client = ClientBuilder.newClient()) {
            var target = client.target(String.format("%s/api/v1/invoice/resources/invoice-types", firsBaseUrl));
            var requestBuilder = target.request();
           
            var httpResponse = requestBuilder.get();
            switch (httpResponse.getStatus()) {
                case 200 -> 
                {
                }
                case 400, 401, 403 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid retreive Invoice types error {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid list Invoice types {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("retreive  Invoice types exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while processing Invoice types {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
            }
            
            response = httpResponse.readEntity(InvoiceTypeResponse.class);
            
        }
       
        return  response;
    }
    
    
    public @NotNull CurrenciesResponse doGetCurrencies() {
       
        CurrenciesResponse response;// == null;
        
        try (var client = ClientBuilder.newClient()) {
            var target = client.target(String.format("%s/api/v1/invoice/resources/currencies", firsBaseUrl));
            var requestBuilder = target.request();
           
            var httpResponse = requestBuilder.get();
            log.info("-- ");
            switch (httpResponse.getStatus()) {
                case 200 -> 
                {
                }
                case 400, 401, 403 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid retreive cuurencies error {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid list cuurencies {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("retreive cuurencies request exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while processing get cuurencies {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
            }
            response = httpResponse.readEntity(CurrenciesResponse.class);
           
        }
       
        return response;
    }
    
    
    public @NotNull CountriesResponse doGetCountries() {
       
        CountriesResponse response;// == null;
        
        try (var client = ClientBuilder.newClient()) {
            var target = client.target(String.format("%s/api/v1/invoice/resources/countries", firsBaseUrl));
            var requestBuilder = target.request();
           
            var httpResponse = requestBuilder.get();
            log.info("-- ");
            switch (httpResponse.getStatus()) {
                case 200 -> 
                {
                }
                case 400, 401, 403 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid retreive other resources error {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid list other resources {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("retreive other resources request exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while processing other resources {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
            }
            response = httpResponse.readEntity(CountriesResponse.class);
        }
       
        return response;
    }
    
    
}
