/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.shared;

import com.khoders.kfms.entities.Bird;
import com.khoders.kfms.entities.Customer;
import com.khoders.kfms.entities.Product;
import com.khoders.kfms.entities.Supplier;
import com.khoders.kfms.entities.account.ChartOfAccount;
import com.khoders.kfms.entities.account.Invoice;
import com.khoders.kfms.entities.account.Purchase;
import com.khoders.kfms.entities.feedFormulation.FeedConfig;
import com.khoders.kfms.entities.settings.BirdType;
import com.khoders.kfms.entities.settings.Category;
import com.khoders.kfms.entities.settings.Country;
import com.khoders.kfms.entities.feedFormulation.FeedType;
import com.khoders.kfms.entities.feedFormulation.Ingredient;
import com.khoders.kfms.entities.settings.Medication;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.kfms.services.FeedFormulationService;
import com.khoders.kfms.services.ProductionService;
import com.khoders.resource.jpa.CrudApi;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "commonClass")
@SessionScoped
public class CommonClass implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    @Inject private ProductionService productionService;
    @Inject FeedFormulationService feedFormulationService;
    
    
    private List<BirdType> birdTypeList = new LinkedList<>();
    private List<Bird> birdList = new LinkedList<>();
    private List<FeedType> feedTypeList = new LinkedList<>();
    private List<Medication> medicationList = new LinkedList<>();
    private List<Customer> customerList = new LinkedList<>();
    private List<ChartOfAccount> accountList = new LinkedList<>();
    private List<Supplier> supplierList = new LinkedList<>();
    private List<Product> productList = new LinkedList<>();
    private List<Country> countryList = new LinkedList<>();
    private List<Invoice> invoiceList = new LinkedList<>();
    private List<Purchase> purchaseList = new LinkedList<>();
    private List<Ingredient> ingredientList = new LinkedList<>();
    private List<FeedConfig> feedConfigList = new LinkedList<>();
    private List<Category> categoryList = new LinkedList<>();
    
    @PostConstruct
    public void init()
    {
        birdTypeList = productionService.getBirdTypeList();
    
        birdList = productionService.getBirdList();
        
        feedTypeList = productionService.getFeedTypeList();
        
        medicationList = productionService.getMedicationList();
        ingredientList = feedFormulationService.getIngredientList();
        feedConfigList = feedFormulationService.getFeedConfigList();
        categoryList = productionService.getCategoryList();
        
        String qryCustomer = "SELECT e FROM Customer e WHERE e.farmAccount = ?1";
        customerList = crudApi.getEm().createQuery(qryCustomer, Customer.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
        String qrySupplier = "SELECT e FROM Supplier e WHERE e.farmAccount = ?1";
        supplierList = crudApi.getEm().createQuery(qrySupplier, Supplier.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
        String qryChartOfAccount = "SELECT e FROM ChartOfAccount e WHERE e.farmAccount = ?1";
        accountList = crudApi.getEm().createQuery(qryChartOfAccount, ChartOfAccount.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
        String qryProduct = "SELECT e FROM Product e WHERE e.farmAccount = ?1";
        productList = crudApi.getEm().createQuery(qryProduct, Product.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
        String qryCountry = "SELECT e FROM Country e ";
        countryList = crudApi.getEm().createQuery(qryCountry, Country.class)
                .getResultList();
        
         String qryInvoice = "SELECT e FROM Invoice e WHERE e.farmAccount=?1";
        invoiceList = crudApi.getEm().createQuery(qryInvoice, Invoice.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
         String qryPurchase = "SELECT e FROM Purchase e WHERE e.farmAccount=?1";
        purchaseList = crudApi.getEm().createQuery(qryPurchase, Purchase.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
    }

    public List<BirdType> getBirdTypeList() {
        return birdTypeList;
    }

    public List<Bird> getBirdList() {
        return birdList;
    }

    public List<FeedType> getFeedTypeList() {
        return feedTypeList;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<ChartOfAccount> getAccountList() {
        return accountList;
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public List<FeedConfig> getFeedConfigList() {
        return feedConfigList;
    }

    public List<Category> getCategoryList()
    {
        return categoryList;
    }

    public List<Purchase> getPurchaseList()
    {
        return purchaseList;
    }
    
}
