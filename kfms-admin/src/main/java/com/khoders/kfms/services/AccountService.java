/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.services;

import com.khoders.kfms.entities.account.PurchasePayment;
import com.khoders.kfms.entities.account.Invoice;
import com.khoders.kfms.entities.account.InvoiceItem;
import com.khoders.kfms.entities.account.InvoicePayment;
import com.khoders.kfms.entities.account.Purchase;
import com.khoders.kfms.entities.account.PurchaseItem;
import com.khoders.kfms.entities.enums.PaymentStatus;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateRangeUtil;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

/**
 *
 * @author khoders
 */
@Stateless
public class AccountService {
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;

    public List<InvoicePayment> getInvoicePayments(Invoice invoice)
    {
        
        String query = "SELECT e FROM InvoicePayment e WHERE e.farmAccount=?1 AND e.invoice=?2";
        
        TypedQuery<InvoicePayment> typedQuery = crudApi.getEm().createQuery(query, InvoicePayment.class)
                                .setParameter(1, appSession.getCurrentUser())
                                .setParameter(2, invoice);
                return typedQuery.getResultList();
    }
    public List<PurchasePayment> getPurchasePayments(Purchase purchase)
    {
        
        String query = "SELECT e FROM PurchasePayment e WHERE e.farmAccount=?1 AND e.purchase=?2 AND e.paymentStatus=?3 ORDER BY e.paymentDate ASC";
        
        TypedQuery<PurchasePayment> typedQuery = crudApi.getEm().createQuery(query, PurchasePayment.class)
                                .setParameter(1, appSession.getCurrentUser())
                                .setParameter(2, purchase);
                return typedQuery.getResultList();
    }
    
    public List<Invoice> getOutStandingInvoice(DateRangeUtil dateRange, Invoice invoice)
    {
        try {
            if(dateRange.getFromDate() == null || dateRange.getToDate() == null)
            {
                  String  queryString = "SELECT e FROM Invoice e WHERE e.farmAccount=?1 AND e.paymentStatus=?2";
                  TypedQuery<Invoice> typedQuery = crudApi.getEm().createQuery(queryString, Invoice.class)
                                .setParameter(1, appSession.getCurrentUser())
                                .setParameter(2, PaymentStatus.PARTIALLY_PAID);

                return typedQuery.getResultList();
            }
            
            String qryString = "SELECT e FROM Invoice e WHERE e.farmAccount=?1 AND e.issueDate BETWEEN ?2 AND ?3 AND e.paymentStatus=?4";
            
            TypedQuery<Invoice> typedQuery = crudApi.getEm().createQuery(qryString, Invoice.class)
                    .setParameter(1, appSession.getCurrentUser())
                    .setParameter(2, dateRange.getFromDate())
                    .setParameter(3, dateRange.getToDate())
                    .setParameter(4, PaymentStatus.PARTIALLY_PAID);
            
           return typedQuery.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    public List<Invoice> getFullyPaidInvoiceList(DateRangeUtil dateRange)
    {
        try {
            if(dateRange.getFromDate() == null || dateRange.getToDate() == null)
            {
                  String  queryString = "SELECT e FROM Invoice e WHERE e.farmAccount=?1 AND e.paymentStatus=?2";
                  TypedQuery<Invoice> typedQuery = crudApi.getEm().createQuery(queryString, Invoice.class)
                                .setParameter(1, appSession.getCurrentUser())
                                .setParameter(2, PaymentStatus.FULLY_PAID);

                return typedQuery.getResultList();
            }
            
            String qryString = "SELECT e FROM Invoice e WHERE e.farmAccount=?1 AND e.issueDate BETWEEN ?2 AND ?3 AND e.paymentStatus=?4";
            
            TypedQuery<Invoice> typedQuery = crudApi.getEm().createQuery(qryString, Invoice.class)
                    .setParameter(1, appSession.getCurrentUser())
                    .setParameter(2, dateRange.getFromDate())
                    .setParameter(3, dateRange.getToDate())
                    .setParameter(4, PaymentStatus.FULLY_PAID);
            
           return typedQuery.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<Purchase> getFullyPaidPurchaseList(DateRangeUtil dateRange)
    {
        try {
            if(dateRange.getFromDate() == null || dateRange.getToDate() == null)
            {
                  String  queryString = "SELECT e FROM Purchase e WHERE e.farmAccount=?1 AND e.paymentStatus=?2";
                  TypedQuery<Purchase> typedQuery = crudApi.getEm().createQuery(queryString, Purchase.class)
                                .setParameter(1, appSession.getCurrentUser())
                                .setParameter(2, PaymentStatus.FULLY_PAID);

                return typedQuery.getResultList();
            }
            
            String qryString = "SELECT e FROM Purchase e WHERE e.farmAccount=?1 AND e.issueDate BETWEEN ?2 AND ?3 AND e.paymentStatus=?4";
            
            TypedQuery<Purchase> typedQuery = crudApi.getEm().createQuery(qryString, Purchase.class)
                    .setParameter(1, appSession.getCurrentUser())
                    .setParameter(2, dateRange.getFromDate())
                    .setParameter(3, dateRange.getToDate())
                    .setParameter(4, PaymentStatus.FULLY_PAID);
            
           return typedQuery.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<InvoiceItem> getInvoiceList(Invoice invoice)
    {
        String query = "SELECT e FROM InvoiceItem e WHERE e.farmAccount=?1 AND e.invoice=?2";
        
        TypedQuery<InvoiceItem> typedQuery = crudApi.getEm().createQuery(query, InvoiceItem.class)
                                .setParameter(1, appSession.getCurrentUser())
                                .setParameter(2, invoice);
                return typedQuery.getResultList();
    }
    
    public List<PurchaseItem> getPurchasedList(Purchase purchase)
    {
        String query = "SELECT e FROM PurchaseItem e WHERE e.farmAccount=?1 AND e.purchase=?2";
        
        TypedQuery<PurchaseItem> typedQuery = crudApi.getEm().createQuery(query, PurchaseItem.class)
                                .setParameter(1, appSession.getCurrentUser())
                                .setParameter(2, purchase);
                return typedQuery.getResultList();
    }
    
    
    public List<InvoicePayment> getInvoicePayment()
    {
        try {
            
            String  queryString = "SELECT e FROM InvoicePayment e WHERE e.farmAccount=?1 AND e.paymentStatus=?2";
            TypedQuery<InvoicePayment> typedQuery = crudApi.getEm().createQuery(queryString, InvoicePayment.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, PaymentStatus.PARTIALLY_PAID);
//                            .setParameter(3, PaymentStatus.PENDING);
            
           return typedQuery.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    
    public List<InvoicePayment> getFullInvoicePayment()
    {
        try {
            
            String query = "SELECT e FROM InvoicePayment e WHERE e.farmAccount=?1 AND e.paymentStatus=?2";
            TypedQuery<InvoicePayment> typedQuery = crudApi.getEm().createQuery(query, InvoicePayment.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, PaymentStatus.FULLY_PAID);
            
           return typedQuery.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<PurchasePayment> getPurchasePayment(Purchase purchase)
    {
        
        String query = "SELECT e FROM PurchasePayment e WHERE e.farmAccount=?1 AND e.purchase=?2";
        
        TypedQuery<PurchasePayment> typedQuery = crudApi.getEm().createQuery(query, PurchasePayment.class)
                                .setParameter(1, appSession.getCurrentUser())
                                .setParameter(2, purchase);
                return typedQuery.getResultList();
    }
    
    
    public List<Purchase> getPurchaseListData(DateRangeUtil dateRange, Purchase purchase)
    {
        try 
        {
            if(dateRange.getFromDate() == null || dateRange.getToDate() == null)
            {
                  String  queryPurchase = "SELECT e FROM Purchase e WHERE e.farmAccount=?1";
                  TypedQuery<Purchase> typedQuery = crudApi.getEm().createQuery(queryPurchase, Purchase.class)
                                .setParameter(1, appSession.getCurrentUser());

                return typedQuery.getResultList();
            }
            
            String qryPurchase = "SELECT e FROM Purchase e WHERE e.farmAccount=?1 AND e.issueDate BETWEEN ?2 AND ?3";
            
            TypedQuery<Purchase> typedQuery = crudApi.getEm().createQuery(qryPurchase, Purchase.class)
                    .setParameter(1, appSession.getCurrentUser())
                    .setParameter(2, dateRange.getFromDate())
                    .setParameter(3, dateRange.getToDate());
            
           return typedQuery.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
   
    public List<PurchasePayment> getFullPurchasePayment()
    {
        try {
            
            String query = "SELECT e FROM PurchasePayment e WHERE e.farmAccount=?1 AND e.paymentStatus=?2";
            TypedQuery<PurchasePayment> typedQuery = crudApi.getEm().createQuery(query, PurchasePayment.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, PaymentStatus.FULLY_PAID);
            
           return typedQuery.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<PurchasePayment> getPurchasePayment()
    {
        try {
            
            String  queryString = "SELECT e FROM PurchasePayment e WHERE e.farmAccount=?1 AND e.paymentStatus=?2";
            TypedQuery<PurchasePayment> typedQuery = crudApi.getEm().createQuery(queryString, PurchasePayment.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, PaymentStatus.PARTIALLY_PAID);
//                            .setParameter(3, PaymentStatus.PENDING);
            
           return typedQuery.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    public List<PurchaseItem> getPurchaseList(Purchase purchase)
    {
        String query = "SELECT e FROM PurchaseItem e WHERE e.farmAccount=?1 AND e.purchase=?2";
        
        TypedQuery<PurchaseItem> typedQuery = crudApi.getEm().createQuery(query, PurchaseItem.class)
                                .setParameter(1, appSession.getCurrentUser())
                                .setParameter(2, purchase);
                return typedQuery.getResultList();
    }
    
}
