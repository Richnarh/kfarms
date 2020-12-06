/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.feedFormulation.FeedConfig;
import com.khoders.kfms.jpa.AppSession;
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
@Named(value = "feedConfigController")
@SessionScoped
public class FeedConfigController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private String optionText;
    private FeedConfig feedConfig = new FeedConfig();
    private List<FeedConfig> feedConfigList = new LinkedList<>();
    
    private FormView formView = FormView.listForm();
    
        
    public void initFeedConfig()
    {
        clearFeedConfig();
        formView.restToCreateView();
    }
    
    public void saveInvoice()
    {
        try 
        {
            feedConfig.genCode();
            if(crudApi.save(feedConfig) != null)
            {
                feedConfigList = CollectionList.washList(feedConfigList, feedConfig);
                
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
            }
            else
            {
               FacesContext.getCurrentInstance().addMessage(null, 
                      new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.FAILED_MESSAGE, null));
            }
            clearFeedConfig();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    
    public void editFeedConfig(FeedConfig feedConfig)
    {
        this.feedConfig = feedConfig;
        optionText = "Update";
        formView.restToCreateView();
    }
    
    public void deleteFeedConfig(FeedConfig feedConfig)
    {
        try 
        {
            if(crudApi.delete(feedConfig))
            {
                feedConfigList.remove(feedConfig);
                FacesContext.getCurrentInstance().addMessage(null, 
                      new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, 
                      new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void clearFeedConfig()
    {
        feedConfig = new FeedConfig();
        feedConfig.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public void closePage()
    {
       feedConfig = new FeedConfig();
       feedConfigList = new LinkedList<>();
       optionText = "Save Changes";
       formView.restToListView();
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public FeedConfig getFeedConfig() {
        return feedConfig;
    }

    public void setFeedConfig(FeedConfig feedConfig) {
        this.feedConfig = feedConfig;
    }

    public FormView getFormView() {
        return formView;
    }

    public void setFormView(FormView formView) {
        this.formView = formView;
    }

    public List<FeedConfig> getFeedConfigList() {
        return feedConfigList;
    }
    
    
}
