/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Context.DBContext;
import Models.Request;
import Models.Timesheet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author quocb
 */
public class RequestDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

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

}
