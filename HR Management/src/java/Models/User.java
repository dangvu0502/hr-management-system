/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Egamorft
 */
public class User {
    private int id;
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private boolean gender;
    private String avatar;
    private Date dob;
    private String address;
    private int role_id;
    private int project_role_id;
    private int supervisor_id;
    private String group_code;
    private boolean status;
    private boolean verified;
    
    public User() {
    }

    
    public User(String fullname, String password, String email, String mobile, boolean gender) {
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
    }
    
    
    public User(int id, String fullname, String username, String password, String email, String mobile, boolean gender, String avatar, Date dob, String address, int role_id, int project_role_id, int supervisor_id, String group_code, boolean status) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.avatar = avatar;
        this.dob = dob;
        this.address = address;
        this.role_id = role_id;
        this.project_role_id = project_role_id;
        this.supervisor_id = supervisor_id;
        this.group_code = group_code;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getProject_role_id() {
        return project_role_id;
    }

    public void setProject_role_id(int project_role_id) {
        this.project_role_id = project_role_id;
    }

    public int getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(int supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
      public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    
}
