/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.exceptions;

/**
 *
 * @author root
 */
//import com.paycraftsystems.error.messages.ErrorCodes;
//import com.paycraftsystems.error.codes.ErrorCodes;
import com.hoptool.resources.ErrorCodes;
import jakarta.json.Json;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;



public class HoptoolException extends WebApplicationException {

    public HoptoolException(String message, Throwable cause) {
        super(Response.status(400).header("message", message).header("cause", cause.getMessage()).build());
    }
    
    public HoptoolException(String message) {
        super(Response.status(400).header("message", message).build());
    }
    
    public HoptoolException(int status, String message, Throwable cause) {
        super(Response.status(status).header("message", message).header("cause", cause.getMessage()).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doErroDesc(status)).build()).build());
    }
    
    public HoptoolException(int status, String cause) {
        
        
        super(Response.status(status).header("message", cause).header("cause", cause).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doErroDesc(status)).build()).build());
    }

}
