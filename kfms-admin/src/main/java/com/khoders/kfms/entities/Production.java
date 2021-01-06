/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "production")
public class Production extends FarmAccountRecord implements Serializable{
    @Column(name = "production_code")
    private String productionCode;
    
    @Column(name = "production_date")
    private LocalDate productionDate;
    
    @JoinColumn(name = "bird")
    @ManyToOne
    private Bird bird;
    
    @Column(name = "bird_batch_no")
    private String birdBatchNumber;

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }
    
    public String getBirdBatchNumber() {
        return birdBatchNumber;
    }

    public void setBirdBatchNumber(String birdBatchNumber) {
        this.birdBatchNumber = birdBatchNumber;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public void genCode()
    {
        if(getProductionCode() != null)
        {
            setProductionCode(getProductionCode());
        }
        else
        {
            setProductionCode(SystemUtils.generateCode());
        }
    }
    
    public void flockBatch()
    {
        setBirdBatchNumber(productionDate.toString() +"/"+ SystemUtils.generateShortCode());
    }
    
    public void batchNo()
    {
        if(getBirdBatchNumber() != null)
        {
           setBirdBatchNumber(getBirdBatchNumber());
        }
        else
        {
           setBirdBatchNumber(SystemUtils.generateShortCode());
        }
       
    }
    
}
