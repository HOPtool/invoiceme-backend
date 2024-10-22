/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.invoiceme.filters;

/**
 *
 * @author taysayshaguy
 */




import com.google.gson.Gson;
import com.hoptool.exceptions.HoptoolException;
import com.hoptool.invoiceme.auth.dto.AuthClient;
import com.hoptool.invoiceme.dto.UserSecRequest;
//import com.hoptool.invoiceme.dto.UserSecObject;
import com.hoptool.invoiceme.repositories.SysDataRepository;
import com.hoptool.resources.AESCrypter;
import com.hoptool.resources.ErrorCodes;
import com.hoptool.resources.KeyGenerator;
import com.hoptool.resources.SecException;
import com.hoptool.service.dto.HeaderClientObj;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import jakarta.json.Json;
import jakarta.json.bind.JsonbBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;
//import org.jboss.resteasy.core.ResourceMethodInvoker;
//import org.jboss.resteasy.core.ResourceMethodInvoker;
//import org.jboss.resteasy.core.ResourceMethodInvoker;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Slf4j
@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    
   // private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JWTTokenNeededFilter.class);
    
    
    @Inject
    KeyGenerator sysKeyGenerator;

    
    @Inject
    SysDataRepository sysDataRepo;
    
    
    @Inject
    @RestClient
    jakarta.inject.Provider<AuthClient> authclient;
    
    String doLog = "-1";
    
    //@Inject
    //ClientInfoHelper clientInfoHelper;

    // ======================================
    // =          Business methods          =
    // ======================================

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException 
    {
        String key = sysDataRepo.doLookUpByNameStr("SYS_KEY", "XXX");
        String iv = sysDataRepo.doLookUpByNameStr("SYS_IV", "XXX");
        log.info("@@--filter -- iv "+iv+" key : "+key);
        doLog = sysDataRepo.doLookUpByNameStr("DO-LOG-FILTER", "0");
        log.info(" ### requestContext = " + doLog);  
        int prodOrDev = Integer.parseInt(sysDataRepo.doLookUpByNameStr("INTEGRATION-MODE", "0"));
       
        log.info("-- integration mode -- "+prodOrDev);
        String encryptedfeed = "";
        
        //ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        Method method = null;// methodInvoker.getMethod();

        // Get the HTTP Authorization header from the request
        String userAuthorizationHeader = requestContext.getHeaderString("USER_AUTHORIZATION");
        if(doLog.trim().equals("1"))log.info("#### userAuthorizationHeader : *** " + userAuthorizationHeader);
        
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(doLog.trim().equals("1"))log.info("-- #### authorizationHeader : *** " + authorizationHeader);
        
        if(authorizationHeader ==null)
        {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        
        if (!method.getName().endsWith("USX") && !method.getName().endsWith("NP") && !method.getName().endsWith("XOX") &&  (userAuthorizationHeader == null || !userAuthorizationHeader.startsWith("Bearer"))) {
            log.info("!! XXXXXX ---????## "+method.getName());
            throw new NotAuthorizedException("Authorization header must be provided");
            
        }
        
        if (method !=null && !method.getName().endsWith("XOX") && !method.getName().endsWith("XOXNP") && userAuthorizationHeader == null || (userAuthorizationHeader !=null && !userAuthorizationHeader.startsWith("Bearer") )) {
            log.info(" !!!---- XXXXXX --- "+method.getName());
            throw new HoptoolException(ErrorCodes.INVALID_USER_JWT, ErrorCodes.doErroDesc(ErrorCodes.INVALID_USER_JWT));
            
        }

        if (method == null) {
            requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
            return;
        }
       
        if(doLog.trim().equals("1"))log.info("  ### authorizationHeader +1+ = " + authorizationHeader.substring("Bearer".length()+1).length());
        
        //System.out.println("--- authorizationHeader = " +authorizationHeader);
        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()+1).trim();
        String usertoken =  method.getName().endsWith("USX")? userAuthorizationHeader.substring("Bearer".length()+1).trim():"NA";
        Jws<Claims> userParseClaimsJws = null;
        UserSecRequest doParseUserClientParams = null;
         Claims userClaims = null;
         String requestStr = "";
        if(doLog.trim().equals("1"))log.info(" ** token = " + token);
        try 
        {
            
           // System.out.println(" ####  = sysDataHelper.getProps(\"SYS_IV\") -- " +  sysDataHelper.getProps("SYS_IV")+" --- sysDataHelper.getProps(\"SYS_KEY\") -- "+sysDataHelper.getProps("SYS_KEY"));
            Key byClientKey = sysKeyGenerator.generateKey(iv, key);//clientInfoHelper.getByClientKey(apiuser);
           
            if(doLog.trim().equals("1"))log.info("key algo = " + byClientKey.getAlgorithm()+" : token.trim().length() "+token.trim().length());//+" : "+token.length());
            //System.out.println("byClientKey = " + byClientKey);
            if(method.getName().endsWith("USX"))
            {
              userParseClaimsJws = Jwts.parser().setSigningKey(byClientKey).parseClaimsJws(usertoken.trim());
              userClaims = userParseClaimsJws.getBody();
              doParseUserClientParams =  JsonbBuilder.create().fromJson(new AESCrypter(sysDataRepo.doLookUpByNameStr("SYS_KEY", "KEY"),sysDataRepo.doLookUpByNameStr("SYS_IV", "IV")).decrypt(userClaims.getSubject()), UserSecRequest.class);// new RequestUtil().doParseClientParams(new AESCrypter(defaultobjectbean.getSystemParamValue("DS_KEY").getParamValue(),defaultobjectbean.getSystemParamValue("SYS_IV").getParamValue()).decrypt(claims.getSubject()));
              //LOGGER.info(" -- doParseUserClientParams -- "+doParseUserClientParams);
            }
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(byClientKey).parseClaimsJws(token.trim());
            Claims claims = parseClaimsJws.getBody();
            
            //LOGGER.info("token -- doParseClientParams claims #= " + claims+" userClaims : "+userClaims);
            
            /*
            Add IP of calling client in the header so can validate if the IP used in the request is the same
             */
            String decrypt = new AESCrypter(key,iv).decrypt(claims.getSubject());
           // LOGGER.info(" --- SUB ---- "+decrypt);
            HeaderClientObj doParseClientParams =  JsonbBuilder.create().fromJson(decrypt, HeaderClientObj.class);// new RequestUtil().doParseClientParams(new AESCrypter(defaultobjectbean.getSystemParamValue("DS_KEY").getParamValue(),defaultobjectbean.getSystemParamValue("SYS_IV").getParamValue()).decrypt(claims.getSubject()));
            
            if(doLog.trim().equals("1"))
            {
                log.error("++------doParseClientParams = " + doParseClientParams);

                log.info("ID: " + claims.getId());
                log.info("Subject: " + claims.getSubject());
                //System.out.println("Subject: " + new AESCrypter(defaultobjectbean.getSystemParamValue("DS_KEY").getParamValue(),defaultobjectbean.getSystemParamValue("SYS_IV").getParamValue()).decrypt(claims.getSubject()));
                log.info("Issuer: " + claims.getIssuer());
                log.info("Expiration: " + claims.getExpiration());
                log.info("doParseUserClientParams: " + doParseUserClientParams);

                log.info(" ** called method = " + method.getName());
            }
            
            
            if(method.getName().equals("doListFERoleMenus"))
            {
               // System.out.println("   NO ARGS ");
            }
            
            if( method.getName().equals("doVOTP") || method.getName().equals("doListProfiledAccount") || method.getName().equals("doAddAccount") ||  method.getName().equals("doCreateProfile") ||  method.getName().equals("doUserLogin") ||  method.getName().equals("doBalanceEnquiry")  || method.getName().equals("doMiniStatement") ||  method.getName().equals("doStopCheque") || method.getName().equals("doChequeRequest") || method.getName().equals("doLocalFundsTransfer") || method.getName().equals("doExtFundsTransfer") ||  method.getName().equals("doAccountsList") || method.getName().equals("doPinValidation") ||   method.getName().equals("doChangePin") ||    method.getName().equals("doPinGeneration") ||  method.getName().equals("doNameEnquiry") ||   method.getName().equals("doExtNameEnquiry") ||   method.getName().equals("doTSQ"))        
            {
              
                encryptedfeed = IOUtils.toString(requestContext.getEntityStream(), Charsets.UTF_8);
                
                //System.out.println(" @@@@@@@@ encryptedfeed @@@@@@ = " + encryptedfeed);
                
                if(doLog.trim().equals("1"))log.info("encryptedfeed = " + encryptedfeed);
                
                
                encryptedfeed = new AESCrypter(key,iv).decrypt(encryptedfeed);
                
                if(doLog.trim().equals("1")) log.info(" ** encryptedfeed ** "+encryptedfeed+"##");// + new AESCrypter(doParseClientParams.getPx(),doParseClientParams.getRx()).decrypt(encryptedfeed));
            
            }
             System.out.println("@@ GOT HERE method.getName().endsWith(\"USX\") = " + method.getName().endsWith("USX")+" ");
            if(method.getName().endsWith("USX"))
            {
                     if(userAuthorizationHeader != null && !userAuthorizationHeader.trim().equals(""))
                     {
                         
                        encryptedfeed = IOUtils.toString(requestContext.getEntityStream(), Charsets.UTF_8);
                                    
                        log.info(" @@++++### +++++++ USX+ RAW ENCRYPTED  ~  MSG ---- = " + encryptedfeed);
                                    
                        log.info("+ --- doParseClientParams ---  "+doParseClientParams);
                        encryptedfeed = new AESCrypter(key,iv).decrypt(encryptedfeed.replaceAll("\"", ""));

                        log.info(" ++++### +++++++ USX+ PLAIN  ~  MSG ---- = " + encryptedfeed);
                     
                        log.info(" ++++### +++++++ USX+ PLAIN  ~  MSG --##-- = " + encryptedfeed);
                           
                        requestStr = encryptedfeed+"##"+new Gson().toJson(doParseUserClientParams);
                           
                        log.info("## doParseUserClientParams plain rqx=!!!>>>> " + requestStr);
                           
                        requestContext.setEntityStream(IOUtils.toInputStream(requestStr));
                     }
                     else
                     {
                           throw new HoptoolException(ErrorCodes.INVALID_USER_JWT, ErrorCodes.doErroDesc(ErrorCodes.INVALID_USER_JWT));
                     }
                 
                 
            }
            else if( method.getName().endsWith("XOX"))
            {
                
                    encryptedfeed = IOUtils.toString(requestContext.getEntityStream(), Charsets.UTF_8);
                    
                    encryptedfeed = new AESCrypter(key,iv).decrypt(encryptedfeed.replaceAll("\"", ""));

                   // LOGGER.info("doParseClientParams = @@@ encryptedfeed " + encryptedfeed);
              
                   // if(sysDataHelper.getProps("DO-LOG").equals("1"))logger.info("@@@#### valid token : " + token);
            
            }
            else if( method.getName().endsWith("XOXNP"))
            {
                    // LOGGER.info("# !!!doParseClientParams = @@@ encryptedfeed " + encryptedfeed+" method.getName() "+method.getName());
                     
                   // if(sysDataHelper.getProps("DO-LOG").equals("1"))logger.info("@@@#### valid token : " + token);
            
            }
           
            try
            {
               log.info(" doParseClientParams.toObj() "+doParseClientParams.toObj());
               //if(method.getName().endsWith("XOX") && !method.getName().equals("doUserLoginXOX") && !method.getName().equals("doChangePinUSX")) LOGGER.info(" CTR doParseClientParams = " + doParseClientParams); LOGGER.info(" XOX encryptedfeed = " + encryptedfeed);  LOGGER.info(" ### XOX doParseClientParams.toObj() = " + doParseClientParams.toObj());
               
               if(method.getName().endsWith("XOX"))
               {
                   requestContext.setEntityStream(IOUtils.toInputStream(encryptedfeed+"##"+doParseClientParams.toObj(), StandardCharsets.UTF_8));
               
               }
               else if(method.getName().endsWith("NP") && !method.getName().endsWith("XOXNP")) // added to grip to methods with no params
               {
                   log.info(" XXXX doParseClientParams.toObj() "+doParseClientParams.toObj());
                   requestContext.setEntityStream(IOUtils.toInputStream(encryptedfeed+"##"+doParseClientParams.toObj(), StandardCharsets.UTF_8));
               
               }
               else  if(method.getName().endsWith("USX"))
               {
                    
                    requestContext.setEntityStream(IOUtils.toInputStream(encryptedfeed+"##"+new Gson().toJson(doParseUserClientParams), StandardCharsets.UTF_8));
               
               }
               else
               {
                    encryptedfeed = IOUtils.toString(requestContext.getEntityStream(), Charsets.UTF_8);
                    
                    //LOGGER.info("%%doParseClientParams XXX @@@ encryptedfeed " + encryptedfeed);
               }
               
            }
            catch(Exception e)
            {
                log.info(" WAHALA : ",e);
            }

        }
        catch (io.jsonwebtoken.SignatureException e) 
        {
            //System.out.println("e = " + e.getMessage());
            e.printStackTrace();
            if(doLog.trim().equals("1"))log.error("#### invalid token yy : " + token);
            requestContext.abortWith(Response.status(ErrorCodes.JWT_SIGNATURE_EXCEPTION).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doErroDesc(ErrorCodes.JWT_SIGNATURE_EXCEPTION)).build()).build());
            if(doLog.trim().equals("1"))e.printStackTrace();
            if(doLog.trim().equals("1")) log.error("#### invalid token : sig ",e);
        }
        catch(io.jsonwebtoken.ExpiredJwtException x)
        {
            log.error("#### invalid token xx : " + token);
            requestContext.abortWith(Response.status(ErrorCodes.EXPIRED_JWT).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doErroDesc(ErrorCodes.EXPIRED_JWT)).build()).build());
            if(doLog.trim().equals("1"))x.printStackTrace();
              if(doLog.trim().equals("1")) log.error("#### invalid token : exp ",x);
        }
        catch(SecException x)
        {
            if(doLog.trim().equals("1"))log.info("####  error decrypting request : " + x.getMessage());
            requestContext.abortWith(Response.status(ErrorCodes.DECRYPTION_ERROR).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doErroDesc(ErrorCodes.DECRYPTION_ERROR)).build()).build());
            if(doLog.trim().equals("1"))x.printStackTrace();
            if(doLog.trim().equals("1")) log.info("#### invalid token : sex ",x);
        }
        catch (Exception e) 
        {
          
            if(doLog.trim().equals("1"))e.printStackTrace();
            if(doLog.trim().equals("1")) log.info("+#### invalid token : ## " + token);
            if(doLog.trim().equals("1")) log.error("#### invalid token : ex ",e);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doErroDesc(401)).build()).build());
        }
    }
    
}
