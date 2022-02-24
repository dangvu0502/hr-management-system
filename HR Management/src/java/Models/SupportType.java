/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Hide
 */
public class SupportType {

    
    private int id;
    private String name;
    private String email;
    private boolean status;
    private boolean delete;
    private String in_charge_group;
    private String description;
    
    public SupportType() {
    }


    public SupportType(int id, String name, String email, boolean status, boolean delete, String in_charge_group, String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.delete = delete;
        this.in_charge_group = in_charge_group;
        this.description = description;
    }

    public SupportType(String name) {
        this.name = name;
    }

    public String getIn_charge_group() {
        return in_charge_group;
    }

    public void setIn_charge_group(String in_charge_group) {
        this.in_charge_group = in_charge_group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }


    


    
    
}
