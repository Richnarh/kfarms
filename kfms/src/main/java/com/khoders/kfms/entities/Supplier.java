/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

import com.khoders.resource.jpa.BaseModel;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "supplier")
public class Supplier extends FarmAccountRecord implements Serializable{
    @Column(name = "supplier_code")
    private String supplierCode;
    
    public static final String _supplierName = "supplierName";
    @Column(name = "supplier_name")
    private String supplierName;
    
    public static final String _emailAddess = "emailAddess";
    @Column(name = "email_address")
    private String emailAddess;
    
    public static final String _phone = "phone";
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "business_name")
    private String businessName;
    
    
    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEmailAddess() {
        return emailAddess;
    }

    public void setEmailAddess(String emailAddess) {
        this.emailAddess = emailAddess;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void genCode()
    {
        setSupplierCode(SystemUtils.generateCode());
    }

    @Override
    public String toString() {
        return supplierName;
    }
    
    
}

