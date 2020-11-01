/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

import com.khoders.kfms.entities.enums.CullingMortality;
import com.khoders.resource.jpa.BaseModel;
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
@Table(name = "mortality")
public class Mortality extends BaseModel implements Serializable{
    @Column(name = "culling_mortality")
    @Enumerated(EnumType.STRING)
    private CullingMortality cullingMortality;
    
    @Column(name = "number")
    private int number;
    
    @Column(name = "reason")
    @Lob
    private String reason;
    
    @JoinColumn(name = "production", referencedColumnName = "id")
    @ManyToOne
    private Production production;

    public CullingMortality getCullingMortality() {
        return cullingMortality;
    }

    public void setCullingMortality(CullingMortality cullingMortality) {
        this.cullingMortality = cullingMortality;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }
    
    
}
