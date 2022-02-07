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
                    + "limit 3 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                Setting e = new Setting();
                e.setId(rs.getInt(1));
                e.setType_id(rs.getInt(2));
                e.setSetting_name(rs.getString(3));
                e.setSetting_value(rs.getString(4));
                e.setStatus(rs.getInt(5));
                vec.add(e);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }

    public int getTotalSetting() {
        int total = 0;
        try {
            String sql = "select count(*) FROM hr_system_v2.setting";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
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

    public static void main(String[] args) {
        SettingDAO sDAO = new SettingDAO();
        Vector<Setting> s = sDAO.getSettingList(1);
        for (Setting a : s) {
            System.out.println(a.getStatus());
        }
    }
}
