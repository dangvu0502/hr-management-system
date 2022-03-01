/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Egamorft
 */
public class DBContext {

    private static String jdbcURL = "jdbc:mysql://localhost:3306/hr_system_v2?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "123456";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    //check Connection
//    public static void main(String[] args) {
//        DBContext dBContext = new DBContext();
//        try {
//            System.out.println("Thanh Cong");
//            System.out.println(dBContext.getConnection());
//        } catch (Exception ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static String MyFormatDate(String date) throws ParseException {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
    }

    public static void main(String[] args) throws ParseException {

        System.out.println(MyFormatDate("2001-01-1 00:00:00"));
    }

}
