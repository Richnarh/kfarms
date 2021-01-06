/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Inventory;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.ProductionService;
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
@Named(value = "inventoryController")
@SessionScoped
public class InventoryController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    @Inject private  ProductionService productionService;
    
    private Inventory inventory = new Inventory();
    private List<Inventory> inventoryList = new LinkedList<>();
    
    private FormView formView = FormView.listForm();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
        inventoryList = productionService.getInventoryList();
    }
    
    public void initInventory()
    {
        inventory = new Inventory();
        formView.restToCreateView();
        optionText = "Save Changes";
        clearInventory();
    }
    
    public void saveInventory()
    {
        try 
        {
          inventory.genCode();
          if(crudApi.save(inventory) != null)
          {
              inventoryList = CollectionList.washList(inventoryList, inventory);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearInventory();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteInventory(Inventory inventory)
    {
        try 
        {
            
          if(crudApi.delete(inventory))
          {
              inventoryList.remove(inventory);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
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
    
    public void editInventory(Inventory inventory)
    {
        formView.restToCreateView();
        optionText = "Update";
        this.inventory=inventory;
    }
    
    public void clearInventory() 
    {
        inventory = new Inventory();
        inventory.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void close()
    {
      inventory = null;
      formView.restToListView();  
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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

}
