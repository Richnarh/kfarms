/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.EggCollection;
import com.khoders.kfms.entities.Production;
import com.khoders.kfms.jpa.AppSession;
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
@Named(value = "productionController")
@SessionScoped
public class ProductionController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private Production production = new Production();
    private EggCollection eggCollection = new EggCollection();
    private List<Production> productionList = new LinkedList<>();
    
    private FormView formView = FormView.listForm();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
//        String qryString = "SELECT e FROM Production e WHERE e.farmAccount = ?1";
        String qryString = "SELECT e FROM Production e";
        productionList = crudApi.getEm().createQuery(qryString, Production.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    }
    
    public void initProduction()
    {
        production = new Production();
        formView.restToCreateView();
        optionText = "Save Changes";
        clearProduction();
    }
    
    public void saveProduction()
    {
        try 
        {
          production.genCode();
          production.flockBatch();
          if(crudApi.save(production) != null)
          {
              productionList = CollectionList.washList(productionList, production);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearProduction();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteProduction(Production production)
    {
        try 
        {
          if(crudApi.delete(production))
          {
              productionList.remove(production);
              
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
    
    public void editProduction(Production production)
    {
        formView.restToCreateView();
        this.production=production;
    }
    
    public void clearProduction() 
    {
        production = new Production();
        production.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void close()
    {
      production = null;
      eggCollection = null;
      formView.restToListView();  
    }
    
    public void manage(Production production)
    {
        this.production = production;
        
        formView.restToDetailView();
        
    }

    public List<Production> getProductionList() {
        return productionList;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
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
