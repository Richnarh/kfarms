/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller;

import com.khoders.kfms.entities.account.PurchasePayment;
import com.khoders.kfms.jpa.AppSession;
import com.khoders.resource.jpa.CrudApi;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author khoders
 */
@Named(value = "purchasePaymentController")
@SessionScoped
public class PurchasePaymentController implements Serializable{
    @Inject private CrudApi crudApi;
    @Inject private AppSession appSession;
    
}
