/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.model;

import java.io.Serializable;

/**
 *
 * @author khoders
 */
public class SwitchTab implements Serializable{
     private int activeTab = 0;
     
     public static SwitchTab firstTab()
     {
         SwitchTab switchTab = new SwitchTab();
         switchTab.switchFirstTab();
         
         return switchTab;
     }
   
    public void switchTabView()
    {
        if(activeTab == 0)
        {
            setActiveTab(1);
        }
        else
        {
            setActiveTab(0);
        }
    }
    
    public void switchFirstTab()
    {
        activeTab = 0;
    }

    public int getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(int activeTab) {
        this.activeTab = activeTab;
    }
}
