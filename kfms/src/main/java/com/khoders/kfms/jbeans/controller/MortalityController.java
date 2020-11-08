/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Mortality;
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
@Named(value = "mortalityController")
@SessionScoped
public class MortalityController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private Mortality mortality = new Mortality();
    private List<Mortality> mortalityList=  new LinkedList<>();
    private FormView formView = FormView.listForm();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
//        String qryString = "SELECT e FROM Mortality e WHERE e.farmAccount = ?1";
        String qryString = "SELECT e FROM Mortality e";
        mortalityList = crudApi.getEm().createQuery(qryString, Mortality.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    }
    
    public void saveMortality()
    {
        try 
        {
            mortality.setProduction(mortality.getProduction());
          if(crudApi.save(mortality) != null)
          {
              mortalityList = CollectionList.washList(mortalityList, mortality);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearMortality();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteMortality(Mortality mortality)
    {
        try 
        {
          if(crudApi.delete(mortality))
          {
              mortalityList.remove(mortality);
              
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
    
    public void editMortality(Mortality mortality)
    {
       optionText = "Update";
       this.mortality=mortality;
    }
    
    public void clearMortality() 
    {
        mortality = new Mortality();
        mortality.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void close()
    {
      mortality = null;
      formView.restToListView();
    }

    public List<Mortality> getMortalityList() {
        return mortalityList;
    }

    public Mortality getMortality() {
        return mortality;
    }

    public void setMortality(Mortality mortality) {
        this.mortality = mortality;
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
