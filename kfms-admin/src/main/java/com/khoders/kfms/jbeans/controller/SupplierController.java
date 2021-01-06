/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Supplier;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.CollectionList;
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
@Named(value = "supplierController")
@SessionScoped
public class SupplierController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private Supplier supplier = new Supplier();
    private List<Supplier> supplierList =  new LinkedList<>();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
//        String qryString = "SELECT e FROM Supplier e WHERE e.farmAccount = ?1";
        String qryString = "SELECT e FROM Supplier e";
        supplierList = crudApi.getEm().createQuery(qryString, Supplier.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    }
    
    public void saveSupplier()
    {
        try 
        {
            supplier.genCode();
          if(crudApi.save(supplier) != null)
          {
              supplierList = CollectionList.washList(supplierList, supplier);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearSupplier();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteSupplier(Supplier supplier)
    {
        try 
        {
          if(crudApi.delete(supplier))
          {
              supplierList.remove(supplier);
              
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
    
    public void editSupplier(Supplier supplier)
    {
       optionText = "Update";
       this.supplier=supplier;
    }
    
    public void clearSupplier() 
    {
        supplier = new Supplier();
        supplier.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier bird) {
        this.supplier = bird;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

}
