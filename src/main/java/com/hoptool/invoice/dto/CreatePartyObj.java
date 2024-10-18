/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import lombok.ToString;

/**
 *
 * @author paycraftsystems-i
 */

@ToString
public class CreatePartyObj
{
    public CreatePartyObj(CreateParty objx)
    {
        this.business_id = objx.business_id();
        this.party_name = objx.party_name();
        this.postal_address_id  = objx.postal_address_id();
 
    }
            
            
     public String business_id; 
     public String party_name;
     public String postal_address_id;
        
            
    
}
