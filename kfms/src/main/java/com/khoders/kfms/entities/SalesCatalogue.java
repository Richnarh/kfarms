/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

import com.khoders.kfms.entities.settings.Category;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "sales_catalogue")
public class SalesCatalogue extends FarmAccountRecord implements Serializable{
    public static final String _catalogueId = "catalogueId";
    @Column(name = "catalogue_Id")
    private String catalogueId;
    
    public static final String _purchaseDate = "purchaseDate";
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
    
    public static final String _receiptNumber = "receiptNumber";
    @Column(name = "receipt_number")
    private String receiptNumber;
    
    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne
    private Product product;
    
    public static final String _category = "category";
    @JoinColumn(name = "category")
    @ManyToOne
    private Category category;
    
    public static final String _description = "description";
    @Column(name = "description")
    private String description;

    public static final String _quantity = "quantity";
    @Column(name = "quantity")
    private int quantity;

    public static final String _sellingPrice = "sellingPrice";
    @Column(name = "selling_price")
    private double sellingPrice;

    public static final String _costPrice = "costPrice";
    @Column(name = "cost_price")
    private double costPrice;
    
    public static final String _totalAmount = "totalAmount";
    @Column(name = "total_amount")
    private double totalAmount;

    public static final String _profit = "profit";
    @Column(name = "profit")
    private double profit;
    
    public static final String _discount = "discount";
    @Column(name = "discount")
    private double discount;


    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(String catalogueId) {
        this.catalogueId = catalogueId;
    }
    
    public void genCode()
    {
        if(getCatalogueId() != null)
        {
           setCatalogueId(getCatalogueId());
        }
        else
        {
            setCatalogueId(SystemUtils.generateCode());
        }
    }

}
