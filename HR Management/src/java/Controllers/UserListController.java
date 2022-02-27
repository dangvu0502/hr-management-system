/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Context.TrippleDes;
import Dao.GroupDAO;
import Dao.ProjectDAO;
import Dao.SettingDAO;
import Dao.UserDAO;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hide
 */
public class UserListController extends HttpServlet {

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
    private ProjectDAO projectDAO;
    private GroupDAO groupDAO;

    public void init() {
        userDAO = new UserDAO();
        projectDAO = new ProjectDAO();
        groupDAO = new GroupDAO();
        try {
            trippleDes = new TrippleDes();
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getPathInfo() == null ? "" : request.getPathInfo();
            String method = request.getMethod();
            switch (action) {
                case "/UserList":
                    showUserListView(request, response);
                    break;
                case "/ChangeStatus":
                    changeStatus(request, response);
                    break;
                case "/EditView":
                    editView(request, response);
                    break;
                case "/Edit":
                    edit(request, response);
                    break;
                default:
                    response.sendError(404);
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="UserList">
    private void showUserListView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("account");
        int status = request.getParameter("status") != null ? Integer.parseInt(request.getParameter("status")) : -1;
        String search = request.getParameter("search") != null ? request.getParameter("search") : "";
        String group = request.getParameter("group") != null ? request.getParameter("group") : "";
        String gender = request.getParameter("gender") != null ? request.getParameter("gender") : "";
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int offset = (page - 1) * 3;

        //this query for search and filter
        String query1 = "SELECT * FROM hr_system_v2.user where id LIKE '%%'";
        if (status != -1) {
            query1 += " and status =  " + "'" + status + "'";
        }
        if (!search.isEmpty()) {
            query1 += " and username like  " + "'%" + search + "%' or fullname like " + "'%" + search + "%' or mobile like " + "'%" + search + "%' or email like " + "'%" + search + "%' ";
        }
        if (!group.isEmpty()) {
            query1 += " and group_code = " + "'" + group + "'";
        }
        if (!gender.isEmpty()) {
            query1 += " and gender = " + "'" + gender + "'";
        }
        query1 += " limit 3 offset " + offset;

        //this query for  total contract
        String query2 = "SELECT count(*) FROM hr_system_v2.user where id LIKE '%%'";
        if (status != -1) {
            query2 += " and status =  " + "'" + status + "'";
        }
        if (!search.isEmpty()) {
            query2 += " and username like  " + "'%" + search + "%' or fullname like " + "'%" + search + "%' or mobile like " + "'%" + search + "%' or email like " + "'%" + search + "%' ";
        }
        if (!group.isEmpty()) {
            query2 += " and group_code = " + "'" + group + "'";
        }
        if (!gender.isEmpty()) {
            query2 += " and gender = " + "'" + gender + "'";
        }

        int UserCount = userDAO.getTotalUser(query2);

        int total = UserCount / 3 + (UserCount % 3 == 0 ? 0 : 1);
        int begin = 1;
        int end = 3;
        while (page > end) {
            end += 3;
            begin += 3;
        }
        end = Math.min(end, total);
        begin = Math.min(end, begin);
        request.setAttribute("total", total);
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
        request.setAttribute("currentNumber", page);
        request.setAttribute("group", groupDAO.getAllGroupCode());
        request.setAttribute("projectManager", userDAO.getManagerUserName());
        request.setAttribute("userList", userDAO.getUserList(query1));
        request.getRequestDispatcher("/Views/UserListView.jsp").forward(request, response);
    }
    // </editor-fold>

    private void changeStatus(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String status = request.getParameter("status");
            String id = request.getParameter("id");

            int st = Integer.parseInt(status);
            int i = Integer.parseInt(id);
            SettingDAO s = new SettingDAO();

            userDAO.editStatus(st, i);

            String search = request.getParameter("search") != null ? request.getParameter("search") : "";
            String group = request.getParameter("group") != null ? request.getParameter("group") : "";
            String gender = request.getParameter("gender") != null ? request.getParameter("gender") : "";
            int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
            int offset = (page - 1) * 3;
            //this query for search and filter
            String query1 = "SELECT * FROM hr_system_v2.user where id LIKE '%%'";
            query1 += " limit 3 offset " + offset;
            //this query for  total contract
            String query2 = "SELECT count(*) FROM hr_system_v2.user where id LIKE '%%'";
            int UserCount = userDAO.getTotalUser(query2);
            int total = UserCount / 3 + (UserCount % 3 == 0 ? 0 : 1);
            int begin = 1;
            int end = 3;
            while (page > end) {
                end += 3;
                begin += 3;
            }
            end = Math.min(end, total);
            begin = Math.min(end, begin);
            request.setAttribute("total", total);
            request.setAttribute("begin", begin);
            request.setAttribute("end", end);
            request.setAttribute("currentNumber", page);
            request.setAttribute("group", groupDAO.getAllGroupCode());
            request.setAttribute("projectManager", userDAO.getManagerUserName());
            request.setAttribute("userList", userDAO.getUserList(query1));
            request.getRequestDispatcher("/Views/UserListView.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private void editView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            if (request.getParameter("id") != null) {
                User u = userDAO.getUserListById(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("userList", u);
                request.setAttribute("group", groupDAO.getAllGroupCode());
                request.getRequestDispatcher("/Views/UserListEdit.jsp").forward(request, response);
            }

        }
    }

    //Edit
    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String id = request.getParameter("id");
            String groupUser = request.getParameter("groupUser");
            String genderUser = request.getParameter("genderUser");
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String status = request.getParameter("foo");
            String mobille = request.getParameter("mobile");
            int i = Integer.parseInt(id);
//            boolean sta = Boolean.parseBoolean(status);
            boolean sta = status.contains("1") ? true : false;
            boolean g = genderUser.contains("1") ? true : false;
            User u = new User(i, fullname, username, status, email, mobille, g, status, id, status, i, i, i, groupUser, sta);
            boolean check = userDAO.updateUserList(u, i);

            String search = request.getParameter("search") != null ? request.getParameter("search") : "";
            String group = request.getParameter("group") != null ? request.getParameter("group") : "";
            String gender = request.getParameter("gender") != null ? request.getParameter("gender") : "";
            int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
            int offset = (page - 1) * 3;
            //this query for search and filter
            String query1 = "SELECT * FROM hr_system_v2.user where id LIKE '%%'";
            query1 += " limit 3 offset " + offset;
            //this query for  total contract
            String query2 = "SELECT count(*) FROM hr_system_v2.user where id LIKE '%%'";
            int UserCount = userDAO.getTotalUser(query2);
            int total = UserCount / 3 + (UserCount % 3 == 0 ? 0 : 1);
            int begin = 1;
            int end = 3;
            while (page > end) {
                end += 3;
                begin += 3;
            }
            end = Math.min(end, total);
            begin = Math.min(end, begin);
            request.setAttribute("total", total);
            request.setAttribute("begin", begin);
            request.setAttribute("end", end);
            request.setAttribute("currentNumber", page);
            request.setAttribute("group", groupDAO.getAllGroupCode());
            request.setAttribute("projectManager", userDAO.getManagerUserName());
            request.setAttribute("userList", userDAO.getUserList(query1));
            request.getRequestDispatcher("/Views/UserListView.jsp").forward(request, response);

//            supportTypeListImplement(request, response);
        }
    }
}
