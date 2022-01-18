/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Context.SendEmail;
import DAO.EmployeeDAO;
import Models.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dangGG
 */
public class UserRegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UserRegisterController</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UserRegisterController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("error") != null) {
                int error = Integer.parseInt(request.getParameter("error"));
                if (error == 1) {
                    request.setAttribute("message", "Username is exist");
                }
                if (error == 2) {
                    request.setAttribute("message", "Email is exist");
                }
            }

            request.getRequestDispatcher("Views/UserRegisterView.jsp").forward(request, response);
        }
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            Employee employee = new Employee(fullname, username, password, email);
            EmployeeDAO eDAO = new EmployeeDAO();
            // check user name or email exist in databse
            // boolean isExist = eDAO.checkEmailExist(email) != null || eDAO.checkUsernameExist(username) != null;
            if (eDAO.checkUsernameExist(username) != null) {
                response.sendRedirect("UserRegister?error=1");
            } else if (eDAO.checkEmailExist(email) != null) {
                response.sendRedirect("UserRegister?error=2");
            } else {
                String code = SendEmail.getRandom();
                String message = "Your code is: " + code;
                //check if the email send successfully
                if (SendEmail.send(email, "Verify Code", message)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("code", code);
                    session.setAttribute("employee", employee);
                    response.sendRedirect("Views/VerifyUserEmailView.jsp");
                } else {
                    out.println("Failed to send verification email");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserRegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
