/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.feedFormulation;

import com.khoders.kfms.entities.FarmAccountRecord;
import com.khoders.kfms.entities.settings.Ingredient;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "config_item")
public class FeedConfigItem extends FarmAccountRecord implements Serializable{
    @Column(name = "ingredient_amount")
    private double ingredientAmount;
    
    @Column(name = "dcp")
    private double dcp;
    
    @JoinColumn(name = "ingredient", referencedColumnName = "id")
    @ManyToOne
    private Ingredient ingredient;
    
    @JoinColumn(name = "feed_config", referencedColumnName = "id")
    @ManyToOne
    private FeedConfig feedConfig;

    public double getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(double ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public FeedConfig getFeedConfig() {
        return feedConfig;
    }

    public void setFeedConfig(FeedConfig feedConfig) {
        this.feedConfig = feedConfig;
    }

    public double getDcp() {
        return dcp;
    }

    public void setDcp(double dcp) {
        this.dcp = dcp;
    }
    
}
