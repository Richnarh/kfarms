/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.FeedBack;
import com.khoders.kfms.entities.Production;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.ProductionService;
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
@Named(value = "feedBackController")
@SessionScoped
public class FeedBackController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    @Inject private  ProductionService productionService;
    
    private Production production;
    
    private FeedBack feedBack = new FeedBack();
    private List<FeedBack> feedBackList=  new LinkedList<>();
    
    @PostConstruct
    private void init()
    {
        feedBack.setFarmAccount(appSession.getCurrentUser());
    }
    
    public void saveFeedBack()
    {
        try 
        {
            
          if(crudApi.save(feedBack) != null)
          {
              feedBackList = CollectionList.washList(feedBackList, feedBack);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Feeback is submitted successfully!"), null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Oops! something went wrong...!"), null));
          }
          clearFeedBack();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteFeedBack(FeedBack feedBack)
    {
        try 
        {
          if(crudApi.delete(feedBack))
          {
              feedBackList.remove(feedBack);
              
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
    
    public void clearFeedBack()
    {
        feedBack = new FeedBack();
        feedBack.setFarmAccount(appSession.getCurrentUser());
        SystemUtils.resetJsfUI();
    }

    public List<FeedBack> getFeedBackList() {
        return feedBackList;
    }

    public FeedBack getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(FeedBack feedBack) {
        this.feedBack = feedBack;
    }
}
