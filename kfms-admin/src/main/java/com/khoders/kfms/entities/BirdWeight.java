/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

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
@Table(name = "bird_weight")
public class BirdWeight  extends FarmAccountRecord implements Serializable{
    @Column(name = "number_weighed")
    private int numberWeighed;
    
    @Column(name = "average_weight")
    private int averageWeight;
    
    @JoinColumn(name = "production", referencedColumnName = "id")
    @ManyToOne
    private Production production;
        
    public int getNumberWeighed() {
        return numberWeighed;
    }

    public void setNumberWeighed(int numberWeighed) {
        this.numberWeighed = numberWeighed;
    }

    public int getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(int averageWeight) {
        this.averageWeight = averageWeight;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }
    
}
