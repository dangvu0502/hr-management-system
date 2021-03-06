/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Context.DBContext;
import Models.Employee;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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
            String sql = "select * FROM hr_system.employee e, hr_system.type t\n"
                    + "Where e.type_id = t.type_id\n"
                    + "limit 5 offset ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 5);
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
                e.setType_name(rs.getString(10));
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

    public void deleteByID(int id) {
        try {
            String sql = "delete from hr_system.employee where employee_id = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Vector<Employee> getEmployeeBySearch(String setting_type, String input) {
        Vector vec = new Vector();
        try {
            String sql = "SELECT * FROM hr_system.employee e, hr_system.type t"
                    + " where " + setting_type + " = ? and e.type_id = t.type_id";
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
                e.setType_name(rs.getString(10));
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

    public boolean addEmployeeFull(Employee employee) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        int check = 0;
        String query = " INSERT INTO hr_system.employee ( fullname, username, password, email, avatar,status,type_id)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
//            ps.setInt(1, employee.getEmployee_id());
            ps.setString(1, employee.getFullname());
            ps.setString(2, employee.getUsername());
            ps.setString(3, employee.getPassword());
            ps.setString(4, employee.getEmail());
            ps.setString(5, employee.getAvatar());
            ps.setInt(6, employee.getStatus());
            ps.setInt(7, employee.getType_id());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return check > 0;
    }

    public boolean updateEmployee(Employee employee, int employee_id) throws Exception {
        String updateTableSQL = "UPDATE hr_system.employee SET fullname= ? ,username= ?, password= ?, email = ?, avatar= ?, status= ? ,type_id= ? WHERE employee_id= ?";
        int check = 0;
        try (
                Connection con = new DBContext().getConnection();
                PreparedStatement ps = con.prepareStatement(updateTableSQL)) { // user try-with-resources in java
            ps.setString(1, employee.getFullname());
            ps.setString(2, employee.getUsername());
            ps.setString(3, employee.getPassword());
            ps.setString(4, employee.getEmail());
            ps.setString(5, employee.getAvatar());
            ps.setInt(6, employee.getStatus());
            ps.setInt(7, employee.getType_id());
            ps.setInt(8, employee_id);
            // execute update SQL stetement
            check = ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return check > 0;

    }

    public static void main(String[] args) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 
        String s1 = now.toString();
        String s2 = now.toString();
        System.out.println(s1);
        System.out.println(s1.equalsIgnoreCase(s2));
    }

    public User login(String username, String password) {

        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM hr_system_v2.user where (username =? or email = ?) and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                User account = new User();
                account.setId(rs.getInt("id"));
                account.setFullname(rs.getString("fullname"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setMobile(rs.getString("mobile"));
                account.setGender(rs.getBoolean("gender"));
                account.setAvatar(rs.getString("avatar"));
                account.setDob(rs.getString("dob"));
                account.setAddress(rs.getString("address"));
                account.setRole_id(rs.getInt("role_id"));
                account.setProject_role_id(rs.getInt("project_role_id"));
                account.setSupervisor_id(rs.getInt("supervisor_id"));
                account.setGroup_code(rs.getString("group_code"));
                account.setStatus(rs.getBoolean("status"));
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

    public User getStatus(int status) {

        try {
            //mo ket noi
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM hr_system_v2.user where status =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            rs = ps.executeQuery();

            while (rs.next()) {
                User account = new User();
                account.setId(rs.getInt("id"));
                account.setFullname(rs.getString("fullname"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setMobile(rs.getString("mobile"));
                account.setGender(rs.getBoolean("gender"));
                account.setAvatar(rs.getString("avatar"));
                account.setDob(rs.getString("dob"));
                account.setAddress(rs.getString("address"));
                account.setRole_id(rs.getInt("role_id"));
                account.setProject_role_id(rs.getInt("project_role_id"));
                account.setSupervisor_id(rs.getInt("supervisor_id"));
                account.setGroup_code(rs.getString("group_code"));
                account.setStatus(rs.getBoolean("status"));
                return account;
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return null;
    }

//    public void changePass(String username, String newpass) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
