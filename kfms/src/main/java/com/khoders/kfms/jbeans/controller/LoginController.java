/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.Pages;
import com.khoders.resource.utilities.Msg;
import com.khoders.kfms.entities.FarmAccount;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.jbeans.Credential;
import com.khoders.kfms.services.FarmAccountService;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Faces;

/**
 *
 * @author khoders
 */
@Named(value="loginController")
@RequestScoped
public class LoginController implements Serializable
{
    @Inject private AppSession appSession;
    @Inject private FarmAccountService farmAccountService;
    
    private String email;
    private String password;
    
    private Credential credential = new Credential();

        public String doLogin()
    {
        try
        {

            credential.setEmail(email);
            credential.setPassword(password);

            FarmAccount account = farmAccountService.login(credential);

            if (account == null)
            {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Wrong username or Password"), null));
                return null;
            }

            initLogin(account);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
        
        public String initLogin(FarmAccount account)
    {
        try
        {

            if (account == null)
            {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Wrong username or Password"), null));
                return null;
            }

            appSession.login(account);
            
            Faces.redirect(Pages.index);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public String doLogout()
    {
        try
        {
            
            Faces.invalidateSession();
            Faces.logout();
            
            Faces.redirect(Pages.login);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
    
    
}