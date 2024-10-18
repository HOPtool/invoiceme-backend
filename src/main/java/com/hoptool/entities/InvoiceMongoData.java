
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.entities;


import com.hoptool.resources.ResourceHelper;
//import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.time.LocalDateTime;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 *
 * @author paycraftsystems-i
 */

@Slf4j
@ToString
//@MongoEntity(collection="signed_doc")
public class InvoiceMongoData extends PanacheMongoEntity {
   
    @BsonProperty("id")
    public ObjectId id;
    public String invoiceKey;
    public String businessId;
    public String irn;
    public String docData;
    public LocalDateTime loggedDate;
    
    
    public static InvoiceMongoData findByInvoiceKey(String documentKey){
        return find("invoiceKey", documentKey).firstResult();
    }

    public static InvoiceMongoData findInvoice(String businessId, String irn)
    {
        try 
        {
            PanacheQuery<PanacheMongoEntityBase> find = find("businessId = ?1 and irn= ?2 ", businessId, irn); 
    
             if( find !=null)
             {
                 return  find.firstResult();
             }
             else
             {
                return null;       
             }
            
        } catch (Exception e) {
            
            log.error("Exception e InvoiceMongoData ",e);
        }
      
       return null;
    }
    
    public JsonObject toJson() {
        JsonObjectBuilder job = Json.createObjectBuilder();
        ResourceHelper rh = new ResourceHelper();
        String ft = "";
        try 
        {
            
            
        } catch (Exception e) {
           
            log.error("Exception @@ InvoiceMongoData toJson() -- ",e);
            
        }
     return job.build();
    }
    
    
   
    
}