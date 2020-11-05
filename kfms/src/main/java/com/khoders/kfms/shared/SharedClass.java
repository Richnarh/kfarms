/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.shared;

import com.khoders.kfms.entities.enums.BirdCategory;
import com.khoders.kfms.entities.enums.BirdSource;
import com.khoders.kfms.entities.enums.Category;
import com.khoders.kfms.entities.enums.CullingMortality;
import com.khoders.kfms.entities.enums.EggColor;
import com.khoders.kfms.entities.enums.EggSize;
import com.khoders.kfms.entities.enums.FlockPurpose;
import com.khoders.kfms.entities.enums.MedicationType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "sharedClass")
@SessionScoped
public class SharedClass implements Serializable
{
    public List<BirdCategory> getBirdCategoryList()
    {
        return Arrays.asList(BirdCategory.values());
    }
    
    public List<Category> getCategoryList()
    {
        return Arrays.asList(Category.values());
    }
    
    public List<CullingMortality> getCullingMortalityList()
    {
        return Arrays.asList(CullingMortality.values());
    }
    
    public List<EggColor> getEggColorList()
    {
        return Arrays.asList(EggColor.values());
    }
    public List<EggSize> getEggSizeList()
    {
        return Arrays.asList(EggSize.values());
    }
    
    public List<FlockPurpose> getFlockPurposeList()
    {
        return Arrays.asList(FlockPurpose.values());
    }
    
    public List<MedicationType> getMedicationTypeList()
    {
        return Arrays.asList(MedicationType.values());
    }
    
    public List<BirdSource> getBirdSourceList()
    {
        return Arrays.asList(BirdSource.values());
    }
    
}
