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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
public class AccountController extends HttpServlet {

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
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            String action = request.getPathInfo() == null ? "" : request.getPathInfo();
            String method = request.getMethod();
            switch (action) {
                case "/Login":
                    login(request, response, method);
                    break;
                case "/Register":
                    register(request, response, method);
                    break;
                case "/RegisterVerify":
                    setVerified(request, response);
                    break;
                case "/ForgotPassword":
                    forgotPassword(request, response, method);
                    break;
                case "/NewPassword":
                    newPassword(request, response, method);
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

    // <editor-fold defaultstate="collapsed" desc="Login">
    private void login(HttpServletRequest request, HttpServletResponse response, String method)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                loginImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                showLoginView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void loginImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String username = request.getParameter("username");
            String password = trippleDes.encrypt(request.getParameter("password"));
            String verifyMessage = (String) request.getAttribute("verifyMessage");
            String warning = null;

            User account = new UserDAO().login(username, password);
            if (account == null && verifyMessage == null) {
                request.setAttribute("err", "Login failed");
                request.getRequestDispatcher("../Views/Login.jsp").forward(request, response);
            } else if (account == null && verifyMessage != null) {
                request.setAttribute("verifyMessage", verifyMessage);
                request.getRequestDispatcher("../Views/Login.jsp").forward(request, response);
            } else if (account != null) {
                if (!account.isStatus()) {
                    request.setAttribute("err", "You do not have access to this website");
                    request.getRequestDispatcher("../Views/Login.jsp").forward(request, response);
                } else {
                    HttpSession session = request.getSession();
                    account.setPassword(trippleDes.decrypt(account.getPassword()));
                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(account.getDob());
                    String date = simpleDateFormat.format(date1);
                    account.setDob(date);
                    session.setAttribute("account", account);
                    request.getSession().setAttribute("account", account); //lưu trên ss
                    response.sendRedirect("../Views/Home.jsp");
                }
            }
        }
    }

    private void showLoginView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        //out.println(request.getContextPath());
        request.getRequestDispatcher("/Views/Login.jsp").forward(request, response);
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Register and RegisterVerify">
    private void setVerified(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            String key = request.getQueryString();
            String email = trippleDes.decrypt(key).split(" ")[0].trim();
            User user = userDAO.searchUserByEmail(email);
            userDAO.setVerified(user);
            out.println(verifySuccess);
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response, String method)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                registerImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                showRegisterView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void registerImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            boolean gender = request.getParameter("gender").equals("male");
            String password = trippleDes.encrypt(request.getParameter("password"));
            User account = new User(fullname, username, password, email, mobile, gender);
            // check user email or username existed in database
            if (userDAO.searchUserByUsername(username) != null) {
                request.getSession().setAttribute("usernameErrorMessage", "Username existed");
                response.sendRedirect("../Account/Register");
            } else if (userDAO.searchUserByEmail(email) != null) {
                request.getSession().setAttribute("emailErrorMessage", "Email existed");
                response.sendRedirect("../Account/Register");
            } else {
                String message = trippleDes.encrypt(email + " " + SendEmail.getRandom());
                //check if the email send successfully
                if (SendEmail.send(email, "Verify Link", "http://localhost:8080/HR_Management/Account/RegisterVerify?" + message)) {
                    userDAO.addNewAccount(account);
                    out.println(registerSuccess);
                } else {
                    out.println("Failed to send verification email");
                }
            }
        }
    }

    private void showRegisterView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        //out.println(request.getContextPath());
        request.getRequestDispatcher("/Views/UserRegisterView.jsp").forward(request, response);
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Forgot Password and New Password">
    private void newPassword(HttpServletRequest request, HttpServletResponse response, String method)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                newPasswordImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                String encrypt = request.getQueryString();
                String[] decrypt = trippleDes.decrypt(encrypt).split(" ");
                String email = decrypt[0];
                LocalDateTime time = LocalDateTime.parse(decrypt[1]);
                String type = decrypt[2];
                User user = userOnTime(request, response, email, time);
                if (user != null) {
                   if (type.equalsIgnoreCase("ForgotPassword") || user.getPassword() == null && type.equalsIgnoreCase("NewUser")){
                        request.getSession().setAttribute("user", user);
                        showNewPasswordView(request, response);
                   }else{
                        out.println("Something went wrong!!!");
                   }    
                } else {
                    out.println(linkExpired);
                }
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private User userOnTime(HttpServletRequest request, HttpServletResponse response, String email, LocalDateTime time)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {
            LocalDateTime now = LocalDateTime.now();
            if (time.plusMinutes(30).isAfter(now)) {
                return userDAO.searchUserByEmail(email);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
        return null;
    }

    private void newPasswordImplement(HttpServletRequest request, HttpServletResponse response)
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

    private void showNewPasswordView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        //out.println(request.getContextPath());
        request.getRequestDispatcher("/Views/NewPasswordView.jsp").forward(request, response);
    }

    private void forgotPassword(HttpServletRequest request, HttpServletResponse response, String method)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                forgotPasswordImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                showForgotPasswordView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void forgotPasswordImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            User user1 = userDAO.searchUserByEmail(email);
            User user2 = userDAO.searchUserByUsername(username);
            if (user1 == null || user2 == null || !user1.equals(user2)) {
                //HttpSession session = request.getSession();
                request.getSession().setAttribute("message", "Account does not exist");
                response.sendRedirect("../Account/ForgotPassword");
            } else {
                LocalDateTime now = LocalDateTime.now();
                String message = trippleDes.encrypt(email + " " + now.toString() + " " + "ForgotPassword");
                //check if the email send successfully
                if (SendEmail.send(email, "Password Reset Link", "http://localhost:8080/HR_Management/Account/NewPassword?" + message)) {
                    out.println(sendEmaiSuccessfully);
                } else {
                    out.println("Failed to send verification email");
                }
            }
        }
    }

    private void showForgotPasswordView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        //out.println(request.getContextPath());
        request.getRequestDispatcher("/Views/ForgotPasswordView.jsp").forward(request, response);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="HTML">
    // <editor-fold defaultstate="collapsed" desc="RegisterSuccess">
    private String registerSuccess
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
            + "                                    Register Successfully\n"
            + "                                </header>\n"
            + "                                <p>Please check your email, we already send verify link to you</p>\n"
            + "                                <a href=\"../Account/Login\" class=\"btn btn-primary btn-group-lg active\" role=\"button\" aria-pressed=\"true\">Back to login</a>\n"
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
            + "</html>\n"
            + "\n"
            + "";
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="VerifySuccess">
    private String verifySuccess
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
            + "                                <p>Your account is verified. But you still need approval to access this site</p>\n"
            + "                                <a href=\"../Account/Login\" class=\"btn btn-primary btn-group-lg active\" role=\"button\" aria-pressed=\"true\">Back to login</a>\n"
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
            + "</html>\n"
            + "\n"
            + "";
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="sendEmaiSuccessfully">
    private final String sendEmaiSuccessfully
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
            + "                                <a href=\"../Account/Login\" class=\"btn btn-primary btn-group-lg active\" role=\"button\" aria-pressed=\"true\">Back to login</a>\n"
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
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="changePasswordSuccessfully">
    private final String changePasswordSuccessfully
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
            + "                                <a href=\"../Account/Login\" class=\"btn btn-primary btn-group-lg active\" role=\"button\" aria-pressed=\"true\">Back to login</a>\n"
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
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="linkExpired">
    private String linkExpired
            = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "    <head>\n"
            + "        <meta charset=\"UTF-8\">\n"
            + "        <title>Forgot Password</title>\n"
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
            + "                    <div class=\"col-lg-6 \">\n"
            + "                        <section class=\"panel\">\n"
            + "                            <header class=\"panel-heading text-center\">\n"
            + "                                Link Expired\n"
            + "                            </header>\n"
            + "                            "
            + "                            <div class=\"panel-body\">\n"
            + "                                <form action=\"../Account/ForgotPassword\" method=\"GET\" role=\"form\" onsubmit=\"return isValid\">\n"
            + "                                    <div class=\"row\">\n"
            + "                                        <div class=\"col-lg-2\"></div>\n"
            + "                                        <div class=\"col-lg-8\">\n"
            + "                                            <div class=\" form-group text-center\">\n"
            + "                                                <button type=\"submit\" id=\"submit-btn\" class=\"btn btn-info\" >Resend</button>\n"
            + "                                            </div>\n"
            + "                                            <div class=\"row \">\n"
            + "                                                <div class=\"col-lg-4\"></div>\n"
            + "                                                <div class=\"col-lg-8\">\n"
            + "                                                    <p> <a href=\"../Account/Login\"> &nbsp  &nbsp  &nbsp  &nbsp &nbsp Back to login</a></p>\n"
            + "                                                </div>\n"
            + "                                            </div>\n"
            + "                                        </div>\n"
            + "\n"
            + "                                    </div>\n"
            + "                                </form>\n"
            + "\n"
            + "                            </div>\n"
            + "                        </section>\n"
            + "                    </div>\n"
            + "                </div>\n"
            + "            </section>\n"
            + "\n"
            + "        </div>\n"
            + "        <!-- jQuery 2.0.2 -->\n"
            + "        <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js\"></script>\n"
            + "        <script src=\"../js/jquery.min.js\" type=\"text/javascript\"></script>\n"
            + "\n"
            + "        <!-- Bootstrap -->\n"
            + "        <script src=\"../js/bootstrap.min.js\" type=\"text/javascript\"></script>\n"
            + "        <!-- Director App -->\n"
            + "        <script src=\"../js/Director/app.js\" type=\"text/javascript\"></script>\n"
            + "        <script src=\"../js/Director/myScript.js\" type=\"text/javascript\"></script>\n"
            + "        <script>\n"
            + "\n"
            + "                                    /** HIDE ALERT**/\n"
            + "                                    $(document).click(function (e) {\n"
            + "                                        $('.error').hide();\n"
            + "                                    });\n"
            + "                                    /** HIDE ALERT**/\n"
            + "\n"
            + "\n"
            + "        </script>\n"
            + "    </body>\n"
            + "</html>\n"
            + "\n"
            + "";
    //</editor-fold>

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
