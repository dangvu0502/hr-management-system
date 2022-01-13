/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Employee;

/**
 *
 * @author Egamorft
 */
public class EmployeeDAO {
    Connection con;

  
    public Vector<Employee> getEmployeeList() {
        Vector vec = new Vector();
        try {
            String sql = "SELECT * FROM hr_systemdb.employee";
            con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployee_id(rs.getInt(1));
                e.setAccount_id(rs.getInt(2));
                e.setName(rs.getString(3));
                e.setAvatar(rs.getString(4));
                e.setStatus(rs.getBoolean(5));
                e.setJob_id(rs.getInt(6));
                e.setManager_id(rs.getInt(7));
                vec.add(e);
            }
        } catch (Exception e) {
            System.out.println("fbwefbefwndfwegregregrg " + e.getMessage());
        }
        return vec;
    }
}
