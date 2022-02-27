/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Context.DBContext;
import Models.Contract;
import Models.Group;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author quocb
 */
public class GroupDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Group> getGroupList(String query) throws SQLException {
        ArrayList<Group> res = new ArrayList<>();
        try {
            String sql = query;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Group g = new Group(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        new User(rs.getString(4)),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8));
                res.add(g);
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

    public int getTotalGroup(String query) throws SQLException {
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

    public void addnewGroup(String code, int manager_id, String name, int status, String parent_group_code, String update_date) throws SQLException {
        String sql = "INSERT INTO `hr_system_v2`.`group` (`code`, `manager_id`, `name`, `status`, `parent_group_code`, `delete`, `update_date`) \n"
                + "VALUES (?, ?, ?, ?, ?, '1', ?);";
        try (
                Connection con = new DBContext().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.setInt(2, manager_id);
            ps.setString(3, name);
            ps.setInt(4, status);
            ps.setString(5, parent_group_code);
            ps.setString(6, update_date);
            // execute update SQL stetement
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public HashMap<String, String> getAllGroupNameAndCode() {
        HashMap<String, String> role = new HashMap<>();
        try {
            String sql = "SELECT group.code ,  group.name FROM hr_system_v2.group;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                role.put(rs.getString(1), rs.getString(2));
            }
            return role;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public void editStatusDelete(int delete, String code) throws SQLException {
        String sql = "UPDATE `hr_system_v2`.`group` SET `delete` = ? WHERE (`code` = ?);";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        if (delete == 0) {
            ps.setInt(1, 1);
        } else {
            ps.setInt(1, 0);
        }
        ps.setString(2, code);
        ps.executeUpdate();
        if (con != null) {
            con.close();
        }
    }

    public ArrayList<String> getAllPCode() throws SQLException {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String sql = "SELECT parent_group_code FROM hr_system_v2.group group by parent_group_code;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public List<Group> getOne(String code) throws SQLException {
        List<Group> list = new ArrayList<>();
        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT g.manager_id, g.code, g.name, u.fullname, g.parent_group_code, g.status, g.update_date, g.delete \n"
                    + "FROM hr_system_v2.group g join hr_system_v2.user u on g.manager_id = u.id \n"
                    + " where code = ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();

            while (rs.next()) {
                Group g = new Group(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        new User(rs.getString(4)),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8));

                list.add(g);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public Group SearchByCode(String code) throws Exception {
        try {
            String sql = "SELECT * FROM hr_system_v2.group where code like ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + code + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Group(rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8));

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return null;

    }

    public ArrayList<String> getAllGroupCode() throws SQLException {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String sql = "SELECT code FROM hr_system_v2.group group by code;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public ArrayList<String> getAllPGroupCode() throws SQLException {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String sql = "SELECT parent_group_code FROM hr_system_v2.group group by parent_group_code;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public ArrayList<String> getDate(String code) throws SQLException {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String sql = "SELECT update_date FROM hr_system_v2.group where code = ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public ArrayList<String> getAllName() throws SQLException {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String sql = "SELECT name FROM hr_system_v2.group group by name;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public void editGroup(String code, int manager_id, String name, int status, String parent_group_code, String update_date) throws SQLException {
        String sql = "UPDATE `hr_system_v2`.`group` SET `manager_id` = ?, `name` = ?, `status` = ?, `parent_group_code` = ?, `update_date` = ? "
                + "WHERE (`code` = ?);";
        try (
                Connection con = new DBContext().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, manager_id);
            ps.setString(2, name);
            ps.setInt(3, status);
            ps.setString(4, parent_group_code);
            ps.setString(5, update_date);
            ps.setString(6, code);
            // execute update SQL stetement
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static String myFormatDate(String date) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new SimpleDateFormat("dd-MM-yyyy").parse(date));
    }
}
