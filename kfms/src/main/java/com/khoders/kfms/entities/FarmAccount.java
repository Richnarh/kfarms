package com.khoders.kfms.entities;

import com.khoders.kfms.entities.enums.Units;
import com.khoders.kfms.entities.settings.Country;
import com.khoders.resource.jpa.BaseModel;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author 
 */
@Entity
@Table(name = "farm_account")
public class FarmAccount extends BaseModel implements Serializable{
    public static final String _fullName = "fullName";
    @Column(name="fullname")
    private String fullName;
    
    public static final String _farmName = "farmName";
    @Column(name="farm_name")
    private String farmName;
    
    public static final String _businessEmail = "businessEmail";
    @Column(name = "business_email")
    private String businessEmail;
    
    public static final String _phoneNumber = "phoneNumber";
    @Column(name = "phone_number")
    private String phoneNumber;
    
    public static final String _website = "website";
    @Column(name = "website")
    private String website;
    
    public static final String _country = "country";
    @JoinColumn(name = "country")
    @ManyToOne
    private Country country;
    
    public static final String _units = "units";
    @Column(name = "units")
    @Enumerated(EnumType.STRING)
    private Units units;
    
    public static final String _address = "address";
    @Column(name="address")
    private String address;
    
    public static final String _password = "password";
    @Column(name="password")
    private String password;
    
    @Transient
    private String password2;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
}
