/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.entities.enums;

import com.khoders.resource.utilities.MsgResolver;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author khoders
 */
public enum AccountType implements MsgResolver{
    CASH("CASH", "Cash", AccountCategory.ASSETS),
    CURRENT_ASSET("CURRENT_ASSET", "Current Asset", AccountCategory.ASSETS),
    RECEIVEABLE("RECEIVEABLE", "Receivable", AccountCategory.ASSETS),
    INVENTORY("INVENTORY", "Inventory", AccountCategory.ASSETS),
    FIXED_ASSET("FIXED_ASSET", "Fixed Asset", AccountCategory.ASSETS),
    OTHER_ASSET("OTHER_ASSET", "Other Asset", AccountCategory.ASSETS),
    
    PAYABLE("PAYABLE", "Payable", AccountCategory.LIABILITY),
    SALARY("SALARY", "Salary", AccountCategory.LIABILITY),
    WAGES("WAGES", "Wages", AccountCategory.LIABILITY),
    BILLS("BILLS", "Bills ", AccountCategory.LIABILITY),
    DEBT("DEBT", "Debt", AccountCategory.LIABILITY),
    LOAN("LOAN", "Loan", AccountCategory.LIABILITY),
    TAX("TAX", "Tax", AccountCategory.LIABILITY),
    BANK_ACCOUNT_OVERDRAFT("BANK_ACCOUNT_OVERDRAFT", "Bank Account overdraft", AccountCategory.LIABILITY),
    ACCURED_EXPENSE("ACCURED_EXPENSE", "Accrued expenses", AccountCategory.LIABILITY),
    OTHER_LIABILITIES("OTHER_LIABILITIES", "Other Liabilities", AccountCategory.LIABILITY),
    
    INCOME("INCOME", "Income", AccountCategory.REVENUE),
    OTHER_INCOME("INCOME","Other Income", AccountCategory.REVENUE),
    
    EQUITY("EQUITY","Equity", AccountCategory.EQUITY),
    
    EXPENSE("EXPENSE","Expense", AccountCategory.EXPENSE),
    PURCHASES("PURCHASES","Purchases", AccountCategory.EXPENSE),
    RENT("RENT","Rent", AccountCategory.EXPENSE),
    UTILITIES("UTILITIES", "Utilities ", AccountCategory.EXPENSE),
    REPAIR_AND_MAINTENANCE("REPAIR_AND_MAINTENANCE","Repairs and Maintenance ", AccountCategory.EXPENSE),
    OTHER_EXPENSE("OTHER EXPENSE","Other Expense", AccountCategory.EXPENSE),
    COST_OF_GOODS_SOLD("COST_OF_GOODS_SOLD","Cost of Goods Sold", AccountCategory.EXPENSE);
    
    private final String code;
    private final String label;
    private final AccountCategory accountCategory;

    private AccountType(String code, String label, AccountCategory accountCategory)
    {
        this.code = code;
        this.label = label;
        this.accountCategory = accountCategory;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
    
}
