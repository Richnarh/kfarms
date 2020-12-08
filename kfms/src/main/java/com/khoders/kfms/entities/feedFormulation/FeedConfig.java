/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.feedFormulation;

import com.khoders.kfms.entities.FarmAccountRecord;
import com.khoders.kfms.entities.enums.AgeRange;
import com.khoders.kfms.entities.enums.StageType;
import com.khoders.kfms.entities.enums.Units;
import com.khoders.kfms.entities.settings.FeedType;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "feed_config")
public class FeedConfig extends FarmAccountRecord implements Serializable{
    @Column(name = "feed_config_id")
    private String feedConfigId;
    
    @Column(name = "feed_size")
    private int feedSize;
    
    @JoinColumn(name = "feed", referencedColumnName = "id")
    @ManyToOne
    private FeedType feed;
    
    @Column(name = "stage_type")
    @Enumerated(EnumType.STRING)
    private StageType stageType;
    
    @Column(name = "age_range")
    @Enumerated(EnumType.STRING)
    private AgeRange ageRange;
    
    @Column(name = "note")
    @Lob
    private String note;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getFeedSize() {
        return feedSize;
    }

    public void setFeedSize(int feedSize) {
        this.feedSize = feedSize;
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

    @Override
    public String toString() {
        return  feed+"";
    }
    
    
}
