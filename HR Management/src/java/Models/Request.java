/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author quocb
 */
public class Request {

 
    private int id;
    private String title;

    public Request(int id, String request_date, String title, SupportType name, User fullname, int status, String update_date) {
        this.id = id;
        this.request_date = request_date;
        this.title = title;
        this.name = name;
        this.fullname = fullname;
        this.status = status;
        this.update_date = update_date;
    }
    public Request(int id, String request_date, String title, int support_type_id,int incharge_staff, SupportType name, User fullname, int status, String update_date) {
        this.id = id;
        this.request_date = request_date;
        this.title = title;
        this.support_type_id = support_type_id;
        this.incharge_staff = incharge_staff;
        this.name = name;
        this.fullname = fullname;
        this.status = status;
        this.update_date = update_date;
    }
    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", title=" + title + ", request_date=" + request_date + ", update_date=" + update_date + ", support_type_id=" + support_type_id + ", incharge_staff=" + incharge_staff + ", incharge_group=" + incharge_group + ", status=" + status + ", name=" + name + ", fullname=" + fullname + '}';
    }
    private String request_date;
    private String update_date;
    private int support_type_id;
    private int incharge_staff;
    private String incharge_group;
    private int status;
    private SupportType name;
    private User fullname;

    public Request(int id, String title, String request_date, String update_date, int support_type_id, int incharge_staff, String incharge_group, int status, SupportType name, User fullname) {
        this.id = id;
        this.title = title;
        this.request_date = request_date;
        this.update_date = update_date;
        this.support_type_id = support_type_id;
        this.incharge_staff = incharge_staff;
        this.incharge_group = incharge_group;
        this.status = status;
        this.name = name;
        this.fullname = fullname;
    }

    public SupportType getName() {
        return name;
    }

    public User getFullname() {
        return fullname;
    }
 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequest_date() throws ParseException {
       return myFormatDate(request_date);
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public String getUpdate_date() throws ParseException {
        return myFormatDate(update_date);
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public int getSupport_type_id() {
        return support_type_id;
    }

    public void setSupport_type_id(int support_type_id) {
        this.support_type_id = support_type_id;
    }

    public int getIncharge_staff() {
        return incharge_staff;
    }

    public void setIncharge_staff(int incharge_staff) {
        this.incharge_staff = incharge_staff;
    }

    public String getIncharge_group() {
        return incharge_group;
    }

    public void setIncharge_group(String incharge_group) {
        this.incharge_group = incharge_group;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    

    public Request() {
    }
        public static String myFormatDate(String date) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
    }
}
