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
public enum AgeRange implements MsgResolver{
    ZERO_TO_8_WEEKS("ZERO_TO_8_WEEKS", "0 - 8"),
    EIGHT_TO_18_WEEKS("EIGHT_TO_18_WEEKS", "8 - 18"),
    EIGHTEEN_TO_72_WEEKS("EIGHTEEN_TO_72_WEEKS", "18 - 72");
    
    private final String code;
    private final String label;
    
    private AgeRange( String code, String label)
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
