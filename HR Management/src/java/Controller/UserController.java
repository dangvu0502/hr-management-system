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
import javax.servlet.http.HttpSession;

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
                case "/EditProfile":
                    editprofile(request, response, method);
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
                    userDAO.addNewUser(user);
                    request.getSession().setAttribute("successMessage", "Add New User Successfully");
                    response.sendRedirect("../User/NewUser");
//                out.println(userInforEmail(user));
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
        return // <editor-fold defaultstate="collapsed" desc="HTML email">        
                "\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\n"
                + "        <script src=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\"></script>\n"
                + "        <script src=\"//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n"
                + "        <style>\n"
                + "            * {\n"
                + "                box-sizing: border-box;\n"
                + "            }\n"
                + "\n"
                + "            /* Style inputs */\n"
                + "            input[type=text], select, textarea {\n"
                + "                width: 100%;\n"
                + "                padding: 12px;\n"
                + "                border: 1px solid #ccc;\n"
                + "                margin-top: 6px;\n"
                + "                margin-bottom: 16px;\n"
                + "                resize: vertical;\n"
                + "            }\n"
                + "\n"
                + "            input[type=submit] {\n"
                + "                background-color: #23b7e5;\n"
                + "                color: white;\n"
                + "                padding: 12px 20px;\n"
                + "                border: none;\n"
                + "                cursor: pointer;\n"
                + "            }\n"
                + "\n"
                + "            input[type=submit]:hover {\n"
                + "                background-color: #23b7e5;\n"
                + "            }\n"
                + "\n"
                + "            /* Style the container/contact section */\n"
                + "            .container {\n"
                + "                border-radius: 5px;\n"
                + "                background-color: #f2f2f2;\n"
                + "                padding: 10px;\n"
                + "            }\n"
                + "\n"
                + "            /* Create two columns that float next to eachother */\n"
                + "            .column {\n"
                + "                float: left;\n"
                + "                width: 50%;\n"
                + "                margin-top: 6px;\n"
                + "                padding: 20px;\n"
                + "            }\n"
                + "\n"
                + "            .column-hidden {\n"
                + "                float: left;\n"
                + "                width: 25%;\n"
                + "                margin-top: 6px;\n"
                + "                padding: 20px;\n"
                + "            }\n"
                + "\n"
                + "            /* Clear floats after the columns */\n"
                + "            .row:after {\n"
                + "                content: \"\";\n"
                + "                display: table;\n"
                + "                clear: both;\n"
                + "            }\n"
                + "\n"
                + "\n"
                + "            .jander2{\n"
                + "                font-weight: bold;\n"
                + "            }\n"
                + "\n"
                + "            /* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */\n"
                + "            @media screen and (max-width: 600px) {\n"
                + "                .column, input[type=submit] {\n"
                + "                    width: 100%;\n"
                + "                    margin-top: 0;\n"
                + "                }\n"
                + "            }\n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body class=\"bg-light\">\n"
                + "        <div class=\"container\">\n"
                + "            <div style=\"text-align:center\">\n"
                + "                <h2>User information</h2>\n"
                + "            </div>\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"column-hidden\">\n"
                + "                </div>\n"
                + "                <div class=\"column\">\n"
                + "                    <label for=\"group-code\">Group Code</label>\n"
                + "                    <input class=\"jander2\" type=\"text\" id=\"group-code\" value=\"" + user.getGroup_code() + "\" disabled=\"\">\n" + "<br>"
                + "                    <label for=\"fullname\">Full Name</label>\n"
                + "                    <input class=\"jander2\"  type=\"text\" id=\"fullname\" name=\"fullname\" value=\"" + user.getFullname() + "\" disabled=\"\">\n" + "<br>"
                + "                    <label for=\"username\">User Name</label>\n"
                + "                    <input class=\"jander2\" type=\"text\" id=\"username\" name=\"username\" value=\"" + user.getUsername() + "\" disabled=\"\">\n" + "<br>"
                + "                    <label for=\"email\">Email</label>\n"
                + "                    <input class=\"jander2\" type=\"text\" id=\"email\" name=\"email\" value=\"" + user.getEmail() + "\" disabled=\"\">\n" + "<br>"
                + "                    <label for=\"mobile\">Mobile</label>\n"
                + "                    <input class=\"jander2\" type=\"text\" id=\"mobile\" name=\"mobile\" value=\"" + user.getMobile() + "\" disabled=\"\">\n" + "<br>"
                + "                    <label for=\"gender\">Gender</label>\n"
                + "                    <input  class=\"jander2\" type=\"text\" id=\"gender\" name=\"gender\" value=\"" + (user.isGender() == true ? "Male" : "Female") + "\" disabled=\"\">\n" + "<br>"
                + "                    <label for=\"system-role\">System Role</label>\n"
                + "                    <input class=\"jander2\" type=\"text\" id=\"system-role\" name=\"system-role\" value=\"" + user.getRole_id() + "\" disabled=\"\">\n" + "<br>"
                + "                    <label for=\"password\">Password</label>\n"
                + "                    <input class=\"jander2\" type=\"text\" id=\"password\" name=\"password\" value=\"" + user.getPassword() + "\" disabled=\"\">\n" + "<br>"
                + "                    <div >\n"
                + "                        <input  type=\"submit\" value=\"Click here to change password\">\n"
                + "                    </div>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>\n"
                + "\n"
                + "";

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

    private void editprofile(HttpServletRequest request, HttpServletResponse response, String method) {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                editProfileImplement(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void editProfileImplement(HttpServletRequest request, HttpServletResponse response) {
        try {
            /* TODO output your page here. You may use following sample code. */

            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            UserDAO dao = new UserDAO();

            String fullname = request.getParameter("fullname");
            String dob = request.getParameter("dob");
            String address = request.getParameter("address");
            Boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
//            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String avatar = request.getParameter("fileName");
            String username = request.getParameter("username");
            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("account"); 

                user.setFullname(fullname);
                user.setAddress(address);
                user.setDob(dob);
                user.setGender(gender);
                user.setMobile(mobile);
                session.setAttribute("account", user);
                if (avatar.isEmpty()) {
                    dao.UpdateProfileAvtNull(fullname, mobile, gender, dob, address, username);
                } else {
                    user.setAvatar(avatar);
                    dao.UpdateProfile(fullname, avatar, mobile, gender, dob, address, username);
                }
//                request.setAttribute("error", "success");
                response.sendRedirect("../Views/Home.jsp");

//                response.sendRedirect("EditProfile");
        } catch (Exception ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
