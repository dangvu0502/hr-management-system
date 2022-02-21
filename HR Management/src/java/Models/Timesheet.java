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
 * @author dangGG
 */
public class Timesheet {
    int id;
    String title;
    String date;
    int process;
    String duration;
    int status;
    String work_result;
    String reject_reason;
    int user_id;
    String project_code;

    public Timesheet(int id, String title, String date, int process, String duration, int status, String work_result, String reject_reason, int user_id, String project_code) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.process = process;
        this.duration = duration;
        this.status = status;
        this.work_result = work_result;
        this.reject_reason = reject_reason;
        this.user_id = user_id;
        this.project_code = project_code;
    }

    public Timesheet(String title, String date, int process, String duration, int status, int user_id, String project_code) {
        this.title = title;
        this.date = date;
        this.process = process;
        this.duration = duration;
        this.status = status;
        this.user_id = user_id;
        this.project_code = project_code;
    }

    public Timesheet(int id, String title, String date, int process, String duration, String work_result, String project_code) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.process = process;
        this.duration = duration;
        this.work_result = work_result;
        this.project_code = project_code;
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

    public String getDate() throws ParseException {
        return myFormatDate(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getWork_result() {
        return work_result;
    }

    public void setWork_result(String work_result) {
        this.work_result = work_result;
    }

    public String getReject_reason() {
        return reject_reason;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    @Override
    public String toString() {
        return "Timesheet{" + "id=" + id + ", title=" + title + ", date=" + date + ", process=" + process + ", duration=" + duration + ", status=" + status + ", work_result=" + work_result + ", reject_reason=" + reject_reason + ", user_id=" + user_id + ", project_code=" + project_code + '}';
    }
    
    public static String myFormatDate(String date) throws ParseException {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
    }
    
}
