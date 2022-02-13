/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Egamorft
 */
public class Contract {
    private int id;
    private User user_id;
    private String startDate;
    private String endDate;
    private int status;

    public Contract() {
    }

    public Contract(int id, User user_id, String startDate, String endDate, int status) {
        this.id = id;
        this.user_id = user_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Contract(int id, String startDate, String endDate, int status) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
 
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
