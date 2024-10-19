/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.service;

import com.hoptool.exceptions.InvalidRequestException;
import com.hoptool.exceptions.ProcessingException;
import com.hoptool.invoiceme.auth.dto.ChangeProfilePasswordRequest;
import com.hoptool.invoiceme.auth.dto.ChangeProfilePasswordRequestObj;
import com.hoptool.invoiceme.auth.dto.FIRSMBSLogin;
import com.hoptool.invoiceme.auth.dto.FIRSMBSLoginObj;
import com.hoptool.invoiceme.auth.dto.FirsLoginResponse;
import com.hoptool.invoiceme.auth.dto.ForceSyncProfilePasswordRequest;
import com.hoptool.invoiceme.auth.dto.ForceSyncProfilePasswordRequestObj;
import com.hoptool.invoiceme.auth.dto.ForceSyncResponse;
import com.hoptool.invoiceme.auth.dto.ProfileSyncRequest;
import com.hoptool.invoiceme.auth.dto.ProfileSyncRequestObj;
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
    
    @ConfigProperty(name = "firs.mbs.login.url")
    String firsMBSLoginUrl;
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

        return  requestResponse;
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

        return  requestResponse;
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

        return  requestResponse;
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
    
    public @NotNull ProfileSyncResponse doChangePassword(ChangeProfilePasswordRequest request) {
        log.info("-- doChangePassword --"+request);
        ProfileSyncResponse requestResponse;
          
        try (var client = ClientBuilder.newClient()) {
           
            //String format = String.format("%s/change-password",authServiceBaseUrl);
            ///log.info("--- url "+format);
            ChangeProfilePasswordRequestObj irnDTO = new ChangeProfilePasswordRequestObj(request);
            log.info("@@ doChangePassword--irnDTO-- "+irnDTO);
            var target = client.target(String.format("%s/change-password",authServiceBaseUrl));
            var requestBuilder = target.request();
            
            var httpResponse = requestBuilder.post(jakarta.ws.rs.client.Entity.json(new ChangeProfilePasswordRequestObj(request)));
            
            switch (httpResponse.getStatus()) {
                case 200 -> {
                }
                case 400, 401, 403,404,405,500,504 -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid   password change response {} {}", httpResponse.getStatus(), body);
                    throw new InvalidRequestException(String.format("Invalid  password change {%s} : {%s}",
                            httpResponse.getStatus(), body));
                }
                default -> {
                    var body = httpResponse.readEntity(String.class);
                    log.warn("Invalid  password change exception {} {}", httpResponse.getStatus(), body);
                    throw new ProcessingException(String.format("Error occurred while   password change {%s}  {%s} : {%s}",
                            request.code(),httpResponse.getStatus(), body));
                }
            }

            requestResponse = httpResponse.readEntity(ProfileSyncResponse.class);
        }

        return  requestResponse;
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

        return requestResponse;
    }
    
    
}
