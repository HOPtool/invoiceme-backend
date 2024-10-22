/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.invoiceme.auth.dto;

/**
 *
 * @author root
 */



import jakarta.json.JsonObject;
import jakarta.validation.Valid;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;


@RegisterRestClient
@Path("/paycraftsystems/auth-service/api/processor") //@Path("/3gp-paycraftsystems/auth-service/api/processor")
public interface AuthClient {
   
    ///paycraftsystems/auth-service/api/processor/list-clients-by-customer
    //paycraftsystems/auth-service/api/processor/list-clients-by-customer/

    @POST
    @Path("/ping")
    Response doPing();
    
   
    @POST
    @Path("/login")
   // @Produces("application/json")
    Response doSysLogin(@Valid JsonObject json);
    
   
    @POST
    @Path("/reset")
    Response doReset(@Valid JsonObject json);
    
    @POST
    @Path("/resend")
    Response doResend(@Valid JsonObject json);
    
    @POST
    @Path("/encrypt")
    Response doEncrypt(@Valid JsonObject json);
    
    @POST
    @Path("/decrypt")
    Response doDecrypt(String fromJson);
    
    @POST
    @Path("/refresh")
    Response doRefresh(@Valid JsonObject json);
    
    @POST
    @Path("/list-clients")
    Response doListClients();
    
    @POST
    @Path("list-clients/{partnerCode}")
    public Response doListClients(@PathParam("partnerCode") String partnerCode);
    
    @POST
    @Path("/maintain-client")
    Response doMaintainClient(@Valid JsonObject fromJson);
    
    @POST
    @Path("/delete-client")
    public Response doDeleteClient(final JsonObject request);
    
    
    @POST
    @Path("/create-client")
    public Response doCreateClient(final JsonObject request);
    
            
    
    @POST
    @Path("/create-client/{id}")
    public Response doLookupClient(final @PathParam("id") long id);
    
    @POST
    @Path("/get-client-byname/{clientName}")
    Response doGetClient(@Valid @PathParam("clientName") String clientName);
    
      
    @POST
    @Path("list-clients-by-customer/{customercode}")
    public Response doListClientsByCustomer(@PathParam("customercode") String customercode);
    
     
    @POST
    @Path("/sync-profile")
    Response doSyncProfile(@Valid JsonObject fromJson);
    
    @POST
    @Path("/sync-profile-wtp")
    Response doSyncProfileWTP(@Valid JsonObject fromJson);
    
    @POST
    @Path("/force-sync")
    Response doForceSync(@Valid JsonObject fromJson);
    
    @POST
    @Path("/force-sync-client")
    Response doForceSyncClient(@Valid JsonObject fromJson);
    
    @POST
    @Path("force-sync-txp")
    Response doForceSyncTxP(@Valid JsonObject fromJson);
    
    @POST
    @Path("/maintain-profile")
    Response doMaintain(@Valid JsonObject fromJson);
    
    @POST
    @Path("sync-txp")
    Response doSyncTxP(@Valid JsonObject fromJson);
    
    @POST
    @Path("/sync-profile-login")
    Response doSyncProfileAndLogin(@Valid JsonObject fromJson);
    
    @POST
    @Path("/verify-user")
    Response doVerifyUser(@Valid JsonObject fromJson);
    
    @POST
    @Path("verify-user-client")
    public Response doVerifyUserByClient(@Valid final jakarta.json.JsonObject verifyStr);
    
    
    @POST
    @Path("profile-lookup")
    public Response doLookupProfile(final JsonObject request);
    
   
    
    /*
   
    
    @POST
    @Path("/aboutme")
    Response doAboutMe();
    
   
    
    @POST
    @Path("/verify")
    Response doVerify(@Valid JsonObject fromJson);
    
    @POST
    @Path("verify-txp")
    public Response doVerifyTxp(@Valid final JsonObject verifyStr);
    
    
   
    
   
    
    
   
  
    */
    /*
    
    @POST
    @Path("/verify")
    Response doVerify(@Valid JsonObject fromJson);
    */
    
   
}
