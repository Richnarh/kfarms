/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.shared.account;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author khoders
 */
public class Category {
    private String name;
    private List<Product> products;
    
    public Category(String name, Product... products)
    {
        this.name=name;
        this.products=Arrays.asList(products);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    
}
