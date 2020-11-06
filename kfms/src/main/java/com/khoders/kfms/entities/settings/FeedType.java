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
    
    @Column(name = "cost_per_unit")
    private double costPerUnit;
    
    @Column(name = "brand")
    private String brand;

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

    public double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    
    public void genCode()
    {
        setId(SystemUtils.generateCode());
    }

    @Override
    public String toString() {
        return feedName;
    }
    
    
}
