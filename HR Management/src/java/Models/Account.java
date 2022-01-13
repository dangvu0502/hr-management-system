/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author dangGG
 */
public class Account {

    private int account_id;
    private int employee_id;
    private String fullname;
    private String username;
    private String password;
    private String email;
    private int code; // only for email vefify

    public Account() {
    }

    public Account(String fullname, String username, String password, String email, int code) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.code = code;
    }

    public Account(int employee_id, String username, String password, String email) {
        this.employee_id = employee_id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Account(int account_id, int employee_id, String username, String password, String email) {
        this.account_id = account_id;
        this.employee_id = employee_id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Account(String fullname, String username, String password, String email) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
