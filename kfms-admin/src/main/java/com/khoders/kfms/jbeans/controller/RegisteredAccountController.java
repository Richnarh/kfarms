/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.FarmAccount;
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
 * @author pascal
 */
@Named(value = "registeredAccountController")
@SessionScoped
public class RegisteredAccountController implements Serializable
{
    @Inject private CrudApi crudApi;
    
    private FarmAccount farmAccount = new FarmAccount();
    private List<FarmAccount> farmAccountList = new LinkedList<>();
    
    @PostConstruct
    private void init()
    {
        String qryString = "SELECT e FROM FarmAccount e";
        farmAccountList = crudApi.getEm().createQuery(qryString, FarmAccount.class).getResultList();
    }

    public List<FarmAccount> getFarmAccountList()
    {
        return farmAccountList;
    }

}
