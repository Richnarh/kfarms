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
public enum Units implements MsgResolver{
    GRAMS("GRAMS", "Grams", "g"),
    KILOGRAM("KILOGRAMS", "Kilograms", "kg");
    
    private final String label;
    private final String code;
    private final String abbr;
    
    private Units(String label, String code, String abbr)
    {
        this.label=label;
        this.code=code;
        this.abbr=abbr;
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

