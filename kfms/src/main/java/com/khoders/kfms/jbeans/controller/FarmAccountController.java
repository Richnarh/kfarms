/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.Msg;
import com.khoders.resource.utilities.SecurityUtil;
import com.khoders.kfms.entities.FarmAccount;
import com.khoders.kfms.services.FarmAccountService;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value="farmAccountController")
@RequestScoped
public class FarmAccountController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject FarmAccountService accountService;
    
    private FarmAccount farmAccount = new FarmAccount();
    
    private List<FarmAccount> accountList = new LinkedList<>();
    
    public void checkAccountExisitence()
    {
        
    }
    
    public void saveAccount()
    {
        try 
        {
            if(!SecurityUtil.checkPassword(farmAccount.getPassword(), farmAccount.getPassword2()))
            {
                FacesContext
                        .getCurrentInstance()
                        .addMessage(null, 
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, Msg.setMsg("Password do not match"), null));
                return;
            }
            
            if(!accountService.isTaken(farmAccount.getBusinessEmail()))
            {
              farmAccount.setPassword(SecurityUtil.hashText(farmAccount.getPassword()));

            if(crudApi.save(farmAccount) != null)
            {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Account created, Login now!"), null));
            }  
            else
            {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Oops! something went wrong, could not create account!"), null));
            }
          }
          else
           {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Email or phone number is already taken!"), null));
           }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void reset()
    {
        farmAccount = new FarmAccount();
    }
       
    
    public void editAccount(FarmAccount account)
    {
        this.farmAccount = account;
    }
    
    public FarmAccount getFarmAccount() {
        return farmAccount;
    }

    public void setFarmAccount(FarmAccount farmAccount) {
        this.farmAccount = farmAccount;
    }

    public List<FarmAccount> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<FarmAccount> accountList) {
        this.accountList = accountList;
    }

}
