/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.enums;

import com.khoders.resource.utilities.MsgResolver;

/**
 * 31, October 2020
 * @author khoders
 */
public enum PurchaseType implements MsgResolver
{
    EGG("EGG", "Egg"),
    BIRD("BIRD", "Bird"),
    MANURE("MANURE", "Manure"),
    OTHER("OTHER", "Other");
   
    private final String code;
    private final String label;
    
    private PurchaseType(String code, String label)
    {
        this.code = code;
        this.label = label;
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
