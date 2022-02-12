/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Models.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
            String sql = "SELECT * FROM hr_system_v2.group limit 3 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setCode(rs.getString(1));
                g.setManager_id(rs.getInt(2));
                g.setName(rs.getString(3));
                g.setStatus(rs.getBoolean(4));
                g.setDescription(rs.getString(5));
                g.setParent_group_code(rs.getString(6));
                g.setDelete(rs.getBoolean(7));
                g.setUpdate_date(rs.getDate(8));
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
            ps.setString(1, "%" +input+ "%");
            ps.setString(2, "%" +input+ "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setCode(rs.getString(1));
                g.setManager_id(rs.getInt(2));
                g.setName(rs.getString(3));
                g.setStatus(rs.getBoolean(4));
                g.setDescription(rs.getString(5));
                g.setParent_group_code(rs.getString(6));
                g.setDelete(rs.getBoolean(7));
                g.setUpdate_date(rs.getDate(8));
                vec.add(g);
            }
        } catch (Exception e) {
            System.out.println("Error1: " + e.getMessage());
       }
        return vec;
    }
    public static void main(String[] args) {
        GroupDAO g = new GroupDAO();
        Vector<Group> gr = new Vector();
        if(gr != null){
            gr =  g.getGroupBySearch("a");
        }else
        {
            System.out.println("Not");
        }
        
    }
            
    
}
