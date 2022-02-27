/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.GroupDAO;
import Dao.ProjectDAO;
import Dao.SettingDAO;
import Dao.SupportTypeDAO;
import Dao.UserDAO;
import Models.Group;
import static Models.Group.myFormatDate;
import Models.SupportType;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "GroupController", urlPatterns = {"/Group/*"})
public class GroupController extends HttpServlet {

    private GroupDAO groupDAO;
    private UserDAO userDAO;

    public void init() {
        groupDAO = new GroupDAO();
        userDAO = new UserDAO();
    }

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
                case "/GroupAdd":
                    GroupAdd(request, response, method);
                    break;
                case "/GroupEdit":
                    GroupEdit(request, response, method);
                    break;
                case "/Delete":
                    changeStatusDelete(request, response);
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
            String code = request.getParameter("code") != null ? request.getParameter("code") : "";
            String name = request.getParameter("name") != null ? request.getParameter("name") : "";
            String fullname = request.getParameter("fullname") != null ? request.getParameter("fullname") : "";
            String parent_group_code = request.getParameter("parent_group_code") != null ? request.getParameter("parent_group_code") : "";
            int status = request.getParameter("status") != null ? Integer.parseInt(request.getParameter("status")) : -1;
            int delete = request.getParameter("delete") != null ? Integer.parseInt(request.getParameter("delete")) : -1;
            int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
            int offset = (page - 1) * 3;
            String query1 = "SELECT g.code, g.name, u.fullname, g.parent_group_code, g.status, g.update_date, g.delete \n"
                    + "FROM hr_system_v2.group g join hr_system_v2.user u \n"
                    + "where g.manager_id = u.id";
            if (!code.isEmpty()) {
                query1 += " and g.code like " + "'%" + code + "%'";
            }
            if (!fullname.isEmpty()) {
                query1 += " and u.fullname like  " + "'%" + fullname + "%' or g.code like " + "'%" + fullname + "%'";
            }
            if (!parent_group_code.isEmpty()) {
                query1 += " and g.parent_group_code like " + "'%" + parent_group_code + "%'";
            }
            if (status != -1) {
                query1 += " and g.status =  " + "'" + status + "'";
            }
            if (delete != -1) {
                query1 += " and g.delete =  " + "'" + delete + "'";
            }
            query1 += " limit 3 offset " + offset;

            //
            String query2 = "SELECT count(*) FROM (SELECT g.code, g.name, u.fullname, g.parent_group_code, g.status, g.update_date, g.delete \n"
                    + "FROM hr_system_v2.group g join hr_system_v2.user u \n"
                    + "where g.manager_id = u.id) g where status = 1 or status = 0";
            if (!code.isEmpty()) {
                query2 += " and g.code like " + "'%" + code + "%'";
            }
            if (!fullname.isEmpty()) {
                query2 += " and u.fullname like  " + "'%" + fullname + "%' or g.code like " + "'%" + fullname + "%'";
            }

            if (!parent_group_code.isEmpty()) {
                query2 += " and g.parent_group_code like " + "'%" + parent_group_code + "%'";
            }
            if (status != -1) {
                query2 += " and g.status =  " + "'" + status + "'";
            }
            if (delete != -1) {
                query2 += " and g.delete =  " + "'" + delete + "'";
            }
            int count = groupDAO.getTotalGroup(query2);
            int total = count / 3 + (count % 3 == 0 ? 0 : 1);
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
            request.setAttribute("listG", groupDAO.getGroupList(query1));
            request.setAttribute("parentG", groupDAO.getAllPCode());
            request.getRequestDispatcher("/Views/GroupView.jsp").forward(request, response);
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
    // <editor-fold defaultstate="collapsed" desc="GroupAdd">
    private void GroupAdd(HttpServletRequest request, HttpServletResponse response, String method) {
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                GroupAddImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                addGroupView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void GroupAddImplement(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException, Exception {
        String code = request.getParameter("code"); //null
        String manager = request.getParameter("manager");
        String name = request.getParameter("name");
        String fullname = request.getParameter("fullname"); //null
        String parent_group_code = request.getParameter("parent_group_code"); //null
        String status = request.getParameter("status");
        String update_date = request.getParameter("update_date"); //null
        groupDAO.addnewGroup(code, Integer.parseInt(manager), name, Integer.parseInt(status), parent_group_code, update_date);
        if (groupDAO.SearchByCode(code) != null) {
            request.getSession().setAttribute("codeErrorMessage", "Add Group Failed Code existed");
            response.sendRedirect("../Group/GroupAdd");
        } else {
            request.getSession().setAttribute("message", "Add Group Successfully!!");
            response.sendRedirect("../Group/GroupAdd");
        }

    }

    private void addGroupView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setAttribute("listG", groupDAO.getAllGroupCode());
        request.setAttribute("listU", userDAO.getManagerFullname());
        request.setAttribute("group", groupDAO.getAllPGroupCode());
        request.setAttribute("ListN", groupDAO.getAllName());

        request.getRequestDispatcher("/Views/GroupViewAdd.jsp").forward(request, response);
    }

    //delete
    private void changeStatusDelete(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String code = request.getParameter("code") != null ? request.getParameter("code") : "";
            int delete = request.getParameter("delete") != null ? Integer.parseInt(request.getParameter("delete")) : -1;
            groupDAO.editStatusDelete(delete, code);
            String name = request.getParameter("name") != null ? request.getParameter("name") : "";
            String fullname = request.getParameter("fullname") != null ? request.getParameter("fullname") : "";
            String parent_group_code = request.getParameter("parent_group_code") != null ? request.getParameter("parent_group_code") : "";
            int status = request.getParameter("status") != null ? Integer.parseInt(request.getParameter("status")) : -1;

            int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
            int offset = (page - 1) * 3;
            String query1 = "SELECT g.code, g.name, u.fullname, g.parent_group_code, g.status, g.update_date, g.delete \n"
                    + "FROM hr_system_v2.group g join hr_system_v2.user u \n"
                    + "where g.manager_id = u.id";

            if (!fullname.isEmpty()) {
                query1 += " and u.fullname like  " + "'%" + fullname + "%' or g.code like " + "'%" + fullname + "%'";
            }
            if (!parent_group_code.isEmpty()) {
                query1 += "and g.parent_group_code like " + "'%" + parent_group_code + "%'";
            }
            if (status != -1) {
                query1 += " and g.status =  " + "'" + status + "'";
            }

            query1 += " limit 3 offset " + offset;
            String query2 = "SELECT count(*) FROM (SELECT g.code, g.name, u.fullname, g.parent_group_code, g.status, g.update_date, g.delete \n"
                    + "FROM hr_system_v2.group g join hr_system_v2.user u \n"
                    + "where g.manager_id = u.id) g where status = 1 or status = 0";

            if (!fullname.isEmpty()) {
                query2 += " and u.fullname like " + "'%" + fullname + "%'";
            }

            if (!parent_group_code.isEmpty()) {
                query2 += "and g.parent_group_code like " + "'%" + parent_group_code + "%'";
            }
            if (status != -1) {
                query2 += " and g.status =  " + "'" + status + "'";
            }

            int count = groupDAO.getTotalGroup(query2);
            int total = count / 3 + (count % 3 == 0 ? 0 : 1);
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

            request.setAttribute("listG", groupDAO.getGroupList(query1));
            request.setAttribute("parentG", groupDAO.getAllPCode());
            request.getRequestDispatcher("/Views/GroupView.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private void GroupEdit(HttpServletRequest request, HttpServletResponse response, String method) {
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                groupEditImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                groupEditView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void groupEditImplement(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String code = request.getParameter("code"); 
        String manager = request.getParameter("manager");
        String name = request.getParameter("name");
        String parent_group_code = request.getParameter("parent_group_code"); //null
        String status = request.getParameter("status"); //null
        String update_date = request.getParameter("update_date"); 
        groupDAO.editGroup(code, Integer.parseInt(manager), name, Integer.parseInt(status), parent_group_code, update_date);
        request.getSession().setAttribute("message", "Edit Group Successfully!!");
        response.sendRedirect("../Group/GroupEdit?code=" + code);
    }

    private void groupEditView(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        String code = request.getParameter("code");
        List<Group> group = new GroupDAO().getOne(code);
       
        request.setAttribute("listU", userDAO.getManagerFullname());
        request.setAttribute("groupCode", groupDAO.getAllPGroupCode());
        request.setAttribute("ListN", groupDAO.getAllName());
        request.setAttribute("ListD", groupDAO.getDate(code));
     //   request.setAttribute("viDate", myFormatDate());
        request.setAttribute("group", group);
        request.getRequestDispatcher("../Views/GroupViewEdit.jsp").forward(request, response);
    }
     private String myFormatDate(String date) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new SimpleDateFormat("dd-MM-yyyy").parse(date));
    }
}
//</editor-fold>

