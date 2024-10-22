/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.repositories;



import com.hoptool.invoiceme.dto.SupplierRequestObj;
import com.hoptool.invoiceme.dto.SyncSupplierRequestObj;
import com.hoptool.invoiceme.entities.BusinessInfo;
import com.hoptool.invoiceme.entities.EmailVerificationLog;
import com.hoptool.invoiceme.entities.SuppliersLog;
import com.hoptool.invoiceme.enumz.ResourceStatusEnum;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
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
public class SuppliersLogRepository implements  PanacheRepository<SuppliersLog> {
    
    
    public SuppliersLog doLookUpById(long tid) {
     return find("tid = ?1", tid).firstResult();
    
    }
    
    public SuppliersLog doLookUpSupplier(SupplierRequestObj obj) {
     log.info("obj = " + obj);
     return find("tinNumber = ?1  and cacNumber = ?2 and supplierBusinessId = ?3 ", obj.tinNumber, obj.cacNumber, obj.businessId).firstResult();
    
    }
   
   
     public PanacheQuery<SuppliersLog> doList(LocalDateTime start, LocalDateTime end) {
       
        return find("createdDate between ?1 and ?2 order by createdDate desc", start, end);
    
    }
     
    public PanacheQuery<SuppliersLog> doListForBusiness(LocalDateTime start, LocalDateTime end, String businessId) {
       
        return find("createdDate between ?1 and ?2 and supplierBusinessId= ?3  order by createdDate desc", start, end, businessId);
    
    }
    /*
    public PanacheQuery<SuppliersLog> doListForBusiness(LocalDateTime start, LocalDateTime end, String businessId) {
       
        return find("createdDate between ?1 and ?2 and supplierBusinessId= ?3  order by createdDate desc", start, end, businessId);
    
    }
    */
     
    public PanacheQuery<SuppliersLog> doList(LocalDateTime start, LocalDateTime end, String status) {
       
        return find("createdDate between ?1 and ?2 and status = ?3 order by createdDate desc", start, end, status);
    
    }
    
     public PanacheQuery<SuppliersLog> doListForBusiness(LocalDateTime start, LocalDateTime end, String businessId, String status) {
       
        return find("createdDate between ?1 and ?2 and status = ?3 and supplierBusinessId= ?4 order by createdDate desc", start, end,  status, businessId);
    
    }
    
    public PanacheQuery<SuppliersLog> doList(LocalDateTime start, LocalDateTime end, String status, String searchParam) {
       
        return find("createdDate between ?1 and ?2 and status = ?3 and ( companyName like ?4 or cacNumber like ?4 or  tinNumber like 4? or contactEmail like ?4 or contactPhoneNumber like ?4 bankName like ?4 or  bankSortCode like ?4  bankAccountNumber like ?4 or  companyAddress like ?4 or  supplierBusinessId like ?4) order by createdDate desc", start, end, status, searchParam);
    
    }
    
    public PanacheQuery<SuppliersLog> doListForBusiness(LocalDateTime start, LocalDateTime end, String status, String searchParam, String businessId) {
       
        return find("createdDate between ?1 and ?2 and status = ?3  supplierBusinessId= ?5 and ( companyName like ?4 or cacNumber like ?4 or  tinNumber like 4? or contactEmail like ?4 or contactPhoneNumber like ?4 bankName like ?4 or  bankSortCode like ?4  bankAccountNumber like ?4 or  companyAddress like ?4 or  supplierBusinessId like ?4) order by createdDate desc", start, end, status, searchParam,businessId);
    
    }
    
    public PanacheQuery<SuppliersLog> doListAllStatusBySearchParam(LocalDateTime start, LocalDateTime end, String searchParam) {
       
        return find("createdDate between ?1 and ?2  and ( companyName like ?3 or cacNumber like ?3 or  tinNumber like 3? or contactEmail like ?3 or contactPhoneNumber like ?3 bankName like ?3 or  bankSortCode like ?3  bankAccountNumber like ?3 or  companyAddress like ?3 or  supplierBusinessId like ?3) order by createdDate desc", start, end,searchParam);
    
    }
    
    public PanacheQuery<SuppliersLog> doListAllStatusBySearchParamByBusiness(LocalDateTime start, LocalDateTime end, String searchParam, String businessId) {
       
        return find("createdDate between ?1 and ?2 and  supplierBusinessId= ?4 and ( companyName like ?3 or cacNumber like ?3 or  tinNumber like 3? or contactEmail like ?3 or contactPhoneNumber like ?3 bankName like ?3 or  bankSortCode like ?3  bankAccountNumber like ?3 or  companyAddress like ?3 or  supplierBusinessId like ?3) order by createdDate desc", start, end,searchParam,businessId);
    
    }
    
    
    
    @Transactional
    public SuppliersLog doLog(SupplierRequestObj request, String supplierCode) throws Exception {
        SuppliersLog obj = null;
        String firstname = "", lastname ="";
        try 
        {
                obj = new SuppliersLog();
                obj.bankAccountNumber = request.bankAccountNumber;
                obj.bankCode = request.bankCode;
                obj.bankName = request.bankName;
                obj.bankSortCode = request.bankSortCode;
                obj.cacNumber = request.cacNumber;
                obj.companyAddress = request.companyAddress;
                obj.companyName = request.companyName;
                obj.contactEmail = request.contactEmail;
                obj.contactPhoneNumber = request.contactPhoneNumber;
                obj.createdBy = request.actionBy;
                obj.createdByStr = request.actionByStr;
                obj.createdDate = LocalDateTime.now();
                obj.status = ResourceStatusEnum.INACTIVE.name();
                obj.supplierBusinessId = request.businessId;
                obj.tinNumber = request.tinNumber;
                obj.supplierCode = supplierCode;
                obj = Panache.getEntityManager().merge(obj);
          
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
    }
    
    
    @Transactional
    public SuppliersLog doSync(SuppliersLog syncObj, SyncSupplierRequestObj  request) throws Exception {
        SuppliersLog obj = null;
        try 
        {
            
          
                if(syncObj !=null)
                {
                
                syncObj.bankAccountNumber = request.bankAccountNumber;
                syncObj.bankCode = request.bankCode;
                syncObj.bankName = request.bankName;
                syncObj.bankSortCode = request.bankSortCode;
                syncObj.cacNumber = request.cacNumber;
                syncObj.companyAddress = request.companyAddress;
                syncObj.companyName = request.companyName;
                syncObj.contactEmail = request.contactEmail;
                syncObj.contactPhoneNumber = request.contactPhoneNumber;
                syncObj.updatedBy = request.actionBy;
                syncObj.updatedByStr = request.actionByStr;
                syncObj.updatedDate = LocalDateTime.now();
                syncObj.status = ResourceStatusEnum.INACTIVE.name();
                
                syncObj.tinNumber = request.tinNumber;
                
                
                
                  obj = Panache.getEntityManager().merge(syncObj);
          
                
                }
               
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return obj;
    }
    
    
    @Transactional
    public SuppliersLog doSync(SuppliersLog sl) throws Exception {
       
        SuppliersLog resp = null;
        try 
        {
            

               // obj = new EmailVerificationLog();
          
                 if(sl !=null)
                 {
                
                    resp = Panache.getEntityManager().merge(sl);
                 }
          
           
        } 
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
     return resp;
    }
    
    
}
