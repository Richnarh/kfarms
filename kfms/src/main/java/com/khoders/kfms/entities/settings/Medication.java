/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.settings;

import com.khoders.kfms.entities.FarmAccountRecord;
import com.khoders.kfms.entities.enums.MedicationType;
import com.khoders.resource.jpa.BaseModel;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author khoders
 */
@Entity
@Table(name = "medication")
public class Medication extends FarmAccountRecord implements Serializable{
    @Column(name = "medicaion_id")
    private String medicationId; 
    
    @Column(name = "medicaion_name")
    private String medicationName; //vaccine, disinfectant
    
    @Column(name = "medication_type")
    @Enumerated(EnumType.STRING)
    private MedicationType medicationType; 
    
    @Column(name = "note")
    @Lob
    private String note;

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public MedicationType getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(MedicationType medicationType) {
        this.medicationType = medicationType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public void genCode()
    {
        if(getMedicationId() != null)
        {
           setMedicationId(getMedicationId());
        }
        else
        {
            setMedicationId(SystemUtils.generateCode());
        }
        
    }

    @Override
    public String toString() {
        return medicationName;
    }
    
    
}
