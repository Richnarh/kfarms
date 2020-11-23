/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller.settings;

import com.khoders.kfms.entities.settings.BirdType;
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
@Named(value = "birdTypeController")
@SessionScoped
public class BirdTypeController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private BirdType birdType = new BirdType();
    private List<BirdType> birdTypeList=  new LinkedList<>();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
        String qryString = "SELECT e FROM BirdType e WHERE e.farmAccount = ?1";
        birdTypeList = crudApi.getEm().createQuery(qryString, BirdType.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
        clearBirdType();
    }
    
    public void saveBirdType()
    {
        try 
        {
          birdType.genCode();
          if(crudApi.save(birdType) != null)
          {
              birdTypeList = CollectionList.washList(birdTypeList, birdType);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearBirdType();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteBirdType(BirdType birdType)
    {
        try 
        {
          if(crudApi.delete(birdType))
          {
              birdTypeList.remove(birdType);
              
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
    
    public void editBirdType(BirdType birdType)
    {
       optionText = "Update";
       this.birdType=birdType;
    }
    
    public void clearBirdType() 
    {
        birdType = new BirdType();
        birdType.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public List<BirdType> getBirdTypeList() {
        return birdTypeList;
    }

    public BirdType getBirdType() {
        return birdType;
    }

    public void setBirdType(BirdType bird) {
        this.birdType = bird;
    }
    
    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

}
