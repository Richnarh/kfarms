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
    ZERO_TO_4_WEEKS("ZERO_TO_4_WEEKS", "0 - 4"),
    FOUR_TO_8_WEEKS("FOUR_TO_8_WEEKS", "4 - 8"),
    EIGHT_TO_20_WEEKS("EIGHT_TO_20_WEEKS", "8 - 20"),
    TWENTY_TO_72_WEEKS("TWENTY_TO_72_WEEKS", "20 - 72");
    
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
