/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.enums;

import com.khoders.resource.utilities.MsgResolver;

/**
 *
 * @author pascal
 */
public enum ClientType implements MsgResolver
{
    CUSTOMER("CUSTOMER", "Customer"),
    SUPPLIER("SUPPLIER", "Supplier");
    
    private final String code;
    private final String label;
    
    private ClientType(String code, String label)
    {
        this.code=code;
        this.label = label;
    }

    @Override
    public String getCode()
    {
        return code;
    }

    @Override
    public String getLabel()
    {
        return label;
    }

    @Override
    public String toString()
    {
        return label;
    }
}
