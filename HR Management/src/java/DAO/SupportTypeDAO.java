/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import Models.Employee;
import Models.Group;
import Models.SupportType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author quocb
 */
public class SupportTypeDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Vector<SupportType> getSupportTypeList(int page) {
        Vector vec = new Vector();
        try {
            String sql = "SELECT * FROM hr_system_v2.support_type limit 3 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                SupportType s = new SupportType();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setEmail(rs.getString(3));
                s.setStatus(rs.getBoolean(4));
                s.setDelete(rs.getBoolean(5));
                s.setIn_charge_group(rs.getString(6));
                s.setDescription(rs.getString(7));
                vec.add(s);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }

    public int totalSupportType() {
        try {
            //mo ket noi
            String sql = "SELECT count(*) FROM hr_system_v2.support_type";
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
    
    public boolean updateSupportType(SupportType s, int id) throws Exception {
        String updateTableSQL = "UPDATE hr_system_v2.support_type SET name= ? ,email= ?, status= ?, in_charge_group = ?, description= ? WHERE id= ?";
        int check = 0;
        try (
                Connection con = new DBContext().getConnection();
                PreparedStatement ps = con.prepareStatement(updateTableSQL)) { // user try-with-resources in java
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setBoolean(3, s.isStatus());
            ps.setString(4, s.getIn_charge_group());
            ps.setString(5, s.getDescription());
            ps.setInt(6, id);
            // execute update SQL stetement
            check = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return check > 0;

    }
    
      public Vector<Group> getGroupBySearch(String group_type, String input) {
        Vector vec = new Vector();
        try {
            String sql = "SELECT * FROM hr_system_v2.group"
                    + " where " + group_type + " = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, input);
            rs = ps.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setCode(rs.getString(1));
                g.setManager(rs.getString(2));
                g.setName(rs.getString(3));
                g.setStatus(rs.getBoolean(4));
                g.setDescription(rs.getString(5));
                g.setParent_group_code(rs.getString(6));
                g.setDelete(rs.getBoolean(7));
                g.setUpdate_date(rs.getString(8));
                vec.add(g);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
       }
        return vec;
    }
    
    
}
