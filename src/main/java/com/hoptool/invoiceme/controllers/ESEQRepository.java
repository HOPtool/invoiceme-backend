/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.invoiceme.controllers;



import com.hoptool.invoiceme.entities.ESeq;
import com.hoptool.resources.ResourceHelper;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.math.BigInteger;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author taysayshaguy
 */

@ApplicationScoped
public class ESEQRepository implements PanacheRepository<ESeq>
{
    private static Logger LOGGER = LoggerFactory.getLogger(ESEQRepository.class);
   
  
    @Transactional
    public synchronized ESeq genCode(String  seqence_code)
    {
        ESeq resp = null;
        try
        {
            
           if(seqence_code != null && !seqence_code.trim().equals(""))
           {
             
               resp = find("passed", seqence_code.trim()).firstResult();
              
              
              if(resp == null)
              {
                   
                      resp = new ESeq();
                      resp.lastSeq = BigInteger.ONE.intValue();
                      resp.length = 20;
                      resp.seqCode = seqence_code.trim().toUpperCase();
                      
                      resp =  Panache.getEntityManager().merge(resp);
              }
              else
              {
                      resp.lastSeq = resp.lastSeq += BigInteger.ONE.intValue();
                      resp.length = 20;
                      resp.seqCode = seqence_code.trim().toUpperCase();
                      resp =  Panache.getEntityManager().merge(resp);
                  
              }
              
           
           }
           
          
        }
        catch(Exception ex)
        {  //resp = "1";
            ex.printStackTrace();
        }
       return resp;
    }
    
    public synchronized String genCode(String  seqence_code, int len)
    {
        //System.out.println("genCode len = " + len+" - seqence_code : "+seqence_code);
        ESeq resp = null;
        try
        {
            
           if(seqence_code != null && !seqence_code.trim().equals(""))
           {
             
              resp = ESeq.findBySeqCode(seqence_code.trim());
              
              if(resp == null)
              {
                      resp = new ESeq();
                      resp.lastSeq = BigInteger.ONE.intValue();
                      resp.length = len;
                      resp.seqCode = seqence_code.trim().toUpperCase();
                      
                      resp =  Panache.getEntityManager().merge(resp);
              }
              else
              {
                      resp.lastSeq = resp.lastSeq += BigInteger.ONE.intValue();
                      resp.length = len;
                      resp.seqCode = seqence_code.trim().toUpperCase();
                      resp =  Panache.getEntityManager().merge(resp);
                  
              }
              
           
           }
           
          
        }
        catch(Exception ex)
        {  //resp = "1";
            ex.printStackTrace();
        }
       
        return resp !=null?(new ResourceHelper().padZero(len-  String.valueOf(resp.lastSeq).length())+resp.lastSeq):"";
     
    }
   
}
