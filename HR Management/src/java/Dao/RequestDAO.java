/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Context.DBContext;
import Models.Request;
import Models.SupportType;
import Models.Timesheet;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.jasper.tagplugins.jstl.ForEach;

/**
 *
 * @author quocb
 */
public class RequestDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int deleteGroupById(int id) throws SQLException {
        int rows = 0;
        try {
            String sql = "DELETE FROM `hr_system_v2`.`request` WHERE (`id` = ?);";
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

    public ArrayList<Request> getRequestList(String query) throws SQLException {
        ArrayList<Request> res = new ArrayList<>();
        try {
            String sql = query;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Request re = new Request(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        new SupportType(rs.getString(4)),
                        new User(rs.getString(5)),
                        rs.getInt(6),
                        rs.getString(7));
                res.add(re);
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

    public ArrayList<Request> getAllRequest() throws SQLException {
        ArrayList<Request> res = new ArrayList<>();
        try {
            String sql = "SELECT r.request_date, r.title, (s.name) as RequestName, (u.fullname), r.status, r.update_date as 'Incharge Staff' FROM ((hr_system_v2.request r \n"
                    + "join hr_system_v2.`support type` s on r.support_type_id = s.id)\n"
                    + "join hr_system_v2.user u on r.in_charge_staff = u.id);";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Request re = new Request(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        new SupportType(rs.getString(4)),
                        new User(rs.getString(5)),
                        rs.getInt(6),
                        rs.getString(7));

                res.add(re);
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

    public int getTotalRequest(String query) throws SQLException {
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

    public void addnewrequest(String title, String request_date, String update_date, int support_type_id, int in_charge_staff, String in_charge_group, int status) throws SQLException {
        String sql = "INSERT INTO `hr_system_v2`.`request` \n"
                + "(`title`, `request_date`, `update_date`, `support_type_id`, `in_charge_staff`, `in_charge_group`, `status`) \n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, request_date);
            ps.setString(3, update_date);
            ps.setInt(4, support_type_id);
            ps.setInt(5, in_charge_staff);
            ps.setString(6, in_charge_group);
            ps.setInt(7, status);
            
            // execute update SQL stetement
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public int getAllStatus() throws SQLException {

        try {
            String sql = "SELECT status FROM hr_system_v2.request group by status;";
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
        return 0;
    }
}
