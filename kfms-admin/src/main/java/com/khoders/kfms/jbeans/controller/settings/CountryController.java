/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.kfms.jbeans.controller.settings;

import com.khoders.kfms.entities.settings.Country;
import com.khoders.resource.jpa.CrudApi;
import com.khoders.resource.utilities.Msg;
import com.khoders.resource.utilities.ParseValue;
import com.stately.modules.excel.ExcelDataLoader;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.FileUtils;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author khoders
 */
@Named(value = "countryController")
@SessionScoped
public class CountryController implements Serializable{
     
    @Inject private CrudApi crudApi;
    private Country country = new Country();
    private List<Country> countryList;
    
    private UploadedFile file;
    
    public void loadCountryList()
    {
        countryList = crudApi.getEm().createQuery("SELECT e FROM Country e", Country.class).getResultList();
    }
    
    public void uploadCountry()
    {
        countryList = new LinkedList<>();
        try 
        {
            if(file == null){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, Msg.setMsg("Select country file"), null));
                return;
            }
            
            File countryFile = new File(file.getFileName());
            FileUtils.writeByteArrayToFile(countryFile, file.getContent());
            List<Object[]> countryFileList = ExcelDataLoader.read(countryFile.getAbsolutePath()).getWorkBookData();
            
            countryFileList.remove(0);
            
            
            for(Object[] objects : countryFileList) 
            {
                country = new Country();
                country.setCountryCode(ParseValue.parseSringValue(objects[0]));
                country.setCountryName(ParseValue.parseSringValue(objects[1]));
                country.setCurrency(ParseValue.parseSringValue(objects[2]));
                country.setCurrencyCode(ParseValue.parseSringValue(objects[3]));
                countryList.add(country);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void saveCountry()
    {
        if(countryList == null)
        {
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, Msg.setMsg("The List is empty"), null));
                return;
        }
        try
        {
            countryList.stream().map(c ->
            {
               c.setId(crudApi.genId());
               return c;
            }
            ).forEachOrdered(c -> 
            {
                crudApi.save(c);
            }
            );
            
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, Msg.setMsg("Country upload saved"), null));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void clearCountry()
    {
        countryList = new LinkedList<>();
    }
    public List<Country> getCountryList()
    {
        return countryList;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    
}
