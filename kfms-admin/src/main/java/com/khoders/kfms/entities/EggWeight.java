/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "egg_weight")
public class EggWeight  extends ProductionRecord implements Serializable{
    @Column(name = "number_weighed")
    private int numberWeighed;
    
    @Column(name = "average_weight")
    private int averageWeight;
    
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

}
