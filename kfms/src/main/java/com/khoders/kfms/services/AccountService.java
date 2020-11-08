/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.services;

import com.khoders.kfms.entities.PurchasePayment;
import com.khoders.kfms.entities.account.Purchase;
import com.khoders.kfms.entities.account.PurchaseItem;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.resource.jpa.CrudApi;
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
//                                .setParameter(1, appSession.getCurrentUser());

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
//                                .setParameter(1, appSession.getCurrentUser());

                return typedQuery.getResultList();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
