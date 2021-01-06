/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.account.ChartOfAccount;
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
@Named(value = "chartOfAccountController")
@SessionScoped
public class ChartOfAccountController implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    
    private ChartOfAccount chartOfAccount = new ChartOfAccount();
    private List<ChartOfAccount> chartOfAccountList = new LinkedList<>();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        
        String qryString = "SELECT e FROM ChartOfAccount e WHERE e.farmAccount = ?1";
        chartOfAccountList = crudApi.getEm().createQuery(qryString, ChartOfAccount.class)   
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
        clearChartOfAccount();
    }
    
    public void saveChartOfAccount()
    {
        try 
        {
            chartOfAccount.genCode();
            if(crudApi.save(chartOfAccount) != null)
            {
                chartOfAccountList = CollectionList.washList(chartOfAccountList, chartOfAccount);
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Oops! something went wrong, could not save item!"), null));
            }
            clearChartOfAccount();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void deleteChartOfAccount(ChartOfAccount chartOfAccount)
    {
        try 
        {
         if(crudApi.delete(chartOfAccount))
         {
             chartOfAccountList.remove(chartOfAccount);
             FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Item deleted!"), null));
         }
         else
         {
             FacesContext.getCurrentInstance().addMessage(null, 
             new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Item deleted!"), null));
         }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void editChartOfAccount(ChartOfAccount chartOfAccount)
    {
        optionText = "Update";
        this.chartOfAccount=chartOfAccount;
    }

    public void clearChartOfAccount() 
    {
        optionText = "Save Changes";
        chartOfAccount = new ChartOfAccount();
        chartOfAccount.setFarmAccount(appSession.getCurrentUser());
        SystemUtils.resetJsfUI();
    }
    public ChartOfAccount getChartOfAccount() {
        return chartOfAccount;
    }

    public void setChartOfAccount(ChartOfAccount chartOfAccount) {
        this.chartOfAccount = chartOfAccount;
    }

    public List<ChartOfAccount> getChartOfAccountList() {
        return chartOfAccountList;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

}
