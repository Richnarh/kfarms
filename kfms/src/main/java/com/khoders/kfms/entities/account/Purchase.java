/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.account;

import com.khoders.kfms.entities.Customer;
import com.khoders.kfms.entities.FarmChartRecord;
import com.khoders.kfms.entities.enums.PaymentStatus;
import com.khoders.resource.enums.PaymentMethod;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "purchase")
public class Purchase extends FarmChartRecord implements Serializable{
    @Column(name = "purchase_id")
    private String purchaseId;
    
    @Column(name = "received_date")
    private LocalDate receivedDate;
    
    @Column(name = "dueDate")
    private LocalDate dueDate;
    
    @Column(name = "receipt_number")
    private String receiptNo;
    
    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    @JoinColumn(name = "customer", referencedColumnName = "id")
    @ManyToOne
    private Customer customer;
    
    @Column(name = "total_amount")
    private double totalAmount;
    
    @Column(name = "amount_remaining")
    private double AmountRemaining;

    @Column(name = "note")
    private String note;

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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getAmountRemaining() {
        return AmountRemaining;
    }

    public void setAmountRemaining(double AmountRemaining) {
        this.AmountRemaining = AmountRemaining;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    
    public void genCode()
    {
       if(getPurchaseId() != null)
        {
           setPurchaseId(getPurchaseId());
        }
        else
        {
          setPurchaseId(SystemUtils.generateCode());
        }
        
    }
}
