/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.shared.account;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "accountClass")
@SessionScoped
public class AccountClass implements Serializable{

    private final List<SelectItem> accountTypeList = new LinkedList<>();
    
    @PostConstruct
    private void init()
    {
        SelectItemGroup assetGroup = new SelectItemGroup(AccountCategory.ASSETS.getLabel());
        assetGroup.setSelectItems(new SelectItem[]{
            new SelectItem(AccountType.CASH),
            new SelectItem(AccountType.CURRENT_ASSET),
            new SelectItem(AccountType.RECEIVEABLE),
            new SelectItem(AccountType.INVENTORY),
            new SelectItem(AccountType.FIXED_ASSET),
            new SelectItem(AccountType.OTHER_ASSET),
        });
        
        SelectItemGroup liabilityGroup = new SelectItemGroup(AccountCategory.LIABILITY.getLabel());
        liabilityGroup.setSelectItems(new SelectItem[]{
            new SelectItem(AccountType.PAYABLE),
            new SelectItem(AccountType.SALARY),
            new SelectItem(AccountType.WAGES),
            new SelectItem(AccountType.BILLS),
            new SelectItem(AccountType.DEBT),
            new SelectItem(AccountType.LOAN),
            new SelectItem(AccountType.TAX),
            new SelectItem(AccountType.BANK_ACCOUNT_OVERDRAFT),
            new SelectItem(AccountType.ACCURED_EXPENSE),
            new SelectItem(AccountType.OTHER_LIABILITIES)
        });
        SelectItemGroup expenseGroup = new SelectItemGroup(AccountCategory.EXPENSE.getLabel());
        expenseGroup.setSelectItems(new SelectItem[]{
            new SelectItem(AccountType.EXPENSE),
            new SelectItem(AccountType.PURCHASES),
            new SelectItem(AccountType.RENT),
            new SelectItem(AccountType.UTILITIES),
            new SelectItem(AccountType.REPAIR_AND_MAINTENANCE),
            new SelectItem(AccountType.COST_OF_GOODS_SOLD),
            new SelectItem(AccountType.OTHER_EXPENSE),
        });
        SelectItemGroup revenueGroup = new SelectItemGroup(AccountCategory.REVENUE.getLabel());
        revenueGroup.setSelectItems(new SelectItem[]{
            new SelectItem(AccountType.INCOME),
            new SelectItem(AccountType.OTHER_INCOME)
        });
        SelectItemGroup equityGroup = new SelectItemGroup(AccountCategory.EQUITY.getLabel());
        equityGroup.setSelectItems(new SelectItem[]{
            new SelectItem(AccountType.EQUITY)
        });

        accountTypeList.add(assetGroup);
        accountTypeList.add(liabilityGroup);
        accountTypeList.add(expenseGroup);
        accountTypeList.add(revenueGroup);
        accountTypeList.add(equityGroup);
    }
    
    

    public List<SelectItem> getAccountTypeList() {
        return accountTypeList;
    }

}
