/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.entities;


import com.hoptool.invoice.dto.SignInvoiceObj;
import com.hoptool.invoice.dto.SignInvoiceValidationObj;
import com.hoptool.resources.ResourceHelper;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.find;
import io.smallrye.common.constraint.Nullable;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */
@Entity
@Table(name = "sign_invoice_request_log")
@Slf4j
@ToString()
public class SignInvoiceRequestLog extends PanacheEntityBase
{
    
      
    @Id
    @GeneratedValue
    @Column(name="tid")
    public long tid;
    
    
    @NotNull
    @Column(name="business_id")
    public String businessId;
    
//    @NotNull
//    @Column(name="request_id")
//    public String requestId;
    
//    @NotNull
//    @Column(name="validation_count")
//    public Integer validationCount;
    
    @NotNull
    @Column(name="irn")
    public String irn;
    @NotNull
    @Column(name="issue_date")
    public LocalDate issueDate;
    
    @Nullable
    @Column(name="due_date")
    public LocalDate dueDate;
    
    @Column(name="created_date")
    public LocalDateTime createdDate;
    
    @Column(name="issue_time")
    public LocalTime issueTime;
    
    @NotBlank
    @Size(min=3, max = 15, message="invalid payment_status  should be in format YYYY-MM-DD")
    @Column(name="payment_status")
    public String paymentStatus;
    
    @NotBlank
    @Size(min=5,  message="invalid note  should have a minimum of 5 characters")
    @Column(name="note")
    public String note;
    
    @Size(min=3, max = 30, message="invalid buyer_reference  should be in format YYYY-MM-DD")
    @Column(name="buyer_reference")
    public String buyerReference;
    
    
    
    @Nullable
   // @Size(min=10, max = 10, message="invalid tax_point_date  should be in format YYYY-MM-DD")
    @Column(name="tax_point_date")
    public LocalDate taxPointDate;
    @NotBlank
    @Size(min=2,  message="invalid document currency code  should have a minimum of 2 characters")
    @Column(name="document_currency_code")
    public String documentCurrencyCode ;
    @NotBlank
    @Size(min=2,  message="invalid tax currency code  should have a minimum of 2 characters")
    @Column(name="tax_currency_code")
    public String taxCurrencyCode;
    @NotBlank
    @Size(min=2,message="invalid document currency code  should have a minimum of 2 characters")
    @Column(name="accounting_cost")
    public String accountingCost;
    
    @Column(name="order_reference")
    public String orderReference;
    
    @Nullable
    //@Size(min=10, max = 10, message="invalid actual_delivery_date  should be in format YYYY-MM-DD")
    @Column(name="actual_delivery_date")
    public LocalDate actualDeliveryDate;
    
    
    //@Nullable
    //@Size(min=10, max = 10, message="invalid actual_delivery_date  should be in format YYYY-MM-DD")
    @Column(name="response_date")
    public LocalDateTime responseDate;
    
    @Column(name="status")
    public int status;
    
    
    @Nullable
    @Size(min=10,  message="invalid payment_terms_note  should be not be null")
    @Column(name="payment_terms_note")
    
    public String paymentTermsNote;
    
    @Nullable
    @Size(min=4, max=50, message="invalid payment_terms_note  should be in format YYYY-MM-DD")
    @Column(name="product_category")
    public String productCategory;
    

    
     public static SignInvoiceRequestLog doLookUp(SignInvoiceRequestLog obj) {
     if(obj !=null)
     {
        log.info("obj = " + obj.irn);
        return SignInvoiceRequestLog.find("irn = ?1", obj.irn).firstResult();
     }
     else
     {
         return null;
     }
    
    }
     
    public static SignInvoiceRequestLog doLookUp(SignInvoiceObj obj) {
     log.info("obj = " + obj.irn);
     return SignInvoiceRequestLog.find("irn = ?1", obj.irn).firstResult();
    
    }

    public  static SignInvoiceRequestLog doLookUpInvoiceValidationCount(SignInvoiceValidationObj obj) {
     log.info("obj = " + obj.irn);
     return InvoiceValidationRequestLog.find("irn = ?1  and status = 200 and max(tid)", obj.irn).firstResult();
    //List<InvoiceDownloadsLogs>
    }
    
    
    public  static SignInvoiceRequestLog doLookUpInvoiceValidation(SignInvoiceValidationObj obj) {
     log.info("obj = " + obj.irn);
     return find("irn = ?1  and status = 200 and max(tid)", obj.irn).firstResult();
    //List<InvoiceDownloadsLogs>
    }
    
    
    
    @Transactional
    public static  SignInvoiceRequestLog doSync(SignInvoiceRequestLog request, int code) throws Exception {
        SignInvoiceRequestLog obj = new SignInvoiceRequestLog();
        try 
        {
           SignInvoiceRequestLog objx = doLookUp(request);
           log.info("objx = " + objx+" : : code "+code);
           if(objx != null)
           {
            objx.responseDate = LocalDateTime.now();
            objx.status = code;
            /*
            if(objx.status == 913)
            {
                objx.validationCount = (objx.validationCount == null?0+1:objx.validationCount+1);
            }
           */
           }
           
           return Panache.getEntityManager().merge(objx);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }
    
    
    
    @Transactional
    public static SignInvoiceRequestLog doLog(SignInvoiceObj request) throws Exception {
        SignInvoiceRequestLog obj = null;
        ResourceHelper rs = new ResourceHelper();
        String productCategory ="";
        JsonArrayBuilder jar = Json.createArrayBuilder();
        try 
        {
           if(request.invoice_line !=null)
           {
               request.invoice_line.stream().forEach(a->
               {
                   
                   productCategory.concat(a.product_category().concat(","));
                    jar.add(a.product_category().concat(","));
                       
               });
           }
           log.info(" -- productCategory -- "+productCategory +" --"+request.payment_terms_note.length()+"  "+request.payment_terms_note);
           obj = new SignInvoiceRequestLog();
           obj.businessId = request.business_id;
           obj.irn = request.irn;
           obj.createdDate = LocalDateTime.now();
           //obj.requestId = request.requestId;
           //obj.dueDate = rs.strToLD(request.due_date);
           obj.issueDate = rs.strToLD(request.issue_date);
           obj.irn = request.irn;
           obj.issueTime = rs.strToTime(request.issue_time);
           obj.accountingCost = request.accounting_cost;
           obj.documentCurrencyCode = request.document_currency_code;
           obj.buyerReference = request.buyer_reference;
           obj.note = request.note;
           obj.orderReference = request.order_reference;
           obj.paymentStatus = request.payment_status;
           obj.paymentTermsNote = request.payment_terms_note;
           obj.taxPointDate = rs.strToLD(request.tax_point_date);
           obj.actualDeliveryDate = rs.strToLD(request.actual_delivery_date);
           obj.taxCurrencyCode = request.tax_currency_code;
           obj.buyerReference = request.buyer_reference;
           obj.productCategory = jar.build().toString();
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
    
   
    


}
