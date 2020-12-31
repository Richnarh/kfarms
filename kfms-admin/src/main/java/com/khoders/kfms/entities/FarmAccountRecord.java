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
public class FarmAccountRecord extends BaseModel implements Serializable{
    private static final String _farmAccount = "farmAccount";
    private static final String _farmName = FarmAccount._farmName;
    private static final String _farmEmail = FarmAccount._businessEmail;
    @JoinColumn(name = "farm_account")
    @ManyToOne
    private FarmAccount farmAccount;

    public FarmAccount getFarmAccount() {
        return farmAccount;
    }

    public void setFarmAccount(FarmAccount farmAccount) {
        this.farmAccount = farmAccount;
    }

}
