/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ContractDAO;
import DAO.EmployeeDAO;
import DAO.GroupDAO;
import DAO.UserDAO;
import Models.Contract;
import Models.Group;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author quocb
 */
@WebServlet(name = "GroupListController", urlPatterns = {"/Group/*"})
public class GroupListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String action = request.getPathInfo() == null ? "" : request.getPathInfo();
            String method = request.getMethod();
            switch (action) {
                case "/GroupList":
                    groupListImplement(request, response);
                    break;
                case "/GroupEdit":
                    GroupEdit(request, response,method);
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

    // <editor-fold defaultstate="collapsed" desc="GroupList">
//     private void groupList(HttpServletRequest request, HttpServletResponse response, String method)
//            throws Exception {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter();) {
//            if (method.equalsIgnoreCase("post")) {
//                groupListImplement(request, response);
//            } else if (method.equalsIgnoreCase("get")) {
//                showGroupView(request, response);
//            }
//        } catch (Exception ex) {
//            log(ex.getMessage());
//        }
//    }
    private void groupListImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String group_type = request.getParameter("type");
            String input = request.getParameter("input");
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("page", page);
            GroupDAO gDAO = new GroupDAO();
            int count = gDAO.totalGroup();
            int endPage = count / 6;
            if (endPage % 5 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            request.setAttribute("gr", group_type);
            request.setAttribute("txtS", input);
            Vector<Group> g = new Vector();
            if (input == null || input.isEmpty()) {
                g = gDAO.getGroupList(Integer.parseInt(page));
            } else {
                g = gDAO.getGroupBySearch(input);
            }
            request.setAttribute("listG", g);
            request.getRequestDispatcher("../Views/GroupView.jsp").forward(request, response);
        }
    }

    private void showGroupView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        //out.println(request.getContextPath());
        request.getRequestDispatcher("../Views/GroupView.jsp").forward(request, response);
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="GroupEdit">
    private void GroupEdit(HttpServletRequest request, HttpServletResponse response, String method) {
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                groupEditImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                editGroupView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void editGroupView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Vector<Group> group = new GroupDAO().getOne(Integer.parseInt(id));
        request.setAttribute("group", group);
        request.getRequestDispatcher("../Views/GroupViewEdit.jsp").forward(request, response);
    }

    private void groupEditImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String code = request.getParameter("code");
            String manager_id = request.getParameter("manager_id");
            String name = request.getParameter("name");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String description = request.getParameter("description");
            String parent_group_code = request.getParameter("parent_group_code");
            String update_date = request.getParameter("update_date");
            String id = request.getParameter("id");
            
            GroupDAO group = new GroupDAO();
            group.editGroup(code, Integer.parseInt(manager_id), name, status, description, parent_group_code, update_date, Integer.parseInt(id));
            response.sendRedirect("../Contract/GroupEdit?id=" + id);
        }
    }
    //</editor-fold>
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
