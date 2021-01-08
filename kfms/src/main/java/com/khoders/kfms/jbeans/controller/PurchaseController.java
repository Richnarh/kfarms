/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.account.PurchasePayment;
import com.khoders.kfms.entities.account.Purchase;
import com.khoders.kfms.entities.account.PurchaseItem;
import com.khoders.kfms.entities.enums.PaymentStatus;
import com.khoders.kfms.jbeans.model.SwitchTab;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.AccountService;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.CollectionList;
import com.khoders.resource.utilities.DateRangeUtil;
import com.khoders.resource.utilities.FormView;
import com.khoders.resource.utilities.Msg;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import java.time.LocalDate;
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
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    @Inject private AccountService accountService;
    
    private Purchase purchase = new Purchase();
    private PurchaseItem purchaseItem = new PurchaseItem();
    private PurchasePayment purchasePayment = new PurchasePayment();
    private List<Purchase> purchaseList = new LinkedList<>();
    private List<Purchase> fullyPaidPurchaseList = new LinkedList<>();
    private List<PurchasePayment> purchasePaymentList = new LinkedList<>();
    private List<PurchasePayment> fullPurchasePaymentList = new LinkedList<>();
    
    private List<PurchaseItem> purchaseItemList = new LinkedList<>();
    private List<PurchaseItem> purchaseItemInfoList = new LinkedList<>();
    
    private DateRangeUtil dateRange = new DateRangeUtil();
    
    private FormView formView = FormView.listForm();
    private FormView paymentView = FormView.listForm();
    private SwitchTab switchTab = SwitchTab.firstTab();
    
    private String optionText;
    
    private double totalAmount = 0.0;
    private double totalAmountPaid = 0.0;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
        purchasePaymentList = accountService.getPurchasePayment();
        
        clearPurchase();
    }
    
    public void fetchFullyPaid()
    {
        purchasePaymentList = accountService.getFullPurchasePayment();
    }
    
    public void fetchFullyPaidBill()
    {
        fullyPaidPurchaseList = accountService.getFullyPaidPurchaseList(dateRange);
    }
    
    public void initPurchase()
    {
        clearPurchase();
        formView.restToCreateView();
    }
    
    public void outStandingBills()
    {
        purchaseList = accountService.getOutStandingBills(dateRange, purchase);
    }
    
    public void reset()
    {
        purchaseList = new LinkedList<>();
    }
    

    public void savePurchase()
    {
        try 
        {
            if(purchase.getAmountRemaining() == 0.0)
            {
                purchase.setAmountRemaining(purchase.getTotalAmount());
            }
            else
            {
                purchase.setAmountRemaining(purchase.getAmountRemaining());
            }
            purchase.genCode();
            if(crudApi.save(purchase) != null)
            {
                purchaseList = CollectionList.washList(purchaseList, purchase);
                
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
            }
            else
            {
               FacesContext.getCurrentInstance().addMessage(null, 
                      new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.FAILED_MESSAGE, null));
            }
            clearPurchase();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void editPurchase(Purchase purchase)
    {
        this.purchase = purchase;
        optionText = "Update";
        formView.restToCreateView();
    }
    
    public void deletePurchase(Purchase purchase)
    {
        try 
        {
           purchaseItemList = accountService.getPurchasedItemList(purchase);
           purchasePaymentList = accountService.getPurchasePaymentList(purchase);
           
           if(!purchaseItemList.isEmpty())
           {
               purchaseItemList.forEach(item -> {
                    crudApi.delete(item); 
               });
               
               purchaseItemList = accountService.getPurchasedItemList(purchase);
               
               if(purchaseItemList.isEmpty())
               {
                   if(crudApi.delete(purchase))
                   {
                       purchaseList.remove(purchase);
                       FacesContext.getCurrentInstance().addMessage(null, 
                      new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
                   }
                   else
                   {
                       FacesContext.getCurrentInstance().addMessage(null, 
                      new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.DELETE_MESSAGE, null));
                   }
                   
               }
           }
           
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public void clearPurchase()
    {
        purchase = new Purchase();
        purchase.setValueDate(LocalDate.now());
        purchase.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void closePage()
    {
       purchase = new Purchase();
       purchaseItemList = new LinkedList<>();
       totalAmount = 0;
       optionText = "Save Changes";
       formView.restToListView();
    }
    

    
    public void managePurchaseItem(Purchase purchase)
    {
       this.purchase = purchase;
        formView.restToDetailView();
        
        clearPurchaseItem();

        purchaseItemList = accountService.getPurchasedItemList(purchase);
        
        for (PurchaseItem items : purchaseItemList) 
        {
            totalAmount += items.getTotalAmount();
            setTotalAmount(totalAmount);
        }
    }
   

    public void addPurchaseItem()
    {
        try 
        {
            if(purchaseItem.getQuantity() <= 0)
            {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Please enter quantity"), null));
              return;
            }
            
            if(purchaseItem.getUnitPrice() <= 0)
            {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Please enter purchase cost"), null));
              return;
            }
            
              if(purchaseItem != null)
              {
                
                totalAmount= purchaseItem.getQuantity() * purchaseItem.getUnitPrice();
                  
                purchaseItem.setTotalAmount(totalAmount);
                purchaseItemList.add(purchaseItem);
                purchaseItemList = CollectionList.washList(purchaseItemList, purchaseItem);
                
                FacesContext
                        .getCurrentInstance()
                        .addMessage(null, 
                            new FacesMessage(
                                    FacesMessage.SEVERITY_INFO, Msg.setMsg("Purchase item added"), null));
              }
              else
                {
                   FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Purchase item removed!"), null));
                }
            clearPurchaseItem();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void saveAll()
    {
        try 
        {
            if(purchaseItemList != null)
            {
                for (PurchaseItem items : purchaseItemList) 
                {
                    items.genCode();
                    items.setFarmAccount(appSession.getCurrentUser());
                    crudApi.save(items);
                    
                    totalAmount += items.getTotalAmount();
                    
                    setTotalAmount(totalAmount);
                    
                }
                
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Purchase item list saved!"), null));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, Msg.setMsg("The list is empty!"), null));
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
     
    public void editPurchaseItem(PurchaseItem purchaseItem)
    {
        this.purchaseItem = purchaseItem;
        optionText = "Update";
    }
    
    public void deletePurchaseItem(PurchaseItem purchaseItem)
    {
        try 
        {
            if(crudApi.delete(purchaseItem))
            {
                purchaseItemList.remove(purchaseItem);
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.DELETE_MESSAGE, null));
            }
            else
            {
               FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.DELETE_MESSAGE, null));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void clearPurchaseItem()
    {
        purchaseItem = new PurchaseItem();
        optionText = "Save Changes";
        purchaseItem.setPurchase(purchase);
        SystemUtils.resetJsfUI();
    }
    
    public void addPyament(Purchase purchase)
    {
       this.purchase = purchase;
        
        clearPurchasePayment();

        purchasePaymentList = accountService.getPurchasePaymentList(purchase);
    }
        
    public void savePurchasePayment()
    {
        if (purchasePayment.getAmountPaid() > purchase.getAmountRemaining())
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please you're paying more than the remaining amount", null));
            return;
        }

        if (purchasePayment.getAmountPaid() != purchase.getAmountRemaining())
        {
            purchasePayment.setPaymentStatus(PaymentStatus.PARTIALLY_PAID);
        } else
        {
            purchasePayment.setPaymentStatus(PaymentStatus.FULLY_PAID);

            purchase = crudApi.getEm().find(Purchase.class, purchasePayment.getPurchase().getId());
            purchase.setPaymentStatus(PaymentStatus.FULLY_PAID);
            crudApi.save(purchase);
        }

        try
        {
            purchasePayment.genCode();
            if (crudApi.save(purchasePayment) != null)
            {
                purchase.setAmountRemaining(purchase.getAmountRemaining() - purchasePayment.getAmountPaid());
                crudApi.save(purchase);

                purchasePaymentList = CollectionList.washList(purchasePaymentList, purchasePayment);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.FAILED_MESSAGE, null));
            }
        clearPurchasePayment();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    public void editPurchasePayment(PurchasePayment purchasePayment)
    {
        this.purchasePayment = purchasePayment;
        optionText = "Update";
    }
    
    public void undoPurchasePayment(PurchasePayment purchasePayment)
    {
        try 
        {          
            purchase = crudApi.getEm().find(Purchase.class, purchasePayment.getPurchase().getId());
            
            System.out.println("Amount Remaining -- "+purchase.getAmountRemaining());
            System.out.println("Amount Paid -- "+purchasePayment.getAmountPaid());
            
            purchase.setAmountRemaining(purchase.getAmountRemaining() + purchasePayment.getAmountPaid());
            System.out.println("Amount Remaining (Updated) -- "+purchase.getAmountRemaining());
            crudApi.save(purchase);
          
            if (crudApi.delete(purchasePayment))
            {
                purchasePaymentList.remove(purchasePayment);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Undo successfully!"), null));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
            }
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
        }
    }
    
    public void fullPaymentList(Purchase purchase)
    {
       
       this.purchase = purchase;
       
       paymentView.restToCreateView();
        
        fullPurchasePaymentList = accountService.getPurchasePayments(purchase);
        
        fullPurchasePaymentList.forEach(payment -> {
            totalAmountPaid += payment.getAmountPaid();
        });
    }
    
    public void purchaseItemInfo(Purchase purchase)
    {
       this.purchase = purchase;
        paymentView.restToDetailView();
        
        purchaseItemInfoList = accountService.getPurchasedList(purchase);
        
        for (PurchaseItem items : purchaseItemInfoList) 
        {
            totalAmountPaid += items.getTotalAmount();
        }
    }
//   ddddd
    public void closeFullPayment()
    {
       fullPurchasePaymentList = new LinkedList<>();
       totalAmountPaid = 0;
       paymentView.restToListView();
    }
    
    public void resetFullPayment()
    {
        fullyPaidPurchaseList = new LinkedList<>();
    }
    
    public void clearPurchasePayment()
    {
        purchasePayment = new PurchasePayment();
        optionText = "Save Changes";
        purchasePayment.setFarmAccount(appSession.getCurrentUser());
        purchasePayment.setPurchase(purchase);
        SystemUtils.resetJsfUI();
    }
    
    public FormView getFormView() {
        return formView;
    }

    public void setFormView(FormView formView) {
        this.formView = formView;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public DateRangeUtil getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRangeUtil dateRange) {
        this.dateRange = dateRange;
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

    public void setPurchaseItemList(List<PurchaseItem> purchaseItemList) {
        this.purchaseItemList = purchaseItemList;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public SwitchTab getSwitchTab() {
        return switchTab;
    }

    public void setSwitchTab(SwitchTab switchTab) {
        this.switchTab = switchTab;
    }

    public FormView getPaymentView() {
        return paymentView;
    }

    public void setPaymentView(FormView paymentView) {
        this.paymentView = paymentView;
    }

    public List<Purchase> getFullyPaidPurchaseList() {
        return fullyPaidPurchaseList;
    }

    public List<PurchasePayment> getFullPurchasePaymentList()
    {
        return fullPurchasePaymentList;
    }
    
    public List<PurchaseItem> getPurchaseItemInfoList() {
        return purchaseItemInfoList;
    }

    public double getTotalAmountPaid() {
        return totalAmountPaid;
    }
    
}
