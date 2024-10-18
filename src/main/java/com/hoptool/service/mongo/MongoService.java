/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.service.mongo;

import com.google.gson.Gson;
import com.hoptool.invoice.dto.SignInvoice;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;



/**
 *
 * @author paycraftsystems-i
 */
@Slf4j
@ApplicationScoped
public class MongoService {
    
    
    @Inject 
    MongoClient mongoClient;
    
    
    public List<SignInvoice> list(SignInvoice invoice){
        List<SignInvoice> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                
              //  MongoCollection collection = getCollection().;
                
//                Fruit fruit = new Fruit();
//                fruit.setName(document.getString("name"));
//                fruit.setDescription(document.getString("description"));
//                list.add(fruit);
;
            }
        } finally {
            cursor.close();
        }
        return list;
    }
    
    public Document doFindDocument(String collectionName, String docKey) {
        System.out.println("docKey = " + docKey);
        Document doc = null;
        try 
        {
            
            MongoDatabase database = mongoClient.getDatabase("firs_eivc");
            
            MongoCollection<Document> collection = database.getCollection(collectionName);
            
            Document filter = new Document("docKey", docKey);
            doc = collection.find(filter).first();
           
            
        } 
        catch (Exception e) {
        
            log.error("Exception -- doFindDocument ",e);
        }
     return doc;
    }
    
    
    
    public void add(SignInvoice invoice, LocalDateTime now){
        Document document = new Document()
                .append("docKey", invoice.business_id()+invoice.irn())
                .append("businessId", invoice.business_id())
                .append("irn", invoice.irn())
                .append("createdDate", now)
                .append("signed_doc", new Gson().toJson(invoice));
        getCollection().insertOne(document);
    }
    
    
     private MongoCollection getCollection(){
        return mongoClient.getDatabase("firs_eivc").getCollection("signed_documents");
    }

    
}
