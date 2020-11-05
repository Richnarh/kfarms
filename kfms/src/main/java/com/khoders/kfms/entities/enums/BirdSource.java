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
public enum BirdSource implements MsgResolver{
    
    HATCHERY("HATCHERY", "Hatchery"),
    BREEDER("BREEDER", "Incubator(Own)"),
    HEN_HACTCHING_EGG("HEN_HACTCHING_EGGS", "Hen(Hen hatching Eggs)");
    
    private final String code;
    private final String label;
    
    private BirdSource(String code, String label)
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
