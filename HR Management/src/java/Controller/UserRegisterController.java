/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Context.SendEmail;
import Context.TrippleDes;
import DAO.EmployeeDAO;
import DAO.UserDAO;
import Models.User;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
      
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
            UserDAO userDAO = new UserDAO();
            TrippleDes td = new TrippleDes();
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            boolean gender = request.getParameter("gender").equals("male") ? true : false;
            String password = td.encrypt(request.getParameter("password"));
            User user = new User(fullname, password, email, mobile, gender);
            boolean isExist = userDAO.checkEmailExist(email) != null ;
            log(isExist+" ");
            // check user name or email exist in databse
            // boolean isExist = eDAO.checkEmailExist(email) != null || eDAO.checkUsernameExist(username) != null;
            if (userDAO.checkEmailExist(email) != null) {
                response.sendRedirect("UserRegister?error=2");
            } else {
                String code = SendEmail.getRandom();
                String message = "Your code is: " + code;
                //check if the email send successfully
                if (SendEmail.send(email, "Verify Code", message)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("code", code);
                    session.setAttribute("user", user);
                    response.sendRedirect("Views/VerifyUserEmailView.jsp");
                } else {
                    out.println("Failed to send verification email");
                }
            }
        } catch (Exception ex) {
            log(ex.getMessage());
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
