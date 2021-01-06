package com.khoders.kfms.entities;

import com.khoders.kfms.entities.enums.EggColor;
import com.khoders.kfms.entities.enums.EggSize;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 *
 * @author 
 */
@Entity
@Table(name = "egg_collection")
public class EggCollection extends ProductionRecord implements Serializable{
    @Column(name = "egg_color")
    @Enumerated(EnumType.STRING)
    private EggColor eggColor;
    
    @Column(name = "egg_size")
    @Enumerated(EnumType.STRING)
    private EggSize eggSize;
    
    @Column(name = "healthy_qty")
    private int healthyQty;
   
    @Column(name = "damanged_qty")
    private int damagedQty;
    
    public EggColor getEggColor() {
        return eggColor;
    }

    public void setEggColor(EggColor eggColor) {
        this.eggColor = eggColor;
    }

    public EggSize getEggSize() {
        return eggSize;
    }

    public void setEggSize(EggSize eggSize) {
        this.eggSize = eggSize;
    }

    public int getHealthyQty() {
        return healthyQty;
    }

    public void setHealthyQty(int healthyQty) {
        this.healthyQty = healthyQty;
    }

    public int getDamagedQty() {
        return damagedQty;
    }

    public void setDamagedQty(int damagedQty) {
        this.damagedQty = damagedQty;
    }
    
}
