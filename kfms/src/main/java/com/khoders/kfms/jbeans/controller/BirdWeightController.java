/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.BirdWeight;
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
@Named(value = "birdWeightController")
@SessionScoped
public class BirdWeightController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private BirdWeight birdWeight = new BirdWeight();
    private List<BirdWeight> birdWeightList=  new LinkedList<>();
    private FormView formView = FormView.listForm();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
//        String qryString = "SELECT e FROM BirdWeight e WHERE e.farmAccount = ?1";
        String qryString = "SELECT e FROM BirdWeight e";
        birdWeightList = crudApi.getEm().createQuery(qryString, BirdWeight.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        clearBirdWeight();
    }
    
    public void saveBirdWeight()
    {
        try 
        {
            birdWeight.setProduction(birdWeight.getProduction());
          if(crudApi.save(birdWeight) != null)
          {
              birdWeightList = CollectionList.washList(birdWeightList, birdWeight);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearBirdWeight();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteBirdWeight(BirdWeight birdWeight)
    {
        try 
        {
          if(crudApi.delete(birdWeight))
          {
              birdWeightList.remove(birdWeight);
              
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
    
    public void editBirdWeight(BirdWeight birdWeight)
    {
       optionText = "Update";
       this.birdWeight=birdWeight;
    }
    
    public void clearBirdWeight() 
    {
        birdWeight = new BirdWeight();
        birdWeight.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void close()
    {
      birdWeight = null;
      formView.restToListView();
    }

    public List<BirdWeight> getBirdWeightList() {
        return birdWeightList;
    }

    public BirdWeight getBirdWeight() {
        return birdWeight;
    }

    public void setBirdWeight(BirdWeight birdWeight) {
        this.birdWeight = birdWeight;
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
