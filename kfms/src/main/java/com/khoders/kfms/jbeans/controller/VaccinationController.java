/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Vaccination;
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
@Named(value = "vaccinationController")
@SessionScoped
public class VaccinationController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private Vaccination vaccination = new Vaccination();
    private List<Vaccination> vaccinationList=  new LinkedList<>();
    private FormView formView = FormView.listForm();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
//        String qryString = "SELECT e FROM Vaccination e WHERE e.farmAccount = ?1";
        String qryString = "SELECT e FROM Vaccination e";
        vaccinationList = crudApi.getEm().createQuery(qryString, Vaccination.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        clearVaccination();
    }
    
    public void saveVaccination()
    {
        try 
        {
            vaccination.setProduction(vaccination.getProduction());
          if(crudApi.save(vaccination) != null)
          {
              vaccinationList = CollectionList.washList(vaccinationList, vaccination);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearVaccination();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteVaccination(Vaccination vaccination)
    {
        try 
        {
          if(crudApi.delete(vaccination))
          {
              vaccinationList.remove(vaccination);
              
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
    
    public void editVaccination(Vaccination vaccination)
    {
       optionText = "Update";
       this.vaccination=vaccination;
    }
    
    public void clearVaccination() 
    {
        vaccination = new Vaccination();
        vaccination.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void close()
    {
      vaccination = null;
      formView.restToListView();
    }

    public List<Vaccination> getVaccinationList() {
        return vaccinationList;
    }

    public Vaccination getVaccination() {
        return vaccination;
    }

    public void setVaccination(Vaccination vaccination) {
        this.vaccination = vaccination;
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
