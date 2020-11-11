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
public enum ProductStatus implements MsgResolver{
    ACTIVE("ACTIVE", "Active"),
    IN_ACTIVE("IN_ACTIVE", "Inactive");
    
    private final String code,label;
    
    private ProductStatus(String code, String label)
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
