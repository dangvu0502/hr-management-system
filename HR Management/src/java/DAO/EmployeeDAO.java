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

    public Vector<Employee> getEmployeeList(int page) {
        Vector vec = new Vector();
        try {
            String sql = "select * FROM hr_system.employee\n"
                    + "LIMIT 3 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page-1)*3);
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

    public void editStatus(int status, int employee_id) throws SQLException {
        String sql = "UPDATE `hr_system`.`employee` SET `status` = ? WHERE (`employee_id` = ?)";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        if (status == 0) {
            ps.setInt(1, 1);
        } else {
            ps.setInt(1, 0);
        }
        ps.setInt(2, employee_id);
        ps.executeUpdate();
    }

    public Vector<Employee> getEmployeeBySearch(String setting_type, String input) {
        Vector vec = new Vector();
        try {
            String sql = "SELECT * FROM hr_system.employee where " + setting_type + " = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, input);
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

    public int getTotalEmployee() {
        int total = 0;
        try {
            String sql = "select count(*) FROM hr_system.employee";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
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
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            ps.setString(1, employee.getFullname());
            ps.setString(2, employee.getUsername());
            ps.setString(3, employee.getPassword());
            ps.setString(4, employee.getEmail());
            rows = ps.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            System.err.println("Error: " + e.getMessage());
        }
        return rows;
    }

    public static void main(String[] args) throws Exception {
        EmployeeDAO eDAO = new EmployeeDAO();
//         System.out.println(eDAO.addEmployee(new Employee("test","test","test","test@gmail.com")));
//        Vector<Employee> e = eDAO.getEmployeeList();
//        for (Employee s : e) {
//            System.out.println(s.getFullname());
//        }
    }

    public void UpdateProfile(String fullname, String avatar, String username) {
        try {
            //mo ket noi
            String sql = "update hr_system.employee set fullname = ?, avatar = ? where username =?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, avatar);
            ps.setString(3, username);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

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
    public Employee getStatus(int status) {

        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM hr_system.employee where status =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
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
                return account;
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }
    
    public void changePass(String username, String newpass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
