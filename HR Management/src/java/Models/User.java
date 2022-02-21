/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import java.util.Objects;

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
    private String dob;
    private String address;
    private int role_id;
    private int project_role_id;
    private int supervisor_id;
    private String group_code;
    private boolean status;
    private boolean verified;
    
    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }
    
    public User(String fullname, String username, String password, String email, String mobile, boolean gender) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
    } 

    public User(String fullname, String username,  String email, String mobile, boolean gender, String group_code, boolean status, boolean verified, int role_id) {
        this.role_id = role_id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.group_code = group_code;
        this.status = status;
        this.verified = verified;
    }
    
    
    
    public User(int id, String fullname, String username, String password, String email, String mobile, boolean gender, String avatar, String dob, String address, int role_id, int project_role_id, int supervisor_id, String group_code, boolean status) {
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
    
      public User(int id, String fullname, String username, String password, String email, String mobile, boolean gender, String avatar, String dob, String address, int role_id, int project_role_id, int supervisor_id, String group_code, boolean status, boolean verified) {
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
        this.verified = verified;
    }

    public User(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.fullname);
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.password);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.mobile);
        hash = 53 * hash + (this.gender ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.avatar);
        hash = 53 * hash + Objects.hashCode(this.dob);
        hash = 53 * hash + Objects.hashCode(this.address);
        hash = 53 * hash + this.role_id;
        hash = 53 * hash + this.project_role_id;
        hash = 53 * hash + this.supervisor_id;
        hash = 53 * hash + Objects.hashCode(this.group_code);
        hash = 53 * hash + (this.status ? 1 : 0);
        hash = 53 * hash + (this.verified ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.gender != other.gender) {
            return false;
        }
        if (this.role_id != other.role_id) {
            return false;
        }
        if (this.project_role_id != other.project_role_id) {
            return false;
        }
        if (this.supervisor_id != other.supervisor_id) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.verified != other.verified) {
            return false;
        }
        if (!Objects.equals(this.fullname, other.fullname)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.mobile, other.mobile)) {
            return false;
        }
        if (!Objects.equals(this.avatar, other.avatar)) {
            return false;
        }
        if (!Objects.equals(this.dob, other.dob)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.group_code, other.group_code)) {
            return false;
        }
        return true;
    }

   
    
}
