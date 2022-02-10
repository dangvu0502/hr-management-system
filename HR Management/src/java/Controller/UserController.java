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

    // <editor-fold defaultstate="collapsed" desc="New User">
    
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
          
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            boolean gender = request.getParameter("gender").equals("male");
            int roleId = Integer.parseInt(request.getParameter("system-role"));
            boolean status = true;
            boolean verified = true;
            //   out.println(groupcode+" "+fullname+" "+username+" "+email+" "+mobile+" "+gender+" "+role_id);
            User user = new User(fullname, username, email, mobile, gender, groupcode, status, verified, roleId);
            // check user email or username existed in database
            if (userDAO.searchUserByUsername(username) != null) {
                request.getSession().setAttribute("usernameErrorMessage", "Username existed");
                response.sendRedirect("../User/NewUser");
            } else if (userDAO.searchUserByEmail(email) != null) {
                request.getSession().setAttribute("emailErrorMessage", "Email existed");
                response.sendRedirect("../User/NewUser");
            } else {
                //check if the email send successfully
                 LocalDateTime now = LocalDateTime.now();
                String message = trippleDes.encrypt(email + " " + now.plusYears(999999).toString());
                if (SendEmail.send(email, "User infor", userInforEmail(user,"http://localhost:8080/HR_Management/Account/NewPassword?"+message))) {
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

    private String userInforEmail(User user, String link)
            throws Exception {
        return // <editor-fold defaultstate="collapsed" desc="HTML email">        
                "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + "table, th, td {\n"
                + "  border: 1px solid black;\n"
                + "   border-collapse: collapse;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "\n"
                + "\n"
                + "<table>\n"
                + "  <tr>\n"
                + "    <th colspan=\"2\">User infor</th>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>Group Code</td>\n"
                + "    <td>"+user.getGroup_code()+" </td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>Full name</td>\n"
                + "    <td>"+user.getFullname()+"  </td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>Username</td>\n"
                + "    <td>"+user.getUsername()+"  </td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>Email</td>\n"
                + "    <td>"+user.getEmail()+"  </td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>Mobile</td>\n"
                + "    <td>"+user.getMobile()+"  </td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>Gender</td>\n"
                + "    <td>"+(user.isGender() == true ? "Male" : "Female")+"  </td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>System Role</td>\n"
                + "    <td>"+user.getRole_id()+"</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td  colspan=\"2\">Click here to set up your password:"+link+"</td>\n"
                + "  </tr>\n"
                + "</table>\n"
                + "\n"
                + "</body>\n"
                + "</html>";

        // </editor-fold>
    }

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
