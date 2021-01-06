/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.converters;

import com.khoders.kfms.entities.Bird;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

/**
 *
 * @author khoders
 */
@FacesConverter(forClass = Bird.class)
public class BirdConverter extends SelectItemsConverter{
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if(value == null)
        {
            return null;
        }
        return ((Bird)value).getId();
    }
}
