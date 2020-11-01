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
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name="customer")
public class Customer extends BaseModel implements Serializable{
    @Column(name = "customer_code")
    private String customerCode;
    
    public static final String _customerName = "customerName";
    @Column(name = "customer_name")
    private String customerName;
    
    public static final String _company = "company";
    @Column(name = "company")
    private String company;
    
    public static final String _streetName = "streetName";
    @Column(name = "street_name")
    private String streetName;
    
    public static final String _zipCode = "zipCode";
    @Column(name = "zip_code")
    private String zipCode;
    
    public static final String _phone = "phone";
    @Column(name = "phone")
    private String phone;
    
    public static final String _emailAddress = "emailAddress";
    @Column(name = "email_address")
    private String emailAddress;
    
    @Column(name = "note")
    @Lob
    private String note;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public void genCode()
    {
        setCustomerCode(SystemUtils.generateCode());
    }
}
    
