/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import java.util.List;
import lombok.ToString;

/**
 *
 * @author paycraftsystems-i
 */
@ToString
public class SignInvoiceValidationObj
{

    public SignInvoiceValidationObj(SignInvoiceValidation objx) {
        
        //System.err.println("*** SignInvoiceObj objx = " +  objx);
        
        this.business_id = objx.business_id();
        this.irn = objx.irn();
        this.requestId = objx.requestId();
        this.issue_date = objx.issue_date();
        this.due_date = objx.due_date();
        this.issue_time = objx.issue_time();
        this.invoice_type_code = objx.invoice_type_code();
        this.payment_status = objx.payment_status();
        this.note = objx.note();
        this.tax_point_date = objx.tax_point_date();
        this.document_currency_code = objx.document_currency_code();
        this.tax_currency_code = objx.tax_currency_code();
        this.accounting_cost = objx.accounting_cost();
        this.buyer_reference = objx.buyer_reference();
        this.invoice_delivery_period = objx.invoice_delivery_period();
        this.order_reference = objx.order_reference();
        this.billing_reference = objx.billing_reference();
        this.dispatch_document_reference = objx.dispatch_document_reference();
        this.receipt_document_reference = objx.receipt_document_reference();
        this.originator_document_reference = objx.originator_document_reference();
        this.contract_document_reference = objx.contract_document_reference();
        this.additional_document_reference = objx.additional_document_reference();
        this.accounting_supplier_party = objx.accounting_supplier_party();
        this.accounting_customer_party = objx.accounting_customer_party();
        this.actual_delivery_date = objx.actual_delivery_date();
        this.payment_means = objx.payment_means();
        this.project_reference = objx.project_reference();
        this.payment_terms_note = objx.payment_terms_note();
        this.allowance_charge = objx.allowance_charge();
        this.tax_total = objx.tax_total();
        this.legal_monetary_total = objx.legal_monetary_total();
        this.invoice_line = objx.invoice_line();
        
    }
   
    public String business_id;
    public String requestId;
    public String irn;
    public String issue_date;
    public String due_date;
    public String issue_time;
    public String invoice_type_code;
    public String payment_status;
    public String note;
    public String tax_point_date;
    public String document_currency_code;
    public String tax_currency_code;
    public String accounting_cost;
    public String buyer_reference;
    public InvoiceDeliveryPeriod invoice_delivery_period;
    public String order_reference;
    public List<BillingRefData> billing_reference;
    public DispatchDocumentReference dispatch_document_reference;
    public ReceiptDocumentReference receipt_document_reference;
    public OriginatorDocumentReference originator_document_reference;
    public ContractDocumentReference contract_document_reference;
    public List<AdditionalDocumentReference>  additional_document_reference;
    public AccountingSupplierParty accounting_supplier_party;
    public AccountingCustomerParty accounting_customer_party;
    public String actual_delivery_date;
    public List<PaymentMeansData> payment_means;
    public String project_reference;
    public String payment_terms_note;
    public List<AllowanceChargeData> allowance_charge;
    public List<TaxTotalObj> tax_total;
    public LegalMonetaryTotalObj legal_monetary_total;
    public List<InvoiceLineItemInfo> invoice_line;
    
    
    
}
