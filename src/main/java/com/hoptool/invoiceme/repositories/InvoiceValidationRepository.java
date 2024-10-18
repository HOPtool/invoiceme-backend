/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.repositories;


import com.hoptool.entities.InvoiceValidationRequestLog;
import com.hoptool.invoice.dto.SignInvoiceValidationObj;
import com.hoptool.resources.ResourceHelper;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
@Slf4j
public class InvoiceValidationRepository implements  PanacheRepository<InvoiceValidationRequestLog>, Serializable{
    
    
     
    public InvoiceValidationRequestLog doLookUp(SignInvoiceValidationObj obj) {
     log.info("obj = " + obj.requestId);
     return find("requestId = ?1", obj.requestId).firstResult();
    
    }
    
    
    public InvoiceValidationRequestLog doLookUpValidationInvoiceRequest(SignInvoiceValidationObj obj) {
     log.info("obj = " + obj.requestId);
     return find("requestId = ?1 and irn = ?2 ", obj.requestId, obj.irn).firstResult();
    
    }

     public PanacheQuery<InvoiceValidationRequestLog> doList(LocalDateTime start, LocalDateTime end) {
       
        return find("requestDate between ?1 and ?2 order by requestDate desc", start, end);
    
    }
     
     
    public PanacheQuery<InvoiceValidationRequestLog> doList(LocalDateTime start, LocalDateTime end, int status) {
       
        return find("requestDate between ?1 and ?2 and status = ?3 order by requestDate desc", start, end, status);
    
    }
    
     public   InvoiceValidationRequestLog doLookUpInvoiceValidationCount(SignInvoiceValidationObj obj) {
      log.info("obj = " + obj.irn);
      return find("irn = ?1  and status = 200 order by tid desc  limit 1", obj.irn).firstResult();

    }
   
    
    
    @Transactional
    public InvoiceValidationRequestLog doLog(SignInvoiceValidationObj request) throws Exception {
        System.out.println("request = " + request);
        InvoiceValidationRequestLog obj = null;
        ResourceHelper rs = new ResourceHelper();
        String productCategory ="";
        JsonArrayBuilder json = Json.createArrayBuilder();
        try 
        {
           //System.out.println("@@@--### "+request.invoice_line);
           if(request.invoice_line !=null)
           {
               request.invoice_line.stream().forEach(a->
               {
                   //System.out.println(" ---  here -- "+a.product_category());
                   productCategory.concat(a.product_category().concat(","));
                   
                    //System.out.println(" --- ***   here -- productCategory "+productCategory);
                     
                    json.add(a.product_category()+",");
               });
           }
           log.info(" -- productCategory -- "+productCategory+" json : ");
           obj = new InvoiceValidationRequestLog();
           obj.businessId = request.business_id;
           obj.irn = request.irn;
           obj.createdDate = LocalDateTime.now();
           obj.requestId = request.requestId;
           obj.dueDate = rs.strToLD(request.due_date);
           obj.issueDate = rs.strToLD(request.issue_date);
           obj.irn = request.irn;
           obj.accountingCost = request.accounting_cost;
           obj.documentCurrencyCode = request.document_currency_code;
           obj.buyerReference = request.buyer_reference;
           obj.note = request.note;
           obj.orderReference = request.order_reference;
           obj.paymentStatus = request.payment_status;
           //obj.productCategory = request.
           obj.paymentTermsNote = request.payment_terms_note;
           obj.taxPointDate = rs.strToLD(request.tax_point_date);
           obj.actualDeliveryDate = rs.strToLD(request.actual_delivery_date);
           obj.requestId = request.requestId;
           obj.taxCurrencyCode = request.tax_currency_code;
           obj.buyerReference = request.buyer_reference;
           obj.validationCount = 1;
           obj.productCategory = json.build().toString();//productCategory;// (request.invoice_line !null && request.invoice_line.get(0) !=null)? request.invoice_line.get(0).product_category():"NA";// &&  &&  productCategory;
           obj.status = 0;
           obj = Panache.getEntityManager().merge(obj);
          // return obj;
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
    }
    
    
    @Transactional
    public InvoiceValidationRequestLog doSync(SignInvoiceValidationObj request, int code) throws Exception {
        InvoiceValidationRequestLog obj = new InvoiceValidationRequestLog();
        try 
        {
           InvoiceValidationRequestLog objx = doLookUp(request);
           log.info("objx = " + objx+" : : code "+code);
           if(objx != null)
           {
            objx.responseDate = LocalDateTime.now();
            objx.status = code;
           }
           
           return Panache.getEntityManager().merge(objx);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }
    
    
    public InvoiceValidationRequestLog doLookUpByIRNAndRequestId(SignInvoiceValidationObj obj) {
      log.info("obj = " + obj.irn+" : "+obj.requestId);
     return find("irn = ?1 and requestId = ?2 ", obj.irn, obj.requestId).firstResult();
    
    }
    
    
    
    @Transactional
    public static  InvoiceValidationRequestLog doSyncValidationCount(InvoiceValidationRequestLog objx, InvoiceValidationRequestLog existing) throws Exception {
       InvoiceValidationRequestLog obj = new InvoiceValidationRequestLog();
       try 
        {
         
           log.info(" doSyncConfirmationCount objx = " + objx);
           log.info(" existing "+existing);
           
           //doLookUpInvoiceConfirmationCount
           if(objx == null && existing !=null)
           {
          
               existing.responseDate  = existing.responseDate;
               existing.status = existing.status;
               existing.validationCount = existing.validationCount == null?1:existing.validationCount+1;
              
               return Panache.getEntityManager().merge(existing);
           }
           else if(objx != null && existing !=null)
           {
                 System.out.println(" existing  = " +existing);
                 System.out.println(" Here  = " +objx);
                 existing.responseDate  = objx.responseDate;
                 existing.status = objx.status;
                 existing.validationCount = objx.validationCount==null?0+1:objx.validationCount+1;
                 //objx
               return Panache.getEntityManager().merge(existing);
               
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
    
    
    @Transactional
    public InvoiceValidationRequestLog doSync(SignInvoiceValidationObj request, int code, InvoiceValidationRequestLog maxCountObj) throws Exception {
        InvoiceValidationRequestLog obj = new InvoiceValidationRequestLog();
        try 
        {
           InvoiceValidationRequestLog objx = doLookUpByIRNAndRequestId(request);
           
           log.info("-- do sync objx = " + objx+" : : code "+code);
           if(objx != null)
           {
             System.out.println(" got inside objx = " + objx);
             System.out.println("max count objx = " + maxCountObj);
             objx.responseDate = LocalDateTime.now();
             objx.status = code;
                if(maxCountObj !=null)
                {
                    System.out.println(" inside maxCountObj = " +maxCountObj);
                    if(objx.status ==200)
                    {
                        objx.validationCount = maxCountObj.validationCount ==null?0+1:(maxCountObj.validationCount==null?1:maxCountObj.validationCount+1);
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
                         
                         objx.validationCount = objx.validationCount==null?0+1:(objx.validationCount+1);
                    }
                    else if(objx.status == 913)
                    {
                         objx.duplicateCount = objx.duplicateCount==null?0+1: objx.duplicateCount+1;
                    }
                            
                     return Panache.getEntityManager().merge(objx);
                }
             
           }
           else
           {
               return null;
           }
           /*
           else if(objx != null && existing !=null)
           {
                 System.out.println(" existing  = " +existing);
                 System.out.println(" Here  = " +objx);
                 existing.responseDate  = objx.responseDate;
                 existing.status = objx.status;
                 existing.confirmationCount = objx.confirmationCount==null?0+1:objx.confirmationCount+1;
                 //objx
               return Panache.getEntityManager().merge(existing);
               
           }
           */
           // System.out.println("about to persist objx = " + objx);
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }

   
    
    
    
}
