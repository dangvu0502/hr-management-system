/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Models.Employee;

/**
 *
 * @author quocb
 */
public class AccountDAO {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public Employee login(String username, String password) {

        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM hr_system.employee where (username =? or email = ?) and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, password);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                Employee account = new Employee();
                account.setEmployee_id(rs.getInt("employee_id"));
                account.setFullname(rs.getString("fullname"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setAvatar(rs.getString("avatar"));
                account.setStatus(rs.getInt("status"));
                account.setType_id(rs.getInt("type_id"));
                if (password.equals(account.getPassword())) {
                    System.out.println("password true");
                    return account;
                }
                System.out.println("password false");
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }
 
}
