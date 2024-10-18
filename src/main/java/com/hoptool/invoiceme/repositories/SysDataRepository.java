/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.repositories;




import com.hoptool.invoiceme.dto.SysDataDTOObj;
import com.hoptool.invoiceme.entities.SysData;

import com.hoptool.invoiceme.enumz.ResourceStatusEnum;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
@Slf4j
public class SysDataRepository implements  PanacheRepository<SysData> {
    
    
    public SysData doLookUpById(long tid) {
    // log.info("obj = " + obj.reference);
     return find("tid = ?1", tid).firstResult();
    
    }
    
    public SysData doLookUpByName(String name) {
     log.info("obj = " + name);
     return find("paramName = ?1 ", name).firstResult();
    
    }
    
    
    public String doLookUpByNameStr(String name, String defaultTo) {
      log.info("obj = " + name);
      SysData firstResult = find("paramName = ?1 ", name).firstResult();
      return firstResult !=null?firstResult.paramValue:defaultTo;
    
    }
   
    
    /*

     public PanacheQuery<InvoiceUpdateLogs> doList(LocalDateTime start, LocalDateTime end) {
       
        return find("requestDate between ?1 and ?2 order by requestDate desc", start, end);
    
    }
     
     
    public PanacheQuery<InvoiceUpdateLogs> doList(LocalDateTime start, LocalDateTime end, int status) {
       
        return find("requestDate between ?1 and ?2 and status = ?3 order by requestDate desc", start, end, status);
    
    }
    
    */
    
    
    @Transactional
    public SysData doLog(SysDataDTOObj request) throws Exception {
        SysData obj = null;
        String firstname = "", lastname ="";
        try 
        {
            
           SysData doLookUpByParamName = doLookUpByName(request.paramName);
           log.info("--doLookUpByParamName -- "+doLookUpByParamName);
           if(doLookUpByParamName == null)
           {
          
                obj = new SysData();
          
                
           
                obj.paramName = request.paramName;
                obj.paramValue = request.paramValue;
                obj.createdDate = LocalDateTime.now();
                obj.status = ResourceStatusEnum.INACTIVE.name();
                obj = Panache.getEntityManager().merge(obj);
           }
           else
           {
               return obj;
           }
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
    }
    
    /*
    @Transactional
    public InvoiceUpdateLogs doSync(UpdateInvoiceObj request, int code, InvoiceUpdateLogs maxCountObj) throws Exception {
        InvoiceUpdateLogs obj = new InvoiceUpdateLogs();
        try 
        {
           InvoiceUpdateLogs objx = doLookUpByIRNAndRequestId(request);
           
           log.info("-- do sync objx = " + objx+" : : code "+code);
           if(objx != null)
           {
             log.info(" got inside objx = " + objx);
             System.out.println("max count objx = " + maxCountObj);
             objx.responseDate = LocalDateTime.now();
             objx.status = code;
                if(maxCountObj !=null)
                {
                    log.info(" inside maxCountObj = " +maxCountObj);
                    
                    if(objx.duplicateCount == null)
                    {
                        objx.duplicateCount = 0;
                    }
                    if(objx.status ==200)
                    {
                        objx.updateCount = maxCountObj.updateCount==null?0+1:(maxCountObj.updateCount==null?1:maxCountObj.updateCount+1);
                    }
                    else if(objx.status == 913)
                    {
                         objx.duplicateCount = objx.duplicateCount==null?0+1: objx.duplicateCount+1;//.(maxCountObj.duplicateCount==null?1:maxCountObj.confirmationCount+1);
                   
                    }
                    return Panache.getEntityManager().merge(objx);
                }
                else
                {
                    if(objx.status ==200)
                    {
                         
                         objx.updateCount = objx.updateCount==null?0+1:(objx.updateCount+1);
                    }
                    else if(objx.status == 913)
                    {
                         objx.duplicateCount = objx.duplicateCount==null?0+1: objx.duplicateCount+1;
                    }
                    
                    if(objx.duplicateCount == null)
                    {
                        objx.duplicateCount = 0;
                    }
                            
                     return Panache.getEntityManager().merge(objx);
                }
             
           }
           else
           {
               return null;
           }
          
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }
   

  */
    
}
