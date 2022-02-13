/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author quocb
 */
public class Group {
    private int id;
    private String code;
    private String manager;
    private String name;
    private boolean status;
    private String description;
    private String parent_group_code;
    private boolean delete;
    private Date update_date;

    public Group() {
    }

    public Group(int id, String code, String manager, String name, boolean status, String description, String parent_group_code, boolean delete, Date update_date) {
        this.id = id;
        this.code = code;
        this.manager = manager;
        this.name = name;
        this.status = status;
        this.description = description;
        this.parent_group_code = parent_group_code;
        this.delete = delete;
        this.update_date = update_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent_group_code() {
        return parent_group_code;
    }

    public void setParent_group_code(String parent_group_code) {
        this.parent_group_code = parent_group_code;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

   
   

    
   
}
