/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Production;
import com.khoders.kfms.entities.Treatment;
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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "treatmentController")
@SessionScoped
public class TreatmentController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    @Inject ProductionService productionService;
    
    private Treatment treatment = new Treatment();
    private List<Treatment> treatmentList=  new LinkedList<>();
    private FormView formView = FormView.listForm();
    
    private Production production;
    
    private String optionText;
    
    public void initTreatment(Production production)
    {
       clearTreatment();
       treatment.setProduction(production);
    }
    
    public void saveTreatment()
    {
        try 
        {
            treatment.setProduction(treatment.getProduction());
          if(crudApi.save(treatment) != null)
          {
              treatmentList = CollectionList.washList(treatmentList, treatment);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
            initTreatment(production);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteTreatment(Treatment treatment)
    {
        try 
        {
          if(crudApi.delete(treatment))
          {
              treatmentList.remove(treatment);
              
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
    
    public void editTreatment(Treatment treatment)
    {
       optionText = "Update";
       this.treatment=treatment;
    }
    
    public void loadTreatment(Production production)
    {
        treatmentList = productionService.getTreatmentList(production);
    }
    
    public void clearTreatment() 
    {
        treatment = new Treatment();
        treatment.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void close()
    {
      treatment = null;
      formView.restToListView();
    }

    public List<Treatment> getTreatmentList() {
        return treatmentList;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
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
