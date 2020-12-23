/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.services;

import com.khoders.kfms.entities.Cart;
import com.khoders.kfms.entities.Inventory;
import com.khoders.kfms.entities.SalesCatalogue;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.entities.settings.Category;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateRangeUtil;
import com.stately.common.utils.DateTimeUtils;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Store;
import javax.persistence.TypedQuery;
import com.google.common.base.Strings;

/**
 *
 * @author pascal
 */
@Stateless
public class InventoryService {
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    
    public List<Inventory> getInventory(Inventory inventory, DateRangeUtil dateRange)
    {
        try 
        {
            if(dateRange.getFromDate() == null || dateRange.getToDate() == null)
            {
                  String  queryString = "SELECT e FROM Inventory e WHERE e.farmAccount=?1 AND e.itemStatus=?2";
                  TypedQuery<Inventory> typedQuery = crudApi.getEm().createQuery(queryString, Inventory.class)
                                .setParameter(1, appSession.getCurrentUser());
                return typedQuery.getResultList();
            }
            
            
            String qryString = "SELECT e FROM Inventory e WHERE e.farmAccount=?1 AND e.itemStatus=?4 AND e.receivedDate BETWEEN ?2 AND ?3";
            
            TypedQuery<Inventory> typedQuery = crudApi.getEm().createQuery(qryString, Inventory.class)
                    .setParameter(1, appSession.getCurrentUser())
                    .setParameter(2, dateRange.getFromDate())
                    .setParameter(3, dateRange.getToDate());
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<Inventory> getCategoryInventory(Category category)
    {
        String query = "SELECT e FROM Inventory e WHERE e.farmAccount=?1 AND e.category=?2";
        try 
        {
            TypedQuery<Inventory> typedQuery =  crudApi.getEm().createQuery(query, Inventory.class)
                    .setParameter(1, appSession.getCurrentUser())
                    .setParameter(2, category);
            
            return typedQuery.getResultList();
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
       return Collections.emptyList();
    }
    
    public List<Inventory> getStoreInventory(Store store)
    {
        String query = "SELECT e FROM Inventory e WHERE e.farmAccount=?1 AND e.store = ?2";
        try 
        {
            TypedQuery<Inventory> typedQuery =  crudApi.getEm().createQuery(query, Inventory.class)
                    .setParameter(1, appSession.getCurrentUser())
                    .setParameter(2, store);
            
            return typedQuery.getResultList();
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
       return Collections.emptyList();
    }
    
    public List<Cart> getCartList(SalesCatalogue salesCatalogue)
    {
        try 
        {
            
            String query = "SELECT e FROM Cart e WHERE e.farmAccount=?1 AND e.salesCatalogue=?2";
            TypedQuery<Cart> typedQuery = crudApi.getEm().createQuery(query, Cart.class)
                    .setParameter(1, appSession.getCurrentUser())
                    .setParameter(2, salesCatalogue);
            
                   return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<SalesCatalogue> getSalesRecord(String receipt)
    {
        try 
        {
            
           if(receipt != null)
           {
                String qryString = "SELECT e FROM SalesCatalogue e WHERE e.farmAccount=?1 AND e.receiptNumber=?2";
            
                TypedQuery<SalesCatalogue> typedQuery = crudApi.getEm().createQuery(qryString, SalesCatalogue.class);
                    typedQuery.setParameter(1, appSession.getCurrentUser());
                    typedQuery.setParameter(2, receipt);
                    
                  return typedQuery.getResultList();
           }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<Cart> getSalesList(SalesCatalogue salesCatalogue)
    {
        String qryString = "SELECT e FROM Cart e WHERE e.farmAccount=?1 AND e.salesCatalogue=?2";
        try 
        {
            TypedQuery<Cart> typedQuery = crudApi.getEm().createQuery(qryString, Cart.class);
                    typedQuery.setParameter(1, appSession.getCurrentUser());
                    typedQuery.setParameter(2, salesCatalogue);
                    
                return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<Inventory> getShortageList()
    {
        String qryString = "SELECT e FROM Inventory e WHERE e.farmAccount=?1 AND e.quantity <= 5";
        try {
            
            TypedQuery<Inventory> typedQuery = crudApi.getEm().createQuery(qryString, Inventory.class);
               typedQuery.setParameter(1, appSession.getCurrentUser());
               
               return typedQuery.getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
   
    public List<SalesCatalogue> getSummaryInfo(DateRangeUtil dateRange)
    {
        try 
        {  
            if(dateRange.getFromDate() == null || dateRange.getToDate() == null)
                {
                  String  query = "SELECT e FROM SalesCatalogue e WHERE e.farmAccount=?1";
                  TypedQuery<SalesCatalogue> typedQuery
                        = crudApi
                                .getEm()
                                .createQuery(query, SalesCatalogue.class)
                                .setParameter(1, appSession.getCurrentUser());

                return typedQuery.getResultList();
            }
            
            String query = "SELECT e FROM SalesCatalogue e WHERE e.farmAccount=?1 AND e.purchaseDate BETWEEN ?2 AND ?3";
            TypedQuery<SalesCatalogue> typedQuery = crudApi
                            .getEm()
                            .createQuery(query, SalesCatalogue.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, dateRange.getFromDate())
                            .setParameter(3, dateRange.getToDate());
            
                return typedQuery.getResultList();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
       return Collections.emptyList();
    }
    
    public void generateReceipt(SalesCatalogue salesCatalogue)
    {
        if(Strings.isNullOrEmpty(salesCatalogue.getReceiptNumber()))
        {
            String receiptNumber = DateTimeUtils.formatDate(new Date(), "MMddhmss")
                    +UUID.randomUUID().toString().substring(0, 5).toUpperCase();
            
            salesCatalogue.setReceiptNumber(receiptNumber);
        }
    }
    
}