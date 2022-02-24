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

    /* public ArrayList<Request> getRequestList(String fromDate, String toDate, String title, String requestName, int status, int support_type_id, int page) throws SQLException {
        ArrayList<Request> res = new ArrayList<>();
        try {
            String sql = "SELECT r.request_date, r.title, (s.name) as RequestName, (u.fullname) as 'Incharge Staff', r.status, r.update_date FROM ((hr_system_v2.request r \n"
                    + "join hr_system_v2.`support type` s on r.support_type_id = s.id)\n"
                    + "join hr_system_v2.user u on r.in_charge_staff = u.id)\n"
                    + "where r.support_type_id = ?";
            if (!fromDate.isEmpty()) {
                sql += " and r.request_date >= " + "'" + fromDate + "'";
            }
            if (!toDate.isEmpty()) {
                sql += "r.request_date <= " + "'" + toDate + "'";
            }
            if (!requestName.isEmpty()) {
                sql += " s.name like  " + "'%" + requestName + "%'";
            }
            if (!title.isEmpty()) {
                sql += " and r.title like  " + "'%" + title + "%'";
            }
            if (status != 0) {
                sql += " r.status = " + "'" + status + "'";
            }
            sql += " limit 3 offset " + (page - 1) * 3;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, support_type_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Request re = new Request(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8));
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
     */
    public ArrayList<Request> getRequestList(String query) throws SQLException {
        ArrayList<Request> res = new ArrayList<>();
        try {
            String sql = query;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Request re = new Request(
                        rs.getString(1),
                        rs.getString(2),
                        new SupportType(rs.getString(3)),
                        new User(rs.getString(4)),
                        rs.getInt(5),
                        rs.getString(6));
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
                        rs.getString(1),
                        rs.getString(2),
                        new SupportType(rs.getString(3)),
                        new User(rs.getString(4)),
                        rs.getInt(5),
                        rs.getString(6));

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

    /*
    public int getTotalRequest(String fromDate, String toDate, String title, String requestName, int status, int support_type_id) throws SQLException {
        try {
            String sql = "SELECT count(r.support_type_id) FROM ((hr_system_v2.request r \n"
                    + "join hr_system_v2.`support type` s on r.support_type_id = s.id)\n"
                    + "join hr_system_v2.user u on r.in_charge_staff = u.id)\n"
                    + "where r.support_type_id = ?;";
            if (!fromDate.isEmpty()) {
                sql += " and r.request_date >= " + "'" + fromDate + "'";
            }
            if (!toDate.isEmpty()) {
               sql += "r.request_date <= " + "'" + toDate + "'";
            }
            if (!requestName.isEmpty()) {
                sql += " s.name like  " + "'%" + requestName + "%'";
            }
            if (!title.isEmpty()) {
                sql += " and r.title like  " + "'%" + title + "%'";
            }
            if (status != 0) {
                sql += " r.status = " + "'" + status + "'";
            }
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, support_type_id);
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
     */
}
