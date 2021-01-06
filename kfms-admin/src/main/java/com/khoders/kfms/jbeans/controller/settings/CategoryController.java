/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller.settings;

import com.khoders.kfms.entities.settings.Category;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.ProductionService;
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
@Named(value = "categoryController")
@SessionScoped
public class CategoryController implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    @Inject private  ProductionService productionService;
    
    private Category category = new Category();
    private List<Category> categoryList = new LinkedList<>();
    private String optionText;
    
    @PostConstruct
    private void init()
    {
        clearCategory();
        
        categoryList = productionService.getCategoryList();
    }
    
    public void saveCategory()
    {
        try 
        {
            category.genCode();
            if(crudApi.save(category) != null)
            {
                categoryList = CollectionList.washList(categoryList, category);
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Category saved!"), null));
            }
            else
            {
                 FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
            }
            clearCategory();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void editCategory(Category category)
    {
        this.category = category;
        optionText = "Update";
    }
    
    public void deleteCategory(Category category)
    {
        try 
        {
            if(crudApi.delete(category))
            {
                categoryList.remove(category);
                 FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Category deleted success!"), null));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.setMsg("Category deleted failed!"), null));
                
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public void clearCategory() 
    {
        category = new Category();
        category.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save";
        SystemUtils.resetJsfUI();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String getOptionText() {
        return optionText;
    }
    
    
}
