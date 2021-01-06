/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Bird;
import com.khoders.kfms.entities.EggCollection;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.ProductionService;
import com.khoders.resource.jpa.CrudApi;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "dashboardController")
@SessionScoped
public class DashboardController implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private  AppSession appSession;
    @Inject private  ProductionService productionService;
    
    private List<EggCollection> eggCollectionList=  new LinkedList<>();
    
    private Long birdSize;
    private Long eggSize;
    
    private int healthyEggs;
    private int damagedEggs;
    
    @PostConstruct
    private void init()
    {
        loadEggs();
        totalBirds();
        birdTypes();
    }
    
    private void totalBirds()
    {
        birdSize = productionService.getBirdCount();
    }
    
    private void birdTypes()
    {
        eggSize = productionService.getEggCollectionCount();
    }
    
    public void loadEggs()
    {
         eggCollectionList = productionService.getEggCollectionList();
         eggCollectionList.forEach(eggs -> {
             healthyEggs += eggs.getHealthyQty();
             damagedEggs += eggs.getDamagedQty();
         });
    }

    public Long getBirdSize() {
        return birdSize;
    }

    public Long getEggSize() {
        return eggSize;
    }

    public int getHealthyEggs() {
        return healthyEggs;
    }

    public int getDamagedEggs() {
        return damagedEggs;
    }
    
}
