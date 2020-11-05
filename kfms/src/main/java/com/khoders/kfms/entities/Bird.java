/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

import com.khoders.kfms.entities.enums.BirdSource;
import com.khoders.kfms.entities.settings.BirdType;
import com.khoders.resource.utilities.DateUtil;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@Table(name = "bird")
public class Bird extends FarmAccountRecord implements Serializable{
    @Column(name = "bird_id")
    private String birdId;
    
    @Column(name = "received_date")
    private LocalDate receivedDate;
    
    @Column(name = "retirement_date")
    private LocalDate retirementDate; // expected date to sell bird or end of productivity
    
    @JoinColumn(name = "bird_type")
    @ManyToOne
    private BirdType birdType;
    
    @Column(name = "source")
    @Enumerated(EnumType.STRING)
    private BirdSource birdSource;
    
    @Column(name = "bird_location")
    private String birdLocation; // e.g house, coup
    
    @Column(name = "initial_stock")
    private int initialStock;
    
    @Column(name = "current_stock")
    private int currentStock;
    
    @Column(name = "age")
    private int age;
    
    @Column(name = "note")
    @Lob
    private String note;

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public LocalDate getRetirementDate() {
        return retirementDate;
    }

    public void setRetirementDate(LocalDate retirementDate) {
        this.retirementDate = retirementDate;
    }

    public BirdType getBirdType() {
        return birdType;
    }

    public void setBirdType(BirdType birdType) {
        this.birdType = birdType;
    }

    public BirdSource getBirdSource() {
        return birdSource;
    }

    public void setBirdSource(BirdSource birdSource) {
        this.birdSource = birdSource;
    }

    public String getBirdLocation() {
        return birdLocation;
    }

    public void setBirdLocation(String birdLocation) {
        this.birdLocation = birdLocation;
    }

    public int getInitialStock() {
        return initialStock;
    }

    public void setInitialStock(int initialStock) {
        this.initialStock = initialStock;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBirdId() {
        return birdId;
    }

    public void setBirdId(String birdId) {
        this.birdId = birdId;
    }
    
    public void genCode()
    {
        setBirdId(SystemUtils.generateCode());
    }

    @Override
    public String toString() {
        return DateUtil.parseLocalDateString(receivedDate, "dd/MM/yyyy") + " " +birdLocation +" "+ birdType;
    }
    
}
