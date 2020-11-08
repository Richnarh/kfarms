/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities;

import com.khoders.kfms.entities.settings.Medication;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "vaccination")
public class Vaccination extends FarmAccountRecord implements Serializable{
    @JoinColumn(name = "medication", referencedColumnName = "id")
    @ManyToOne
    private Medication medication;
    
    @Column(name = "dosage")
    private String dosage;
    
    @Column(name = "note")
    @Lob
    private String note;
    
    @JoinColumn(name = "production", referencedColumnName = "id")
    @ManyToOne
    private Production production;

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    
    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
