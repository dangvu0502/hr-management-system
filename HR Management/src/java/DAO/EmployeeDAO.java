/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBContext;
import Models.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Egamorft
 */
public class EmployeeDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
  
    public Vector<Employee> getEmployeeList() throws Exception {
        Vector vec = new Vector();
        try {
            String sql = "SELECT * FROM hr_system.employee";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployee_id(rs.getInt(1));
                e.setFullname(rs.getString(2));
                e.setUsername(rs.getString(3));
                e.setPassword(rs.getString(4));
                e.setEmail(rs.getString(5));
                e.setAvatar(rs.getString(6));
                e.setStatus(rs.getInt(7));
                e.setType_id(rs.getInt(8));
                vec.add(e);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return vec;
    }
    
    public Employee checkEmailExist(String email) throws Exception {
        try {
            String sql = "SELECT * FROM hr_system.employee WHERE email = ? ";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployee_id(rs.getInt(1));
                e.setFullname(rs.getString(2));
                e.setUsername(rs.getString(3));
                e.setPassword(rs.getString(4));
                e.setEmail(rs.getString(5));
                e.setAvatar(rs.getString(6));
                e.setStatus(rs.getInt(7));
                e.setType_id(rs.getInt(8));
                return e;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    
    public Employee checkUsernameExist(String username) throws Exception {
        try {
            String sql = "SELECT * FROM hr_system.employee WHERE username = ? ";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployee_id(rs.getInt(1));
                e.setFullname(rs.getString(2));
                e.setUsername(rs.getString(3));
                e.setPassword(rs.getString(4));
                e.setEmail(rs.getString(5));
                e.setAvatar(rs.getString(6));
                e.setStatus(rs.getInt(7));
                e.setType_id(rs.getInt(8));
                return e;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    
    public int addEmployee(Employee employee) throws Exception {
        int rows = 0;
        try {
            String sql = "INSERT INTO `hr_system`.`employee` (`fullname`,`username`,`password`,`email`) VALUES (?,?,?,?)";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, employee.getFullname());
            ps.setString(2, employee.getUsername());
            ps.setString(3, employee.getPassword());
            ps.setString(4, employee.getEmail());
            rows = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rows;
    }
    
    public static void main(String[] args) throws Exception {
         EmployeeDAO eDAO = new EmployeeDAO();
         System.out.println(eDAO.addEmployee(new Employee("test","test","test","test@gmail.com")));
    }
}
