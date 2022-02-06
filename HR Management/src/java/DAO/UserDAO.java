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
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author dangGG
 */
public class UserDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public User searchUserByUsername(String username) throws Exception {
        try {
            String sql = "SELECT * FROM hr_system_v2.user WHERE username = ? ";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getString(14),
                        rs.getBoolean(15),
                        rs.getBoolean(16)
                );
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public User searchUserByEmail(String email) throws Exception {
        try {
            String sql = "SELECT * FROM hr_system_v2.user WHERE email = ? ";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getString(14),
                        rs.getBoolean(15),
                        rs.getBoolean(16)
                );
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public int addUser(User user) throws Exception {
        int rows = 0;
        try {
            String sql = "INSERT INTO `hr_system_v2`.`user` (`fullname`,`email`,`mobile`,`gender`,`password`,`username`) VALUES (?,?,?,?,?,?)";
            con = new DBContext().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMobile());
            ps.setBoolean(4, user.isGender());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getUsername());
            rows = ps.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            System.err.println("Error: " + e.getMessage());
        }
        return rows;
    }

    public void setVerified(User user) throws SQLException {
        String sql = "UPDATE `hr_system_v2`.`user` SET `verified` = '1' WHERE (`id` = ?)";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, user.getId());
        ps.executeUpdate();
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
//    public static void main(String[] args) throws Exception {
//        UserDAO userDAO = new UserDAO();
//        System.out.println(userDAO.searchUserByEmail("dangvu0502@gmail.com"));
//    }

    public void ChangePassword(String newpassword, String username) {
        try {
            //mo ket noi
            String sql = "update `hr_system_v2`.`user` set password = ? where username =?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, newpassword);
            ps.setString(2, username);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
