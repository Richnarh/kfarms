/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.settings;

import com.khoders.resource.utilities.MsgResolver;

/**
 *
 * @author khoders
 */
public enum Units implements MsgResolver{
    GRAMS("GRAMS", "g"),
    KILOGRAM("KILOGRAMS", "kg");
    
    private final String label;
    private final String code;
    private Units(String code, String label)
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

