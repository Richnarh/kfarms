/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.settings.FeedType;
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
@Named(value = "feedTypeController")
@SessionScoped
public class FeedTypeController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private FeedType feedType = new FeedType();
    private List<FeedType> feedTypeList =  new LinkedList<>();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
//        String qryString = "SELECT e FROM FeedType e WHERE e.farmAccount = ?1";
        String qryString = "SELECT e FROM FeedType e";
        feedTypeList = crudApi.getEm().createQuery(qryString, FeedType.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    }
    
    public void saveFeedType()
    {
        try 
        {
            feedType.genCode();
          if(crudApi.save(feedType) != null)
          {
              feedTypeList = CollectionList.washList(feedTypeList, feedType);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearFeedType();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteFeedType(FeedType feedType)
    {
        try 
        {
          if(crudApi.delete(feedType))
          {
              feedTypeList.remove(feedType);
              
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
    
    public void editFeedType(FeedType feedType)
    {
       this.feedType=feedType;
    }
    
    public void clearFeedType() 
    {
        feedType = new FeedType();
        feedType.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public List<FeedType> getFeedTypeList() {
        return feedTypeList;
    }

    public FeedType getFeedType() {
        return feedType;
    }

    public void setFeedType(FeedType bird) {
        this.feedType = bird;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

}
