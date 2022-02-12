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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                    + "FROM hr_system_v2.contract c inner join hr_system_v2.user u \n"
                    + "where c.user_id = u.id limit 2 offset ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 2);
            rs = ps.executeQuery();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            while (rs.next()) {
                Contract c = new Contract(rs.getInt(1), new User(rs.getString(2), rs.getString(3)),
                        simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(4))),
                        simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(5))), rs.getInt(6));
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

    public List<Contract> getContractBySearch(String setting_type, String txtSearch, int page) {
        List<Contract> list = new ArrayList<>();
        try {
            String sql = "SELECT c.id , u.fullname, u.email, c.start_date, c.end_date, c.status FROM hr_system_v2.contract c inner join hr_system_v2.user u\n"
                    + "on c.user_id = u.id where " + setting_type + " LIKE ? limit 2 offset ? ";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setInt(2, (page - 1) * 2);
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

    public void setStatus(Contract contract) throws SQLException {
        String sql = "UPDATE `hr_system_v2`.`contract` SET `status` = '1' WHERE (`id` = ?)";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, contract.getId());
        ps.executeUpdate();
    }

//    public Contract getAllContract() {
//        try {
//            //mo ket noi
//            Connection conn = new DBContext().getConnection();
//            String sql = "SELECT * FROM hr_system_v2.contract";
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Contract c = new Contract(rs.getInt(1), new User(rs.getString(2), rs.getString(3)), rs.getString(4), rs.getString(5), rs.getInt(6));
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace(System.out);
//        }
//
//        return null;
//    }
    public void updateContract(String StartDate, String EndDate, int id) throws Exception {
        String sql = "UPDATE hr_system_v2.contract SET startdate = ?, enddate = ? WHERE id= ?";
        try (
                Connection con = new DBContext().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) { // user try-with-resources in java
            ps.setString(1, StartDate);
            ps.setString(2, EndDate);
            ps.setInt(3, id);
            // execute update SQL stetement
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }

    }

    public List<Contract> getOne(int id) {
        List<Contract> list = new ArrayList<>();
        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "select c.id , u.fullname, u.email, c.start_date, c.end_date, c.status \n"
                    + "FROM hr_system_v2.contract c inner join hr_system_v2.user u\n"
                    + "on c.user_id = u.id where c.id = ? ;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            while (rs.next()) {
                Contract c = new Contract(rs.getInt(1), new User(rs.getString(2), rs.getString(3)), 
                        simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(4))), 
                        simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(5))), rs.getInt(6));
                list.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }
}
