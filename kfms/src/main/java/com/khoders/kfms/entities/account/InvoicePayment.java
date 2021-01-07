/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.account;

import com.khoders.kfms.entities.FarmChartRecord;
import com.khoders.kfms.entities.enums.PaymentStatus;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
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
@Table(name = "invoice_payment")
public class InvoicePayment extends FarmChartRecord implements Serializable{
    public static final String _paymentCode = "paymentCode";
    @Column(name = "payment_code")
    private String paymentCode;
    
    public static final String _invoice = "invoice";
    @JoinColumn(name = "invoice", referencedColumnName = "id")
    @ManyToOne
    private Invoice invoice;
    
    public static final String _paymentDate = "paymentDate";
    @Column(name = "payment_date")
    private LocalDate paymentDate = LocalDate.now();
    
    public static final String _amountPaid = "amountPaid";
    @Column(name = "amount_paid")
    private double amountPaid;
    
    public static final String _note = "note";
    @Lob
    @Column(name = "note")
    private String note;
    
    public static final String _paymentStatus = "paymentStatus";
    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    
    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
        
    public void genCode()
    {
        if(getPaymentCode() != null)
        {
           setPaymentCode(getPaymentCode());
        }
        else
        {
          setPaymentCode(SystemUtils.generateCode());
        }
    }
}