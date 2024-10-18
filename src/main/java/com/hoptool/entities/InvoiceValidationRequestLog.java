/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.entities;


//import static com.hoptool.entities.InvoiceConfirmationLogs.doLookUp;
import com.hoptool.invoice.dto.SignInvoiceValidationObj;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.find;
import io.smallrye.common.constraint.Nullable;
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
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */


@Entity
@Table(name = "invoice_validation_logs")
@Slf4j
public class InvoiceValidationRequestLog  extends PanacheEntityBase{
    
      
    @Id
    @GeneratedValue
    @Column(name="tid")
    public long tid;
    
    
    @NotNull
    @Column(name="business_id")
    public String businessId;
    
    @NotNull
    @Column(name="request_id")
    public String requestId;
    
    @NotNull
    @Column(name="validation_count")
    public Integer validationCount;
    
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
    
    @Column(name="duplicate_count")
    public Integer duplicateCount;
    
    
    
    @Nullable
    //@Size(min=10, max = 10, message="invalid tax_point_date  should be in format YYYY-MM-DD")
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
    @Size(min=10,  message="invalid payment_terms_note  should be in not be null")
    @Column(name="payment_terms_note")
    public String paymentTermsNote;
    
    @NotBlank
    @Size(min=4, max=50, message="invalid product_category  should be not be null")
    @Column(name="product_category")
    public String productCategory;
    
    
    public static InvoiceValidationRequestLog doLookUp(SignInvoiceValidationObj obj) {
     log.info("obj = " + obj.requestId);
     return InvoiceValidationRequestLog.find("requestId = ?1", obj.requestId).firstResult();
    
    }

    public  static InvoiceValidationRequestLog doLookUpInvoiceValidationCount(SignInvoiceValidationObj obj) {
     log.info("obj = " + obj.irn);
     return InvoiceValidationRequestLog.find("irn = ?1  and status = 200 order by tid desc  limit 1", obj.irn).firstResult();
    //List<InvoiceDownloadsLogs>
    }
    
    
    public  static InvoiceValidationRequestLog doLookUpInvoiceValidation(SignInvoiceValidationObj obj) {
     log.info("obj = " + obj.irn);
     return find("irn = ?1  and status = 200 and max(tid)", obj.irn).firstResult();
    //List<InvoiceDownloadsLogs>
    }
    
    
    @Transactional
    public static  InvoiceValidationRequestLog doSync(SignInvoiceValidationObj request, int code) throws Exception {
        InvoiceValidationRequestLog obj = new InvoiceValidationRequestLog();
        try 
        {
           InvoiceValidationRequestLog objx = doLookUp(request);
           log.info("objx = " + objx+" : : code "+code);
           if(objx != null)
           {
            objx.responseDate = LocalDateTime.now();
            objx.status = code;
            if(objx.status == 913)
            {
                objx.validationCount = (objx.validationCount == null?0+1:objx.validationCount+1);
            }
           }
           
           return PanacheEntity.getEntityManager().merge(objx);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }
    
    
    
    @Transactional
    public static  InvoiceValidationRequestLog doSyncValidationCount(InvoiceValidationRequestLog objx) throws Exception {
        InvoiceValidationRequestLog obj = new InvoiceValidationRequestLog();
        try 
        {
         
           log.info(" doSyncVerificationCount objx = " + objx);
           if(objx != null)
           {
          
                objx.validationCount = objx.validationCount == null?0:objx.validationCount+1;
           }
           
           
           return PanacheEntity.getEntityManager().merge(objx);
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     
    }
    
    

}
