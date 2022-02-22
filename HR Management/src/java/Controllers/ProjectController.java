/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.GroupDAO;
import Dao.ProjectDAO;
import Dao.UserDAO;
import Models.Project;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Egamorft
 */
public class ProjectController extends HttpServlet {

    private ProjectDAO projectDAO;
    private GroupDAO groupDAO;
    private UserDAO userDAO;

    public void init() {
        projectDAO = new ProjectDAO();
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
                case "/List":
                    showProjectView(request, response);
                    break;
                case "/Edit":
                    Edit(request, response, method);
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

    // <editor-fold defaultstate="collapsed" desc="ProjectList">
    private void showProjectView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("account");
        String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
        int status = request.getParameter("status") != null ? Integer.parseInt(request.getParameter("status")) : -1;
        String search = request.getParameter("search") != null ? request.getParameter("search") : "";
        String group = request.getParameter("group") != null ? request.getParameter("group") : "";
        String pm = request.getParameter("pm") != null ? request.getParameter("pm") : "";
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int offset = (page - 1) * 3;

        //this query for search and filter
        String query1 = "SELECT * FROM hr_system_v2.project where code LIKE '%%'";
        if (!fromDate.isEmpty()) {
            query1 += " and start_date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            query1 += " and end_date <= " + "'" + toDate + "'";
        }
        if (status != -1) {
            query1 += " and status =  " + "'" + status + "'";
        }
        if (!search.isEmpty()) {
            query1 += " and code like  " + "'%" + search + "%' or project_name like " + "'%" + search + "%'";
        }
        if (!group.isEmpty()) {
            query1 += " and group_code = " + "'" + group + "'";
        }
        if (!pm.isEmpty()) {
            query1 += " and manager_id = " + "'" + pm + "'";
        }
        query1 += " limit 3 offset " + offset;

        //this query for  total contract
        String query2 = "SELECT count(*) FROM hr_system_v2.project where code LIKE '%%'";
        if (!fromDate.isEmpty()) {
            query2 += " and start_date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            query2 += " and end_date <= " + "'" + toDate + "'";
        }
        if (status != -1) {
            query2 += " and c.status =  " + "'" + status + "'";
        }
        if (!search.isEmpty()) {
            query2 += " and code or project_name like  " + "'%" + search + "%'";
        }
        if (!group.isEmpty()) {
            query2 += " and group_code = " + "'" + group + "'";
        }
        if (!pm.isEmpty()) {
            query2 += " and manager_id = " + "'" + pm + "'";
        }
        List<Project> p = new ArrayList<>();
        p = projectDAO.getProjectList(query1);
        for (int i = 0; i < p.size(); i++) {
            if(p.get(i).getEffort()==100){
                p.get(i).setStatus(1);
                projectDAO.setStatus1(p.get(i));
            }else{
                p.get(i).setStatus(0);
                projectDAO.setStatus0(p.get(i));
            }
        }
        int projectCount = projectDAO.getTotalProject(query2);
        int total = projectCount / 3 + (projectCount % 3 == 0 ? 0 : 1);
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
        request.setAttribute("projectList", projectDAO.getProjectList(query1));
//        request.setAttribute("contractProcess", settingDAO.getTimesheetProcess());
//        request.setAttribute("contractStatus", settingDAO.getTimesheetStatus());
        request.getRequestDispatcher("/Views/ProjectList.jsp").forward(request, response);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="EditProject">
    private void Edit(HttpServletRequest request, HttpServletResponse response, String method) {
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                projectEditImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                editProjectView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void editProjectView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String code = request.getParameter("code");
        List<Project> project = new ProjectDAO().getOne(code);
        request.setAttribute("listU", userDAO.getManagerUserName());
        request.setAttribute("group", groupDAO.getAllGroupCode());
        request.setAttribute("project", project);
        request.getRequestDispatcher("../Views/ProjectEdit.jsp").forward(request, response);
    }

    private void projectEditImplement(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        String code = request.getParameter("code");
        String groupCode = request.getParameter("group");
        String manager = request.getParameter("manager");
        String projectName = request.getParameter("projectName");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String description = request.getParameter("description");
        String effort = request.getParameter("effort");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (sdf.parse(startDate).before(sdf.parse(endDate))) {
            projectDAO.updateProject(groupCode, Integer.parseInt(manager), projectName, startDate, endDate, description, Integer.parseInt(effort), code);
            if(Integer.parseInt(effort) == 100){
                projectDAO.updateStatus(1, code);
            }else{
                projectDAO.updateStatus(0, code);
            }
            request.getSession().setAttribute("message", "Edit Project Successfully!!");
            response.sendRedirect("../Project/Edit?code=" + code);
        } else {
            request.getSession().setAttribute("message", "End Date must after Start Date!!");
            response.sendRedirect("../Project/Edit?code=" + code);
        }
    }
    // </editor-fold>
}
