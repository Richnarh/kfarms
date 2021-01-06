/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.services;

import com.khoders.kfms.entities.FarmAccount;
import com.khoders.resource.jpa.CrudApi;
import static com.khoders.resource.utilities.SecurityUtil.hashText;
import com.khoders.kfms.jbeans.Credential;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

/**
 *
 * @author Khoders
 */
@Stateless
public class FarmAccountService
{
    @Inject private CrudApi crudApi;
    
    public FarmAccount login(Credential credential)
    {
        
        try
        {
            String qryString = "SELECT e FROM FarmAccount e WHERE e.businessEmail=?1 AND e.password=?2";
            TypedQuery<FarmAccount> typedQuery = crudApi.getEm().createQuery(qryString, FarmAccount.class)
                    .setParameter(1, credential.getEmail())
                    .setParameter(2, hashText(credential.getPassword()));
            
                 if(typedQuery.getSingleResult() != null)
                 {
                    return typedQuery.getSingleResult();
                 }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean isTaken(String email)
    {
        String qryString = "SELECT e FROM FarmAccount e WHERE e.businessEmail=?1";
        try {
            FarmAccount account = crudApi.getEm().createQuery(qryString, FarmAccount.class)
                    .setParameter(1, email)
                    .getSingleResult();
            
            return account != null;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return false;
    }
}
