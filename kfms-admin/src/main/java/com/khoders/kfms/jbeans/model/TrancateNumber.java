/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.model;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 *
 * @author khoders
 */
public class TrancateNumber {

    public static String shrinkNumber(double number) 
    {
        NumberFormat format = NumberFormat.getInstance();
        format.setRoundingMode(RoundingMode.DOWN);
        format.setMaximumFractionDigits(2);
        return format.format(number);
    }
}
