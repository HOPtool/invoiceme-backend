package com.hoptool.invoiceme.boundary;

import com.hoptool.invoiceme.auth.dto.FIRSMBSLogin;
import com.hoptool.invoiceme.auth.dto.FirsLoginResponse;
import com.hoptool.invoiceme.dto.ChangeProfilePasswordRequest;
import com.hoptool.invoiceme.auth.dto.ForceSyncResponse;
import com.hoptool.invoiceme.auth.dto.ProfileSyncResponse;
import com.hoptool.invoiceme.controllers.CryptoHelper;
import com.hoptool.invoiceme.controllers.InvoiceMeRequestController;
import com.hoptool.invoiceme.controllers.UserController;
import com.hoptool.invoiceme.dto.CompleteBusinessOnboarding;
import com.hoptool.invoiceme.dto.ForceSyncProfilePasswordRx;
import com.hoptool.invoiceme.dto.InitForcePasswordChangeResponse;
import com.hoptool.invoiceme.dto.InitForceUserPasswordReset;
import com.hoptool.invoiceme.dto.InitUserCreationResponse;
import com.hoptool.invoiceme.dto.OTPValidationRequest;
import com.hoptool.invoiceme.dto.OTPVerificationResponse;
import com.hoptool.invoiceme.dto.OnboardingCompletionResponse;
import com.hoptool.invoiceme.dto.UserLogResponse;
import com.hoptool.invoiceme.dto.UserLogin;
import com.hoptool.invoiceme.dto.UserSignUpStepOne;
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


@Path("/v1/user-management")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    
  
    @Inject
    CryptoHelper cryptoHelper;
    
    
    @Inject
    UserController userController;
    
    
   
    final InvoiceService invoiceService;
    final InvoiceMeRequestController invoiceController;

    public UserResource(InvoiceService invoiceService, InvoiceMeRequestController invoiceController) {
      
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
    @Path("user-login")
    //@JWTTokenNeeded
    public UserLogResponse doInvoiceMeUserLogin(@Valid final UserLogin request) {
        System.out.println("doInvoiceMeUserLogin request = " +  request);
       return userController.doLogin(request);
    }
    
    
    @POST
    @Path("init-force-password-reset")
    public InitForcePasswordChangeResponse doInitForcePasswordReset(@Valid final InitForceUserPasswordReset request) {
        System.out.println("doInitForcePasswordReset request = " +  request);
       return userController.doInitForcePasswordReset(request);
    }
    
    @POST
    @Path("force-password-reset")
    public ForceSyncResponse doForcePasswordReset(@Valid final ForceSyncProfilePasswordRx request) {
        System.out.println("doInitForcePasswordReset request = " +  request);
       return userController.doForcePasswordReset(request);
    }
    
    
    
    @POST
    @Path("user-login-mbs")
    public UserLogResponse doFIRSMBSUserLogin(@Valid final UserLogin request) {
        
      return userController.doLogin(request);
    }
    
    
    @POST
    @Path("init-onboarding")
    public InitUserCreationResponse doInitOnboarding(@Valid final UserSignUpStepOne request) {
        
      return userController.doInitOnboarding(request);
    }
    
    
    @POST
    @Path("validate-otp")
    public OTPVerificationResponse doValidateOTP(@Valid final OTPValidationRequest request) {
        
      return userController.doValidateOTP(request);
    }
    
    
    @POST
    @Path("complete-onboarding")
    public OnboardingCompletionResponse doCompleteOnBoarding(@Valid final CompleteBusinessOnboarding request) {
        
      return userController.doCompleteOnboarding(request);
    }
    
    @POST
    @Path("change-password")
    public ProfileSyncResponse doChangePassword(@Valid final ChangeProfilePasswordRequest request) {
        
      return userController.doChangePassword(request);
    }
    
    
    @POST
    @Path("firs-mbs-login")
    public FirsLoginResponse doChangePassword(@Valid final FIRSMBSLogin request) {
        
      return userController.doFIRSMBSLogin(request);
    }
    

}
