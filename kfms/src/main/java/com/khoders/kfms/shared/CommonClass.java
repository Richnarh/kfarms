/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.shared;

import com.khoders.kfms.entities.Bird;
import com.khoders.kfms.entities.settings.BirdType;
import com.khoders.kfms.jpa.AppSession;
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
@Named(value = "commonClass")
@SessionScoped
public class CommonClass implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    
    private List<BirdType> birdTypeList = new LinkedList<>();
    private List<Bird> birdList = new LinkedList<>();
    
    @PostConstruct
    public void init()
    {
//        String qryBirdType = "SELECT e FROM BirdType e WHERE e.farmAccount = ?1";
        String qryBirdType = "SELECT e FROM BirdType e ";
        birdTypeList = crudApi.getEm().createQuery(qryBirdType, BirdType.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    
//        String qryBird = "SELECT e FROM Bird e WHERE e.farmAccount = ?1";
        String qryBird = "SELECT e FROM Bird e ";
        birdList = crudApi.getEm().createQuery(qryBird, Bird.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    }

    public List<BirdType> getBirdTypeList() {
        return birdTypeList;
    }

    public List<Bird> getBirdList() {
        return birdList;
    }
    
}
