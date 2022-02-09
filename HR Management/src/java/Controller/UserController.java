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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dangGG
 */
public class UserController extends HttpServlet {

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
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            String action = request.getPathInfo() == null ? "" : request.getPathInfo();
            String method = request.getMethod();

            switch (action) {
                case "/NewUser":
                    newUser(request, response, method);
                    break;
                default:

                    break;
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void newUser(HttpServletRequest request, HttpServletResponse response, String method)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        if (method.equalsIgnoreCase("get")) {
            showNewUserView(request, response);
        } else {
            newUserImplement(request, response);
        }
    }

    private void newUserImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String groupcode = request.getParameter("group-code");
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String passwordRaw = SendEmail.getRandom();
            String password = trippleDes.encrypt(passwordRaw);
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            boolean gender = request.getParameter("gender").equals("male");
            int roleId = Integer.parseInt(request.getParameter("system-role"));
            boolean status = true;
            boolean verified = true;
            //   out.println(groupcode+" "+fullname+" "+username+" "+email+" "+mobile+" "+gender+" "+role_id);
            // check user email or username existed in database
            if (userDAO.searchUserByEmail(email) != null) {
                request.getSession().setAttribute("emailErrorMessage", "Email existed");
                response.sendRedirect("../User/NewUser");
            } else if (userDAO.searchUserByUsername(username) != null) {
                request.getSession().setAttribute("usernameErrorMessage", "Username existed");
                response.sendRedirect("../User/NewUser");
            } else {
                //check if the email send successfully
                if (SendEmail.send(email, "User infor", passwordRaw)) {
                    userDAO.addNewUser(new User(fullname, username, password, email, mobile, gender, groupcode, status, verified, roleId));
                    request.getSession().setAttribute("successMessage", "Add New User Successfully");
                    response.sendRedirect("../User/NewUser");
                } else {
                    out.println("Failed to send Email");
                }
            }
        }
    }

    private void showNewUserView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("/Views/NewUserView.jsp").forward(request, response);
    }

// </editor-fold>
   
          
}
