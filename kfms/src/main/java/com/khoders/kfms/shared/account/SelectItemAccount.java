/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.shared.account;

import com.khoders.kfms.entities.enums.AccountCategory;
import com.khoders.kfms.shared.account.AccountType;
import com.khoders.resource.jpa.CrudApi;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "selectItemAccount")
@SessionScoped
public class SelectItemAccount implements Serializable{
    private static final long serialVersionUID = 1L;
   
    private Product selectedProduct;
    private List<Category> categories;
    private List<SelectItem> categorySelectItems;
   
   @PostConstruct
    public void init() {
        categories = loadCategories();
        categorySelectItems = categories.stream().map(category -> {
            SelectItemGroup group = new SelectItemGroup(category.getName());
            group.setSelectItems(category.getProducts().stream()
                .map(product -> new SelectItem(product, product.getCategoryName()))
                .toArray(SelectItem[]::new));
            return group;
        }).collect(toList());
    }
   
   public List<Category> loadCategories()
   {
       List<Category> categoryList = new LinkedList<>();
       
       categoryList.add(new Category(AccountCategory.ASSETS.getLabel(),
               new Product(AccountType.CASH.getLabel()), 
               new Product(AccountType.CURRENT_ASSET.getLabel()),
               new Product(AccountType.RECEIVEABLE.getLabel()),
               new Product(AccountType.FIXED_ASSET.getLabel()),
               new Product(AccountType.OTHER_ASSET.getLabel())
       ));
       
       categoryList.add(new Category(AccountCategory.LIABILITY.getLabel(), 
               new Product(AccountType.PAYABLE.getLabel()), 
               new Product(AccountType.SALARY.getLabel()),
               new Product(AccountType.WAGES.getLabel()),
               new Product(AccountType.BILLS.getLabel()),
               new Product(AccountType.DEBT.getLabel()),
               new Product(AccountType.LOAN.getLabel()),
               new Product(AccountType.TAX.getLabel()),
               new Product(AccountType.BANK_ACCOUNT_OVERDRAFT.getLabel()),
               new Product(AccountType.ACCURED_EXPENSE.getLabel()),
               new Product(AccountType.OTHER_LIABILITIES.getLabel())
       ));
       
       categoryList.add(new Category(AccountCategory.EQUITY.getLabel(), 
               new Product(AccountType.EQUITY.getLabel())
       ));
       
       categoryList.add(new Category(AccountCategory.REVENUE.getLabel(), 
               new Product(AccountType.INCOME.getLabel()),
               new Product(AccountType.OTHER_INCOME.getLabel())
       ));
       
       categoryList.add(new Category(AccountCategory.EXPENSE.getLabel(), 
               new Product(AccountType.EXPENSE.getLabel()),
               new Product(AccountType.PURCHASES.getLabel()),
               new Product(AccountType.RENT.getLabel()),
               new Product(AccountType.UTILITIES.getLabel()),
               new Product(AccountType.REPAIR_AND_MAINTENANCE.getLabel()),
               new Product(AccountType.OTHER_EXPENSE.getLabel()),
               new Product(AccountType.COST_OF_GOODS_SOLD.getLabel())
       ));
       
       return categoryList;
   }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Category> getCategories() {
        return categories;
    }
    
    public List<SelectItem> getCategorySelectItems() {
        return categorySelectItems;
    }
}
