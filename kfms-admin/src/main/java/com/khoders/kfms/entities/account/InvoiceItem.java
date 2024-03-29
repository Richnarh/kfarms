/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.account;

import com.khoders.kfms.entities.FarmAccountRecord;
import com.khoders.kfms.entities.Product;
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
@Table(name = "invoice_item")
public class InvoiceItem extends FarmAccountRecord implements Serializable{
   @Column(name = "item_code")
   private String itemCode;
   
    @JoinColumn(name = "product", referencedColumnName = "id")
    @ManyToOne
    private Product product;
   
   @JoinColumn(name = "invoice", referencedColumnName = "id")
   @ManyToOne
   private Invoice invoice;
   
   @Column(name = "quantity")
   private int quantity;
   
   @Column(name = "item_name")
   private String itemName;
      
   @Column(name = "unit_price")
   private double unitPrice;
   
   @Column(name = "discount")
   private double discount;
   
   @Column(name = "total_amount")
   private double totalAmount;
   
   @Column(name = "charges")
   private double charges;
   
   @Column(name = "note")
   private String note;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
