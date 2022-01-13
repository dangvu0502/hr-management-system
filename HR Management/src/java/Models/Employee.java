/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Egamorft
 */
public class Employee {
    private int employeeId;
    private int accountId;
    private String fullname;
    private String avatar;
    private String status;
    private int jobId;
    private int managerId;

    public Employee() {
    }

    public Employee(int employeeId, int accountId, String fullname, String avatar, String status, int jobId, int managerId) {
        this.employeeId = employeeId;
        this.accountId = accountId;
        this.fullname = fullname;
        this.avatar = avatar;
        this.status = status;
        this.jobId = jobId;
        this.managerId = managerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    
    
    
}
