/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author quocb
 */
public class Group {

    private String code;
    private int manager_id;
    private String name;
    private int status;
    private String description;
    private String parent_group_code;
    private int delete;
    private String update_date;
    private User fullname;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public User getFullname() {
        return fullname;
    }

    public void setFullname(User fullname) {
        this.fullname = fullname;
    }

    public Group(String code, int manager_id, String name, int status, String description, String parent_group_code, int delete, String update_date) {
        this.code = code;
        this.manager_id = manager_id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.parent_group_code = parent_group_code;
        this.delete = delete;
        this.update_date = update_date;

    }

    public Group(String code, String name, User fullname, String parent_group_code, int status, String update_date, int delete) {
        this.code = code;
        this.name = name;
        this.fullname = fullname;
        this.parent_group_code = parent_group_code;
        this.status = status;
        this.update_date = update_date;
        this.delete = delete;
    }

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

    public String getUpdate_date() throws ParseException {
        return myFormatDate(update_date);
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public Group() {
    }

    public static String myFormatDate(String date) throws ParseException {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
    }

}
