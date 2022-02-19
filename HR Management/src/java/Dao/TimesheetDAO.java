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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

   

    public ArrayList<Timesheet> getTimesheetList(String query) throws SQLException {
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
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return res;
    }

    public Timesheet getTimesheetById(int id) throws SQLException {
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
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public int getTotalTimesheet(String query) throws SQLException {
        try {
            String sql = query;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public int addNewTimesheet(Timesheet timesheet) throws SQLException {
        int rows = 0;
        try {
            String sql = "INSERT INTO `hr_system_v2`.`timesheet` (`title`, `date`, `process`, \n"
                    + "`duration`, `status`, `user_id`, `project_code`) \n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, timesheet.getTitle());
            ps.setString(2, timesheet.getDate());
            ps.setInt(3, timesheet.getProcess());
            ps.setString(4, timesheet.getDuration());
            ps.setInt(5, timesheet.getStatus());
            ps.setInt(6, timesheet.getUser_id());
            ps.setString(7, timesheet.getProject_code());
            rows = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return rows;
    }

    public int deleteTimesheetById(int id) throws SQLException {
        int rows = 0;
        try {
            String sql = "DELETE FROM `hr_system_v2`.`timesheet` WHERE (`id` = ?)";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rows = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return rows;
    }

    public void updateTimesheet(Timesheet timesheet) throws SQLException {
        String sql = "UPDATE `hr_system_v2`.`timesheet` SET `title` = ? , `date` = ?, `process` = ?"
                + ", `duration` = ?,  `work_result` = ?, `project_code` = ? WHERE (`id` = ?);";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, timesheet.getTitle());
        ps.setString(2, timesheet.getDate());
        ps.setInt(3, timesheet.getProcess());
        ps.setString(4, timesheet.getDuration());
        ps.setString(5, timesheet.getWork_result());
        ps.setString(6, timesheet.getProject_code());
        ps.setInt(7, timesheet.getId());
        ps.executeUpdate();
    }

    public static void main(String[] args) {
        TimesheetDAO tsDAO = new TimesheetDAO();

    }
}
