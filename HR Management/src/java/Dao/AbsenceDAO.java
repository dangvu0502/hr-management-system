/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Context.DBContext;
import Models.Absence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Kha Chinh
 */
public class AbsenceDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Vector<Absence> getListAbsenceByStaff(String sql, int page) throws SQLException {
        Vector vec = new Vector();
        try {
            sql += " limit 5 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                Absence a = new Absence();
                a.setId(rs.getInt(1));
                a.setUser_id(rs.getInt(2));
                a.setTitle(rs.getString(3));
                a.setType(rs.getInt(4));
                a.setStatus(rs.getInt(5));
                a.setRequest_date(rs.getString(6));
                a.setFrom(rs.getString(7));
                a.setTo(rs.getString(8));
                a.setReject_reason(rs.getString(9));
                a.setDuration(rs.getFloat(10));
                vec.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return vec;
    }

    public int getTotalAbsenceOfStaff(String sql) throws SQLException {
        int total = 0;
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return total;
    }

    public void deleteTimesheetById(int id) throws SQLException {
        try {
            String sql = "DELETE FROM `hr_system_v2`.`absence` WHERE (`id` = ?)";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static void main(String[] args) {

    }
}
