/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.EggCollection;
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
@Named(value = "eggCollectionController")
@SessionScoped
public class EggCollectionController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private EggCollection eggCollection = new EggCollection();
    private List<EggCollection> eggCollectionList=  new LinkedList<>();
    private FormView formView = FormView.listForm();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
//        String qryString = "SELECT e FROM EggCollection e WHERE e.farmAccount = ?1";
        String qryString = "SELECT e FROM EggCollection e";
        eggCollectionList = crudApi.getEm().createQuery(qryString, EggCollection.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    }
    
    public void saveEggCollection()
    {
        try 
        {
            eggCollection.setProduction(eggCollection.getProduction());
          if(crudApi.save(eggCollection) != null)
          {
              eggCollectionList = CollectionList.washList(eggCollectionList, eggCollection);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearEggCollection();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteEggCollection(EggCollection eggCollection)
    {
        try 
        {
          if(crudApi.delete(eggCollection))
          {
              eggCollectionList.remove(eggCollection);
              
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
    
    public void editEggCollection(EggCollection eggCollection)
    {
       this.eggCollection=eggCollection;
    }
    
    public void clearEggCollection() 
    {
        eggCollection = new EggCollection();
        eggCollection.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void close()
    {
      eggCollection = null;
      formView.restToListView();
    }

    public List<EggCollection> getEggCollectionList() {
        return eggCollectionList;
    }

    public EggCollection getEggCollection() {
        return eggCollection;
    }

    public void setEggCollection(EggCollection eggCollection) {
        this.eggCollection = eggCollection;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public FormView getFormView() {
        return formView;
    }

    public void setFormView(FormView formView) {
        this.formView = formView;
    }

}
