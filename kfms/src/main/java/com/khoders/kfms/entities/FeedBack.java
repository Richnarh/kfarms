package com.khoders.kfms.entities;

import com.khoders.kfms.entities.FarmAccountRecord;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author 
 */
@Entity
@Table(name = "feedback")
public class FeedBack extends FarmAccountRecord implements Serializable{
        
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "message")
    private String message;

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
   
    
}
