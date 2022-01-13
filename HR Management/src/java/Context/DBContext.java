/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Egamorft
 */
public class DBContext {
    Connection conn;
    public Connection getConnection() throws Exception{
        String url = "jdbc:mysql://localhost:3306/hr_system";
        String user = "root";
        String password = "dang";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
    //check Connection
    public static void main(String[] args) {
        DBContext dBContext = new DBContext();
        try {
            System.out.println("Thanh Cong");
            System.out.println(dBContext.getConnection());
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
