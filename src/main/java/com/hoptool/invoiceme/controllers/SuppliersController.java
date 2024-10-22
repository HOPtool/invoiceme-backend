/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.controllers;

import com.hoptool.invoiceme.dto.ApproveOrDeleteSupplierRequest;
import com.hoptool.invoiceme.dto.ApproveOrDeleteSupplierRequestObj;
import com.hoptool.invoiceme.dto.FilterSuppliers;
import com.hoptool.invoiceme.dto.FilterSuppliersObj;
import com.hoptool.invoiceme.dto.SupplierResponse;
import com.hoptool.invoiceme.dto.SupplierRequest;
import com.hoptool.invoiceme.dto.SupplierRequestObj;
import com.hoptool.invoiceme.dto.SuppliersSearchResponse;
import com.hoptool.invoiceme.dto.SyncSupplierRequest;
import com.hoptool.invoiceme.dto.SyncSupplierRequestObj;
import com.hoptool.invoiceme.entities.SuppliersLog;
import com.hoptool.invoiceme.enumz.ResourceStatusEnum;
import com.hoptool.invoiceme.repositories.EmailValidationRepository;
import com.hoptool.invoiceme.repositories.SuppliersLogRepository;
import com.hoptool.invoiceme.repositories.SysDataRepository;
import com.hoptool.invoiceme.repositories.UserLogRepository;
import com.hoptool.resources.ErrorCodes;
import com.hoptool.resources.ResourceHelper;
import com.hoptool.service.AuthService;
import com.hoptool.service.InvoiceService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
@Slf4j
public class SuppliersController {
    
    
    @Inject
    UserLogRepository userLogRepo;
    
    @Inject
    SysDataRepository sysDataRepo;
    
    @Inject
    EmailValidationRepository emailValidationRepo;
    
    @Inject
    AuthService authService;
    
    @Inject
    InvoiceService invoiceService;
    
    @Inject
    SuppliersLogRepository supplierRepo;
    
    @Inject
    ESEQRepository eseqRepository;
    
    
    
    public SupplierResponse doCreateSupplier(@Valid SupplierRequest request) {
        
        SupplierResponse response = null;
        try 
        {
            if(request !=null)
            {
                    SupplierRequestObj supplierObj = new SupplierRequestObj(request);

                    if(supplierObj !=null)
                    {

                            SuppliersLog doLookUp = supplierRepo.doLookUpSupplier(supplierObj);
                            log.info("--- doLookUp -- "+doLookUp);
                            if(doLookUp == null)
                            {

                                String supplierCode = sysDataRepo.doLookUpByNameStr("SUPPLIER-CODE-PREFIX", "XXX")+eseqRepository.genCode(sysDataRepo.doLookUpByNameStr("SUPPLIER-CODE", "XXX"), Integer.parseInt(sysDataRepo.doLookUpByNameStr("SUPPLIER-ID-LEN", "0")));
                                log.info("-- supplierCode -- "+supplierCode);
                                
                                SuppliersLog doLog = supplierRepo.doLog(supplierObj, supplierCode);
                                
                                if(doLog !=null)
                                {
                                     return  new SupplierResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), doLog);
                                }
                                else
                                {
                                  return  new SupplierResponse(ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR), doLog);  

                                }

                            }
                            else
                            {
                                return  new SupplierResponse(ErrorCodes.DUPLICATE_TRANSACTION, ErrorCodes.doErroDesc(ErrorCodes.DUPLICATE_TRANSACTION), doLookUp);  

                            }
                    }
                    else
                    {

                        return  new SupplierResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR), null);  


                    }
            }
            else
            {

                return  new SupplierResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR)+" request is "+request, null);  


             }
           
            
        } catch (Exception e) {
            
            if(e.getCause() instanceof java.sql.SQLSyntaxErrorException)
            {
                
              /// Exception ex = (Exception)
               return new SupplierResponse(ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" - "+e.getCause().getMessage(), null);
          
            }
            
            log.info("Exception @ doCreateSupplier ",e);
            
             return new SupplierResponse(ErrorCodes.SYSTEM_ERROR, e.getMessage(), null);
         
        }
    
      //return response;  
    }
    
    //
    
    public SupplierResponse doSyncSupplier(@Valid SyncSupplierRequest request) {
        
        SupplierResponse response = null;
        try 
        {
            
            if(request !=null)
            {
            
                    SyncSupplierRequestObj supplierObj = new SyncSupplierRequestObj(request);

                    if(supplierObj !=null)
                    {

                            SuppliersLog doLookUp = supplierRepo.doLookUpById(supplierObj.tid);
                            log.info("--- doLookUp -- "+doLookUp);
                            if(doLookUp != null)
                            {

                                SuppliersLog doLog = supplierRepo.doSync(doLookUp, supplierObj);

                                if(doLog !=null)
                                {
                                     return  new SupplierResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), doLog);
                                }
                                else
                                {
                                  return  new SupplierResponse(ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR), doLog);  

                                }

                            }
                            else
                            {
                                return  new SupplierResponse(ErrorCodes.NO_RECORD_FOUND, ErrorCodes.doErroDesc(ErrorCodes.NO_RECORD_FOUND), doLookUp);  

                            }
                    }
                    else
                    {

                        return  new SupplierResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR), null);  


                    }
            }
            else
            {

                        return  new SupplierResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR)+" request is "+request, null);  


            }
           
            
        } catch (Exception e) {
            
            if(e.getCause() instanceof java.sql.SQLSyntaxErrorException)
            {
                
              /// Exception ex = (Exception)
               return new SupplierResponse(ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" - "+e.getCause().getMessage(), null);
          
            }
            
            log.info("Exception @ doSyncSupplier ",e);
            
             return new SupplierResponse(ErrorCodes.SYSTEM_ERROR, e.getMessage(), null);
         
        }
    
      //return response;  
    }
    
    public SupplierResponse doApproveSupplier(@Valid ApproveOrDeleteSupplierRequest request) {
        
        SupplierResponse response = null;
        try 
        {
            
            if(request != null)
            {
                    ApproveOrDeleteSupplierRequestObj supplierObj = new ApproveOrDeleteSupplierRequestObj(request);

                    if(supplierObj !=null)
                    {

                            SuppliersLog doLookUp = supplierRepo.doLookUpById(supplierObj.tid);
                            log.info("--- doLookUp -- "+doLookUp);
                            if(doLookUp != null && doLookUp.status !=null && !ResourceStatusEnum.DELETED.name().equals(doLookUp.status))
                            {

                                doLookUp.status = ResourceStatusEnum.ACTIVE.name();
                                doLookUp.updatedBy = supplierObj.actionBy;
                                doLookUp.updatedByStr = supplierObj.actionByStr;

                                SuppliersLog doLog = supplierRepo.doSync(doLookUp);

                                if(doLog !=null)
                                {
                                     return  new SupplierResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), doLog);
                                }
                                else
                                {
                                  return  new SupplierResponse(ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR), doLog);  

                                }

                            }
                            else if(doLookUp != null && doLookUp.status !=null && ResourceStatusEnum.DELETED.name().equals(doLookUp.status))
                            {
                                 return  new SupplierResponse(ErrorCodes.TRANSACTION_FORBIDDEN, ErrorCodes.doErroDesc(ErrorCodes.NO_RECORD_FOUND), doLookUp);  

                            }
                            else
                            {
                                return  new SupplierResponse(ErrorCodes.NO_RECORD_FOUND, ErrorCodes.doErroDesc(ErrorCodes.NO_RECORD_FOUND), doLookUp);  

                            }
                    }
                    else
                    {

                        return  new SupplierResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR), null);  


                    }
            }
            else
            {

                return  new SupplierResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR)+" request is "+request, null);  


            }
           
            
        } catch (Exception e) {
            
            if(e.getCause() instanceof java.sql.SQLSyntaxErrorException)
            {
                
              /// Exception ex = (Exception)
               return new SupplierResponse(ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" - "+e.getCause().getMessage(), null);
          
            }
            
            log.info("Exception @ doApproveSupplier ",e);
            
             return new SupplierResponse(ErrorCodes.SYSTEM_ERROR, e.getMessage(), null);
         
        }
    
      //return response;  
    }
    
    public SupplierResponse doDeleteSupplier(@Valid ApproveOrDeleteSupplierRequest request) {
        
        SupplierResponse response = null;
        try 
        {
            
          if(request != null)
          {
             ApproveOrDeleteSupplierRequestObj supplierObj = new ApproveOrDeleteSupplierRequestObj(request);
            
            if(supplierObj !=null)
            {

                    SuppliersLog doLookUp = supplierRepo.doLookUpById(supplierObj.tid);
                    log.info("--- doLookUp -- "+doLookUp);
                    if(doLookUp != null)
                    {
                        
                        doLookUp.status = ResourceStatusEnum.DELETED.name();
                        doLookUp.updatedBy = supplierObj.actionBy;
                        doLookUp.updatedByStr = supplierObj.actionByStr;
                        
                        SuppliersLog doLog = supplierRepo.doSync(doLookUp);
                        
                        if(doLog !=null)
                        {
                             return  new SupplierResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), doLog);
                        }
                        else
                        {
                          return  new SupplierResponse(ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR), doLog);  
                        
                        }

                    }
                    else
                    {
                        return  new SupplierResponse(ErrorCodes.NO_RECORD_FOUND, ErrorCodes.doErroDesc(ErrorCodes.NO_RECORD_FOUND), doLookUp);  
                        
                    }
            }
            else
            {
              
                return  new SupplierResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR), null);  
                        
                        
            }
         
          }
          else
          {

                return  new SupplierResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR)+" request is "+request, null);  


          }
           
            
        } catch (Exception e) {
            
            if(e.getCause() instanceof java.sql.SQLSyntaxErrorException)
            {
                
              /// Exception ex = (Exception)
               return new SupplierResponse(ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" - "+e.getCause().getMessage(), null);
          
            }
            
            log.info("Exception @ doDeleteSupplier ",e);
            
             return new SupplierResponse(ErrorCodes.SYSTEM_ERROR, e.getMessage(), null);
         
        }
    
      //return response;  
    }
    
    
    
    public SuppliersSearchResponse doFilterSuppliers(@Valid FilterSuppliers request) {
        System.out.println("request = " + request);
        SuppliersSearchResponse response = null;
        ResourceHelper rh = new ResourceHelper();
        try 
        {
            
          if(request != null)
          {
              FilterSuppliersObj suppliersFilter = new FilterSuppliersObj(request);
              System.out.println("suppliersFilter = " + suppliersFilter);
              if(suppliersFilter !=null)
              {
                
                   System.out.println("fromdate = " + rh.strToLDT(suppliersFilter.fromDate)+" todate "+rh.strToLDT(suppliersFilter.toDate));
                   System.out.println("suppliersFilter = " + (rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)))+" suppliersFilter.status.equalsIgnoreCase(\"ALL\") "+suppliersFilter.status.equalsIgnoreCase("ALL")+" --suppliersFilter.searchParam "+suppliersFilter.searchParam);
             
                        if(!rh.doValidateLDT(suppliersFilter.fromDate))
                        {

                              return  new SuppliersSearchResponse(ErrorCodes.INVALID_START_DATE, ErrorCodes.doErroDesc(ErrorCodes.INVALID_START_DATE), 0, null);
                        
                        }
                        else if(!rh.doValidateLDT(suppliersFilter.toDate))
                        {

                              return  new SuppliersSearchResponse(ErrorCodes.INVALID_END_DATE, ErrorCodes.doErroDesc(ErrorCodes.INVALID_END_DATE),0, null);
                        
                        }
                        else if(rh.strToLDT(suppliersFilter.toDate).isBefore( rh.strToLDT(suppliersFilter.fromDate)))
                        {
                          
                            return  new SuppliersSearchResponse(ErrorCodes.DATE_DISPARITY, ErrorCodes.doErroDesc(ErrorCodes.DATE_DISPARITY),0, null);
                        
                        }
                        else if((suppliersFilter.pageId) < 1 || suppliersFilter.pageSize < 1)// || !resourceH.isValidWithLen(fromJson.getPhoneNumberPri().replaceAll("\\+", ""), 13))
                        {
                          
                           return  new SuppliersSearchResponse(ErrorCodes.INVALID_PAGE_ID_OR_FETCH_SIZE, ErrorCodes.doErroDesc(ErrorCodes.INVALID_PAGE_ID_OR_FETCH_SIZE),0, null);
                        
                             
                        }
                        else if(rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)) && suppliersFilter.searchParam ==null && suppliersFilter.status.equalsIgnoreCase("ALL"))
                        {
                          
                           // List<SuppliersLog> list = supplierRepo.doList(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate)).list();
                            PanacheQuery<SuppliersLog> doList = supplierRepo.doList(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate)); //.list();
                           
                            List<SuppliersLog> list = doList.page(Page.of(suppliersFilter.pageId-1, suppliersFilter.pageSize)).list();
                            
                            return  new SuppliersSearchResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), list.size(), list);
                        
                        }
                        else if(rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)) && suppliersFilter.searchParam ==null && suppliersFilter.status.equalsIgnoreCase("ALL"))
                        {
                            System.out.println("suppliersFilter = " + suppliersFilter);
                           // List<SuppliersLog> list = supplierRepo.doList(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate)).list();
                            PanacheQuery<SuppliersLog> doList = supplierRepo.doList(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate)); //.list();
                           
                            List<SuppliersLog> list = doList.page(Page.of(suppliersFilter.pageId-1, suppliersFilter.pageSize)).list();
                            
                             
                             
                            return  new SuppliersSearchResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), list.size(), list);
                        
                        }
                        else if(rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)) && suppliersFilter.searchParam ==null && !suppliersFilter.status.equalsIgnoreCase("ALL"))
                        {
                          
                           // List<SuppliersLog> list = supplierRepo.doList(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate)).list();
                            PanacheQuery<SuppliersLog> doList = supplierRepo.doList(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate),suppliersFilter.status); //.list();
                           
                            List<SuppliersLog> list = doList.page(Page.of(suppliersFilter.pageId-1, suppliersFilter.pageSize)).list();
                            
                             
                             
                            return  new SuppliersSearchResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), list.size(), list);
                        
                        }
                        else if(rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)) && suppliersFilter.searchParam !=null && !suppliersFilter.status.equalsIgnoreCase("ALL"))
                        {
                          
                            PanacheQuery<SuppliersLog> doList = supplierRepo.doList(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate), suppliersFilter.status, suppliersFilter.searchParam); //.list();
                           
                            List<SuppliersLog> list = doList.page(Page.of(suppliersFilter.pageId-1, suppliersFilter.pageSize)).list();
                             
                             
                            return  new SuppliersSearchResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), list.size(), list);
                        
                        }
                        else if(rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)) && suppliersFilter.searchParam !=null && !suppliersFilter.status.equalsIgnoreCase("ALL"))
                        {
                          
                            PanacheQuery<SuppliersLog> doList = supplierRepo.doListAllStatusBySearchParam(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate), suppliersFilter.searchParam); //.list();
                           
                            List<SuppliersLog> list = doList.page(Page.of(suppliersFilter.pageId-1, suppliersFilter.pageSize)).list();
                             
                             
                            return  new SuppliersSearchResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), list.size(), list);
                        
                        }
                        else
                        {
                            System.out.println(" @@@@ suppliersFilter = " +   suppliersFilter);
                            return  new SuppliersSearchResponse(ErrorCodes.INVALID_SEARCH_PARAM, ErrorCodes.doErroDesc(ErrorCodes.INVALID_SEARCH_PARAM), 0, null);
                        
                        }

            }
            else
            {
                 
                return new SuppliersSearchResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR),0, null);
         
                        
            }
            
          }
          else
          {

                return  new SuppliersSearchResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR)+" request is "+request,0, null);  


          }
           
            
        } catch (Exception e) {
            
            if(e.getCause() instanceof java.sql.SQLSyntaxErrorException)
            {
                
              /// Exception ex = (Exception)
               return new SuppliersSearchResponse(ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" - "+e.getCause().getMessage(),0, null);
          
            }
            
            log.info("Exception @ doFilterSuppliers ",e);
            
             return new SuppliersSearchResponse(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR),0, null);
         
        }
    
      //return response;  
    }
    
    
    public SuppliersSearchResponse doFilterSuppliersForBusiness(@Valid FilterSuppliers request) {
        System.out.println("doFilterSuppliersForBusiness = " + request);
        SuppliersSearchResponse response = null;
        ResourceHelper rh = new ResourceHelper();
        try 
        {
            
          if(request != null)
          {
              FilterSuppliersObj suppliersFilter = new FilterSuppliersObj(request);
              System.out.println("suppliersFilter = " + suppliersFilter);
              if(suppliersFilter !=null)
              {
                
                   System.out.println("fromdate = " + rh.strToLDT(suppliersFilter.fromDate)+" todate "+rh.strToLDT(suppliersFilter.toDate));
                   System.out.println("suppliersFilter = " + (rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)))+" suppliersFilter.status.equalsIgnoreCase(\"ALL\") "+suppliersFilter.status.equalsIgnoreCase("ALL")+" --suppliersFilter.searchParam "+suppliersFilter.searchParam);
             
                        if(!rh.doValidateLDT(suppliersFilter.fromDate))
                        {

                              return  new SuppliersSearchResponse(ErrorCodes.INVALID_START_DATE, ErrorCodes.doErroDesc(ErrorCodes.INVALID_START_DATE), 0, null);
                        
                        }
                        else if(!rh.doValidateLDT(suppliersFilter.toDate))
                        {

                              return  new SuppliersSearchResponse(ErrorCodes.INVALID_END_DATE, ErrorCodes.doErroDesc(ErrorCodes.INVALID_END_DATE),0, null);
                        
                        }
                        else if(rh.strToLDT(suppliersFilter.toDate).isBefore( rh.strToLDT(suppliersFilter.fromDate)))
                        {
                          
                            return  new SuppliersSearchResponse(ErrorCodes.DATE_DISPARITY, ErrorCodes.doErroDesc(ErrorCodes.DATE_DISPARITY),0, null);
                        
                        }
                        else if((suppliersFilter.pageId) < 1 || suppliersFilter.pageSize < 1)// || !resourceH.isValidWithLen(fromJson.getPhoneNumberPri().replaceAll("\\+", ""), 13))
                        {
                          
                           return  new SuppliersSearchResponse(ErrorCodes.INVALID_PAGE_ID_OR_FETCH_SIZE, ErrorCodes.doErroDesc(ErrorCodes.INVALID_PAGE_ID_OR_FETCH_SIZE),0, null);
                        
                             
                        }
                        else if(suppliersFilter.businessId == null ||  suppliersFilter.businessId.trim().equals(""))// || !resourceH.isValidWithLen(fromJson.getPhoneNumberPri().replaceAll("\\+", ""), 13))
                        {
                          
                           return  new SuppliersSearchResponse(ErrorCodes.INVALID_BUSINESS_ID, ErrorCodes.doErroDesc(ErrorCodes.INVALID_BUSINESS_ID),0, null);
                        
                             
                        }/*
                        else if(rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)) && suppliersFilter.searchParam ==null && suppliersFilter.status.equalsIgnoreCase("ALL"))
                        {
                          
                           // List<SuppliersLog> list = supplierRepo.doList(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate)).list();
                            PanacheQuery<SuppliersLog> doList = supplierRepo.doList(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate), suppliersFilter.businessId); //.list();
                            
                            List<SuppliersLog> list = doList.page(Page.of(suppliersFilter.pageId-1, suppliersFilter.pageSize)).list();
                            
                            return  new SuppliersSearchResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), list.size(), list);
                        
                        }*/
                        else if(rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)) && suppliersFilter.searchParam ==null && suppliersFilter.status.equalsIgnoreCase("ALL"))
                        {
                            System.out.println("suppliersFilter = " + suppliersFilter);
                           
                            PanacheQuery<SuppliersLog> doList = supplierRepo.doListForBusiness(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate),suppliersFilter.businessId); //.list();
                           
                            List<SuppliersLog> list = doList.page(Page.of(suppliersFilter.pageId-1, suppliersFilter.pageSize)).list();
                            
                             
                             
                            return  new SuppliersSearchResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), list.size(), list);
                        
                        }
                        else if(rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)) && suppliersFilter.searchParam ==null && !suppliersFilter.status.equalsIgnoreCase("ALL"))
                        {
                          
                           // List<SuppliersLog> list = supplierRepo.doList(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate)).list();
                            PanacheQuery<SuppliersLog> doList = supplierRepo.doListForBusiness(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate),suppliersFilter.businessId,suppliersFilter.status); //.list();
                           
                            List<SuppliersLog> list = doList.page(Page.of(suppliersFilter.pageId-1, suppliersFilter.pageSize)).list();
                            
                             
                             
                            return  new SuppliersSearchResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), list.size(), list);
                        
                        }
                        else if(rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)) && suppliersFilter.searchParam !=null && !suppliersFilter.status.equalsIgnoreCase("ALL"))
                        {
                          
                            PanacheQuery<SuppliersLog> doList = supplierRepo.doListForBusiness(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate), suppliersFilter.status, suppliersFilter.searchParam,suppliersFilter.businessId); //.list();
                           
                            List<SuppliersLog> list = doList.page(Page.of(suppliersFilter.pageId-1, suppliersFilter.pageSize)).list();
                             
                             
                            return  new SuppliersSearchResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), list.size(), list);
                        
                        }
                        else if(rh.strToLDT(suppliersFilter.fromDate).isBefore(rh.strToLDT(suppliersFilter.toDate)) && suppliersFilter.searchParam !=null && !suppliersFilter.status.equalsIgnoreCase("ALL"))
                        {
                          
                            PanacheQuery<SuppliersLog> doList = supplierRepo.doListAllStatusBySearchParam(rh.strToLDT(suppliersFilter.fromDate), rh.strToLDT(suppliersFilter.toDate), suppliersFilter.searchParam); //.list();
                           
                            List<SuppliersLog> list = doList.page(Page.of(suppliersFilter.pageId-1, suppliersFilter.pageSize)).list();
                             
                             
                            return  new SuppliersSearchResponse(ErrorCodes.SUCCESSFUL, ErrorCodes.doErroDesc(ErrorCodes.SUCCESSFUL), list.size(), list);
                        
                        }
                        else
                        {
                            System.out.println(" @@@@ suppliersFilter = " +   suppliersFilter);
                            return  new SuppliersSearchResponse(ErrorCodes.INVALID_SEARCH_PARAM, ErrorCodes.doErroDesc(ErrorCodes.INVALID_SEARCH_PARAM), 0, null);
                        
                        }

            }
            else
            {
                 
                return new SuppliersSearchResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR),0, null);
         
                        
            }
            
          }
          else
          {

                return  new SuppliersSearchResponse(ErrorCodes.FORMAT_ERROR, ErrorCodes.doErroDesc(ErrorCodes.FORMAT_ERROR)+" request is "+request,0, null);  


          }
           
            
        }
        catch (Exception e) {
            
            if(e.getCause() instanceof java.sql.SQLSyntaxErrorException)
            {
                
              /// Exception ex = (Exception)
               return new SuppliersSearchResponse(ErrorCodes.DATABASE_ERROR, ErrorCodes.doErroDesc(ErrorCodes.DATABASE_ERROR)+" - "+e.getCause().getMessage(),0, null);
          
            }
            log.info("Exception @ doFilterSuppliers ",e);
            
             return new SuppliersSearchResponse(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR),0, null);
         
        }
    
      //return response;  
    }

}
