/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.shared;

import com.khoders.kfms.entities.enums.BirdCategory;
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
@Named(value = "sharedModule")
@SessionScoped
public class SharedModule implements Serializable
{
    private List<BirdCategory> getBirdCategoryList()
    {
        return Arrays.asList(BirdCategory.values());
    }
    
    private List<Category> getCategoryList()
    {
        return Arrays.asList(Category.values());
    }
    
    private List<CullingMortality> getCullingMortalityList()
    {
        return Arrays.asList(CullingMortality.values());
    }
    
    private List<EggColor> getEggColorList()
    {
        return Arrays.asList(EggColor.values());
    }
    private List<EggSize> getEggSizeList()
    {
        return Arrays.asList(EggSize.values());
    }
    
    private List<FlockPurpose> getFlockPurposeList()
    {
        return Arrays.asList(FlockPurpose.values());
    }
    
    private List<MedicationType> getMedicationTypeList()
    {
        return Arrays.asList(MedicationType.values());
    }
    
}
