/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
