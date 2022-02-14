/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBContext;
import Models.Setting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Kha Chinh
 */
public class SettingDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Vector<Setting> getSettingList(int page) {
        Vector vec = new Vector();
        try {
            String sql = "SELECT * FROM hr_system_v2.setting\n"
                    + "limit 5 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                Setting e = new Setting();
                e.setId(rs.getInt(1));
                e.setType(rs.getString(2));
                e.setValue(rs.getString(3));
                e.setStatus(rs.getBoolean(4));
                e.setOrder(rs.getInt(5));
                e.setNote(rs.getString(6));
                vec.add(e);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }

    public int getTotalSetting(String filter, String search) {
        int total = 0;
        try {
            String sql = "select count(*) FROM hr_system_v2.setting\n";
            if (filter != null && search == null) {
                if ("0".equals(filter) || "1".equals(filter)) {
                    sql += "where status = ?";
                } else {
                    sql += "where type = ?";
                }
            } else if (filter == null && search != null) {
                sql += "where value like ?";
            }
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            if (filter != null) {
                ps.setString(1, filter);
            } else if (search != null) {
                ps.setString(1,"%" + search + "%");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
    }

    public Vector<String> getAllType() {
        Vector vec = new Vector();
        try {
            String sql = "select type from hr_system_v2.setting\n"
                    + "group by type";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                vec.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }

    public Vector<Setting> filterSettingList(String input, int option, int page) {
        Vector vec = new Vector();
        try {
            String sql = "select * from hr_system_v2.setting\n";
            switch (option) {
                case 1:
                    sql += "where type = ?\n";
                    break;
                case 2:
                    sql += "where status = ?\n";
                    break;
            }
            sql += "limit 5 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, input);
            ps.setInt(2, (page - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                Setting e = new Setting();
                e.setId(rs.getInt(1));
                e.setType(rs.getString(2));
                e.setValue(rs.getString(3));
                e.setStatus(rs.getBoolean(4));
                e.setOrder(rs.getInt(5));
                e.setNote(rs.getString(6));
                vec.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }

    public Vector<Setting> searchSetting(String input, int page) {
        Vector vec = new Vector();
        try {
            String sql = "select * FROM hr_system_v2.setting\n"
                    + "where value like ?\n"
                    + "limit 5 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + input + "%");
            ps.setInt(2, (page - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                Setting e = new Setting();
                e.setId(rs.getInt(1));
                e.setType(rs.getString(2));
                e.setValue(rs.getString(3));
                e.setStatus(rs.getBoolean(4));
                e.setOrder(rs.getInt(5));
                e.setNote(rs.getString(6));
                vec.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }

    public void editStatus(int status, int setting_id) throws SQLException {
        String sql = "UPDATE `hr_system_v2`.`setting` SET `status` = ? WHERE (`id` = ?);";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        if (status == 0) {
            ps.setInt(1, 1);
        } else {
            ps.setInt(1, 0);
        }
        ps.setInt(2, setting_id);
        ps.executeUpdate();
    }

    public HashMap<Integer, String> getAllRole() {
        HashMap<Integer, String> role = new HashMap<>();
        try {
            String sql = "SELECT setting.order, setting.value FROM hr_system_v2.setting where setting.type = 'role';";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                role.put(rs.getInt(1), rs.getString(2));
            }
            return role;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public boolean updateSettingList(Setting s, int id) throws Exception {
        String updateTableSQL = "UPDATE hr_system_v2.setting SET type= ? ,value= ?, status= ?, setting.order = ? WHERE id= ?";
        int check = 0;
        try (
                Connection con = new DBContext().getConnection();
                PreparedStatement ps = con.prepareStatement(updateTableSQL)) { // user try-with-resources in java
            ps.setString(1, s.getType());
            ps.setString(2, s.getValue());
            ps.setBoolean(3, s.isStatus());
            ps.setInt(4, s.getOrder());
            ps.setInt(5, id);
            // execute update SQL stetement
            check = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return check > 0;
    }

    //add
    public boolean addNewSetting(Setting s) throws Exception {
        int check = 0;
        try {
            String sql = "INSERT INTO `hr_system_v2`.`setting` (`type`,`value`,`status`,`order`) VALUES (?,?,?,?)";
            con = new DBContext().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getType());
            ps.setString(2, s.getValue());
            ps.setBoolean(3, s.isStatus());
            ps.setInt(4, s.getOrder());
            check = ps.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            System.err.println("Error: " + e.getMessage());
        }
        return check > 0;
    }

    //delete
    public void deleteByID(int id) {
        try {
            String sql = "delete from hr_system_v2.setting where id = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SettingDAO st = new SettingDAO();
        int role = st.getTotalSetting(null, "m");

            System.out.println(role);

    }
}
