/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.service.redis;

/**
 *
 * @author paycraftsystems-i
 */
//import com.paycraftsystems.hellovas.controllers.SysDataController;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import io.smallrye.mutiny.Uni;
import jakarta.json.Json;
import jakarta.json.JsonObject;
//import jakarta.json.bind.JsonbBuilder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Slf4j
public class RedisClient {
  
    
   // @Inject
   // SysDataController sysDataController;
    
    
    private ReactiveKeyCommands<String> keyCommands; 
    private ValueCommands<String, String> countCommands; 

    public RedisClient(RedisDataSource ds, ReactiveRedisDataSource reactive) { 
        countCommands = ds.value(String.class); 
        keyCommands = reactive.key();  

    }


    String get(String key) {
        String value = countCommands.get(key); 
        if (value == null) {
            return "";
        }
        return value;
    }
    
     public JsonObject getJsonObject(String key) {
        JsonObject jsonObject =null;
        String value = countCommands.get(key); 
        if (value == null) {
            return Json.createObjectBuilder().build();
        }
        else
        {
             log.info(" --Redis Manager value --  "+value);
             jsonObject = null;// value !=null?JsonbBuilder.create().fromJson(value.toString().replaceAll("SUBX-", "").replaceAll("DATASYNC-", ""), JsonObject.class):Json.createObjectBuilder().build();
       
        }
        
        return jsonObject;
    }
     
     
    public boolean doesObjectExist(String key) {
        JsonObject jsonObject =null;
        String value = countCommands.get(key); 
        if(value == null) {
            
            return false;
        }
        else
        {
            return true;
       
        }
   
    }
   

    public void set(String key, String value) {
        countCommands.set(key, value); 
    }
    
    
    public boolean setObjectIfNotExists(String key, String value)
    {
        
        try 
        {
            if(!doesObjectExist(key))
            {
                 log.info("@-- setObjectIfNotExists about to write -- "+key);
                 countCommands.set(key, value);
                 
                 return true;
            }
            else
            {
                return false;
            }
            
        } catch (Exception e) {
            
           log.info("Exception @ setObjectIfNotExists : ",e);
           // throw new Exception(e);
           
           return false;
        }
        
    }
   
    

   public  Uni<Void> del(String key) {
        return keyCommands.del(key) 
            .replaceWithVoid();
    }

    Uni<List<String>> keys() {
        return keyCommands.keys("*"); 
    }
   
}
