/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.SettingDAO;
import Dao.TimesheetDAO;
import Models.Timesheet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

/**
 *
 * @author dangGG
 */
public class TimesheetController extends HttpServlet {

    private TimesheetDAO timesheetDAO;
    private SettingDAO settingDAO;

    public void init() {
        timesheetDAO = new TimesheetDAO();
        settingDAO = new SettingDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            String action = request.getPathInfo() == null ? "" : request.getPathInfo();
            String method = request.getMethod();
            switch (action) {
                case "/TimesheetList":
                    showTimesheetListView(request, response);
                    break;
                case "/NewTimesheet":
                    showNewTimesheetView(request, response);
                case "/TimesheetDetail":
                    showTimesheetDetailView(request, response);
          
                default:
                    response.sendError(404);
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

    private void showTimesheetListView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
        String project_code = request.getParameter("project") != null ? request.getParameter("project") : "";
        int process = request.getParameter("process") != null ? Integer.parseInt(request.getParameter("process")) : 0;
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        page -= 1;
        page *= 3;
        String query ="SELECT * FROM hr_system_v2.timesheet Where user_id= "+99;
        if(!fromDate.isEmpty()) query += " and date >= " + "'" + fromDate + "'";
        if(!toDate.isEmpty()) query += " and date <= " +  "'" + toDate+ "'";
        if(!project_code.isEmpty()) query += " and project_code =  " +  "'" + project_code+ "'";
        if(process != 0) query += " and process = " + "'"+ process+ "'"; 
        query += " limit 3 offset " + page;
        response.getWriter().println(query);
        request.setAttribute("timesheetList", timesheetDAO.getTimesheetList(query));
        request.setAttribute("timesheetProcess", settingDAO.getTimesheetProcess());
        request.setAttribute("timesheetStatus", settingDAO.getTimesheetStatus());
        request.getRequestDispatcher("/Views/TimesheetListView.jsp").forward(request, response);
    }

    private void showNewTimesheetView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("timesheetProcess", settingDAO.getTimesheetProcess());
        request.getRequestDispatcher("/Views/NewTimesheetView.jsp").forward(request, response);
    }

    private void showTimesheetDetailView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("timesheet", timesheetDAO.getTimesheetById(id));
        request.setAttribute("timesheetStatus", settingDAO.getTimesheetStatus());
        request.setAttribute("timesheetProcess", settingDAO.getTimesheetProcess());
        request.getRequestDispatcher("/Views/TimesheetDetailView.jsp").forward(request, response);
    }


}
