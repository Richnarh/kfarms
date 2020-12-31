/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.db;

import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author khoders
 */
@Stateless
public class QueryService implements Serializable{
    private String qryString = "";
    
//    private String selectQry(Class clasz)
//    {
//        qryString = "SELECT e FROM "+clasz.getSimpleName()+" e WHERE e.account = :account";
//    }
}
