/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.enums;

import com.khoders.resource.utilities.MsgResolver;

/**
 *
 * @author khoders
 */
public enum AccountCategory implements MsgResolver{
    ASSETS("ASSET", "Assets"),
    LIABILITY("LIABILITY","Liability"),
    EQUITY("EQUITY","Equity"),
    REVENUE("REVENUE","Revenue"),
    EXPENSE("EXPENSE","Expense");
    
    private final String code;
    private final String label;
    
    private AccountCategory(String code, String label)
    {
        this.code=code;
        this.label=label;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
