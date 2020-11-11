/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.services;

import com.khoders.kfms.entities.PurchasePayment;
import com.khoders.kfms.entities.account.Invoice;
import com.khoders.kfms.entities.account.InvoiceItem;
import com.khoders.kfms.entities.account.InvoicePayment;
import com.khoders.kfms.entities.account.Purchase;
import com.khoders.kfms.entities.account.PurchaseItem;
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
    
    public List<PurchaseItem> getPurchaseItemList(Purchase purchase)
    {
//        String qryPurchaseItem = "SELECT e FROM PurchaseItem e WHERE e.farmAccount=?1";
        String qryPurchaseItem = "SELECT e FROM PurchaseItem e WHERE e.purchase=?1";
        try 
        {
            TypedQuery<PurchaseItem> typedQuery = crudApi.getEm().createQuery(qryPurchaseItem, PurchaseItem.class)
                                .setParameter(1, purchase);
//                              .setParameter(1, appSession.getCurrentUser());

                return typedQuery.getResultList();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<PurchasePayment> getPurchasePaymentList(Purchase purchase)
    {
//        String qryPurchaseItem = "SELECT e FROM PurchaseItem e WHERE e.farmAccount=?1";
        String qryPurchasePayment = "SELECT e FROM PurchasePayment e WHERE e.purchase=?1";
        try 
        {
            TypedQuery<PurchasePayment> typedQuery = crudApi.getEm().createQuery(qryPurchasePayment, PurchasePayment.class)
                                .setParameter(1, purchase);
//                              .setParameter(1, appSession.getCurrentUser());

                return typedQuery.getResultList();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<InvoicePayment> getInvoicePayments(Invoice invoice)
    {
        
        String query = "SELECT e FROM InvoicePayment e WHERE e.farmAccount=?1 AND e.invoice=?2";
        
        TypedQuery<InvoicePayment> typedQuery = crudApi.getEm().createQuery(query, InvoicePayment.class)
                                .setParameter(1, appSession.getCurrentUser())
                                .setParameter(2, invoice);
                return typedQuery.getResultList();
    }
    
    public List<Invoice> getInvoiceListData(DateRangeUtil dateRange, Invoice invoice)
    {
        try {
            if(dateRange.getFromDate() == null || dateRange.getToDate() == null)
            {
                  String  queryString = "SELECT e FROM Invoice e WHERE e.farmAccount=?1";
                  TypedQuery<Invoice> typedQuery = crudApi.getEm().createQuery(queryString, Invoice.class)
                                .setParameter(1, appSession.getCurrentUser());

                return typedQuery.getResultList();
            }
            
            String qryString = "SELECT e FROM Invoice e WHERE e.farmAccount=?1 AND e.valueDate BETWEEN ?2 AND ?3";
            
            TypedQuery<Invoice> typedQuery = crudApi.getEm().createQuery(qryString, Invoice.class)
                    .setParameter(1, appSession.getCurrentUser())
                    .setParameter(2, dateRange.getFromDate())
                    .setParameter(3, dateRange.getToDate());
            
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
    
}
