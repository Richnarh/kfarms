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
public enum StageType implements MsgResolver{
    CHICK("CHICK", "Chick"),
    PULLET("PULLET", "Pullet (Adolescent)"),
    HEN("HEN", "Hen (Adult)");
    
    private final String label;
    private final String code;
    
    private StageType(String label, String code)
    {
        this.label=label;
        this.code=code;
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
