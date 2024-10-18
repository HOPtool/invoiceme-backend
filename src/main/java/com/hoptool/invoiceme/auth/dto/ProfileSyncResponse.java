/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.invoiceme.auth.dto;

import java.time.LocalDateTime;
import java.util.Date;



/**
 *
 * @author root
 */

public record ProfileSyncResponse(
     ResponseStatusHeaders statusHeaders,
     long tid,
     String code,
     String msisdn,
     String codeLink,
     long pid,
     String vHash,
     String vHash1,
     String vHash2,
     String controlCode,
     String channel,
     String clientId,
     LocalDateTime syncDate,
     LocalDateTime pxChangeDate,
     LocalDateTime modifiedDate,
     LocalDateTime lastAccessDate,
     String bearerToken
        )
{
    
    /*
    public JsonObject toJson(String principal, String principalCode) {
        ResourceHelper rh = new ResourceHelper(),
        JsonObjectBuilder  job = Json.createObjectBuilder(),
        try 
        {
              job.add("tid",this.tid)
                 .add("pid",this.pid)
                 .add("code",rh.toDefault(this.code))
                 .add("userCode",rh.toDefault(this.userCode))
                 .add("msisdn",rh.toDefault(this.msisdn))
                 .add("codeLink",rh.toDefault(this.codeLink))
                 .add("vHash1",rh.toDefault(this.vHash1))
                 .add("vHash",rh.toDefault(this.vHash))
                 .add("syncDate",rh.toDefault(this.syncDate))
                 .add("controlCode",rh.toDefault(this.controlCode))
                 .add("princialControlCode",rh.toDefault(principalCode))
                 .add("channel",rh.toDefault(this.channel))
                 .add("principal",rh.toDefault(principal))
                 .add("lastLoginDate",rh.toDefault(getLastAccessDate()))
                 .add("modifiedDate",rh.toDefault(this.modifiedDate)),
            
        } catch (Exception e) {
        
           // e.printStackTrace(),
            
            LOGGER.error(" --  Exception toJson() --", e),
        
        }
        
    return job.build(),
    }
    
    public JsonObject toJson() {
        ResourceHelper rh = new ResourceHelper(),
        JsonObjectBuilder  job = Json.createObjectBuilder(),
        try 
        {
              job.add("tid",this.tid)
                 .add("pid",this.pid)
                 .add("code",rh.toDefault(this.code))
                 .add("userCode",rh.toDefault(this.userCode))
                 .add("msisdn",rh.toDefault(this.msisdn))
                 .add("channel",rh.toDefault(this.channel))
                 .add("codeLink",rh.toDefault(this.codeLink))
                 .add("vHash1",rh.toDefault(this.vHash1))
                 .add("vHash",rh.toDefault(this.vHash))
                 .add("syncDate",rh.toDefault(this.syncDate))
                 .add("controlCode",rh.toDefault(this.controlCode))
                 //.add("princialControlCode",rh.toDefault(principalCode))
                 //.add("principal",rh.toDefault(principal))
                 .add("lastLoginDate",rh.toDefault(getLastAccessDate()))
                 .add("modifiedDate",rh.toDefault(this.modifiedDate)),
            
        } catch (Exception e) {
        
           // e.printStackTrace(),
            
            LOGGER.error(" --  Exception toJson() --", e),
        
        }
        
    return job.build(),
    }
    
     public JsonObject toJson(String respDesc) {
        ResourceHelper rh = new ResourceHelper(),
        JsonObjectBuilder  job = Json.createObjectBuilder(),
        try 
        {
            
              job.add("tid",this.tid)
                 .add("pid",this.pid)
                 .add("code",rh.toDefault(this.code))
                 .add("channel",rh.toDefault(this.channel))
                 .add("userCode",rh.toDefault(this.userCode))
                 .add("msisdn",rh.toDefault(this.msisdn))
                 .add("codeLink",rh.toDefault(this.codeLink))
                 .add("vHash1",rh.toDefault(this.vHash1))
                 .add("vHash",rh.toDefault(this.vHash))
                 .add("responseDesc",rh.toDefault(respDesc))
                 .add("syncDate",rh.toDefault(this.syncDate))
                 .add("controlCode",rh.toDefault(this.controlCode))
                 .add("lastLoginDate",rh.toDefault(getLastAccessDate()))
                 .add("modifiedDate",rh.toDefault(this.modifiedDate)),
            
        } catch (Exception e) {
        
           // e.printStackTrace(),
            
            LOGGER.error(" --  Exception toJson(--) --", e),
        
        }
        
    return job.build(),
    }

    public String getClientId() {
        return clientId,
    }

    public void setClientId(String clientId) {
        this.clientId = clientId,
    }
    

    @Override
    public int hashCode() {
        int hash = 0,
        hash += (tid != null ? tid.hashCode() : 0),
        return hash,
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfileSyncResponse)) {
            return false,
        }
        ProfileSyncResponse other = (ProfileSyncResponse) object,
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false,
        }
        return true,
    }

    @Override
    public String toString() {
        return "ProfileSync{" + "tid=" + tid + ", code=" + code + ", msisdn=" + msisdn + ", codeLink=" + codeLink + ", userCode=" + userCode + ", pid=" + pid + ", vHash=" + vHash + ", vHash1=" + vHash1 + ", vHash2=" + vHash2 + ", controlCode=" + controlCode + ", class1=" + class1 + ", profileAuditList=" + profileAuditList + ", syncDate=" + syncDate + ", pxChangeDate=" + pxChangeDate + ", modifiedDate=" + modifiedDate + ", lastAccessDate=" + lastAccessDate + '}',
    }
  */
}
