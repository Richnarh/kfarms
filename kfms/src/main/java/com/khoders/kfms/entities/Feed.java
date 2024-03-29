/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

import com.khoders.kfms.entities.feedFormulation.FeedType;
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
@Table(name = "feed")
public class Feed extends ProductionRecord implements Serializable{
    @JoinColumn(name = "feed_type", referencedColumnName = "id")
    @ManyToOne
    private FeedType feedType;
    
    @Column(name = "feed_amount")
    private double feedAmount;
    
    public FeedType getFeedType() {
        return feedType;
    }

    public void setFeedType(FeedType feedType) {
        this.feedType = feedType;
    }

    public double getFeedAmount() {
        return feedAmount;
    }

    public void setFeedAmount(double feedAmount) {
        this.feedAmount = feedAmount;
    }

    
}
