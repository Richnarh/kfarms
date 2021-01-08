/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.account.Invoice;
import com.khoders.kfms.entities.account.InvoiceItem;
import com.khoders.kfms.entities.account.InvoicePayment;
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
@Named(value = "invoiceController")
@SessionScoped
public class InvoiceController implements Serializable
{
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    @Inject private AccountService accountService;
    
    private Invoice invoice;
    private InvoiceItem invoiceItem = new InvoiceItem();
    private InvoicePayment invoicePayment = new InvoicePayment();
    private List<Invoice> invoiceList;
    private List<Invoice> fullyPaidInvoiceList;
    private List<InvoicePayment> invoicePaymentList;
    private List<InvoicePayment> fullInvoicePaymentList;
    
    private List<InvoiceItem> invoiceItemList = new LinkedList<>();
    private List<InvoiceItem> invoiceItemInfoList = new LinkedList<>();
    
    private DateRangeUtil dateRange = new DateRangeUtil();
    
    private FormView formView = FormView.listForm();
    private FormView paymentView = FormView.listForm();
    
    private SwitchTab switchTab = SwitchTab.firstTab();
    
    private String optionText;
    
    private double totalAmount;
    
    private double totalAmountPaid = 0.0;
    
    @PostConstruct
    private void init()
    {
        clearInvoice();
        clearInvoicePayment();
    }
   
    public void fetchFullyPaid()
    {
        fullyPaidInvoiceList = accountService.getFullyPaidInvoiceList(dateRange);
    }
    
    public void initInvoice()
    {
        clearInvoice();
        formView.restToCreateView();
    }
    
    public void filterOutStandingInvoice()
    {
        invoiceList = accountService.getOutStandingInvoice(dateRange, invoice);   
    }
    
    public void reset()
    {
        invoiceList = new LinkedList<>();
    }
    
    public void resetFullPayment()
    {
        fullyPaidInvoiceList = new LinkedList<>();
    }
    

    public void saveInvoice()
    {
        try 
        {
            if(invoice.getAmountRemaining() == 0.0)
            {
                invoice.setAmountRemaining(invoice.getTotalAmount());
            }
            else
            {
                invoice.setAmountRemaining(invoice.getAmountRemaining());
            }
            if(crudApi.save(invoice) != null)
            {
                invoiceList = CollectionList.washList(invoiceList, invoice);
                
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
            }
            else
            {
               FacesContext.getCurrentInstance().addMessage(null, 
                      new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.FAILED_MESSAGE, null));
            }
            clearInvoice();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
  
    public void editInvoice(Invoice invoice)
    {
        this.invoice = invoice;
        optionText = "Update";
        formView.restToCreateView();
    }
    
    public void deleteInvoice(Invoice invoice)
    {
        try 
        {
            invoiceItemList = accountService.getInvoiceList(invoice);
            invoicePaymentList = accountService.getInvoicePayments(invoice);

            if (!invoiceItemList.isEmpty() || !invoicePaymentList.isEmpty())
            {
                invoiceItemList.forEach(item ->
                {
                    crudApi.delete(item);
                });

                invoicePaymentList.forEach(payment ->
                {
                    crudApi.delete(payment);
                });

                invoiceItemList = accountService.getInvoiceList(invoice);
                invoicePaymentList = accountService.getInvoicePayments(invoice);

                if (invoiceItemList.isEmpty() || invoicePaymentList.isEmpty())
                {
                    if (crudApi.delete(invoice))
                    {
                        invoiceList.remove(invoice);
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
                    } else
                    {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.DELETE_MESSAGE, null));
                    }

                }
            } 
            else
            {
                if (crudApi.delete(invoice))
                {
                    invoiceList.remove(invoice);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
                } else
                {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.DELETE_MESSAGE, null));
                }
            }
           
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void clearInvoice()
    {
        invoice = new Invoice();
        invoice.setValueDate(LocalDate.now());
        invoice.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void closePage()
    {
       invoice = new Invoice();
       invoiceItemList = new LinkedList<>();
       totalAmount = 0;
       optionText = "Save Changes";
       formView.restToListView();
    }
    
    public void closeFullPayment()
    {
       fullInvoicePaymentList = new LinkedList<>();
       totalAmountPaid = 0;
       paymentView.restToListView();
    }
    

    
    public void manageInvoiceItem(Invoice invoice)
    {
       this.invoice = invoice;
        formView.restToDetailView();
        
        clearBillItem();

        invoiceItemList = accountService.getInvoiceList(invoice);
        
        for (InvoiceItem items : invoiceItemList) 
        {
            totalAmount += items.getTotalAmount();
            setTotalAmount(totalAmount);
        }
    }
   
    public void invoiceItemInfo(Invoice invoice)
    {
       this.invoice = invoice;
        paymentView.restToDetailView();
        
        invoiceItemInfoList = accountService.getInvoiceList(invoice);
        
        for (InvoiceItem items : invoiceItemInfoList) 
        {
            totalAmountPaid += items.getTotalAmount();
        }
    }
   

    public void addBillItemToTable()
    {
        try 
        {
            if(invoiceItem.getQuantity() <= 0)
            {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Please enter quantity"), null));
              return;
            }
            
            if(invoiceItem.getUnitPrice() <= 0)
            {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Please enter purchase cost"), null));
              return;
            }
            
              if(invoiceItem != null)
              {
                
                totalAmount= invoiceItem.getQuantity() * invoiceItem.getUnitPrice();
                  
                invoiceItem.setTotalAmount(totalAmount);
                invoiceItemList.add(invoiceItem);
                invoiceItemList = CollectionList.washList(invoiceItemList, invoiceItem);
                
                FacesContext
                        .getCurrentInstance()
                        .addMessage(null, 
                            new FacesMessage(
                                    FacesMessage.SEVERITY_INFO, Msg.setMsg("Invoice item added"), null));
              }
              else
                {
                   FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Invoice item removed!"), null));
                }
            clearBillItem();
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
            if(invoiceItemList != null)
            {
                for (InvoiceItem items : invoiceItemList) 
                {
                    items.setFarmAccount(appSession.getCurrentUser());
                    crudApi.save(items);
                    
                    totalAmount += items.getTotalAmount();
                    
                    setTotalAmount(totalAmount);
                    
                }
                
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Invoice item list saved!"), null));
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
     
    public void editInvoiceItem(InvoiceItem invoiceItem)
    {
        this.invoiceItem = invoiceItem;
        optionText = "Update";
    }
    
    public void deleteInvoiceItem(InvoiceItem invoiceItem)
    {
        try 
        {
            if(crudApi.delete(invoiceItem))
            {
                invoiceItemList.remove(invoiceItem);
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
    
    public void clearBillItem()
    {
        invoiceItem = new InvoiceItem();
        optionText = "Save Changes";
        invoiceItem.setInvoice(invoice);
        SystemUtils.resetJsfUI();
    }
    
    public void addPyament(Invoice invoice)
    {
       this.invoice = invoice;
        
        clearInvoicePayment();

        invoicePaymentList = accountService.getInvoicePayments(invoice);
    }
        
    
    public void fullPaymentList(Invoice invoice)
    {
       
       this.invoice = invoice;
       
       paymentView.restToCreateView();
        
        fullInvoicePaymentList = accountService.getInvoicePayments(invoice);
        
        fullInvoicePaymentList.forEach(payment -> {
            totalAmountPaid += payment.getAmountPaid();
        });
    }
        
    public void saveInvoicePayment()
    {
        if(invoicePayment.getAmountPaid() > invoice.getAmountRemaining())
        {
            FacesContext.getCurrentInstance().addMessage(null, 
                   new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please you're paying more than the remaining amount", null));
           return;
        }
        
        if(invoicePayment.getAmountPaid() == 0.0) return;
        
        if(invoicePayment.getAmountPaid() != invoice.getAmountRemaining())
        {
            invoicePayment.setPaymentStatus(PaymentStatus.PARTIALLY_PAID);
        }
        else
        {
            invoicePayment.setPaymentStatus(PaymentStatus.FULLY_PAID);
            
            invoice = crudApi.getEm().find(Invoice.class, invoicePayment.getInvoice().getId());
            invoice.setPaymentStatus(PaymentStatus.FULLY_PAID);
            crudApi.save(invoice);
        }
        try 
        {
            
            invoicePayment.setFarmAccount(appSession.getCurrentUser());
            invoicePayment.genCode();
            if(crudApi.save(invoicePayment) != null)
            {
                invoice.setAmountRemaining(invoice.getAmountRemaining() - invoicePayment.getAmountPaid());
                crudApi.save(invoice);
                
               invoicePaymentList = CollectionList.washList(invoicePaymentList, invoicePayment);
               
               FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.FAILED_MESSAGE, null));
            }
            clearInvoicePayment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editInvoicePayment(InvoicePayment invoicePayment)
    {
        this.invoicePayment = invoicePayment;
        optionText = "Update";
    }
    
    public void undoInvoicePayment(InvoicePayment invoicePayment)
    {
        try 
        {          
            invoice = crudApi.getEm().find(Invoice.class, invoicePayment.getInvoice().getId());
                        
            invoice.setAmountRemaining(invoice.getAmountRemaining() + invoicePayment.getAmountPaid());
            
            crudApi.save(invoice);
          
          if(crudApi.delete(invoicePayment))
          {
              invoicePaymentList.remove(invoicePayment);
               FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Undo successfully!"), null));
          }
          else
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
    
    public void clearInvoicePayment()
    {
        invoicePayment = new InvoicePayment();
        optionText = "Save Changes";
        invoicePayment.setInvoice(invoice);
        SystemUtils.resetJsfUI();
    }
    
    public FormView getFormView() {
        return formView;
    }

    public void setFormView(FormView formView) {
        this.formView = formView;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
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

    public InvoiceItem getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    public List<InvoiceItem> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }

    public InvoicePayment getInvoicePayment() {
        return invoicePayment;
    }

    public void setInvoicePayment(InvoicePayment invoicePayment) {
        this.invoicePayment = invoicePayment;
    }

    public List<InvoicePayment> getInvoicePaymentList() {
        return invoicePaymentList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Invoice> getFullyPaidInvoiceList() {
        return fullyPaidInvoiceList;
    }

    public List<InvoicePayment> getFullInvoicePaymentList() {
        return fullInvoicePaymentList;
    }

    public double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public FormView getPaymentView() {
        return paymentView;
    }

    public void setPaymentView(FormView paymentView) {
        this.paymentView = paymentView;
    }

    public List<InvoiceItem> getInvoiceItemInfoList() {
        return invoiceItemInfoList;
    }

    public SwitchTab getSwitchTab() {
        return switchTab;
    }

    public void setSwitchTab(SwitchTab switchTab) {
        this.switchTab = switchTab;
    }

}
