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
import java.util.ArrayList;
import java.util.List;

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

    public int addNewAccount(User user) throws Exception {
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

    public int addNewUser(User user) throws Exception {
        int rows = 0;
        try {
            String sql = "INSERT INTO `hr_system_v2`.`user` (`fullname`,`email`,`mobile`,`gender`,`username`"
                    + ",`status`,`verified`,`group_code`,`role_id`) VALUES (?,?,?,?,?,?,?,?,?)";
            con = new DBContext().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMobile());
            ps.setBoolean(4, user.isGender());
            ps.setString(5, user.getUsername());
            ps.setBoolean(6, user.isStatus());
            ps.setBoolean(7, user.isVerified());
            ps.setString(8, user.getGroup_code());
            ps.setInt(9, user.getRole_id());
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

    public void setNewPassword(User user, String password) {
        try {
            String sql = "UPDATE `hr_system_v2`.`user` SET `password` = ? WHERE (`id` = ?)";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setInt(2, user.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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

    public User login(String username, String password) {

        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM hr_system_v2.user where (username =? or email = ?) and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                User account = new User();
                account.setId(rs.getInt("id"));
                account.setFullname(rs.getString("fullname"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setMobile(rs.getString("mobile"));
                account.setGender(rs.getBoolean("gender"));
                account.setAvatar(rs.getString("avatar"));
                account.setDob(rs.getString("dob"));
                account.setAddress(rs.getString("address"));
                account.setRole_id(rs.getInt("role_id"));
                account.setProject_role_id(rs.getInt("project_role_id"));
                account.setSupervisor_id(rs.getInt("supervisor_id"));
                account.setGroup_code(rs.getString("group_code"));
                account.setStatus(rs.getBoolean("status"));
                if (password.equals(account.getPassword())) {
                    System.out.println("password true");
                    return account;
                }
                System.out.println("password false");
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }

    public List<User> getUserList() {
        List<User> list = new ArrayList<>();
        try {
            String sql = "select * from hr_system_v2.user";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setFullname(rs.getString(2));
                u.setUsername(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setMobile(rs.getString(6));
                u.setGender(rs.getBoolean(7));
                u.setAvatar(rs.getString(8));
                u.setDob(rs.getString(9));
                u.setAddress(rs.getString(10));
                u.setRole_id(rs.getInt(11));
                u.setProject_role_id(rs.getInt(12));
                u.setSupervisor_id(rs.getInt(13));
                u.setGroup_code(rs.getString(14));
                u.setStatus(rs.getBoolean(15));
                u.setVerified(rs.getBoolean(16));
                list.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

//    public static void main(String[] args) throws Exception {
//
//    }

    public List<User> getUserById(int id) {
        List<User> list = new ArrayList<>();
        try {
            String sql = "select * from hr_system_v2.user where id = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setFullname(rs.getString(2));
                u.setUsername(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setMobile(rs.getString(6));
                u.setGender(rs.getBoolean(7));
                u.setAvatar(rs.getString(8));
                u.setDob(rs.getString(9));
                u.setAddress(rs.getString(10));
                u.setRole_id(rs.getInt(11));
                u.setProject_role_id(rs.getInt(12));
                u.setSupervisor_id(rs.getInt(13));
                u.setGroup_code(rs.getString(14));
                u.setStatus(rs.getBoolean(15));
                u.setVerified(rs.getBoolean(16));
                list.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }
}
