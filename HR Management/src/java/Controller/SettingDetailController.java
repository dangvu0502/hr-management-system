/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.EmployeeDAO;
import Models.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hide
 */
@WebServlet(name = "SettingDetailController", urlPatterns = {"/SettingDetailController"})
public class SettingDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String typef = request.getParameter("typef");
            String idIP = request.getParameter("id");
            String statusIP = request.getParameter("status");
            String typenameIP = request.getParameter("typename");
            if (typef.equals("add")) {
//                int id = Integer.parseInt(idIP);
                String userName = request.getParameter("username");
                String fullName = request.getParameter("fullname");
                String passWord = request.getParameter("password");
                String email = request.getParameter("mail");
                String avatar = "https://www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png";
                int status = Integer.parseInt(statusIP);
                int typename = Integer.parseInt(typenameIP);
                EmployeeDAO eDAO = new EmployeeDAO();
                Employee employee = new Employee(fullName, userName, passWord, email, avatar, status, typename);
                try {
                    eDAO.addEmployeeFull(employee);
                    String setting_type = request.getParameter("type");
                    String input = request.getParameter("input");
                    String page = request.getParameter("page");
                    if (page == null) {
                        page = "1";
                    }
                    request.setAttribute("page", page);
                    int count = eDAO.getTotalEmployee();
                    int endPage = count / 5;
                    if (endPage % 5 != 0) {
                        endPage++;
                    }
                    request.setAttribute("endP", endPage);
                    Vector<Employee> e = new Vector();
                    if (setting_type == null || input == null) {
                        e = eDAO.getEmployeeList(Integer.parseInt(page));
                    } else {
                        e = eDAO.getEmployeeBySearch(setting_type, input);
                    }
                    request.setAttribute("listE", e);
                    request.getRequestDispatcher("Views/SettingList.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            } else if (typef.equals("edit")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String userName = request.getParameter("username");
                String fullName = request.getParameter("fullname");
                String passWord = request.getParameter("password");
                String email = request.getParameter("mail");
                String avatar = "https://www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png";
                int status = Integer.parseInt(statusIP);
                int typename = Integer.parseInt(typenameIP);
                EmployeeDAO eDAO = new EmployeeDAO();
                Employee employee = new Employee(id, fullName, userName, passWord, email, avatar, status, typename);
                try {
                    eDAO.updateEmployee(employee, id);
                    String setting_type = request.getParameter("type");
                    String input = request.getParameter("input");
                    String page = request.getParameter("page");
                    if (page == null) {
                        page = "1";
                    }
                    request.setAttribute("page", page);
                    int count = eDAO.getTotalEmployee();
                    int endPage = count / 5;
                    if (endPage % 5 != 0) {
                        endPage++;
                    }
                    request.setAttribute("endP", endPage);
                    Vector<Employee> e = new Vector();
                    if (setting_type == null || input == null) {
                        e = eDAO.getEmployeeList(Integer.parseInt(page));
                    } else {
                        e = eDAO.getEmployeeBySearch(setting_type, input);
                    }
                    request.setAttribute("listE", e);
                    request.getRequestDispatcher("Views/SettingList.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            } else {
                int id = Integer.parseInt(request.getParameter("id"));
                EmployeeDAO eDAO = new EmployeeDAO();
                try {
                    eDAO.deleteByID(id);
                    String setting_type = request.getParameter("type");
                    String input = request.getParameter("input");
                    String page = request.getParameter("page");
                    if (page == null) {
                        page = "1";
                    }
                    request.setAttribute("page", page);
                    
                    int count = eDAO.getTotalEmployee();
                    int endPage = count / 5;
                    if (endPage % 5 != 0) {
                        endPage++;
                    }
                    request.setAttribute("endP", endPage);
                    Vector<Employee> e = new Vector();
                    if (setting_type == null || input == null) {
                        e = eDAO.getEmployeeList(Integer.parseInt(page));
                    } else {
                        e = eDAO.getEmployeeBySearch(setting_type, input);
                    }
                    request.setAttribute("listE", e);
                    request.getRequestDispatcher("Views/SettingList.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
