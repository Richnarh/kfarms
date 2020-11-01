/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.settings;

import com.khoders.resource.jpa.BaseModel;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name ="country")
public class Country extends BaseModel implements Serializable{
 public static final String _countryName = "countryName";
    @Column(name = "country_name")
    private String countryName;
    
    public static final String _countryCode = "countryCode";
    @Column(name = "country_code")
    private String countryCode;
    
    public static final String _currency = "currency";
    @Column(name = "currency")
    private String currency;
    
    public static final String _currencyCode = "currencyCode";
    @Column(name = "currency_code")
    private String currencyCode;   

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return countryName;
    }
    
    
}
