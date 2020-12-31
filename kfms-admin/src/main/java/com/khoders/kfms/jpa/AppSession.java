/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jpa;

import com.khoders.kfms.entities.FarmAccount;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "appSession")
@SessionScoped
public class AppSession implements Serializable{
    private FarmAccount currentUser;
    
    public void login(FarmAccount account)
    {
        this.currentUser = account;
    }
    
    public void logout()
    {
        this.currentUser = null;
    }

    public FarmAccount getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(FarmAccount currentUser) {
        this.currentUser = currentUser;
    }
}
