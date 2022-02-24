/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Context.DBContext;
import Models.Project;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dangGG
 */
public class ProjectDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<String> getAllProjectCode() throws SQLException {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String sql = "SELECT code FROM hr_system_v2.project;";
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
    
    
    public ArrayList<String> getAllProjectCode(String group_code) throws SQLException {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String sql = "SELECT code FROM hr_system_v2.project where group_code = ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, group_code);
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

    public static void main(String[] args) throws SQLException {
        ProjectDAO projectDAO = new ProjectDAO();
        for (String code : projectDAO.getAllProjectCode()) {
            System.out.println(code);
        }
    }

    public int getTotalProject(String query) throws SQLException {
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

    public ArrayList<Project> getProjectList(String query) throws SQLException {
        ArrayList<Project> res = new ArrayList<>();
        try {
            String sql = query;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            while (rs.next()) {
                Project p = new Project(rs.getString(1), rs.getString(2), new User(rs.getInt(3)), rs.getString(4),
                        simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(5))),
                        simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(6))), rs.getString(7), rs.getInt(8), rs.getInt(9));
                res.add(p);
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

    public List<Project> getOne(String code) {
        List<Project> list = new ArrayList<>();
        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "select p.code, p.group_code, u.username, p.project_name, p.start_date, p.end_date, p.description, p.status, p.effort \n"
                    + "from hr_system_v2.project p inner join hr_system_v2.user u \n"
                    + "on p.manager_id = u.id where code = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            while (rs.next()) {
                Project p = new Project(rs.getString(1), rs.getString(2), new User(rs.getString(3)), rs.getString(4),
                        simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(5))),
                        simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(6))), rs.getString(7), rs.getInt(8), rs.getInt(9));
                list.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }

    public void updateProject(String groupCode, int manager, String projectName, String startDate, String endDate, String description, int effort, String code) throws SQLException {
        String sql = "UPDATE hr_system_v2.project SET group_code = ?, manager_id = ?, project_name = ?, start_date = ?, end_date = ?, description = ?, effort = ? WHERE code = ?";
        try (
                Connection con = new DBContext().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, groupCode);
            ps.setInt(2, manager);
            ps.setString(3, projectName);
            ps.setString(4, startDate);
            ps.setString(5, endDate);
            ps.setString(6, description);
            ps.setInt(7, effort);
            ps.setString(8, code);
            // execute update SQL stetement
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void setStatus1(Project project) throws SQLException {
        String sql = "UPDATE `hr_system_v2`.`project` SET `status` = '1' WHERE (`code` = ?)";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, project.getCode());
        ps.executeUpdate();
    }
    public void setStatus0(Project project) throws SQLException {
        String sql = "UPDATE `hr_system_v2`.`project` SET `status` = '0' WHERE (`code` = ?)";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, project.getCode());
        ps.executeUpdate();
    }

    public void updateStatus(int status, String code) throws SQLException{
        String sql = "UPDATE `hr_system_v2`.`project` SET `status` = ? WHERE (`code` = ?)";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, status);
        ps.setString(2, code);
        ps.executeUpdate();
    }
}
