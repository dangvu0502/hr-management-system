/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Context.DBContext;
import Models.Employee;
import Models.Group;
import Models.Setting;
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
            String sql = "SELECT * FROM hr_system_v2.`support type` limit 3 offset ?";
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
    
    public Vector<Group> getCodeGroupBAList() {
        Vector vec = new Vector();
        try {
            String sql = "SELECT a.code FROM hr_system_v2.group a join hr_system_v2.`support type` b where a.manager_id = b.id and a.status = 1;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Group s = new Group();
                s.setCode(rs.getString(1));
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
            String sql = "SELECT count(*) FROM hr_system_v2.`support type`;";
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
    
    //add
    public boolean addNewSupportType(SupportType s) throws Exception {
        int check = 0;
        try {
            String sql = "INSERT INTO `hr_system_v2`.`support type` (`name`,`email`,`status`,`delete`,`incharge group`,`description`) VALUES (?,?,?,?,?,?)";
            con = new DBContext().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setBoolean(3, s.isStatus());
            ps.setBoolean(4, s.isDelete());
            ps.setString(5, s.getIn_charge_group());
            ps.setString(6, s.getDescription());
            check = ps.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            System.err.println("Error: " + e.getMessage());
        }
        return check > 0;
    }
    
    public boolean updateSupportType(SupportType s, int id) throws Exception {
        String updateTableSQL = "UPDATE `hr_system_v2`.`support type` SET `name` = ?, `email` = ?, `status` = ?, `delete` = ?, `incharge group` = ?, `description` = ? WHERE (`id` = ?);";
        int check = 0;
        try (
                Connection con = new DBContext().getConnection();
                PreparedStatement ps = con.prepareStatement(updateTableSQL)) { // user try-with-resources in java
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setBoolean(3, s.isStatus());
            ps.setBoolean(4, s.isDelete());
            ps.setString(5, s.getIn_charge_group());
            ps.setString(6, s.getDescription());
            ps.setInt(7, id);
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
                g.setManager_id(rs.getInt(2));
                g.setName(rs.getString(3));
                g.setStatus(rs.getInt(4));
                g.setDescription(rs.getString(5));
                g.setParent_group_code(rs.getString(6));
                g.setDelete(rs.getInt(7));
                g.setUpdate_date(rs.getString(8));
                vec.add(g);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
       }
        return vec;
    }
    
    public void editStatusDelete(int delete, int id) throws SQLException {
        String sql = "UPDATE `hr_system_v2`.`support type` SET `delete` = ? WHERE (`id` = ?);";
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
    
     public List<SupportType> getAllSpName() throws SQLException {
        List<SupportType> result = new ArrayList<SupportType>();
        try {
            String sql = "SELECT id, fullname FROM hr_system_v2.user;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SupportType u = new SupportType(rs.getInt(1), rs.getString(2));
                result.add(u);
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
}
