package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Context.TrippleDes;
import DAO.EmployeeDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author quocb
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
           // Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            HttpSession session = request.getSession();
//            Employee account = (Employee) session.getAttribute("account");
//            //kiem tra nếu acc = null thì chưa đăng nhập
//            if (account == null) {
//                request.getRequestDispatcher("Views/login.jsp").forward(request, response);
//            } else {
//                response.sendRedirect("homepage");
//            }
            HttpSession session = request.getSession();
            User account = (User) session.getAttribute("account");
            if (account == null) {
                request.getRequestDispatcher("Views/login.jsp").forward(request, response);
            } else {
                response.sendRedirect("Views/Home.jsp");
            }

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

        String username = request.getParameter("username");
        String password = trippleDes.encrypt(request.getParameter("password"));
        String verifyMessage = (String) request.getAttribute("verifyMessage");
        String warning = null;

        User account = new UserDAO().login(username, password);
        if (account == null && verifyMessage == null) {
            request.setAttribute("err", "Login failed");
            request.getRequestDispatcher("Views/login.jsp").forward(request, response);
        } else if (account == null && verifyMessage != null) {
            request.setAttribute("verifyMessage", verifyMessage);
            request.getRequestDispatcher("Views/login.jsp").forward(request, response);
        } else if (account != null) {
            if (!account.isStatus()) {
                request.setAttribute("err", "You do not have access to this website");
                request.getRequestDispatcher("Views/login.jsp").forward(request, response);
            } else {
                account.setPassword(trippleDes.decrypt(account.getPassword()));
                request.getSession().setAttribute("account", account); //lưu trên ss
                response.sendRedirect("Views/Home.jsp");
            }
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
