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
import com.khoders.kfms.entities.settings.BirdType;
import com.khoders.kfms.entities.settings.Country;
import com.khoders.kfms.entities.settings.FeedType;
import com.khoders.kfms.entities.settings.Medication;
import com.khoders.kfms.jpa.AppSession;
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
    
    @PostConstruct
    public void init()
    {
        String qryBirdType = "SELECT e FROM BirdType e WHERE e.farmAccount = ?1";
        birdTypeList = crudApi.getEm().createQuery(qryBirdType, BirdType.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
    
        String qryBird = "SELECT e FROM Bird e WHERE e.farmAccount = ?1";
        birdList = crudApi.getEm().createQuery(qryBird, Bird.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
        String qryFeedType = "SELECT e FROM Bird e WHERE e.farmAccount = ?1";
        feedTypeList = crudApi.getEm().createQuery(qryFeedType, FeedType.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
        String qryMedication = "SELECT e FROM Medication e WHERE e.farmAccount = ?1";
        medicationList = crudApi.getEm().createQuery(qryMedication, Medication.class)
                .setParameter(1, appSession.getCurrentUser())
                .getResultList();
        
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
    
}
