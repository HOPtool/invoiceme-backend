/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.controllers;


//import com.paycraftsystems.resources.GenericFilterObject;

import com.hoptool.invoiceme.entities.SysData;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import bind.JsonbBuilder;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
public class SysDataController {
    
    private static  Logger LOGGER = LoggerFactory.getLogger(SysDataController.class);
    
    
   // @Inject 
    //SysDataRepository sysDataRepository;
    
    
    //@Inject
   // RequestsValidator requestsValidator;
    
   
    
//    ValidationHelper rh = new ValidationHelper();
   
   
    public List<SysData>  all;
    
    
    
   // @Inject
   // EntityManager em;
    
    @PostConstruct
    public void doReadAll() throws Exception {
        List<SysData> query =  new ArrayList<>();
        try 
        {
               all = null;// sysDataRepository.listAll();//  SysData.listAll();// em.createNamedQuery(SysData.ALL, SysData.class);
              
        } catch (Exception e) {
        
             e.printStackTrace();
             
             throw new Exception(e);
             
            // return new ArrayList<SysData>(){};
        }
     //return query;
    }
    
    
    public long countAll()
    {
        return Long.parseLong("0");//sysDataRepository.findAll().count();//.doReadAll().size();
    }
    
    public int countAll(String query)
    {
        return  0;//em.createNamedQuery(query).getResultList().size();
    }
    
    
    public String doLookUpOrDefault(String  resp, String defaultTo) {
    
     return null;// sysDataRepository.doFindOrDefaultTo(resp,defaultTo);
    }
    
    /*
    public Response doCreate(String jsonStr) 
    {
        int prodOrDev = Integer.parseInt(doLookUpByParamNameStr("INTEGRATION-MODE", "0"));
       
        try 
        {
            SysDataDTO fromJson = JsonbBuilder.create().fromJson(jsonStr, SysDataDTO.class);
            
            //validate dto
            int doValidateProviderCategory = requestsValidator.doValidateSysDataCreate(fromJson);
            
            if(doValidateProviderCategory == ErrorCodes.SUCCESSFUL)
            {
                SysData doLog = sysDataRepository.doLog(fromJson); //doFindByDescription(jsonStr)
            
                if(doLog != null)
                {
                    return Response.ok().entity(doLog.toJson()).build();
                }
                else
                {
                    return Response.status(ErrorCodes.DATABASE_ERROR).entity(Json.createObjectBuilder().add("errorDesc",ErrorCodes.doDesc(ErrorCodes.DATABASE_ERROR,prodOrDev)).build()).build();
                }
                
            
            }
            else
            {
                return Response.status(doValidateProviderCategory).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(doValidateProviderCategory,prodOrDev)).build()).build();
            }
        
        }
        catch (HelloException e) {
            //System.out.println("hola!!! ");
            return Response.status(e.getResponse().getStatus()).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(e.getResponse().getStatus(),prodOrDev)).build()).build();
        }
        catch (Exception e) {
        
                
                if(e.getCause() instanceof HelloException)
                {
                    System.err.println(" ** AHA = " + e.getMessage()+" cause "+e.getCause());
                    HelloException exp =   (HelloException) e.getCause();
                    return Response.status(exp.getResponse().getStatus()).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(exp.getResponse().getStatus(),prodOrDev)).build()).build();
                }
                LOGGER.error("Exception @ Response doCreate ...",e);
               if((e.getCause() instanceof java.sql.SQLSyntaxErrorException) || ( e.getCause() instanceof  org.hibernate.HibernateException) || ( e.getCause() instanceof  org.hibernate.exception.ConstraintViolationException) || ( e.getCause() instanceof  java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException)  || (e.getCause() instanceof jakarta.persistence.PersistenceException))
                {
                     throw new HelloException(ErrorCodes.DATABASE_ERROR, e.getMessage(),prodOrDev);
                }
                else if((e.getCause() instanceof java.io.IOException))
                {
                     throw new HelloException(ErrorCodes.IO_EXCEPTION, e.getMessage(),prodOrDev);
                }
                else
                {
                     throw new HelloException(ErrorCodes.SYSTEM_ERROR, e.getMessage(),prodOrDev);
                }
          }
        
    }
    
    public Response doEdit(String jsonStr) {
        int prodOrDev = Integer.parseInt(doLookUpByParamNameStr("INTEGRATION-MODE", "0"));
       
        try 
        {
            SysDataDTO fromJson = JsonbBuilder.create().fromJson(jsonStr, SysDataDTO.class);
            
            //validate dto
            int doValidateProviderCategory = requestsValidator.doValidateSysDataEdit(fromJson);
            
            if(doValidateProviderCategory == ErrorCodes.SUCCESSFUL)
            {
                SysData doLog = sysDataRepository.doSync(fromJson); //doFindByDescription(jsonStr)
            
                if(doLog != null)
                {
                    return Response.ok().entity(doLog.toJson()).build();
                }
                else
                {
                    return Response.status(ErrorCodes.DATABASE_ERROR).entity(Json.createObjectBuilder().add("errorDesc",ErrorCodes.doDesc(ErrorCodes.DATABASE_ERROR,prodOrDev)).build()).build();
                }
                
            
            }
            else
            {
                return Response.status(doValidateProviderCategory).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(doValidateProviderCategory,prodOrDev)).build()).build();
            }
        
        }
        catch (HelloException e) {
             e.printStackTrace();
            return Response.status(e.getResponse().getStatus()).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(e.getResponse().getStatus(),prodOrDev)).build()).build();
        }
        catch (Exception e) {
        
               LOGGER.error("Exception @ Response sysdata doEdit ",e);
               
                if(e.getCause() instanceof HelloException)
                {
                    //System.err.println(" ** AHA = " + e.getMessage()+" cause "+e.getCause());
                    HelloException exp =   (HelloException) e.getCause();
                    return Response.status(exp.getResponse().getStatus()).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(exp.getResponse().getStatus(),prodOrDev)).build()).build();
                }
               
                if((e.getCause() instanceof java.sql.SQLSyntaxErrorException) || ( e.getCause() instanceof  org.hibernate.HibernateException) || ( e.getCause() instanceof  org.hibernate.exception.ConstraintViolationException) || ( e.getCause() instanceof  java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException)  || (e.getCause() instanceof jakarta.persistence.PersistenceException))
                {
                     throw new HelloException(ErrorCodes.DATABASE_ERROR, e.getMessage(),prodOrDev);
                }
                else if((e.getCause() instanceof java.io.IOException))
                {
                     throw new HelloException(ErrorCodes.IO_EXCEPTION, e.getMessage(),prodOrDev);
                }
                else
                {
                     throw new HelloException(ErrorCodes.SYSTEM_ERROR, e.getMessage(),prodOrDev);
                }
        
        }
        
    }
    
    
    public Response doDelete(String jsonStr) {
        int prodOrDev = Integer.parseInt(doLookUpByParamNameStr("INTEGRATION-MODE", "0"));
       
        try 
        {
            SysDataDTO fromJson = JsonbBuilder.create().fromJson(jsonStr, SysDataDTO.class);
            
            //validate dto
            int doValidateProviderCategory = requestsValidator.doValidateSysDataDeleteOrAprrove(fromJson);
            
            if(doValidateProviderCategory == ErrorCodes.SUCCESSFUL)
            {
                SysData doLog = sysDataRepository.doDelete(fromJson); //doFindByDescription(jsonStr)
            
                if(doLog != null)
                {
                    return Response.ok().entity(doLog.toJson()).build();
                }
                else
                {
                    return Response.status(ErrorCodes.DATABASE_ERROR).entity(Json.createObjectBuilder().add("errorDesc",ErrorCodes.doDesc(ErrorCodes.DATABASE_ERROR,prodOrDev)).build()).build();
                }
                
            
            }
            else
            {
                return Response.status(doValidateProviderCategory).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(doValidateProviderCategory,prodOrDev)).build()).build();
            }
        
        }
        catch (HelloException e) {
             e.printStackTrace();
            return Response.status(e.getResponse().getStatus()).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(e.getResponse().getStatus(),prodOrDev)).build()).build();
        }
        catch (Exception e) {
        
               LOGGER.error("Exception @ Response sysdata doEdit ",e);
               
                if(e.getCause() instanceof HelloException)
                {
                    //System.err.println(" ** AHA = " + e.getMessage()+" cause "+e.getCause());
                    HelloException exp =   (HelloException) e.getCause();
                    return Response.status(exp.getResponse().getStatus()).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(exp.getResponse().getStatus(),prodOrDev)).build()).build();
                }
               
                if((e.getCause() instanceof java.sql.SQLSyntaxErrorException) || ( e.getCause() instanceof  org.hibernate.HibernateException) || ( e.getCause() instanceof  org.hibernate.exception.ConstraintViolationException) || ( e.getCause() instanceof  java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException)  || (e.getCause() instanceof jakarta.persistence.PersistenceException))
                {
                     throw new HelloException(ErrorCodes.DATABASE_ERROR, e.getMessage(),prodOrDev);
                }
                else if((e.getCause() instanceof java.io.IOException))
                {
                     throw new HelloException(ErrorCodes.IO_EXCEPTION, e.getMessage(),prodOrDev);
                }
                else
                {
                     throw new HelloException(ErrorCodes.SYSTEM_ERROR, e.getMessage(),prodOrDev);
                }
        
            //e.printStackTrace();
            ///return  Response.status(ErrorCodes.SYSTEM_ERROR).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ErrorCodes.SYSTEM_ERROR)).build()).build();
        }
        
    }
    
   @Transactional
    public Response doApprove(@Valid String json) {
        int prodOrDev = Integer.parseInt(doLookUpByParamNameStr("INTEGRATION-MODE", "0"));
       
        try 
        {
            SysDataDTO fromJson = JsonbBuilder.create().fromJson(json, SysDataDTO.class);
            
            
                      SysData merge = sysDataRepository.doApprove(fromJson);
                      
                      if (merge == null) 
                      {
                           return Response.status(ErrorCodes.DATABASE_ERROR).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ErrorCodes.DATABASE_ERROR,prodOrDev)).build()).build();
          
                      }
                      else
                      {
                           return Response.ok().entity(merge.toJson()).build();
          
                      }
            
           
        }
        catch (HelloException e)
        {
            e.printStackTrace();
            return Response.status(e.getResponse().getStatus()).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(e.getResponse().getStatus(), 1))).build();
        }
        catch (Exception e) {
            
                if(e.getCause() instanceof HelloException)
                {
                    System.out.println("e = " + e);
                    HelloException ex = (HelloException)e.getCause();
                   return Response.status(ex.getResponse().getStatus()).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ex.getResponse().getStatus(), 1))).build();
                }
                LOGGER.error("- EXCEPTION sysdata doApprove ", e);//e.printStackTrace();
            
                if((e.getCause() instanceof java.sql.SQLSyntaxErrorException) || ( e.getCause() instanceof  org.hibernate.HibernateException) || ( e.getCause() instanceof  org.hibernate.exception.ConstraintViolationException) || ( e.getCause() instanceof  java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof jakarta.persistence.PersistenceException))
                {
                     throw new HelloException(ErrorCodes.DATABASE_ERROR, e.getMessage(),prodOrDev);
                }
                else if((e.getCause() instanceof java.io.IOException))
                {
                     throw new HelloException(ErrorCodes.IO_EXCEPTION, e.getMessage(),prodOrDev);
                }
                else
                {
                     throw new HelloException(ErrorCodes.SYSTEM_ERROR, e.getMessage(),prodOrDev);
                }
        
        
        }
        
    }
    
    @Transactional
    public Response doDelete(@Valid JsonObject json) {
        int prodOrDev = Integer.parseInt(doLookUpByParamNameStr("INTEGRATION-MODE", "0"));
       
        System.out.println("json doDelete= " + json);
        try 
        {
            SysDataDTO fromJson = JsonbBuilder.create().fromJson(json.toString(), SysDataDTO.class);
            
            SysData sysobj = doLookUpById(fromJson.tid);
            
            if(sysobj != null) 
            {
               
                      sysobj.sts =  Long.parseLong(doLookUpByParamNameStr("DELETE-RSC-STS"));
                      sysobj.statusStr = ResourceStsInfo.findDescbyID(sysobj.sts);
                      sysobj.lastUpatedDate = LocalDateTime.now();
                      sysobj.lastUpdatedBy = fromJson.actionBy;
                      sysobj.lastUpdatedByStr = fromJson.actionByStr;//.lastUpdatedByStr;// UserLog.doFindNameByTID(sysobj.lastUpdatedBy);
                      
                      SysData merge = Panache.getEntityManager().merge(sysobj);
                      
                      if (merge == null) 
                      {
                           return Response.status(ErrorCodes.DATABASE_ERROR).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ErrorCodes.DATABASE_ERROR,prodOrDev)).build()).build();
          
                      }
                      else
                      {
                           return Response.ok().entity(merge.toJson()).build();
          
                      }
                
            }
            else
            {
                return Response.status(ErrorCodes.NO_RECORD_FOUND).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ErrorCodes.NO_RECORD_FOUND,prodOrDev)).build()).build();
            }
         
        } 
        catch (HelloException e)
        {
            e.printStackTrace();
            return Response.status(e.getResponse().getStatus()).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(e.getResponse().getStatus(), 1))).build();
        }
        catch (Exception e) {
            
            
                LOGGER.error("- EXCEPTION SysDataHelper doApprove ", e);//e.printStackTrace();
            
                if((e.getCause() instanceof java.sql.SQLSyntaxErrorException) || ( e.getCause() instanceof  org.hibernate.HibernateException) || ( e.getCause() instanceof  org.hibernate.exception.ConstraintViolationException) || ( e.getCause() instanceof  java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof jakarta.persistence.PersistenceException))
                {
                     throw new HelloException(ErrorCodes.DATABASE_ERROR, e.getMessage(),prodOrDev);
                }
                else if((e.getCause() instanceof java.io.IOException))
                {
                     throw new HelloException(ErrorCodes.IO_EXCEPTION, e.getMessage(),prodOrDev);
                }
                else
                {
                     throw new HelloException(ErrorCodes.SYSTEM_ERROR, e.getMessage(),prodOrDev);
                }
        
        
        }
        
    }
    
    
    
    public Response doLookup(@Valid long tid) {
         int prodOrDev = Integer.parseInt(doLookUpByParamNameStr("INTEGRATION-MODE", "0"));
       
        try 
        {
          
            SysData doLookUpByParamName = doLookUpById(tid);
            
            if (doLookUpByParamName != null) 
            {
                   
                return Response.ok().entity(doLookUpByParamName.toJson()).build();
          
            }
            else
            {
                return Response.status(ErrorCodes.NO_RECORD_FOUND).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ErrorCodes.NO_RECORD_FOUND,prodOrDev)).build()).build();
            }
           
        } 
        catch (Exception e) {
            
            
                LOGGER.error("- EXCEPTION SysDataHelper doLog ", e);//e.printStackTrace();
            
                if((e.getCause() instanceof java.sql.SQLSyntaxErrorException) || ( e.getCause() instanceof  org.hibernate.HibernateException) || ( e.getCause() instanceof  org.hibernate.exception.ConstraintViolationException) || ( e.getCause() instanceof  java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof jakarta.persistence.PersistenceException))
                {
                     throw new HelloException(ErrorCodes.DATABASE_ERROR, e.getMessage(),prodOrDev);
                }
                else if((e.getCause() instanceof java.io.IOException))
                {
                     throw new HelloException(ErrorCodes.IO_EXCEPTION, e.getMessage(),prodOrDev);
                }
                else
                {
                     throw new HelloException(ErrorCodes.SYSTEM_ERROR, e.getMessage(),prodOrDev);
                }
        
        
        }
        
    }
    
    
     public Response doLoadList(@Valid  String requestStr) {
         int prodOrDev = Integer.parseInt(doLookUpByParamNameStr("INTEGRATION-MODE", "0"));
       
        long status = -1;
        JsonArrayBuilder jar = jakarta.json.Json.createArrayBuilder();
        try 
        {
          
            GenericFilterObject fromJson = JsonbBuilder.create().fromJson(requestStr, GenericFilterObject.class);
           
            
             if(!rh.doValidateLDT(fromJson.fromDate))
             {
                            
                      return Response.status(ErrorCodes.INVALID_START_DATE).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ErrorCodes.INVALID_START_DATE, prodOrDev)).build()).build();
             }
             else if(!rh.doValidateLDT(fromJson.toDate))
             {
                            
                      return Response.status(ErrorCodes.INVALID_END_DATE).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ErrorCodes.INVALID_END_DATE, prodOrDev)).build()).build();
             }
             else if(rh.strToLDT(fromJson.toDate).isBefore( rh.strToLDT(fromJson.fromDate)))
             {
                   return Response.status(ErrorCodes.DATE_DISPARITY).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ErrorCodes.DATE_DISPARITY, prodOrDev)).build()).build();     
             }
             else if((fromJson.pageId) < 1 || fromJson.pageSize < 1)// || !resourceH.isValidWithLen(fromJson.getPhoneNumberPri().replaceAll("\\+", ""), 13))
             {
                 return Response.status(ErrorCodes.INVALID_PAGE_ID_OR_FETCH_SIZE).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ErrorCodes.INVALID_PAGE_ID_OR_FETCH_SIZE, prodOrDev)).build()).build();
             }
             else
             {
                 
                    //status = rh.toRolesPrivilegeStatus(fromJson.controlCode);
                     
                   JsonObject doLoadList =  doLoadConfigsList(fromJson.fromDate, fromJson.toDate,  fromJson.searchKey,fromJson.status,  fromJson.pageId, fromJson.pageSize);
                   //List<SysData> doLoadList = doLoadList(fromJson);//rh.strToLDT(fromJson.fromDate), rh.strToLDT(fromJson.toDate),  status, fromJson.searchKey);
                   // s
                    if(doLoadList != null) 
                    {

                       return Response.ok().entity(doLoadList).build();

                    }
                    else
                    {
                        return Response.status(ErrorCodes.NO_RECORD_FOUND).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ErrorCodes.NO_RECORD_FOUND,prodOrDev)).build()).build();
                    }
             }
         
        } 
        catch (Exception e) {
            
            
                LOGGER.error("- EXCEPTION SysDataHelper doLoadList ", e);//e.printStackTrace();
            
                if((e.getCause() instanceof java.sql.SQLSyntaxErrorException) || ( e.getCause() instanceof  org.hibernate.HibernateException) || ( e.getCause() instanceof  org.hibernate.exception.ConstraintViolationException) || ( e.getCause() instanceof  java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof jakarta.persistence.PersistenceException))
                {
                     throw new HelloException(ErrorCodes.DATABASE_ERROR, e.getMessage(),prodOrDev);
                }
                else if((e.getCause() instanceof java.io.IOException))
                {
                     throw new HelloException(ErrorCodes.IO_EXCEPTION, e.getMessage(),prodOrDev);
                }
                else
                {
                     throw new HelloException(ErrorCodes.SYSTEM_ERROR, e.getMessage(),prodOrDev);
                }
        
        
        }
        
    }
     
     
     public Response doLoadAllConfig() {
        int prodOrDev = Integer.parseInt(doLookUpByParamNameStr("INTEGRATION-MODE", "0"));
       
        JsonArrayBuilder jar = jakarta.json.Json.createArrayBuilder();
        try 
        {
          
                    List<SysData> doLoadList = SysData.listAll();//doLoadList(rh.strToLDT(fromJson.fromDate), rh.strToLDT(fromJson.toDate),  fromJson.status, fromJson.searchKey);
                 
                    LOGGER.info(" ++ doLoadAllConfig ++ "+doLoadList.size());
                    if (doLoadList != null) 
                    {

                        doLoadList.stream().map(a->a.toJson()).forEach(f->jar.add(f));
                        
                        
                        
                       return Response.ok().entity(jar.build()).build();

                    }
                    else
                    {
                        return Response.status(ErrorCodes.NO_RECORD_FOUND).entity(Json.createObjectBuilder().add("errorDesc", ErrorCodes.doDesc(ErrorCodes.NO_RECORD_FOUND,prodOrDev)).build()).build();
                    }
             
         
        } 
        catch (Exception e) {
            
            
                LOGGER.error("- EXCEPTION SysDataHelper doLoadList ", e);//e.printStackTrace();
            
                if((e.getCause() instanceof java.sql.SQLSyntaxErrorException) || ( e.getCause() instanceof  org.hibernate.HibernateException) || ( e.getCause() instanceof  org.hibernate.exception.ConstraintViolationException) || ( e.getCause() instanceof  java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) || (e.getCause() instanceof jakarta.persistence.PersistenceException))
                {
                     throw new HelloException(ErrorCodes.DATABASE_ERROR, e.getMessage(),prodOrDev);
                }
                else if((e.getCause() instanceof java.io.IOException))
                {
                     throw new HelloException(ErrorCodes.IO_EXCEPTION, e.getMessage(),prodOrDev);
                }
                else
                {
                     throw new HelloException(ErrorCodes.SYSTEM_ERROR, e.getMessage(),prodOrDev);
                }
        
        
        }
        
    }
    
    
    public SysData doLookUpByParamName(String paramName) {
        SysData obj = null;
        try 
        {
            
            obj  = sysDataRepository.doFindByName(paramName);// find("paramName",paramName).firstResult();
           
            
        } catch (Exception e) {
        
        }
      return obj;
    }
    
    public String doLookUpByParamNameStr(String paramName) {
        SysData obj = null;
        try 
        {
            
            obj  = sysDataRepository.doFindByName(paramName);// find("paramName",paramName).firstResult();
           
            
        } catch (Exception e) {
        
        }
      return obj==null?"0":obj.paramValue;
    }
    
    public String doLookUpByParamNameStr(String paramName, String defaultTo) {
        System.out.println("defaultTo = " + paramName);
        SysData obj = null;
        try 
        {
            
            obj  =   sysDataRepository.doFindByName(paramName);//find("paramName",paramName).firstResult();
           
            
        } catch (Exception e) {
        
            e.printStackTrace();
        }
      return obj==null?defaultTo:obj.paramValue;
    }
    
    public SysData doLookUpById(long tid) {
        SysData obj = null;
        try 
        {
            
            obj  = sysDataRepository.findById(tid);// find("tid",tid).firstResult();
           
            
        } catch (Exception e) {
        
        }
      return obj;
    }
    
    public List<SysData> doLoadList(LocalDateTime fromLLdt, LocalDateTime toLdt,  long status, String paramName) {
       
        List<SysData> obj = new ArrayList<>();
        try 
        {
               if(status == 0 && (paramName == null || paramName.trim().equals("")))
               {
                     obj = sysDataRepository.findByParams(fromLLdt, toLdt).list();// find("createdDate between ?1 and ?2 ",fromLLdt, toLdt).list();
                     
                     //System.out.println("obj = 1 ");
               }
               else if(status != 0 && (paramName == null || paramName.trim().equals("")))
               {
                    obj = sysDataRepository.findByParams(status, fromLLdt, toLdt).list();//  find("createdDate between ?1 and ?2  and sts = ?3 ",fromLLdt, toLdt, status).list();
                    //System.out.println("obj = 2 ");
               }
               else if(status != 0 && (paramName != null && !paramName.trim().equals("")))
               {
                    obj = sysDataRepository.findByParams(status, fromLLdt, toLdt, paramName).list();// find("createdDate between ?1 and ?2  and sts = ?3 and (paramName like ?4  or  paramValue like ?4 )",fromLLdt, toLdt, status, paramName+'%').list();
                    //System.out.println("obj = 3 ");
               }
           
            
        } catch (Exception e) {
            
            
            e.printStackTrace();
        
        }
      return obj;
    }
    public JsonObject doLoadConfigsList(String  fromDate, String toDate,  String searchKey,  long status, int pageIndex, int pageSize) {
        List<SysData> obj = new ArrayList<>();
        JsonArrayBuilder jar = Json.createArrayBuilder();
        JsonObjectBuilder job = Json.createObjectBuilder();
        ValidationHelper rh = new ValidationHelper();
        List<SysData> query = new ArrayList<>();
        
        try 
        {
            
            long dataCounta = sysDataRepository.count();// SysData.doList(searchKey, status, fromDate,  toDate).size();// doPullWalletTransactionSummaryByTransactionTypeCount(fromDate, toDate, transType);
           
             LOGGER.info(" COUNTA doLoadConfigsList--- "+dataCounta+" pageSize "+pageSize+" status "+status);
           
             long lastPage = (dataCounta/pageSize) +1;
             job.add("lastPage", lastPage);
             job.add("totalRecords", dataCounta);
             
             if(status == 0 && (!rh.isValid(searchKey) || "NA".equals(searchKey) || searchKey == null))
             {
                LOGGER.info("lastPage =status " + status);
                query = sysDataRepository.findByParams(rh.strToLDT(fromDate),  rh.strToLDT(toDate)).list();//List<SysData>)em.createNamedQuery(SysData.ALL_CONFIG_BY_DATE, SysData.class).setParameter("from", rh.strToLDT(fromDate)).setParameter("to", rh.strToLDT(toDate)).setFirstResult((pageIndex -1) * pageSize).setMaxResults(pageSize).getResultList();
                
                LOGGER.info("--  HERE 0 @ "+query.size());
             }
             if(status == 0 && rh.isValid(searchKey) && !"NA".equals(searchKey))
             {
                 query = sysDataRepository.findByParams(rh.strToLDT(fromDate),  rh.strToLDT(toDate), searchKey).list(); //(List<SysData>)em.createNamedQuery(SysData.ALL_CONFIG_BY_DATE_AND_SEARCHKEY, SysData.class).setParameter("passed2", searchKey+'%').setParameter("from", rh.strToLDT(fromDate)).setParameter("to", rh.strToLDT(toDate)).setFirstResult((pageIndex -1) * pageSize).setMaxResults(pageSize).getResultList();
           
                 LOGGER.info("  HERE 1 "+query);
             
             }
             
             if(status > 0 && (!rh.isValid(searchKey) || "NA".equals(searchKey) || searchKey == null))
             {
                LOGGER.info("lastPage =status " + status);
                query = sysDataRepository.findByParams(status, rh.strToLDT(fromDate),  rh.strToLDT(toDate)).list();// (List<SysData>)em.createNamedQuery(SysData.ALL_CONFIG_BY_DATE_AND_STATUS, SysData.class).setParameter("from", rh.strToLDT(fromDate)).setParameter("to", rh.strToLDT(toDate)).setParameter("passed", status).setFirstResult((pageIndex -1) * pageSize).setMaxResults(pageSize).getResultList();
                LOGGER.info("--  HERE 0A @ "+query.size());
             }
             if(status > 0 && rh.isValid(searchKey) && !"NA".equals(searchKey))
             {
                 query =  sysDataRepository.findByParams(status, rh.strToLDT(fromDate),  rh.strToLDT(toDate), searchKey).list();// (List<SysData>)em.createNamedQuery(SysData.ALL_CONFIG_BY_DATE_AND_SEARCHKEY_AND_STATUS, SysData.class).setParameter("from", rh.strToLDT(fromDate)).setParameter("to", rh.strToLDT(toDate)).setParameter("passed", status).setParameter("passed2", searchKey+'%').setFirstResult((pageIndex -1) * pageSize).setMaxResults(pageSize).getResultList();
                
                 System.out.println("  HERE 1A "+query);
             
             }
            
             System.out.println("query.size = " + query.size());
             query.stream().forEach(a->a.toJson());
             //query.stream().forEach(a->System.out.print("---"+a.toJsonX().build()));
             query.stream().forEach(a->jar.add(a.toJson()));
             
             JsonArray jarray = jar.build();
           
             if(!jarray.isEmpty())
             {
               
                 job.add("data", jarray);
             }
             else
             {   
               
                 job.add("data", Json.createArrayBuilder().build());
             }
           
            
        } catch (Exception e) {
            
            
            e.printStackTrace();
        
        }
      return job.build();
    }
    public SysData syncObj(SysData  vcode)
    {
        SysData resp = null;
        try
        {
           resp = Panache.getEntityManager().merge(vcode);
           
        }
        catch(Exception ex)
        {  
            ex.printStackTrace();
        }
       return resp;
    }
    
    @Transactional
    public SysData createObj(SysData  objx)
    {
        SysData resp = null;
        try
        {
           resp  =  Panache.getEntityManager().merge(objx);
           
        }
        catch(Exception ex)
        {  
            ex.printStackTrace();
        }
       return resp;
    }
    

    public List<SysData> getAll() {
        return all;
    }

    public void setAll(List<SysData> all) {
        this.all = all;
    }
    
   public String getProps(final List<SysData> props, final String paraname)
   {      
       
       SysData orElse = props.stream().filter(a-> paraname.trim().equals(a.paramName)).findFirst().orElse(null);
       
       return (orElse == null)?"NA":orElse.paramValue;
   }
  
   public String getProps(final String paraname)
   {      
       
       SysData orElse = sysDataRepository.doFindByParamName(paraname);// doReadAll().stream().filter(a-> paraname.trim().equals(a.paramName)).findFirst().orElse(null);
       
       return (orElse == null)?"NA":orElse.paramValue;
   }
   
   public String getProps(final String paraname, String orDefaultTo)
   {      
       
       SysData orElse = sysDataRepository.doFindByParamName(paraname);// doReadAll().stream().filter(a-> paraname.trim().equals(a.paramName)).findFirst().orElse(null);
       
       return (orElse == null)?orDefaultTo:orElse.paramValue;
   }
    
   */ 
    
}
