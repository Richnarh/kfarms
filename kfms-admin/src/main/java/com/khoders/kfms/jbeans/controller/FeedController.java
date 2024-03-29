/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Feed;
import com.khoders.kfms.entities.Production;
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
@Named(value = "feedController")
@SessionScoped
public class FeedController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    @Inject ProductionService productionService;
    
    private Feed feed = new Feed();
    private List<Feed> feedList=  new LinkedList<>();
    private FormView formView = FormView.listForm();
    
    private Production production;
    
    private String optionText;
  
    public void initFeed(Production production) 
    {
        clearFeed();
        
        feed.setProduction(production);
    }
    
    public void saveFeed()
    {
        try 
        {
            feed.setProduction(feed.getProduction());
          if(crudApi.save(feed) != null)
          {
              feedList = CollectionList.washList(feedList, feed);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
            initFeed(production);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteFeed(Feed feed)
    {
        try 
        {
          if(crudApi.delete(feed))
          {
              feedList.remove(feed);
              
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
    
    public void editFeed(Feed feed)
    {
       optionText = "Update";
       this.feed=feed;
    }
    
    public void loadFeed(Production production)
    {
        feedList = productionService.getFeedList(production);
    }
    
    public void clearFeed() 
    {
        feed = new Feed();
        feed.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void close()
    {
      feed = null;
      formView.restToListView();
    }

    public List<Feed> getFeedList() {
        return feedList;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
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
