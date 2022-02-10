/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBContext;
import Models.Contract;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Egamorft
 */
public class ContractDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Contract> getContractList(int page) {
        List<Contract> list = new ArrayList<>();
        try {
            String sql = "SELECT c.id, u.fullname, u.email, c.start_date, c.end_date, c.status \n"
                    + "FROM hr_system_v2.contract c, hr_system_v2.user u \n"
                    + "where c.user_id = u.id limit 2 offset ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 2);
            rs = ps.executeQuery();
            while (rs.next()) {
                Contract c = new Contract(rs.getInt(1), new User(rs.getString(2), rs.getString(3)), rs.getString(4), rs.getString(5), rs.getInt(6));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    public int getTotalContract() {
        int total = 0;
        try {
            String sql = "select count(*) FROM hr_system_v2.contract";
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

    public List<Contract> getContractBySearch(String setting_type, String txtSearch) {
        List<Contract> list = new ArrayList<>();
        try {
            String sql = "SELECT c.id , u.fullname, u.email, c.start_date, c.end_date, c.status FROM hr_system_v2.contract c inner join hr_system_v2.user u\n"
                    + "on c.user_id = u.id where " +setting_type+ " LIKE ? ";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+ txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Contract c = new Contract(rs.getInt(1), new User(rs.getString(2), rs.getString(3)), rs.getString(4), rs.getString(5), rs.getInt(6));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }
}
