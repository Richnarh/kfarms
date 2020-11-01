/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

import com.khoders.kfms.entities.account.ChartOfAccount;
import com.khoders.resource.jpa.BaseModel;
import java.io.Serializable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author khoders
 */
@MappedSuperclass
public class AccountRecord extends BaseModel implements Serializable{
        
    private static final String _accountName = ChartOfAccount._accountName;
    @JoinColumn(name = "chart_of_account", referencedColumnName = "id")
    @ManyToOne
    private ChartOfAccount chartOfAccount;

    public ChartOfAccount getChartOfAccount() {
        return chartOfAccount;
    }

    public void setChartOfAccount(ChartOfAccount chartOfAccount) {
        this.chartOfAccount = chartOfAccount;
    }
    
    
}
