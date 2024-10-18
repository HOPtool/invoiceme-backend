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
 
}
