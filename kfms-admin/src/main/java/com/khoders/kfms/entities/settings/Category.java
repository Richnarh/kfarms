/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.settings;

import com.khoders.kfms.entities.FarmAccountRecord;
import com.khoders.kfms.entities.enums.CategoryStatus;
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
@Table(name = "category")
public class Category extends FarmAccountRecord implements Serializable{
    @Column(name = "category_code")
    private String categoryCode;
    
    public static final String _categoryName = "categoryName";
    @Column(name = "category_name")
    private String categoryName;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CategoryStatus categoryStatus;
    
    @Column(name = "note")
    @Lob
    private String note;
    
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public CategoryStatus getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(CategoryStatus categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
    
    public void genCode()
    {
        if(getCategoryCode() != null)
        {
           setCategoryCode(getCategoryCode());
        }
        else
        {
            setCategoryCode(SystemUtils.generateCode());
        }
        
    }
    @Override
    public String toString() {
        return categoryName;
    }
    
    
}
