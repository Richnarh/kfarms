/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

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
@Table(name = "cart")
public class Cart extends FarmAccountRecord implements Serializable{
    
    public static final String _cartId = "cartId";
    @Column(name = "cart_id")
    private String cartId;

    public static final String _inventory = "inventory";
    @JoinColumn(name = "inventory")
    @ManyToOne
    private Inventory inventory;
    
    public static final String _sales_catalogue = "salesCatalogue";
    @JoinColumn(name = "sales_catalogue")
    @ManyToOne
    private SalesCatalogue salesCatalogue;
    
    public static final String _client = "client";
    @JoinColumn(name = "client", referencedColumnName = "id")
    @ManyToOne
    private Client client;

    public static final String _quantity = "quantity";
    @Column(name = "quantity")
    private int quantity;

    public static final String _sellingPrice = "sellingPrice";
    @Column(name = "selling_price")
    private double sellingPrice;

    public static final String _totalAmount = "totalAmount";
    @Column(name = "total_amount")
    private double totalAmount;

    public static final String _costPrice = "costPrice";
    @Column(name = "cost_price")
    private double costPrice;
    
    public static final String _note = "note";
    @Column(name = "note")
    private String note;
    
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }
    public SalesCatalogue getSalesCatalogue() {
        return salesCatalogue;
    }

    public void setSalesCatalogue(SalesCatalogue salesCatalogue) {
        this.salesCatalogue = salesCatalogue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }
     public void genCode()
    {
        if(getCartId() != null)
        {
           setCartId(getCartId());
        }
        else
        {
            setCartId(SystemUtils.generateCode());
        }
    }
}
