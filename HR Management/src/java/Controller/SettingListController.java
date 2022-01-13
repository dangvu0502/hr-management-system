/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Context.DBContext;
import DAO.EmployeeDAO;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;

/**
 *
 * @author Kha Chinh
 */
public class SettingListController {
        DBContext conn;

    public void init() {
        conn = new DBContext();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EmployeeDAO eDAO = new EmployeeDAO();
        Vector<Employee> e = new Vector();
        e = eDAO.getEmployeeList();
        request.setAttribute("listE", e);
        request.getRequestDispatcher("WEB-INF/settingList.jsp").forward(request, response);
    }
}
