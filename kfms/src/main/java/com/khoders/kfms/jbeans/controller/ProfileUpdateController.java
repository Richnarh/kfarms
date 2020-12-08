/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.jpa.AppSession;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.Msg;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "profileUpdateController")
@SessionScoped
public class ProfileUpdateController implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    
    
    public void updateAccount()
    {
        try 
        {
            if(appSession.getCurrentUser() != null)
            {
                crudApi.save(appSession.getCurrentUser());
                FacesContext.getCurrentInstance().addMessage(null, 
                      new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
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
}
