/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.EggWeight;
import com.khoders.kfms.entities.Production;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.ProductionService;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.CollectionList;
import com.khoders.resource.utilities.FormView;
import com.khoders.resource.utilities.Msg;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import java.time.LocalDate;
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
@Named(value = "eggWeightController")
@SessionScoped
public class EggWeightController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    @Inject ProductionService productionService;
    
    private EggWeight eggWeight = new EggWeight();
    private List<EggWeight> eggWeightList=  new LinkedList<>();
    private FormView formView = FormView.listForm();
    
    private Production production;
    
    private String optionText;
    

    public void initEggWeight(Production production)
    {
        optionText = "Save Changes";
        clearEggWeight();
        eggWeight.setProduction(production);
    }
    
    public void saveEggWeight()
    {
        try 
        {
            eggWeight.setProduction(eggWeight.getProduction());
          if(crudApi.save(eggWeight) != null)
          {
              eggWeightList = CollectionList.washList(eggWeightList, eggWeight);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
            initEggWeight(production);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteEggWeight(EggWeight eggWeight)
    {
        try 
        {
          if(crudApi.delete(eggWeight))
          {
              eggWeightList.remove(eggWeight);
              
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
    
    public void editEggWeight(EggWeight eggWeight)
    {
       this.eggWeight=eggWeight;
    }
    
    public void loadEggWeight(Production production)
    {
        eggWeightList = productionService.getEggWeightList(production);
    }
    
    public void clearEggWeight() 
    {
        eggWeight = new EggWeight();
        eggWeight.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void close()
    {
      eggWeight = null;
      formView.restToListView();
    }

    public List<EggWeight> getEggWeightList()
    {
        return eggWeightList;
    }

    public EggWeight getEggWeight()
    {
        return eggWeight;
    }

    public void setEggWeight(EggWeight eggWeight)
    {
        this.eggWeight = eggWeight;
    }

    public String getOptionText()
    {
        return optionText;
    }

    public void setOptionText(String optionText)
    {
        this.optionText = optionText;
    }

    public FormView getFormView()
    {
        return formView;
    }

    public void setFormView(FormView formView)
    {
        this.formView = formView;
    }

}
