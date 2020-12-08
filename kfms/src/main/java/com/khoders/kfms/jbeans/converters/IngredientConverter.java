/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.converters;

import com.khoders.kfms.entities.settings.Ingredient;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.omnifaces.converter.SelectItemsConverter;

/**
 *
 * @author khoders
 */
@FacesConverter(forClass = Ingredient.class)
public class IngredientConverter extends SelectItemsConverter{
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if(value == null)
        {
            return null;
        }
        return ((Ingredient)value).getId();
    }
}
