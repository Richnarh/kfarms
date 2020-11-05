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
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "feed_type")
public class FeedType  extends FarmAccountRecord implements Serializable{
    @Column(name = "feed_id")
    private String feedId;
              
    @Column(name = "feed_name")
    private String feedName;
    
    @Column(name = "cost_per_kilogram")
    private double costPerKilogram;
    
    @Column(name = "brand")
    private double brand;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public double getCostPerKilogram() {
        return costPerKilogram;
    }

    public void setCostPerKilogram(double costPerKilogram) {
        this.costPerKilogram = costPerKilogram;
    }

    public double getBrand() {
        return brand;
    }

    public void setBrand(double brand) {
        this.brand = brand;
    }
    
    public void genCode()
    {
        setId(SystemUtils.generateCode());
    }
}
