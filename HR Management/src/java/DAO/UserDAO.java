/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBContext;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author dangGG
 */
public class UserDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public User checkEmailExist(String email) throws Exception {
        try {
            String sql = "SELECT * FROM hr_system.employee WHERE email = ? ";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    public void UpdateProfile(String fullname, String avatar, String mobile, Boolean gender, String dob, String address, String username) {
        try {
            //mo ket noi
            String sql = "update `hr_system_v2`.`user` set fullname = ?, mobile = ?, gender = ?, dob = ?, address = ?, avatar = ? where username =?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, mobile);
            ps.setBoolean(3, gender);
//            ps.setDate(5, new java.sql.Date(dob.getTime()));
            ps.setString(4, dob);
            ps.setString(5, address);
            ps.setString(6, avatar);
            ps.setString(7, username);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    public void UpdateProfileAvtNull(String fullname, String mobile, Boolean gender, String dob, String address, String username) {
        try {
            //mo ket noi
            String sql = "update `hr_system_v2`.`user` set fullname = ?, mobile = ?, gender = ?, dob = ?, address = ? where username =?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, mobile);
            ps.setBoolean(3, gender);
//            ps.setDate(5, new java.sql.Date(dob.getTime()));
            ps.setString(4, dob);
            ps.setString(5, address);
            ps.setString(6, username);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
