/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.settings;

import com.khoders.kfms.entities.FarmAccountRecord;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "ingredient")
public class Ingredient extends FarmAccountRecord implements Serializable{
   @Column(name = "ingredient_id")
   private String ingredientId;
   
   @Column(name = "ingredient_name")
   private String ingredientName;
   
   @Lob
   @Column(name = "note")
   private String note;

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public void genCode()
    {
        if(getIngredientId() != null)
        {
            setIngredientId(getIngredientId());
        }
        else
        {
            setIngredientId(SystemUtils.generateCode());
        }
        
    }
    @Override
    public String toString() {
        return ingredientName;
    }
   
   
}
