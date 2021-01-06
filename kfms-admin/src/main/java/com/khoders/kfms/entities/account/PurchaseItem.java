/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.account;

import com.khoders.kfms.entities.FarmChartRecord;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
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
@Table(name = "purchase_item")
public class PurchaseItem extends FarmChartRecord implements Serializable{
   @Column(name = "item_code")
   private String itemCode;
   
   @JoinColumn(name = "purchase", referencedColumnName = "id")
   @ManyToOne
   private Purchase purchase;
   
   @Column(name = "quantity")
   private int quantity;
   
   @Column(name = "item_name")
   private String itemName;
      
   @Column(name = "unit_price")
   private double unitPrice;
   
   @Column(name = "discount")
   private double discount;
   
   @Column(name = "charges")
   private double charges;
   
   @Column(name = "total_amount")
   private double totalAmount;

   @Column(name = "note")
   private String note;

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
   
    public void genCode()
    {
        if(getItemCode() != null)
        {
           setItemCode(getItemCode());
        }
        else
        {
          setItemCode(SystemUtils.generateCode());
        }
        
        
    }
   
}
