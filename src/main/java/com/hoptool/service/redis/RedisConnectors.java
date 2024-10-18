/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.service.redis;


import com.google.gson.Gson;
import com.hoptool.invoice.dto.SignInvoice;
import io.quarkus.redis.client.RedisClient;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;

import io.vertx.mutiny.redis.client.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.json.Json;
import jakarta.json.JsonObject;
//import jakarta.json.bind.JsonbBuilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paycraftsystems-i
 */
@Slf4j
@Singleton
public class RedisConnectors {
 
    @Inject
    RedisClient redisClient;
    
      
    @Inject
    ReactiveRedisClient reactiveRedisClient; 
    
    
    public String get(String key) {
        io.vertx.redis.client.Response resp = redisClient.get(key);
        //LOGGER.info("--##- resp = " + resp.toString());
       
        return resp ==null?"NA":resp.get(key).toString();
    }
    
    public String getV2String(String key) {
        io.vertx.redis.client.Response resp = redisClient.get(key);
        
        
        //LOGGER.info("--##- resp = " + resp.toString());
        
        // JsonObject jsonObject = resp !=null?JsonbBuilder.create().fromJson(resp.toString(), JsonObject.class):Json.createObjectBuilder().build();
        log.info("--**- resp jsonObject getV2String = " + resp.toString());
       
        return resp ==null?"NA":resp.toString();
    }
    
    public String getObjectInfo(String key) throws Exception{
        io.vertx.redis.client.Response resp = redisClient.get(key);
        
        return (resp !=null?resp.toString():"0");
    }
    //new Gson().toJson
    
    public JsonObject getJsonObject(String key) {
        io.vertx.redis.client.Response resp = redisClient.get(key);
        //LOGGER.info("---!! resp = " + (resp !=null?resp.toString():"-2"));
        JsonObject jsonObject = new Gson().fromJson(resp.toString(), JsonObject.class);
        //JsonObject jsonObject = resp !=null?JsonbBuilder.create().fromJson(resp.toString(), JsonObject.class):Json.createObjectBuilder().build();
        log.info("--**- resp jsonObject = " + jsonObject);
        
        return jsonObject;// resp ==null?"NA":resp.get(key).toString();//redisClient.get(key).toString();
    }
    
    
    public SignInvoice getInvoiceObject(String key) {
        io.vertx.redis.client.Response resp = redisClient.get(key);
        
        System.out.println("@@--- resp = " + resp.toString());
        //SignInvoice jsonObject = resp !=null?JsonbBuilder.create().fromJson(resp.toString(), SignInvoice.class):null;
        
        SignInvoice invoiceObject = resp !=null?new Gson().fromJson(resp.toString(), SignInvoice.class):null;
        log.info("--**- resp jsonObject = " + invoiceObject);
        
        return invoiceObject;// resp ==null?"NA":resp.get(key).toString();//redisClient.get(key).toString();
    }
    
    
    public JsonObject getJsonObjectGson(String key) {
        io.vertx.redis.client.Response resp = redisClient.get(key);
        String gsonStr = resp !=null?new Gson().fromJson(resp.toString(), String.class):"";
        log.info("--**- resp jsonObject = " + gsonStr);
        
        return null;// new Gson().t;// resp ==null?"NA":resp.get(key).toString();//redisClient.get(key).toString();
    }
    
    
    public String getQuestionsValidAnswer(String key) {
        io.vertx.redis.client.Response resp = redisClient.get(key);
        
        return  (resp !=null?resp.toString():"-2");// resp ==null?"NA":resp.get(key).toString();//redisClient.get(key).toString();
    }

    public void doSave(String key, JsonObject value) {
        redisClient.set(Arrays.asList(key, value.toString()));
    }
    
    public void doSaveBankKeyAndId(String key, String value) {
        redisClient.set(Arrays.asList(key, value));
    }
    
    public void doSaveBillerCategory(String key, String value) {
        redisClient.set(Arrays.asList(key, value));
    }

    
    //The question is passed with the language code
    public void doSaveQuestionAndAnswerByLangauge(String key, String value) {
        redisClient.set(Arrays.asList(key, value));
    }
    
    public void doSave(String key, String value) {
        redisClient.append(key, value);
    }
    
    public void doSaveBillerMenu(String key, String value) {
        redisClient.append(key, value);
    }
    
    public void doSaveBank(String key, String value) {
        //System.out.println("- key "+key+" - value = " + value);
        redisClient.append(key, value);
    }
    
    public String getBankInfoById(String key) {
       io.vertx.redis.client.Response resp =  redisClient.get(key);
       
          System.out.println("getBankInfoById ="+key+" " + resp);
          
          
        return (resp !=null)? resp.toString():"0";
    }
    
    
    

    /*
     public void set(String key, Integer value) {
        redisClient.set(Arrays.asList(key, value.toString()));
    }

    public void increment(String key, Integer incrementBy) {
        redisClient.incrby(key, incrementBy.toString());
    }
    */
   

   public Uni<Void> del(String key) {
        return reactiveRedisClient.del(Arrays.asList(key))
                .map(response -> null);
    }

    

    public Uni<List<String>> keys() {
        return reactiveRedisClient
                .keys("*")
                .map(response -> {
                    List<String> result = new ArrayList<>();
                    for (Response r : response) {
                        result.add(r.toString());
                    }
                    return result;
                });
    }
}
    

