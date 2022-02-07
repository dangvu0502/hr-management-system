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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        //PrintWriter out = response.getWriter();
        //out.println(action+" "+method);
        try {
            switch (action) {
                case "/Verified":
                    verified(request, response);
                    break;
                case "/Register":
                    register(request, response);
                    break;
                case "/RegisterSuccess":
                    registerSuccess(request, response);
                    break;
                case "/VerifySuccess":
                    verifySuccess(request, response);
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

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            boolean gender = request.getParameter("gender").equals("male") ? true : false;
            String password = trippleDes.encrypt(request.getParameter("password"));
            User user = new User(fullname, username, password, email, mobile, gender);
            // check user email or username existed in database
            if (userDAO.searchUserByEmail(email) != null) {
                request.getSession().setAttribute("message", "Email existed");
                response.sendRedirect("../UserRegister");
            } else if (userDAO.searchUserByUsername(username) != null) {
                request.getSession().setAttribute("message", "Username existed");
                response.sendRedirect("../UserRegister");
            } else {
                String message = trippleDes.encrypt(email + " " + SendEmail.getRandom());
                //check if the email send successfully
                if (SendEmail.send(email, "Verify Link", "http://localhost:8080/HR_Management/UserRegister/Verified?" + message)) {
                    userDAO.addUser(user);
                    response.sendRedirect("../UserRegister/RegisterSuccess");
                } else {
                    out.println("Failed to send verification email");
                }
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void verified(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // PrintWriter out = response.getWriter();
            String key = request.getQueryString();
            String email = trippleDes.decrypt(key).split(" ")[0].trim();
            User user = userDAO.searchUserByEmail(email);
            userDAO.setVerified(user);
            response.sendRedirect("../UserRegister/VerifySuccess");
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void verifySuccess(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("../Views/VerifyUserEmailView.jsp").forward(request, response);
    }

    private void registerSuccess(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("../Views/RegisterSuccessView.jsp").forward(request, response);
    }

    private void view(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("Views/UserRegisterView.jsp").forward(request, response);
    }

}
