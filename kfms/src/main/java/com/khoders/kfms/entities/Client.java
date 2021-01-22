/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

import com.khoders.kfms.entities.enums.ClientType;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name="client")
public class Client  extends FarmAccountRecord implements Serializable{
    @Column(name = "client_code")
    private String clientCode;
    
    public static final String _clientName = "clientName";
    @Column(name = "client_name")
    private String clientName;
    
    public static final String _businessName = "businessName";
    @Column(name = "business_name")
    private String businessName;
    
    @Column(name = "client_type")
    @Enumerated(EnumType.STRING)
    private ClientType clientType;
    
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

    public String getClientCode()
    {
        return clientCode;
    }

    public void setClientCode(String clientCode)
    {
        this.clientCode = clientCode;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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

    public ClientType getClientType()
    {
        return clientType;
    }

    public void setClientType(ClientType clientType)
    {
        this.clientType = clientType;
    }
    
    public void genCode()
    {
        if(getClientCode()!= null)
        {
            setClientCode(getClientCode());
        }
        else
        {
            setClientCode(SystemUtils.generateCode());
        }
    }

    @Override
    public String toString() {
        return clientName;
    } 
}
    
