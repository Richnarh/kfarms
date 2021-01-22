/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.shared;

import com.khoders.kfms.entities.enums.AgeRange;
import com.khoders.kfms.entities.enums.BirdCategory;
import com.khoders.kfms.entities.enums.BirdSource;
import com.khoders.kfms.entities.enums.ClientType;
import com.khoders.kfms.entities.enums.ConfigType;
import com.khoders.kfms.entities.enums.CullingMortality;
import com.khoders.kfms.entities.enums.EggColor;
import com.khoders.kfms.entities.enums.EggSize;
import com.khoders.kfms.entities.enums.FlockPurpose;
import com.khoders.kfms.entities.enums.MedicationType;
import com.khoders.kfms.entities.enums.PaymentStatus;
import com.khoders.kfms.entities.enums.StageType;
import com.khoders.kfms.entities.settings.Units;
import com.khoders.resource.enums.PaymentMethod;
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
    
    public List<PaymentMethod> getPaymentMethodList()
    {
        return Arrays.asList(PaymentMethod.values());
    }
    
    public List<PaymentStatus> getPaymentStatusList()
    {
        return Arrays.asList(PaymentStatus.values());
    }
    
    public List<AgeRange> getAgeRangeList()
    {
        return Arrays.asList(AgeRange.values());
    }
    
    public List<StageType> getStageTypeList()
    {
        return Arrays.asList(StageType.values());
    }
    
    public List<Units> getUnitsList()
    {
        return Arrays.asList(Units.values());
    }
    
    public List<ConfigType> getConfigTypeList()
    {
        return Arrays.asList(ConfigType.values());
    }
    
    public List<ClientType> getClientTypeList()
    {
        return Arrays.asList(ClientType.values());
    }
    
}
