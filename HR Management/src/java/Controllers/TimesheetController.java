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
                case "/GetTimesheetListToJson":
                    getTimesheetListToJson(request, response);
                    break;
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
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        request.setAttribute("timesheetList", timesheetDAO.getTimesheetList(page, 99));
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

    private void getTimesheetListToJson(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(timesheetDAO.getTimesheetList(page, 99), new TypeToken<ArrayList<Timesheet>>() {
        }.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        response.getWriter().println(jsonArray);
    }

}
