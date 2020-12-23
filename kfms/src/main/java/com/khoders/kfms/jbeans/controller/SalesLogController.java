/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Cart;
import com.khoders.kfms.entities.SalesCatalogue;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.InventoryService;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.DateRangeUtil;
import com.khoders.resource.utilities.Msg;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "salesLogController")
@SessionScoped
public class SalesLogController implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private InventoryService inventoryService;
    
    private SalesCatalogue salesCatalogue = new SalesCatalogue();
    private List<SalesCatalogue> salesCatalogueList = new LinkedList<>();
    private List<Cart> cartList = new LinkedList<>();
    DateRangeUtil dateRange = new DateRangeUtil();
    
    public void fetchSummary()
    {
        salesCatalogueList = inventoryService.getSummaryInfo(dateRange);
    }

    public void manageSalesLog(SalesCatalogue salesCatalogue)
    {
        this.salesCatalogue = salesCatalogue;
        cartList = inventoryService.getSalesList(salesCatalogue);
    }
   
    public void resetPage()
    {
        salesCatalogueList = new LinkedList<>();
        cartList = new LinkedList<>();
    }
    
    public void deleteSales()
    {
        try
        {
            if(!cartList.isEmpty())
            {
                cartList.forEach(item -> 
                {
                    crudApi.delete(item);
                });
                
                if(crudApi.delete(salesCatalogue))
                {
                     FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Receipt records deleted successfully!"), null));
                     resetPage();
                }
                else
                {
                  FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Oops! something went wrong..."), null));  
                } 
               
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Please select receipt"), null));

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<Cart> getCartList() {
        return cartList;
    }
    
    public SalesCatalogue getSalesCatalogue() {
        return salesCatalogue;
    }

    public void setSalesCatalogue(SalesCatalogue salesCatalogue) {
        this.salesCatalogue = salesCatalogue;
    }

    public List<SalesCatalogue> getSalesCatalogueList() {
        return salesCatalogueList;
    }

    public void setSalesCatalogueList(List<SalesCatalogue> salesCatalogueList) {
        this.salesCatalogueList = salesCatalogueList;
    }

    public DateRangeUtil getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRangeUtil dateRange) {
        this.dateRange = dateRange;
    }
 
}
