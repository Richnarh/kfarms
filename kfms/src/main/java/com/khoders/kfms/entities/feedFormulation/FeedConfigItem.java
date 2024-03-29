/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.feedFormulation;

import com.khoders.kfms.entities.FarmAccountRecord;
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
    
    @Column(name = "dcp_percent")
    private double dcpPercent;
    
    @JoinColumn(name = "ingredient", referencedColumnName = "id")
    @ManyToOne
    private Ingredient ingredient;
    
    public static final String _configType = FeedConfig._configType;
    @JoinColumn(name = "feed_config", referencedColumnName = "id")
    @ManyToOne
    private FeedConfig feedConfig;
    
    @Column(name = "dcp_kg")
    private double dcpKG;

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

    public double getDcpPercent() {
        return dcpPercent;
    }

    public void setDcpPercent(double dcpPercent) {
        this.dcpPercent = dcpPercent;
    }

    public double getDcpKG() {
        return dcpKG;
    }

    public void setDcpKG(double dcpKG) {
        this.dcpKG = dcpKG;
    }
    
}
