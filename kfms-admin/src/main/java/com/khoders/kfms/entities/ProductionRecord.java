/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

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
public class ProductionRecord extends BaseModel implements Serializable{
    private static final String _production = "production";
    @JoinColumn(name = "production", referencedColumnName = "id")
    @ManyToOne
    private Production production;
    
    private static final String _farmAccount = "farmAccount";
    @JoinColumn(name = "farm_account", referencedColumnName = "id")
    @ManyToOne
    private FarmAccount farmAccount;

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public FarmAccount getFarmAccount() {
        return farmAccount;
    }

    public void setFarmAccount(FarmAccount farmAccount) {
        this.farmAccount = farmAccount;
    }
    
    
}
