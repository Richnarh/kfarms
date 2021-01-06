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
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "feedOverviewController")
@SessionScoped
public class FeedOverviewController implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    @Inject private FeedFormulationService feedFormulationService;
    
    private List<FeedConfigItem> feedOverviewChartList = new LinkedList<>();
    private FeedConfigItem configItem = new FeedConfigItem();
    
    private FeedConfig selectedFeedConfig;
    
    public void initChart()
    {
        feedOverviewChartList = feedFormulationService.getFeedConfigList(selectedFeedConfig);   
    }
    
    public List<FeedConfigItem> getFeedOverviewChartList() {
        return feedOverviewChartList;
    }

    public FeedConfig getSelectedFeedConfig() {
        return selectedFeedConfig;
    }

    public void setSelectedFeedConfig(FeedConfig selectedFeedConfig) {
        this.selectedFeedConfig = selectedFeedConfig;
    }

    public FeedConfigItem getConfigItem() {
        return configItem;
    }

    public void setConfigItem(FeedConfigItem configItem) {
        this.configItem = configItem;
    }
    
}
