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
        try (PrintWriter out = response.getWriter();) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            User user1 = userDAO.searchUserByEmail(email);
            User user2 = userDAO.searchUserByUsername(username);
            if (user1 == null || user2 == null || !user1.equals(user2)) {
                //HttpSession session = request.getSession();
                request.getSession().setAttribute("message", "Account does not exist");
                response.sendRedirect("../ForgotPassword");
            } else {
                LocalDateTime now = LocalDateTime.now();
                String message = trippleDes.encrypt(email + " " + now.toString());
                //check if the email send successfully
                if (SendEmail.send(email, "Password Reset Link", "http://localhost:8080/HR_Management/ForgotPassword/ResetPassword?" + message)) {
                    out.println(sendEmaiSuccessfully);
                } else {
                    out.println("Failed to send verification email");
                }
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
        try (PrintWriter out = response.getWriter();) {
            User user = (User) request.getSession().getAttribute("user");
            String password = trippleDes.encrypt(request.getParameter("password"));
            request.getSession().removeAttribute("user");
            userDAO.setNewPassword(user, password);
            out.println(changePasswordSuccessfully);
        }

    }

    private void view(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("Views/ForgotPasswordView.jsp").forward(request, response);
    }

    private String sendEmaiSuccessfully
            = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "    <head>\n"
            + "        <meta charset=\"UTF-8\">\n"
            + "        <title>Verify Email</title>\n"
            + "        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>\n"
            + "        <meta name=\"description\" content=\"Developed By M Abdur Rokib Promy\">\n"
            + "        <meta name=\"keywords\" content=\"Admin, Bootstrap 3, Template, Theme, Responsive\">\n"
            + "        <!-- bootstrap 3.0.2 -->\n"
            + "        <link href=\"../css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n"
            + "        <!-- font Awesome -->\n"
            + "        <link href=\"../css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n"
            + "        <!-- Ionicons -->\n"
            + "        <link href=\"../css/ionicons.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n"
            + "\n"
            + "        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>\n"
            + "        <!-- Theme style -->\n"
            + "        <link href=\"../css/style.css\" rel=\"stylesheet\" type=\"text/css\" />\n"
            + "\n"
            + "\n"
            + "        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\n"
            + "        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n"
            + "        <!--[if lt IE 9]>\n"
            + "          <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\n"
            + "          <script src=\"https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js\"></script>\n"
            + "        <![endif]-->\n"
            + "    </head>\n"
            + "    <body class=\"skin-black\">\n"
            + "        <div class=\"wrapper row-offcanvas row-offcanvas-left\">\n"
            + "\n"
            + "            <!-- Main content -->\n"
            + "            <section class=\"content\">\n"
            + "                <div class=\"row\">\n"
            + "                    <div  class=\"col-lg-3\"></div>\n"
            + "                    <div class=\"col-lg-6  \">\n"
            + "                        <section class=\"panel\">\n"
            + "                            <div class=\"wrapper text-center \">\n"
            + "                                <header class=\"panel-heading text-center text-black\">\n"
            + "                                    Well done\n"
            + "                                </header>\n"
            + "                                <p>Please check your email, we already send reset password link to you</p>\n"
            + "                                <a href=\"../login\" class=\"btn btn-primary btn-group-lg active\" role=\"button\" aria-pressed=\"true\">Back to login</a>\n"
            + "                            </div>\n"
            + "                        </section>\n"
            + "                    </div>\n"
            + "                </div>\n"
            + "            </section>\n"
            + "\n"
            + "        </div>\n"
            + "\n"
            + "    </body>\n"
            + "    <!-- jQuery 2.0.2 -->\n"
            + "    <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js\"></script>\n"
            + "    <script src=\"js/jquery.min.js\" type=\"text/javascript\"></script>\n"
            + "\n"
            + "\n"
            + "\n"
            + "    <script>\n"
            + "\n"
            + "    \n"
            + "  \n"
            + "    </script>\n"
            + "</html>";

    private String changePasswordSuccessfully
            = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "    <head>\n"
            + "        <meta charset=\"UTF-8\">\n"
            + "        <title>Verify Email</title>\n"
            + "        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>\n"
            + "        <meta name=\"description\" content=\"Developed By M Abdur Rokib Promy\">\n"
            + "        <meta name=\"keywords\" content=\"Admin, Bootstrap 3, Template, Theme, Responsive\">\n"
            + "        <!-- bootstrap 3.0.2 -->\n"
            + "        <link href=\"../css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n"
            + "        <!-- font Awesome -->\n"
            + "        <link href=\"../css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n"
            + "        <!-- Ionicons -->\n"
            + "        <link href=\"../css/ionicons.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n"
            + "\n"
            + "        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>\n"
            + "        <!-- Theme style -->\n"
            + "        <link href=\"../css/style.css\" rel=\"stylesheet\" type=\"text/css\" />\n"
            + "\n"
            + "\n"
            + "        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\n"
            + "        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n"
            + "        <!--[if lt IE 9]>\n"
            + "          <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\n"
            + "          <script src=\"https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js\"></script>\n"
            + "        <![endif]-->\n"
            + "    </head>\n"
            + "    <body class=\"skin-black\">\n"
            + "        <div class=\"wrapper row-offcanvas row-offcanvas-left\">\n"
            + "\n"
            + "            <!-- Main content -->\n"
            + "            <section class=\"content\">\n"
            + "                <div class=\"row\">\n"
            + "                    <div  class=\"col-lg-3\"></div>\n"
            + "                    <div class=\"col-lg-6  \">\n"
            + "                        <section class=\"panel\">\n"
            + "                            <div class=\"wrapper text-center \">\n"
            + "                                <header class=\"panel-heading text-center text-black\">\n"
            + "                                    Well done\n"
            + "                                </header>\n"
            + "                                <p>Now you can login with new password</p>\n"
            + "                                <a href=\"../login\" class=\"btn btn-primary btn-group-lg active\" role=\"button\" aria-pressed=\"true\">Back to login</a>\n"
            + "                            </div>\n"
            + "                        </section>\n"
            + "                    </div>\n"
            + "                </div>\n"
            + "            </section>\n"
            + "\n"
            + "        </div>\n"
            + "\n"
            + "    </body>\n"
            + "    <!-- jQuery 2.0.2 -->\n"
            + "    <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js\"></script>\n"
            + "    <script src=\"js/jquery.min.js\" type=\"text/javascript\"></script>\n"
            + "\n"
            + "\n"
            + "\n"
            + "    <script>\n"
            + "\n"
            + "    \n"
            + "  \n"
            + "    </script>\n"
            + "</html>";

}
