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

/**
 *
 * @author quocb
 */
public class GroupDAO {



    public ArrayList<Group> getAll() {
        try {
            ArrayList<Group> list = new ArrayList<>();
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM hr_system_v2.group";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setCode(rs.getString("code"));
                group.setManager_id(rs.getInt("manager_id"));
                group.setName(rs.getString("name"));
                group.setStatus(rs.getBoolean("status"));
                group.setDescription(rs.getString("description"));
                group.setParent_group_code(rs.getString("parent_group_code"));
                group.setDelete(rs.getBoolean("delete"));
                list.add(group);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }


    public List<Group> getAllPaging(int pageIndex, int pageSize) {
            try {
            List<Group> list = new ArrayList<>();
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM hr_system_v2.group LIMIT ?, ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Group group = new Group();
                group.setCode(rs.getString("code"));
                group.setManager_id(rs.getInt("manager_id"));
                group.setName(rs.getString("name"));
                group.setStatus(rs.getBoolean("status"));
                group.setDescription(rs.getString("description"));
                group.setParent_group_code(rs.getString("parent_group_code"));
                group.setDelete(rs.getBoolean("delete"));
                list.add(group);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;    
    }
     public int totalGroup() {
        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT count(*) FROM hr_system_v2.group";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
}
