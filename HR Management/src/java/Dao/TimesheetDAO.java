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
    
    public  ArrayList<Timesheet> getTimesheetList(int page, int user_id) {
        ArrayList<Timesheet> res = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hr_system_v2.timesheet  where user_id = ? limit 3 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, (page - 1) * 3);
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
    
    public static void main(String[] args) {
        TimesheetDAO tsDAO = new TimesheetDAO();
        ArrayList<Timesheet> rs = tsDAO.getTimesheetList(1,99);
        for(int i = 0; i < rs.size(); i++){
            System.out.println(rs.get(i).toString());
        }
    }
}
