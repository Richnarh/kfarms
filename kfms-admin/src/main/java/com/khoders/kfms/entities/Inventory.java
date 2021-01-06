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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "inventory")
public class Inventory extends FarmAccountRecord implements Serializable{
    @Column(name = "inventory_id")
    private String inventoryId;
    
    @Column(name = "received_date")
    private LocalDate receivedDate = LocalDate.now();
        
    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne
    private Product product;
    
    @JoinColumn(name = "category", referencedColumnName = "id")
    @ManyToOne
    private Category category;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "selling_price")
    private double sellingPrice;
    
    @Column(name = "discount")
    private double discount;
    
    @Column(name = "note")
    @Lob
    private String note;

    public String getInventoryId()
    {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId)
    {
        this.inventoryId = inventoryId;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public double getSellingPrice()
    {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice)
    {
        this.sellingPrice = sellingPrice;
    }
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }
    
    public void genCode()
    {
        if(getInventoryId() != null)
        {
           setInventoryId(getInventoryId());
        }
        else
        {
            setInventoryId(SystemUtils.generateCode());
        }
    }

    @Override
    public String toString() {
        return product+"";
    }
}
