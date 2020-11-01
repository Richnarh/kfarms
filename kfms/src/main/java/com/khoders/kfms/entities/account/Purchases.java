/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.account;

import com.khoders.kfms.entities.FarmRecord;
import com.khoders.kfms.entities.enums.Category;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "purchases")
public class Purchases extends FarmRecord implements Serializable{
    @Column(name = "purchase_id")
    private String purchaseId;
    
    @Column(name = "received_date")
    private LocalDate receivedDate;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "total_amount")
    private double totalAmount;

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    
    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public void genCode()
    {
        setPurchaseId(SystemUtils.generateCode());
    }
}
