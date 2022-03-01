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
public class Project {
    private String code;
    private String groupCode;
    private User managerID;
    private String projectName;
    private String startDate;
    private String endDate;
    private String description;
    private int status;
    private int effort;

    public Project() {
    }

    public Project(String code, String groupCode, User managerID, String projectName, String startDate, String endDate, String description, int status, int effort) {
        this.code = code;
        this.groupCode = groupCode;
        this.managerID = managerID;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.status = status;
        this.effort = effort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public User getManagerID() {
        return managerID;
    }

    public void setManagerID(User managerID) {
        this.managerID = managerID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }
    
    
}
