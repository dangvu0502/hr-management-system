/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author quocb
 */
public class Group {
    private String code;
    private int manager_id;
    private String name;
    private boolean status;
    private String depcription;
    private String parent_group_code;
    private boolean delete;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
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

    public String getDepcription() {
        return depcription;
    }

    public void setDepcription(String depcription) {
        this.depcription = depcription;
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

    public Group(String code, int manager_id, String name, boolean status, String depcription, String parent_group_code, boolean delete) {
        this.code = code;
        this.manager_id = manager_id;
        this.name = name;
        this.status = status;
        this.depcription = depcription;
        this.parent_group_code = parent_group_code;
        this.delete = delete;
    }

    public Group() {
    }
   
}
