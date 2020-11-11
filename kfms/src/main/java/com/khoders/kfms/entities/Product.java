/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

import com.khoders.kfms.entities.enums.ProductStatus;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import static java.util.Collections.frequency;
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
@Table(name = "product")
public class Product extends FarmAccountRecord implements Serializable
{
    @Column(name = "product_code")
    private String productCode;
    
    public static final String _productName = "productName";
    @Column(name = "product_name")
    private String productName;
    
    @Column(name = "product_location")
    private String productLocation;

    @Column(name = "tax_rate")
    private double tax_rate;
    
    @Column(name = "note")
    @Lob
    private String note;
    
    public static final String _productStatus = "productStatus";
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.ACTIVE;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }
    
    public double getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(double tax_rate) {
        this.tax_rate = tax_rate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
    
    public void genCode()
    {
        setProductCode(SystemUtils.generateCode());
    }

    @Override
    public String toString() {
        return productName;
    }

}
