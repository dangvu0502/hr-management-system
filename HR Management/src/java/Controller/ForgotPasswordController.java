/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Context.SendEmail;
import Context.TrippleDes;
import DAO.UserDAO;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dangGG
 */
public class ForgotPasswordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private UserDAO userDAO;
    private TrippleDes trippleDes;

    public void init() {
        userDAO = new UserDAO();
        try {
            trippleDes = new TrippleDes();
        } catch (Exception ex) {
            Logger.getLogger(UserRegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getPathInfo() == null ? "" : request.getPathInfo();
//         PrintWriter out = response.getWriter();
//        out.println(action);
        try {
            switch (action) {
                case "/CheckUsernameAndEmail":
                    checkUsernameAndEmail(request, response);
                    break;
                case "/ResetPassword":
                    resetPassword(request, response);
                    break;
                case "/SetNewPassword":
                    setNewPassword(request, response);
                    break;
                case "/NewPassword":
                    newPassword(request, response);
                    break;
                default:
                    view(request, response);
                    break;
            }
        } catch (Exception ex) {
            log(ex.getMessage());
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private void checkUsernameAndEmail(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        User user1 = userDAO.searchUserByEmail(email);
        User user2 = userDAO.searchUserByUsername(username);
        if (user1 == null || user2 == null || !user1.equals(user2)) {
            //HttpSession session = request.getSession();
            request.getSession().setAttribute("message", "Account does not exist");
            response.sendRedirect("../ForgotPassword");
        } else {
            PrintWriter out = response.getWriter();
            LocalDateTime now = LocalDateTime.now();
            String message = trippleDes.encrypt(email + " " + now.toString());
            //check if the email send successfully
            if (SendEmail.send(email, "Password Reset Link", "http://localhost:8080/HR_Management/ForgotPassword/ResetPassword?" + message)) {
                out.println("Send verification email successfully");
            } else {
                out.println("Failed to send verification email");
            }
        }
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {
            LocalDateTime now = LocalDateTime.now();
            PrintWriter out = response.getWriter();
            String encrypt = request.getQueryString();
            String[] decrypt = trippleDes.decrypt(encrypt).split(" ");
            String email = decrypt[0];
            LocalDateTime time = LocalDateTime.parse(decrypt[1]);
            if (time.plusMinutes(30).isAfter(now)) {
                request.getSession().setAttribute("user", userDAO.searchUserByEmail(email));
                response.sendRedirect("../ForgotPassword/NewPassword");
            } else {
                out.println("Expired");
                out.println(time);
                out.println(now);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }
    
    private void newPassword(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("../Views/NewPasswordView.jsp").forward(request, response);
    }

    private void setNewPassword(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        String password = trippleDes.encrypt(request.getParameter("password"));
        request.getSession().removeAttribute("user");
        PrintWriter out = response.getWriter();
        userDAO.setNewPassword(user, password);
        out.println(password+" "+user.getId());
      
    }

    private void view(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("Views/ForgotPasswordView.jsp").forward(request, response);
    }

}
