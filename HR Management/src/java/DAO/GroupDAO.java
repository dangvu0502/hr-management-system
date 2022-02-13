/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Models.Contract;
import Models.Group;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                g.setManager_id(rs.getInt(3));
                g.setName(rs.getString(4));
                g.setStatus(rs.getBoolean(5));
                g.setDescription(rs.getString(6));
                g.setParent_group_code(rs.getString(7));
                g.setDelete(rs.getBoolean(8));
                g.setUpdate_date(rs.getDate(9));
                vec.add(g);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }

    public int totalGroup() {
        try {
            //mo ket noi
            String sql = "SELECT count(*) FROM hr_system_v2.group";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    public Vector<Group> getGroupBySearch(String input) {
        Vector vec = new Vector();
        try {
            String sql = "SELECT * FROM hr_system_v2.group"
                    + " where code like ? or name like ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + input + "%");
            ps.setString(2, "%" + input + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt(1));
                g.setCode(rs.getString(2));
                g.setManager_id(rs.getInt(3));
                g.setName(rs.getString(4));
                g.setStatus(rs.getBoolean(5));
                g.setDescription(rs.getString(6));
                g.setParent_group_code(rs.getString(7));
                g.setDelete(rs.getBoolean(8));
                g.setUpdate_date(rs.getDate(9));
                vec.add(g);
            }
        } catch (Exception e) {
            System.out.println("Error1: " + e.getMessage());
        }
        return vec;
    }

    public void editGroup(String code, int manager_id, String name, Boolean status, String description, String parent_group_code, String update_date, int id) {
        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE hr_system_v2.group SET code =?, manager_id = ?, name = ?, status = ?, description = ?, parent_group_code = ?, update_date = ? WHERE (id = ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            ps.setInt(2, manager_id);
            ps.setString(3, name);
            ps.setBoolean(4, status);
            ps.setString(5, description);
            ps.setString(6, parent_group_code);
            ps.setString(7, update_date);
            ps.setInt(8, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
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
                g.setManager_id(rs.getInt(3));
                g.setName(rs.getString(4));
                g.setStatus(rs.getBoolean(5));
                g.setDescription(rs.getString(6));
                g.setParent_group_code(rs.getString(7));
                g.setDelete(rs.getBoolean(8));
                g.setUpdate_date(rs.getDate(9));
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
                g.setManager_id(rs.getInt(3));
                g.setName(rs.getString(4));
                g.setStatus(rs.getBoolean(5));
                g.setDescription(rs.getString(6));
                g.setParent_group_code(rs.getString(7));
                g.setDelete(rs.getBoolean(8));
                g.setUpdate_date(rs.getDate(9));
                vec.add(g);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return vec;
    }

}
