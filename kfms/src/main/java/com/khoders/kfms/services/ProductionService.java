/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.services;

import com.khoders.kfms.entities.Bird;
import com.khoders.kfms.entities.BirdWeight;
import com.khoders.kfms.entities.EggCollection;
import com.khoders.kfms.entities.EggWeight;
import com.khoders.kfms.entities.Feed;
import com.khoders.kfms.entities.Inventory;
import com.khoders.kfms.entities.Mortality;
import com.khoders.kfms.entities.Production;
import com.khoders.kfms.entities.Treatment;
import com.khoders.kfms.entities.Vaccination;
import com.khoders.kfms.entities.settings.BirdType;
import com.khoders.kfms.entities.settings.Category;
import com.khoders.kfms.entities.settings.FeedType;
import com.khoders.kfms.entities.settings.Medication;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.Msg;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author khoders
 */
@Stateless
public class ProductionService {
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    
    
    public List<Bird> getBirdList()
    {
        String qryString = "SELECT e FROM Bird e WHERE e.farmAccount = ?1";
        try 
        {
            TypedQuery<Bird> typedQuery = crudApi.getEm().createQuery(qryString, Bird.class);
                            
            typedQuery.setParameter(1, appSession.getCurrentUser());
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<Inventory> getInventoryList()
    {
        String qryString = "SELECT e FROM Inventory e WHERE e.farmAccount = ?1";
        try 
        {
            TypedQuery<Inventory> typedQuery = crudApi.getEm().createQuery(qryString, Inventory.class);
                            
            typedQuery.setParameter(1, appSession.getCurrentUser());
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<Category> getCategoryList()
    {
        String qryString = "SELECT e FROM Category  e WHERE e.farmAccount=?1 ORDER BY e.categoryName ASC";
        try 
        {
            TypedQuery<Category> typedQuery = crudApi.getEm().createQuery(qryString, Category.class);
                            
            typedQuery.setParameter(1, appSession.getCurrentUser());
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<EggCollection> getEggList(Production production)
    {
       if(production != null)
       {
            String qryString = "SELECT e FROM EggCollection e WHERE e.farmAccount =?1 AND e.production=?2";
        try 
        {
            TypedQuery<EggCollection> typedQuery = crudApi.getEm().createQuery(qryString, EggCollection.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, production);
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
       }
       else
       {
          FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Oops! could not load records egg..."), null));  
       }
        return Collections.emptyList();
    }
    
    public List<EggWeight> getEggWeightList(Production production)
    {
       if(production != null)
       {
            String qryEggWeight = "SELECT e FROM EggWeight e WHERE e.farmAccount = ?1 AND e.production=?2";
        try 
        {
            TypedQuery<EggWeight> typedQuery = crudApi.getEm().createQuery(qryEggWeight, EggWeight.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, production);
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
       }
       else
       {
          FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Oops! could not load records..."), null));  
       }
        return Collections.emptyList();
    }
    
    public List<Feed> getFeedList(Production production)
    {
       if(production != null)
       {
            String qryString = "SELECT e FROM Feed e WHERE e.farmAccount = ?1 AND e.production=?2";
        try 
        {
            TypedQuery<Feed> typedQuery = crudApi.getEm().createQuery(qryString, Feed.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, production);
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
       }
       else
       {
          FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Oops! could not load records..."), null));  
       }
        return Collections.emptyList();
    }
    
    public List<Mortality> getMortalityList(Production production)
    {
       if(production != null)
       {
            String qryString = "SELECT e FROM Mortality e WHERE e.farmAccount = ?1 AND e.production=?2";
        try 
        {
            TypedQuery<Mortality> typedQuery = crudApi.getEm().createQuery(qryString, Mortality.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, production);
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
       }
       else
       {
          FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Oops! could not load records..."), null));  
       }
        return Collections.emptyList();
    }
    
    public List<Treatment> getTreatmentList(Production production)
    {
       if(production != null)
       {
            String qryString = "SELECT e FROM Treatment e WHERE e.farmAccount = ?1 AND e.production=?2";
        try 
        {
            TypedQuery<Treatment> typedQuery = crudApi.getEm().createQuery(qryString, Treatment.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, production);
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
       }
       else
       {
          FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Oops! could not load records..."), null));  
       }
        return Collections.emptyList();
    }
    
    
    public List<Vaccination> getVaccinationList(Production production)
    {
       if(production != null)
       {
            String qryString = "SELECT e FROM Vaccination e WHERE e.farmAccount = ?1 AND e.production=?2";
        try 
        {
            TypedQuery<Vaccination> typedQuery = crudApi.getEm().createQuery(qryString, Vaccination.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, production);
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
       }
       else
       {
          FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Oops! could not load records..."), null));  
       }
        return Collections.emptyList();
    }
    
    public List<BirdWeight> getbirdWeightlist(Production production)
    {
       if(production != null)
       {
            String qryString = "SELECT e FROM BirdWeight e WHERE e.farmAccount = ?1 AND e.production=?2";
        try 
        {
            TypedQuery<BirdWeight> typedQuery = crudApi.getEm().createQuery(qryString, BirdWeight.class)
                            .setParameter(1, appSession.getCurrentUser())
                            .setParameter(2, production);
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
       }
       else
       {
          FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Oops! could not load records..."), null));  
       }
        return Collections.emptyList();
    }
    
    public List<EggCollection> getEggCollectionList()
    {
            String qryString = "SELECT e FROM EggCollection e WHERE e.farmAccount =?1";
        try 
        {
            TypedQuery<EggCollection> typedQuery = crudApi.getEm().createQuery(qryString, EggCollection.class)
                            .setParameter(1, appSession.getCurrentUser());
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<FeedType> getFeedTypeList()
    {
        String qryString = "SELECT e FROM FeedType e WHERE e.farmAccount = ?1";
        try 
        {
            TypedQuery<FeedType> typedQuery = crudApi.getEm().createQuery(qryString, FeedType.class);
                            
            typedQuery.setParameter(1, appSession.getCurrentUser());
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<Medication> getMedicationList()
    {
        String qryString = "SELECT e FROM Medication e WHERE e.farmAccount = ?1";
        try 
        {
            TypedQuery<Medication> typedQuery = crudApi.getEm().createQuery(qryString, Medication.class);
                            
            typedQuery.setParameter(1, appSession.getCurrentUser());
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<BirdType> getBirdTypeList()
    {
        String qryString = "SELECT e FROM BirdType e WHERE e.farmAccount = ?1";
        try 
        {
            TypedQuery<BirdType> typedQuery = crudApi.getEm().createQuery(qryString, BirdType.class);
                            
            typedQuery.setParameter(1, appSession.getCurrentUser());
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<Production> getProductionList()
    {
        String qryString = "SELECT e FROM Production e WHERE e.farmAccount = ?1";
        try 
        {
            TypedQuery<Production> typedQuery = crudApi.getEm().createQuery(qryString, Production.class);
                            
            typedQuery.setParameter(1, appSession.getCurrentUser());
            
            return typedQuery.getResultList();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public Long getBirdCount()
    {
        String qryString = "SELECT COUNT(e.id) FROM Bird e WHERE e.farmAccount = ?1";
        try 
        {
            Query query = crudApi.getEm().createQuery(qryString);
                            
            query.setParameter(1, appSession.getCurrentUser());
            
            return (Long)query.getSingleResult();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return getBirdCount();
    }
    
    public Long getEggCollectionCount()
    {
        String qryString = "SELECT COUNT(e.id) FROM EggCollection e WHERE e.farmAccount = ?1";
        try 
        {
            Query query = crudApi.getEm().createQuery(qryString);
                            
            query.setParameter(1, appSession.getCurrentUser());
            
            return (Long)query.getSingleResult();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return getBirdCount();
    }
}
