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

    public Vector<Group> getGroupList(int page) {
        Vector vec = new Vector();
        try {
            String sql = "SELECT * FROM hr_system_v2.group limit 6 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt(1));
                g.setCode(rs.getString(2));
                g.setManager(rs.getString(3));
                g.setName(rs.getString(4));
                g.setStatus(rs.getBoolean(5));
                g.setDescription(rs.getString(6));
                g.setParent_group_code(rs.getString(7));
                g.setDelete(rs.getBoolean(8));
                g.setUpdate_date(rs.getString(9));
                vec.add(g);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }

    public int getTotalGroup(String filter, String search) {
        int total = 0;
        try {
            String sql = "select count(*) FROM hr_system_v2.group\n";
            if (filter != null && search == null) {
                if ("0".equals(filter) || "1".equals(filter)) {
                    sql += "where status = ?";
                } else {
                    sql += "where code = ?";
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

    public Vector<Group> getGroupBySearch(String input, int page) {
        Vector vec = new Vector();
        try {
            String sql = "SELECT * FROM hr_system_v2.group"
                    + " where code like ? or name like ?"
                    + "limit 6 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + input + "%");
            ps.setString(2, "%" + input + "%");
            ps.setInt(3, (page - 1) * 6);
            rs = ps.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt(1));
                g.setCode(rs.getString(2));
                g.setManager(rs.getString(3));
                g.setName(rs.getString(4));
                g.setStatus(rs.getBoolean(5));
                g.setDescription(rs.getString(6));
                g.setParent_group_code(rs.getString(7));
                g.setDelete(rs.getBoolean(8));
                g.setUpdate_date(rs.getString(9));
                vec.add(g);
            }
        } catch (Exception e) {
            System.out.println("Error1: " + e.getMessage());
        }
        return vec;
    }

    public Boolean editGroup(Group g, int id) throws SQLException {
        int check = 0;
        try {
            //mo ket noi
            
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE hr_system_v2.group SET code =?, manager_id = ?, name = ?, status = ?, description = ?, parent_group_code = ?, update_date = ? WHERE (id = ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, g.getCode());
            ps.setString(2, g.getManager());
            ps.setString(3, g.getName());
            ps.setBoolean(4, g.isStatus());
            ps.setString(5, g.getDescription());
            ps.setString(6, g.getParent_group_code());
            ps.setString(7, g.getUpdate_date());
            ps.setInt(8, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return check > 0;
    }

    public Vector<Group> getOne(int id) {
        Vector vec = new Vector();
        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM hr_system_v2.group where id = ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt(1));
                g.setCode(rs.getString(2));
                g.setManager(rs.getString(3));
                g.setName(rs.getString(4));
                g.setStatus(rs.getBoolean(5));
                g.setDescription(rs.getString(6));
                g.setParent_group_code(rs.getString(7));
                g.setDelete(rs.getBoolean(8));
                g.setUpdate_date(rs.getString(9));
                vec.add(g);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return vec;
    }

    public Vector<Group> getGroup() {
        Vector vec = new Vector();
        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM hr_system_v2.group;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt(1));
                g.setCode(rs.getString(2));
                g.setManager(rs.getString(3));
                g.setName(rs.getString(4));
                g.setStatus(rs.getBoolean(5));
                g.setDescription(rs.getString(6));
                g.setParent_group_code(rs.getString(7));
                g.setDelete(rs.getBoolean(8));
                g.setUpdate_date(rs.getString(9));
                vec.add(g);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return vec;
    }
    public  boolean InsertGroup(Group g) throws Exception {
        int check = 0;
        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "INSERT INTO `hr_system_v2`.`group` (`code`, `manager_id`, `name`, `status`, `description`, `parent_group_code`,`delete`, `update_date`) VALUES (?, ?, ?, ?, ?, ?, 1, ?);";
            con = new DBContext().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            ps.setString(1, g.getCode());
            ps.setString(2, g.getManager());
            ps.setString(3, g.getName());
            ps.setBoolean(4, g.isStatus());
            ps.setString(5, g.getDescription());
            ps.setString(6, g.getParent_group_code());
            ps.setString(7, g.getUpdate_date());
            check = ps.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            System.err.println("Error: " + e.getMessage());
        }
        return check > 0;
    }
    
    public HashMap<String,String> getAllGroupNameAndCode() {
        HashMap<String,String> role = new HashMap<>();
        try {
            String sql = "SELECT group.code ,  group.name FROM hr_system_v2.group;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                role.put(rs.getString(1),rs.getString(2));
            }
            return role;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    public void editStatusDelete(int delete, int id) throws SQLException {
        String sql = "UPDATE `hr_system_v2`.`group` SET `delete` = ? WHERE (`id` = ?);";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        if (delete == 0) {
            ps.setInt(1, 1);
        } else {
            ps.setInt(1, 0);
        }
        ps.setInt(2, id);
        ps.executeUpdate();
    }
    public Vector<String> getAllCode() {
        Vector vec = new Vector();
        try {
            String sql = "select code from hr_system_v2.group\n"
                    + "group by code";
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
   
    public Vector<Group> filterGroupList(String input, int option, int page) {
        Vector vec = new Vector();
        try {
            String sql = "select * from hr_system_v2.group\n";
            switch (option) {
                case 1:
                    sql += "where code = ?\n";
                    break;
                case 2:
                    sql += "where status = ?\n";
                    break;
            }
            sql += "limit 6 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, input);
            ps.setInt(2, (page - 1) * 6);
            rs = ps.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt(1));
                g.setCode(rs.getString(2));
                g.setManager(rs.getString(3));
                g.setName(rs.getString(4));
                g.setStatus(rs.getBoolean(5));
                g.setDescription(rs.getString(6));
                g.setParent_group_code(rs.getString(7));
                g.setDelete(rs.getBoolean(8));
                g.setUpdate_date(rs.getString(9));
                vec.add(g);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }
}
