/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.services;

import com.khoders.kfms.entities.enums.ConfigType;
import com.khoders.kfms.entities.feedFormulation.FeedConfig;
import com.khoders.kfms.entities.feedFormulation.FeedConfigItem;
import com.khoders.kfms.entities.feedFormulation.Ingredient;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.resource.jpa.CrudApi;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

/**
 *
 * @author khoders
 */
@Stateless
public class FeedFormulationService {
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    
    public List<Ingredient> getIngredientList()
    {
        String qryString = "SELECT e FROM Ingredient e WHERE e.farmAccount = ?1";
        try 
        {
            TypedQuery<Ingredient> typedQuery = crudApi.getEm().createQuery(qryString, Ingredient.class);
                            
            typedQuery.setParameter(1, appSession.getCurrentUser());
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<FeedConfigItem> getFeedConfigChart(FeedConfig feedConfig, ConfigType configType)
    {
        String qryString =null;
        TypedQuery<FeedConfigItem> typedQuery;
        
        try 
        {
            if(ConfigType.FARMER.equals(configType))
            {
                qryString = "SELECT e FROM FeedConfigItem e WHERE e.farmAccount=?1 AND e.feedConfig=?2";
                typedQuery = crudApi.getEm().createQuery(qryString, FeedConfigItem.class);

                typedQuery.setParameter(1, appSession.getCurrentUser());
                typedQuery.setParameter(2, feedConfig);
            }
            else
            {
                qryString = "SELECT e FROM FeedConfigItem e WHERE e.feedConfig=?1";
                typedQuery = crudApi.getEm().createQuery(qryString, FeedConfigItem.class);

                typedQuery.setParameter(1, feedConfig);
            }
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    
    public List<FeedConfigItem> getFeedConfigList(FeedConfig feedConfig)
    {
        try 
        {
            String qryString = "SELECT e FROM FeedConfigItem e WHERE e.farmAccount=?1 AND e.feedConfig=?2";
            TypedQuery<FeedConfigItem> typedQuery = crudApi.getEm().createQuery(qryString, FeedConfigItem.class);

            typedQuery.setParameter(1, appSession.getCurrentUser());
            typedQuery.setParameter(2, feedConfig);

            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<FeedConfig> getFeedConfiguration(ConfigType configType)
    {
        String qryString = null;
        
        TypedQuery<FeedConfig> typedQuery;
        
      try 
        {
            
        if(ConfigType.FARMER.equals(configType))
        {
            qryString = "SELECT e FROM FeedConfig e WHERE e.farmAccount=?1 AND e.configType=?2";
             typedQuery = crudApi.getEm().createQuery(qryString, FeedConfig.class);
                            
            typedQuery.setParameter(1, appSession.getCurrentUser());
            typedQuery.setParameter(2, configType);
        }
        else
        {
            qryString = "SELECT e FROM FeedConfig e WHERE e.configType=?1";
             typedQuery = crudApi.getEm().createQuery(qryString, FeedConfig.class);
                            
            typedQuery.setParameter(1, configType);
        }
        
        
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
  
    public List<FeedConfig> getFeedConfigList()
    {
        String qryString = "SELECT e FROM FeedConfig e WHERE e.farmAccount=?1 ";
        try 
        {
            TypedQuery<FeedConfig> typedQuery = crudApi.getEm().createQuery(qryString, FeedConfig.class);
                            
            typedQuery.setParameter(1, appSession.getCurrentUser());
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
  
}