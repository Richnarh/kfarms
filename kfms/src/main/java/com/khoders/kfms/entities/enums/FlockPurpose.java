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
public enum FlockPurpose implements MsgResolver{
    EGGS("EGGS", "Eggs("+BirdCategory.LAYERS+")"),
    MEAT("MEAT", "Meat("+BirdCategory.BROILER+")"),
    CHICK_SALE("CHICK_SALE", "Chick Sale("+BirdCategory.HATCHERY+")"),
    BREEDING("BREEDING", "Breeding"),
    OTHER("OTHER", "Other");
    
    private final String code;
    private final String label;
    
    private FlockPurpose(String code, String label)
    {
        this.code = code;
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
}
