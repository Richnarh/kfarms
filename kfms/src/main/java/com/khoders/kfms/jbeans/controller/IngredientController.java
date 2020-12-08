/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.settings.Ingredient;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.FeedFormulationService;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.CollectionList;
import com.khoders.resource.utilities.Msg;
import com.khoders.resource.utilities.SystemUtils;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "ingredientController")
@SessionScoped
public class IngredientController implements Serializable{
    @Inject CrudApi crudApi;
    @Inject AppSession appSession;
    @Inject FeedFormulationService feedFormulationService;
    
    private String optionText;
    
    private Ingredient ingredient = new Ingredient();
    private List<Ingredient> ingredientList = new LinkedList<>();
    
    @PostConstruct
    private void init()
    {
        clearIngredient();
        
        ingredientList = feedFormulationService.getIngredientList();
    }
    
   public void saveIngredient()
    {
        try 
        {
          ingredient.genCode();
          if(crudApi.save(ingredient) != null)
          {
              ingredientList = CollectionList.washList(ingredientList, ingredient);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
           clearIngredient();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
   
    public void editIngredient(Ingredient ingredient)
    {
        optionText = "Update";
       this.ingredient=ingredient;
    }
    
    public void deleteIngredient(Ingredient ingredient)
    {
        try 
        {
          if(crudApi.delete(ingredient))
          {
              ingredientList.remove(ingredient);
              
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null)); 
          }
          else
          {
              FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
          }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void clearIngredient() {
        ingredient = new Ingredient();
        ingredient.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save Changes";
        SystemUtils.resetJsfUI();
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
    
    
}
