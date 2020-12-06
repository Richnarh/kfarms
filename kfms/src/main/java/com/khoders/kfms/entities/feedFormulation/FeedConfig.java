/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.feedFormulation;

import com.khoders.kfms.entities.FarmChartRecord;
import com.khoders.kfms.entities.enums.AgeRange;
import com.khoders.kfms.entities.enums.StageType;
import com.khoders.kfms.entities.enums.Units;
import com.khoders.kfms.entities.settings.FeedType;
import com.khoders.kfms.entities.settings.Ingredient;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "feed_config")
public class FeedConfig extends FarmChartRecord implements Serializable{
    @Column(name = "feed_config_id")
    private String feedConfigId;
    
    @JoinColumn(name = "feed", referencedColumnName = "id")
    @ManyToOne
    private FeedType feed;
    
    @Column(name = "feed_amount")
    private int feedAmount;
    
    @Column(name = "unit")
    @Enumerated(EnumType.STRING)
    private Units units;
    
    @JoinColumn(name = "ingredient", referencedColumnName = "id")
    @ManyToOne
    private Ingredient ingredient;
    
    @Column(name = "stage_type")
    @Enumerated(EnumType.STRING)
    private StageType stageType;
    
    @Column(name = "age_range")
    @Enumerated(EnumType.STRING)
    private AgeRange ageRange;

    public String getFeedConfigId() {
        return feedConfigId;
    }

    public void setFeedConfigId(String feedConfigId) {
        this.feedConfigId = feedConfigId;
    }

    public FeedType getFeed() {
        return feed;
    }

    public void setFeed(FeedType feed) {
        this.feed = feed;
    }

    public int getFeedAmount() {
        return feedAmount;
    }

    public void setFeedAmount(int feedAmount) {
        this.feedAmount = feedAmount;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

    public StageType getStageType() {
        return stageType;
    }

    public void setStageType(StageType stageType) {
        this.stageType = stageType;
    }
    
    public void genCode()
    {
        if(getFeedConfigId() != null)
        {
            setFeedConfigId(getFeedConfigId());
        }
        else
        {
            setFeedConfigId(SystemUtils.generateCode());
        }
        
    }
}
