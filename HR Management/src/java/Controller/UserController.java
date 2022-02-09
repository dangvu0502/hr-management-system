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
                    out.println(pageNotFound);
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
            User user = new User(fullname, username, password, email, mobile, gender, groupcode, status, verified, roleId);
            // check user email or username existed in database
           if (userDAO.searchUserByUsername(username) != null) {
                request.getSession().setAttribute("usernameErrorMessage", "Username existed");
                response.sendRedirect("../User/NewUser");
            } else if (userDAO.searchUserByEmail(email) != null) {
                request.getSession().setAttribute("emailErrorMessage", "Email existed");
                response.sendRedirect("../User/NewUser");
            } else {
                //check if the email send successfully
                if (SendEmail.send(email, "User infor", userInforEmail(user))) {
//                    userDAO.addNewUser(user);
//                    request.getSession().setAttribute("successMessage", "Add New User Successfully");
//                    response.sendRedirect("../User/NewUser");
                out.println(userInforEmail(user));
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
    
    private String userInforEmail(User user)
            throws Exception {
        return  // <editor-fold defaultstate="collapsed" desc="HTML email">        
                "\n" +
"<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\n" +
"        <script src=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\"></script>\n" +
"        <script src=\"//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" +
"        <style>\n" +
"            body {\n" +
"                background-color: #e9ecef;\n" +
"            }\n" +
"\n" +
"            .mainbox {\n" +
"                background-color: #e9ecef;\n" +
"                margin: auto;\n" +
"                height: 600px;\n" +
"                width: 600px;\n" +
"                position: relative;\n" +
"            }\n" +
"\n" +
"            .err {\n" +
"                color: #ffffff;\n" +
"                font-family: 'Nunito Sans', sans-serif;\n" +
"                font-size: 11rem;\n" +
"                position:absolute;\n" +
"                left: 20%;\n" +
"                top: 8%;\n" +
"            }\n" +
"\n" +
"            .far {\n" +
"                position: absolute;\n" +
"                font-size: 8.5rem;\n" +
"                left: 42%;\n" +
"                top: 15%;\n" +
"                color: #ffffff;\n" +
"            }\n" +
"\n" +
"            .err2 {\n" +
"                color: #ffffff;\n" +
"                font-family: 'Nunito Sans', sans-serif;\n" +
"                font-size: 11rem;\n" +
"                position:absolute;\n" +
"                left: 68%;\n" +
"                top: 8%;\n" +
"            }\n" +
"\n" +
"            .msg {\n" +
"                text-align: center;\n" +
"                font-family: 'Nunito Sans', sans-serif;\n" +
"                font-size: 1.6rem;\n" +
"                position:absolute;\n" +
"                left: 16%;\n" +
"                top: 45%;\n" +
"                width: 75%;\n" +
"            }\n" +
"\n" +
"            a {\n" +
"                text-decoration: none;\n" +
"                color: white;\n" +
"            }\n" +
"\n" +
"            a:hover {\n" +
"                text-decoration: underline;\n" +
"            }\n" +
"\n" +
"        </style>\n" +
"    </head>\n" +
"    <body>\n" +
"        <div class=\"container\">\n" +
"            <div class=\"main-body\">\n" +
"\n" +
"                <!-- Breadcrumb -->\n" +
"                <nav aria-label=\"breadcrumb\" class=\"main-breadcrumb\">\n" +
"                    <ol class=\"breadcrumb\">\n" +
"                        <li></li>\n" +
"                        <li></li>\n" +
"                        <li> </li>\n" +
"                    </ol>\n" +
"                </nav>\n" +
"                <!-- /Breadcrumb -->\n" +
"\n" +
"                <div class=\"row gutters-sm\">\n" +
"                    <div class=\"col-md-2 mb-3\">\n" +
"\n" +
"\n" +
"                    </div>\n" +
"                    <div class=\"col-md-8\">\n" +
"                        <div class=\"card mb-3\">\n" +
"                            <div class=\"card-body\">\n" +
"                                <div class=\"row\">\n" +
"                                    <div class=\"col-sm-3\">\n" +
"                                        <h6 class=\"mb-0\">Group Code</h6>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-sm-9 text-secondary\">\n" +
"\n" +user.getGroup_code() +
"                                    </div>\n" +
"                                </div>\n" +
"                                <hr>\n" +
"                                <div class=\"row\">\n" +
"                                    <div class=\"col-sm-3\">\n" +
"                                        <h6 class=\"mb-0\">Full Name</h6>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-sm-9 text-secondary\">\n" +
"\n" + user.getFullname() +
"                                    </div>\n" +
"                                </div>\n" +
"                                <hr>\n" +
"                                <div class=\"row\">\n" +
"                                    <div class=\"col-sm-3\">\n" +
"                                        <h6 class=\"mb-0\">Username</h6>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-sm-9 text-secondary\">\n" +
"\n" + user.getUsername() +
"                                    </div>\n" +
"                                </div>\n" +
"                                <hr>\n" +
"                                <div class=\"row\">\n" +
"                                    <div class=\"col-sm-3\">\n" +
"                                        <h6 class=\"mb-0\">Email</h6>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-sm-9 text-secondary\">\n" +
"\n" + user.getEmail() +
"                                    </div>\n" +
"                                </div>\n" +
"                                <hr>\n" +
"                                <div class=\"row\">\n" +
"                                    <div class=\"col-sm-3\">\n" +
"                                        <h6 class=\"mb-0\">Mobile</h6>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-sm-9 text-secondary\">\n" +
"\n" + user.getMobile() +
"                                    </div>\n" +
"                                </div>\n" +
"                                <hr>\n" +
"                                <div class=\"row\">\n" +
"                                    <div class=\"col-sm-3\">\n" +
"                                        <h6 class=\"mb-0\">Gender</h6>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-sm-9 text-secondary\">\n" +
"\n" + (user.isGender() == true ? "Male" : "Female") +
"                                    </div>\n" +
"                                </div>\n" +
"                                <hr>\n" +
"                                <div class=\"row\">\n" +
"                                    <div class=\"col-sm-3\">\n" +
"                                        <h6 class=\"mb-0\">System role</h6>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-sm-9 text-secondary\">\n" +
"\n" + user.getRole_id() + 
"                                    </div>\n" +
"                                </div>\n" +
"                                <hr>\n" +
"                                <div class=\"row\">\n" +
"                                    <div class=\"col-sm-3\">\n" +
"                                        <h6 class=\"mb-0\">Password</h6>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-sm-9 text-secondary\">\n" +
"\n" + user.getPassword() +
"                                    </div>\n" +
"                                </div>\n" +
"                                <hr>\n" +
"                                <div class=\"row\">\n" +
"                                    <div class=\"col-sm-12 text-center\">\n" +
"                                        <a class=\"btn btn-info \" target=\"__blank\" href=\"\">Click here to change password</a>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                </div>\n" +
"\n" +
"            </div>\n" +
"        </div>\n" +
"    </body>\n" +
"</html>\n" +
"\n" +
"";
        // </editor-fold>
    }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="HTML">
    
    // <editor-fold defaultstate="collapsed" desc="pageNotFound">
    private String pageNotFound = "\n"
            + "<!DOCTYPE html>\n"
            + "<html>\n"
            + "    <head>\n"
            + "        <link href=\"https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@600;900&display=swap\" rel=\"stylesheet\">\n"
            + "        <script src=\"https://kit.fontawesome.com/4b9ba14b0f.js\" crossorigin=\"anonymous\"></script>\n"
            + "        <style>\n"
            + "            body {\n"
            + "                background-color: #95c2de;\n"
            + "            }\n"
            + "\n"
            + "            .mainbox {\n"
            + "                background-color: #95c2de;\n"
            + "                margin: auto;\n"
            + "                height: 600px;\n"
            + "                width: 600px;\n"
            + "                position: relative;\n"
            + "            }\n"
            + "\n"
            + "            .err {\n"
            + "                color: #ffffff;\n"
            + "                font-family: 'Nunito Sans', sans-serif;\n"
            + "                font-size: 11rem;\n"
            + "                position:absolute;\n"
            + "                left: 20%;\n"
            + "                top: 8%;\n"
            + "            }\n"
            + "\n"
            + "            .far {\n"
            + "                position: absolute;\n"
            + "                font-size: 8.5rem;\n"
            + "                left: 42%;\n"
            + "                top: 15%;\n"
            + "                color: #ffffff;\n"
            + "            }\n"
            + "\n"
            + "            .err2 {\n"
            + "                color: #ffffff;\n"
            + "                font-family: 'Nunito Sans', sans-serif;\n"
            + "                font-size: 11rem;\n"
            + "                position:absolute;\n"
            + "                left: 68%;\n"
            + "                top: 8%;\n"
            + "            }\n"
            + "\n"
            + "            .msg {\n"
            + "                text-align: center;\n"
            + "                font-family: 'Nunito Sans', sans-serif;\n"
            + "                font-size: 1.6rem;\n"
            + "                position:absolute;\n"
            + "                left: 16%;\n"
            + "                top: 45%;\n"
            + "                width: 75%;\n"
            + "            }\n"
            + "\n"
            + "            a {\n"
            + "                text-decoration: none;\n"
            + "                color: white;\n"
            + "            }\n"
            + "\n"
            + "            a:hover {\n"
            + "                text-decoration: underline;\n"
            + "            }\n"
            + "\n"
            + "        </style>\n"
            + "    </head>\n"
            + "    <body>\n"
            + "        <div class=\"mainbox\">\n"
            + "            <div class=\"err\">4</div>\n"
            + "            <i class=\"far fa-question-circle fa-spin\"></i>\n"
            + "            <div class=\"err2\">4</div>\n"
            + "            <div class=\"msg\">Maybe this page moved? Got deleted? Is hiding out in quarantine? Never existed in the first place?<p>Let's go <a href=\"#\">home</a> and try from there.</p></div>\n"
            + "        </div>\n"
            + "    </body>\n"
            + "</html>\n"
            + "\n"
            + "";
    //</editor-fold>
   

    // </editor-fold>
}
