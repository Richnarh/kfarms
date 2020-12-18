/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.feedFormulation.FeedConfig;
import com.khoders.kfms.entities.feedFormulation.FeedConfigItem;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.FeedFormulationService;
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
@Named(value = "feedConfigController")
@SessionScoped
public class FeedConfigController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    @Inject FeedFormulationService feedFormulationService;
    
    private String optionText;
    private FeedConfig feedConfig = new FeedConfig();
    private List<FeedConfig> feedConfigList = new LinkedList<>();
    
    private FeedConfigItem feedConfigItem = new FeedConfigItem();
    private List<FeedConfigItem> feedConfigItemList = new LinkedList<>();
    
    private FormView formView = FormView.listForm();
    
    @PostConstruct
    private void init()
    {
        feedConfigList = feedFormulationService.getFeedConfigList();
    }
    
        
    public void initFeedConfig()
    {
        clearFeedConfig();
        formView.restToCreateView();
    }
    
    public void saveFeedConfig()
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
           feedConfigItemList = feedFormulationService.getFeedConfigList(feedConfig);
           
           if(!feedConfigItemList.isEmpty())
           {
               feedConfigItemList.forEach(item -> {
                    crudApi.delete(item); 
               });
               
               feedConfigItemList = feedFormulationService.getFeedConfigList(feedConfig);
               
               if(feedConfigItemList.isEmpty())
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
                      new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.DELETE_MESSAGE, null));
                   }
                   
               }
           }
           
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
   public void addFeedConfigItem()
    {
        try 
        {
              if(feedConfigItem != null)
              {
                feedConfigItemList.add(feedConfigItem);
                feedConfigItemList = CollectionList.washList(feedConfigItemList, feedConfigItem);
                
                FacesContext
                        .getCurrentInstance()
                        .addMessage(null, 
                            new FacesMessage(
                                    FacesMessage.SEVERITY_INFO, Msg.setMsg("Ingredient added"), null));
              }
              else
                {
                   FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Ingredient removed!"), null));
                }
            clearFeedConfigItem();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void manageFeedConfigItem(FeedConfig feedConfig)
    {
       this.feedConfig = feedConfig;
        formView.restToDetailView();
        
        clearFeedConfigItem();

        feedConfigItemList = feedFormulationService.getFeedConfigList(feedConfig);
        
    }
    
    public void editFeedConfigItem(FeedConfigItem feedConfigItem)
    {
        this.feedConfigItem = feedConfigItem;
        optionText = "Update";
    }
    
    public void deleteFeedConfigItem(FeedConfigItem feedConfigItem)
    {
        try 
        {
            if(crudApi.delete(feedConfigItem))
            {
                feedConfigItemList.remove(feedConfigItem);
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.DELETE_MESSAGE, null));
            }
            else
            {
               FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.DELETE_MESSAGE, null));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
     
    public void saveAll()
    {
        try 
        {
            if(feedConfigItemList != null)
            {
                for (FeedConfigItem items : feedConfigItemList) 
                {
                    items.setFarmAccount(appSession.getCurrentUser());
                    items.setDcpKG(items.getIngredientAmount() * (items.getDcpPercent()/100));
                   
                    crudApi.save(items);
                }
                
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Feed Ingredient list saved!"), null));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, Msg.setMsg("The list is empty!"), null));
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
    
    public void clearFeedConfigItem()
    {
        feedConfigItem = new FeedConfigItem();
        optionText = "Save Changes";
        feedConfigItem.setFeedConfig(feedConfig);
        SystemUtils.resetJsfUI();
    }
    
    public void closePage()
    {
       feedConfig = new FeedConfig();
       optionText = "Save Changes";
       formView.restToListView();
    }
    
    public void close()
    {
       feedConfig = new FeedConfig();
       feedConfigItem = new FeedConfigItem();
       feedConfigItemList = new LinkedList<>();
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

    public FeedConfigItem getFeedConfigItem() {
        return feedConfigItem;
    }

    public void setFeedConfigItem(FeedConfigItem feedConfigItem) {
        this.feedConfigItem = feedConfigItem;
    }

    public List<FeedConfigItem> getFeedConfigItemList() {
        return feedConfigItemList;
    }
    
    
}
