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
@Table(name = "type_of_bird")
public class TypeOfBird extends BaseModel implements Serializable{
    @Column(name = "bird_type_name")
    private String birdTypeName;

    public String getBirdTypeName() {
        return birdTypeName;
    }

    public void setBirdTypeName(String birdTypeName) {
        this.birdTypeName = birdTypeName;
    }
    
    
}
