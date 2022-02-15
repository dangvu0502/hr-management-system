/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Context.DBContext;
import Models.Timesheet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 *
 * @author dangGG
 */
public class TimesheetDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return dateTime.format(myFormatObj);
    }
    
    public  ArrayList<Timesheet> getTimesheetList(String query) {
        ArrayList<Timesheet> res = new ArrayList<>();
        try {
            String sql = query;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               Timesheet ts = new Timesheet(
                       rs.getInt(1), 
                       rs.getString(2), 
                       rs.getString(3), 
                       rs.getInt(4),
                       rs.getString(5),
                       rs.getInt(6),
                       rs.getString(7),
                       rs.getString(8),
                       rs.getInt(9),
                        rs.getString(10));
               res.add(ts);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return res;
    }
    
    public Timesheet getTimesheetById(int id) {
        ArrayList<Timesheet> res = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hr_system_v2.timesheet  where id = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
               return new Timesheet(
                       rs.getInt(1), 
                       rs.getString(2), 
                       rs.getString(3), 
                       rs.getInt(4),
                       rs.getString(5),
                       rs.getInt(6),
                       rs.getString(7),
                       rs.getString(8),
                       rs.getInt(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    
    
    public int getTotalTimesheet() {
        try {
            String sql = "select count(id) FROM hr_system_v2.timesheet;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }
    
    public static void main(String[] args) {
        TimesheetDAO tsDAO = new TimesheetDAO();
        System.out.println(tsDAO.getTotalTimesheet());
    }
}
