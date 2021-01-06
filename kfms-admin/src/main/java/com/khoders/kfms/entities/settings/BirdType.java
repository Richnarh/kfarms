package com.khoders.kfms.entities.settings;

import com.khoders.kfms.entities.FarmAccountRecord;
import com.khoders.kfms.entities.enums.FlockPurpose;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * 31st October, 2020 3:36pm
 * @author Khoders
 */
@Entity
@Table(name = "bird_type")
public class BirdType extends FarmAccountRecord implements Serializable{
    @Column(name = "flock_id")
    private String flockId;
    
    @Column(name = "bird_type_name")
    private String flockTypeName; // e.g chicken, turkey, duck

    @Column(name = "breed")
    private String breed;
    
    @Column(name = "flock_purpose")
    @Enumerated(EnumType.STRING)
    private FlockPurpose flockPurpose;

    public String getFlockId() {
        return flockId;
    }

    public void setFlockId(String flockId) {
        this.flockId = flockId;
    }

    public String getFlockTypeName() {
        return flockTypeName;
    }

    public void setFlockTypeName(String flockTypeName) {
        this.flockTypeName = flockTypeName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public FlockPurpose getFlockPurpose() {
        return flockPurpose;
    }

    public void setFlockPurpose(FlockPurpose flockPurpose) {
        this.flockPurpose = flockPurpose;
    }
    
    public void genCode()
    {
        if(getFlockId() != null)
        {
           setFlockId(getFlockId());
        }
        else
        {
            setFlockId(SystemUtils.generateCode());
        }
        
    }

    @Override
    public String toString() {
        return flockTypeName + " ("+ breed +")";
    }
    
    
}
