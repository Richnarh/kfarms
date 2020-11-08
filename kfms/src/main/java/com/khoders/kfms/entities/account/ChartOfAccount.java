/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.account;

import com.khoders.kfms.entities.enums.AccountType;
import com.khoders.resource.jpa.BaseModel;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "chart_of_account")
public class ChartOfAccount extends BaseModel implements Serializable{
    @Column(name = "account_code")
    private String accountCode;
    
    public static final String _accountName = "accountName";
    @Column(name = "account_name")
    private String accountName;
    
    public static final String _accountType = "accountType";
    @Column(name = "account_type")
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;
    
    @Column(name = "note")
    private String note;

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public void genCode()
    {
        setAccountCode(SystemUtils.generateCode());
    }

    @Override
    public String toString() {
        return accountName;
    }
    
    
}
