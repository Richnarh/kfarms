/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.PurchasePayment;
import com.khoders.kfms.entities.account.Purchase;
import com.khoders.kfms.entities.account.PurchaseItem;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.AccountService;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.CollectionList;
import com.khoders.resource.utilities.FormView;
import com.khoders.resource.utilities.Msg;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "purchaseController")
@SessionScoped
public class PurchaseController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    @Inject AccountService accountService;
    
    private Purchase purchase = new Purchase();
    private List<Purchase> purchaseList = new LinkedList<>();
    
    private PurchaseItem purchaseItem = new PurchaseItem();
    private List<PurchaseItem> purchaseItemList = new LinkedList<>();
    
    private PurchasePayment purchasePayment = new PurchasePayment();
    private List<PurchasePayment> purchasePaymentList = new LinkedList<>();
    
    private FormView formView = FormView.listForm();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
//        String qryString = "SELECT e FROM Purchase e WHERE e.farmAccount = ?1";
        String qryString = "SELECT e FROM Purchase e";
        purchaseList = crudApi.getEm().createQuery(qryString, Purchase.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    }
    
    public void initPurchase()
    {
        purchase = new Purchase();
        formView.restToCreateView();
        optionText = "Save Changes";
        clearPurchase();
    }
    
    public void addPurchaseItem()
    {
        if(purchaseItem.getQuantity() <= 0 && purchaseItem.getUnitPrice() <= 0.0)
        {
             FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Please quantity and unit price are required!"), null)); 
             return;
        }
        try 
        {
            double amount = (purchaseItem.getQuantity() * purchaseItem.getUnitPrice()) + purchaseItem.getCharges();
            purchaseItem.setPurchase(purchase);
            purchaseItem.setTotalAmount(amount);
            
            purchaseItemList = CollectionList.washList(purchaseItemList, purchaseItem);
            
            clearPurchaseItem();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    
    public void savePurchase()
    {
        try 
        {
          purchase.genCode();
          if(crudApi.save(purchase) != null)
          {
              purchaseList = CollectionList.washList(purchaseList, purchase);
              
              for (PurchaseItem purchaseItem : purchaseItemList)
              {
                  purchaseItem.genCode();
                  purchaseItem.setPurchase(purchase);
                  purchaseItem.setFarmAccount(appSession.getCurrentUser());
                  crudApi.save(purchaseItem);
              }
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearPurchase();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deletePurchase(Purchase purchase)
    {
        try 
        {
            if(purchaseItem.getPurchase() != null)
            {
                if(crudApi.delete(purchaseItem.getPurchase()))
                {
                     if (crudApi.delete(purchase)) 
                     {
                        purchaseList.remove(purchase);

                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
                    } 
                     else 
                     {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
                     }
                }
                else
                {
                  FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));  
                }
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
            }
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void savePurchasePayment()
    {
        try 
        {
            purchasePayment.setPurchase(purchase);
            if(crudApi.save(purchasePayment) != null)
            {
                purchasePaymentList = CollectionList.washList(purchasePaymentList, purchasePayment);
                 FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
            }
            else
            {
               FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null)); 
            }
        } catch (Exception e) {
        }
    }
    
    public void editPurchasePayment(PurchasePayment purchasePayment)
    {
        this.purchasePayment=purchasePayment;
    }
    
    public void deletePurchasePayment(PurchasePayment purchasePayment)
    {
        try {
            if(crudApi.delete(purchasePayment))
            {
                purchasePaymentList.remove(purchasePayment);
            }
        } catch (Exception e) {
        }
    }
    
    public void removePurchaseItem(PurchaseItem purchaseItem)
    {
        purchaseItemList.remove(purchaseItem);
    }
    
    public void editPurchase(Purchase purchase)
    {
        this.purchase=purchase;
        purchaseItemList = accountService.getPurchaseItemList(purchase);
        formView.restToCreateView();
    }
    
    public void editPurchaseItem(PurchaseItem purchaseItem)
    {
        this.purchaseItem=purchaseItem;
    }
    
    public void clearPurchase() 
    {
        purchase = new Purchase();
        purchaseItem = new PurchaseItem();
        purchase.setFarmAccount(appSession.getCurrentUser());
        purchaseItem.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void clearPurchasePayment()
    {
        purchasePayment = new PurchasePayment();
        purchasePayment.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    public void clearPurchaseItem()
    {
        purchaseItem = new PurchaseItem();
        purchaseItem.setFarmAccount(appSession.getCurrentUser());
        SystemUtils.resetJsfUI();
    }

    public void close()
    {
      purchase = new Purchase();
      purchaseItem = new PurchaseItem();
      purchaseItemList = new LinkedList<>();
      purchasePayment = new PurchasePayment();
      purchasePaymentList = new LinkedList<>();
      formView.restToListView();  
    }
    
    public void purchasePayment(Purchase purchase)
    {
        this.purchase = purchase;
        purchasePaymentList = accountService.getPurchasePaymentList(purchase);
        formView.restToDetailView();
        
    }
    
    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public FormView getFormView() {
        return formView;
    }

    public void setFormView(FormView formView) {
        this.formView = formView;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public PurchaseItem getPurchaseItem() {
        return purchaseItem;
    }

    public void setPurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    public List<PurchaseItem> getPurchaseItemList() {
        return purchaseItemList;
    }

    public PurchasePayment getPurchasePayment() {
        return purchasePayment;
    }

    public void setPurchasePayment(PurchasePayment purchasePayment) {
        this.purchasePayment = purchasePayment;
    }

    public List<PurchasePayment> getPurchasePaymentList() {
        return purchasePaymentList;
    }

}
