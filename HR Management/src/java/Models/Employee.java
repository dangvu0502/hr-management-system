/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kha Chinh
 */
public class Employee {

    private int employee_id;
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private int status;
    private int type_id;
    private String type_name;
    private int code; // only for email vefify

    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_DEACTIVE = 0;

    
    public Employee() {
    }

    public Employee(String fullname, String username, String password, String email) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public Employee(String fullname, String username, String password, String email,String avatar, int status,int type_id) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.status = status;
        this.type_id = type_id;
    }
    
     public Employee(int employee_id,String fullname, String username, String password, String email,String avatar, int status, int type_id) {
        this.employee_id = employee_id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.type_id = type_id;
        this.status = status;
    }

    public Employee(int employee_id, String fullname, String username, String password, String email, String avatar, int status, int type_id, String type_name, int code) {
        this.employee_id = employee_id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.status = status;
        this.type_id = type_id;
        this.type_name = type_name;
        this.code = code;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
