package com.khoders.kfms.entities.settings;

import com.khoders.kfms.entities.enums.FlockPurpose;
import com.khoders.resource.jpa.BaseModel;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 31st October, 2020 3:36pm
 * @author Khoders
 */
@Entity
@Table(name = "bird_type")
public class BirdType extends BaseModel implements Serializable{
    @Column(name = "bird_type")
    private String flockType;
    
    @JoinColumn(name = "bird_category")
    @ManyToOne
    private TypeOfBird typeOfBird;
    
    @Column(name = "breed")
    private String breed;
    
    @Column(name = "flock_purpose")
    @Enumerated(EnumType.STRING)
    private FlockPurpose flockPurpose;

    public TypeOfBird getTypeOfBird() {
        return typeOfBird;
    }

    public void setTypeOfBird(TypeOfBird typeOfBird) {
        this.typeOfBird = typeOfBird;
    }

    public String getFlockType() {
        return flockType;
    }

    public void setFlockType(String flockType) {
        this.flockType = flockType;
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
    
    
}
