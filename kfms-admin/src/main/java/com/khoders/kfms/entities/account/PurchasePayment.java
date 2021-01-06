/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.account;

import com.khoders.kfms.entities.FarmChartRecord;
import com.khoders.kfms.entities.enums.PaymentStatus;
import com.khoders.resource.utilities.SystemUtils;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "purchase_payment")
public class PurchasePayment extends FarmChartRecord{
    @JoinColumn(name = "purchase", referencedColumnName = "id")
    @ManyToOne
    private Purchase purchase;
    
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    
    @Column(name = "payment_code")
    private String paymentCode;
    
    @Column(name = "amount")
    private double totalAmount;
    
    @Column(name = "payment_number")
    private String paymentNo;
    
    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    
   @Column(name = "amount_paid")
   private double AmountPaid;
    
   @Column(name = "note")
   @Lob
   private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public double getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(double AmountPaid) {
        this.AmountPaid = AmountPaid;
    }
    
    public void genCode()
    {
        if(getPaymentCode() != null)
        {
           setPaymentCode(getPaymentCode());
        }
        else
        {
           setPaymentCode(SystemUtils.generateShortCode());
        }
       
    }
    
}
