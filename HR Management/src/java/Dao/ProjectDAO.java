/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Context.DBContext;
import Models.Project;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dangGG
 */
public class ProjectDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<String> getAllProjectCode() throws SQLException {
       ArrayList<String> result = new ArrayList<String>();
        try {
            String sql = "SELECT code FROM hr_system_v2.project;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
              result.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public static void main(String[] args) throws SQLException {
         ProjectDAO projectDAO = new ProjectDAO();
         for(String code : projectDAO.getAllProjectCode()){
             System.out.println(code);
         }
    }

    public int getTotalProject(String query) throws SQLException {
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

    public ArrayList<Project> getProjectList(String query) throws SQLException {
        ArrayList<Project> res = new ArrayList<>();
        try {
            String sql = query;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            while (rs.next()) {
                Project p = new Project(rs.getString(1), rs.getString(2), new User(rs.getInt(3)), rs.getString(4),
                        simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(5))),
                        simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(6))), rs.getString(7), rs.getInt(8), rs.getInt(9));
                res.add(p);
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
}
