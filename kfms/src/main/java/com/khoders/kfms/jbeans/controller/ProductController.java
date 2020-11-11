/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.Product;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.CollectionList;
import com.khoders.resource.utilities.FormView;
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
@Named(value = "productController")
@SessionScoped
public class ProductController implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject AppSession appSession;
    
    private Product product;
    private List<Product> productList = new LinkedList<>();
    
    private FormView formView = FormView.listForm();
    
    private String optionText;
    
    @PostConstruct
    private void init()
    {
//        String qrString = "SELECT e FROM Product e WHERE e.account=?1 ORDER BY e.productName ASC";
        String qrString = "SELECT e FROM Product e";
        productList = crudApi.getEm().createQuery(qrString, Product.class)
//                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    }
    
    public void initProduct()
    {
        product = new Product();
        formView.restToCreateView();
        optionText = "Save";
        clearProduct();
    }
    
    public void clearPage()
    {
        product = null;
        formView.restToListView();
    }
    
    public void saveProduct()
    {
        try 
        {
            product.genCode();
            if(crudApi.save(product) != null)
            {
                productList = CollectionList.washList(productList, product);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
            }
            clearProduct();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void editProduct(Product product)
    {
        this.product = product;
        optionText = "Update";
        formView.restToCreateView();
    }
    
    public void deleteProduct(Product product)
    {
        try 
        {
            if(crudApi.delete(product))
            {
                productList.remove(product);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.SUCCESS_MESSAGE, null));
            }
            else
            {
              FacesContext.getCurrentInstance().addMessage(null,
                      new FacesMessage(FacesMessage.SEVERITY_ERROR, Msg.FAILED_MESSAGE, null));
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void clearProduct()
    {
        product = new Product();
        product.setFarmAccount(appSession.getCurrentUser());
        optionText = "Save";
        SystemUtils.resetJsfUI();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public FormView getFormView() {
        return formView;
    }

    public void setFormView(FormView formView) {
        this.formView = formView;
    }

    public String getOptionText() {
        return optionText;
    }
    
    
}
