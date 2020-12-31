/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.shared;

import com.khoders.kfms.entities.settings.Units;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "sharedClass")
@SessionScoped
public class SharedClass implements Serializable
{
     
    public List<Units> getUnitsList()
    {
        return Arrays.asList(Units.values());
    }
    
}
