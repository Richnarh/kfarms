/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Client;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.CollectionList;
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
@Named(value = "clientController")
@SessionScoped
public class ClientController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    
    private Client client = new Client();
    private List<Client> clientList =  new LinkedList<>();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        optionText = "Save Changes";
        String qryString = "SELECT e FROM Client e WHERE e.farmAccount = ?1";
        clientList = crudApi.getEm().createQuery(qryString, Client.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
        clearClient();
    }
    
    public void saveClient()
    {
        try 
        {
           client.genCode();
          if(crudApi.save(client) != null)
          {
              clientList = CollectionList.washList(clientList, client);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
          clearClient();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteClient(Client client)
    {
        try 
        {
          if(crudApi.delete(client))
          {
              clientList.remove(client);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void editClient(Client client)
    {
       optionText = "Update";
       this.client=client;
    }
    
    public void clearClient() 
    {
        client = new Client();
        client.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }
    
    public List<Client> getClientList() {
        return clientList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client bird) {
        this.client = bird;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

}
