/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Kha Chinh
 */
public class Employee {
    private int employee_id;
    private int account_id;
    private String name;
    private String avatar;
    private boolean status;
    private int job_id;
    private int manager_id;

    public Employee() {
    }

    public Employee(int employee_id, int account_id, String name, String avatar, boolean status, int job_id, int manager_id) {
        this.employee_id = employee_id;
        this.account_id = account_id;
        this.name = name;
        this.avatar = avatar;
        this.status = status;
        this.job_id = job_id;
        this.manager_id = manager_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
    
}
