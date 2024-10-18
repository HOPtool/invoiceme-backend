/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.jboss.logging.annotations.Message;

/**
 *
 * @author paycraftsystems-i
 */
public record SignInvoiceValidation(
    @NotBlank
    @Size(min=4, max = 50, message="invalid business id passed")
    String business_id,
    @NotBlank
    @Size(min=30, max = 50, message="invalid requestId passed")
    String requestId,
    @NotBlank
    @Size(min=3, max = 50, message="invalid irn passed")
    String irn,
    @NotBlank
    @Size(min=10, max = 10, message="invalid issue date should be in format YYYY-MM-DD")
    String issue_date,
    //@NotBlank
    //@Size(min=10, max = 10, message="invalid due date should be in format YYYY-MM-DD")
    String due_date, //optional
    String issue_time,
    @NotBlank
    @Size(min=3, max = 10, message="invalid invoice_type_code  should be in format YYYY-MM-DD")
    String invoice_type_code,
    @NotBlank
    @Size(min=4, max = 9, message="invalid payment_status  should be either PENDING, PAID, CANCELLED")
    String payment_status,
    @NotBlank
    @Size(min=5,  message="invalid note  should have a minimum of 5 characters")
    String note,
    @NotBlank
    @Size(min=10, max = 10, message="invalid tax_point_date  should be in format YYYY-MM-DD")
    String tax_point_date,
    @NotBlank
    @Size(min=2,  message="invalid document currency code  should have a minimum of 2 characters")
    String document_currency_code,
    @NotBlank
    @Size(min=2,  message="invalid tax currency code  should have a minimum of 2 characters")
    String tax_currency_code,
    @NotBlank
    @Size(min=2,  message="invalid document currency code  should have a minimum of 2 characters")
    String accounting_cost,
    @NotBlank
    @Size(min=5, message="invalid buyer_reference  should have a minimum of 5 characters")
    String buyer_reference,
    InvoiceDeliveryPeriod invoice_delivery_period,
    String order_reference,
    @NotNull(message= "Dispatch document reference cannot be null")
    List<BillingRefData> billing_reference,
    @NotNull(message= "Dispatch document reference cannot be null")
    DispatchDocumentReference dispatch_document_reference,
    @NotNull(message= "receipt document reference cannot be null")
    ReceiptDocumentReference receipt_document_reference,
    @NotNull(message= "receipt document reference cannot be null")
    OriginatorDocumentReference originator_document_reference,
    @NotNull(message= "receipt document contract_document_reference cannot be null")
    ContractDocumentReference contract_document_reference,
    List<AdditionalDocumentReference>  additional_document_reference,
    AccountingSupplierParty accounting_supplier_party,
    AccountingCustomerParty accounting_customer_party,
    @NotBlank
    @Size(min=10, max = 10, message="invalid actual delivery date should be in format YYYY-MM-DD")
    String actual_delivery_date,
    List<PaymentMeansData> payment_means,
    String project_reference,
    String payment_terms_note,
    List<AllowanceChargeData> allowance_charge,
    List<TaxTotalObj> tax_total,
    @NotNull(message="legal_monetary_total cannot be null")
    LegalMonetaryTotalObj legal_monetary_total,
    List<InvoiceLineItemInfo> invoice_line
    
   )
{
    
    
}
