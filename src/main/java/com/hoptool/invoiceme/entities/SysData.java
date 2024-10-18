/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.invoiceme.entities;

import com.hoptool.entities.*;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;

/**
 *
 * @author root
 */
@Entity
@Table(name = "sys_data")
@NamedQueries({
    @NamedQuery(name = SysData.ALL, query = "SELECT s FROM SysData s")})
public class SysData implements Serializable {
    
    public static final String ALL = "SysData.findAll";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TID")
    public Long tid;
    @Column(name = "param_name")
    public String paramName;
    @Column(name = "param_value")
    public String paramValue;
    @Column(name = "created_by")
    public Integer createdBy;
    @Column(name = "created_date")
    //@Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime createdDate;
    @Column(name = "auth_by")
    public Integer authBy;
    @Column(name = "auth_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date authDate;
    @Column(name = "opr_comment")
    public String oprComment;
    @JoinColumn(name = "status", referencedColumnName = "TID")
    //@ManyToOne
    public String  status;

   
}
