/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.settings;

import com.khoders.resource.jpa.BaseModel;
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
public class FeedType  extends BaseModel implements Serializable{
    @Column(name = "feed_name")
    private String feedName;
    
    @Column(name = "cost_per_kilogram")
    private double costPerKilogram;
    
    @Column(name = "brand")
    private double brand;

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
    
    
}
