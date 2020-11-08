/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller.settings;

import com.khoders.kfms.entities.settings.Medication;
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
@Named(value = "medicationController")
@SessionScoped
public class MedicationController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private Medication medication = new Medication();
    private List<Medication> medicationList=  new LinkedList<>();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
//        String qryString = "SELECT e FROM Medication e WHERE e.farmAccount = ?1";
        String qryString = "SELECT e FROM Medication e";
        medicationList = crudApi.getEm().createQuery(qryString, Medication.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    }
    
    public void saveMedication()
    {
        try 
        {
          medication.genCode();
          if(crudApi.save(medication) != null)
          {
              medicationList = CollectionList.washList(medicationList, medication);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
              clearMedication();
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearMedication();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteMedication(Medication medication)
    {
        try 
        {
          if(crudApi.delete(medication))
          {
              medicationList.remove(medication);
              
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
    
    public void editMedication(Medication medication)
    {
        optionText = "Update";
       this.medication=medication;
    }
    
    public void clearMedication() 
    {
        medication = new Medication();
        medication.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public List<Medication> getMedicationList() {
        return medicationList;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication bird) {
        this.medication = bird;
    }
    
    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

}
