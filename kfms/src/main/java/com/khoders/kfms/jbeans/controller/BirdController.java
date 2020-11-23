/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Bird;
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
@Named(value = "birdController")
@SessionScoped
public class BirdController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private Bird bird = new Bird();
    private List<Bird> birdList = new LinkedList<>();
    
    private FormView formView = FormView.listForm();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
        String qryString = "SELECT e FROM Bird e WHERE e.farmAccount = ?1";
        birdList = crudApi.getEm().createQuery(qryString, Bird.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    }
    
    public void initBird()
    {
        bird = new Bird();
        formView.restToCreateView();
        optionText = "Save Changes";
        clearBird();
    }
    
    public void saveBird()
    {
        try 
        {
          bird.genCode();
          if(crudApi.save(bird) != null)
          {
              birdList = CollectionList.washList(birdList, bird);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearBird();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteBird(Bird bird)
    {
        try 
        {
          if(crudApi.delete(bird))
          {
              birdList.remove(bird);
              
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
    
    public void editBird(Bird bird)
    {
        formView.restToCreateView();
        optionText = "Update";
        this.bird=bird;
    }
    
    public void clearBird() 
    {
        bird = new Bird();
        bird.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void close()
    {
      bird = null;
      formView.restToListView();  
    }

    public List<Bird> getBirdList() {
        return birdList;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
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
