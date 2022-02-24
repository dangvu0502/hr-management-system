/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.ProjectDAO;
import Dao.SettingDAO;
import Dao.TimesheetDAO;
import Dao.UserDAO;
import Models.Timesheet;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private ProjectDAO projectDAO;
    private UserDAO userDAO;

    public void init() {
        timesheetDAO = new TimesheetDAO();
        settingDAO = new SettingDAO();
        projectDAO = new ProjectDAO();
        userDAO = new UserDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String action = request.getPathInfo() == null ? "" : request.getPathInfo();
            String method = request.getMethod();
            switch (action) {
                case "/TimesheetList":
                    showTimesheetListView(request, response);
                    break;
                case "/NewTimesheet":
                    newTimesheet(request, response, method);
                    break;
                case "/TimesheetDetail":
                    showTimesheetDetailView(request, response);
                    break;
                case "/DeleteTimesheet":
                    deleteTimesheet(request, response);
                    break;
                case "/EditTimesheet":
                    editTimesheet(request, response, method);
                    break;
                case "/Review":
                    showTimesheetReviewList(request, response);
                    break;
                case "/GetAllTimeSheetInGroup":
                    getAllTimesheetInGroup(request, response);
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
        User user = (User) request.getSession().getAttribute("account");
        String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
        String project_code = request.getParameter("project") != null ? request.getParameter("project") : "";
        String title = request.getParameter("title") != null ? request.getParameter("title") : "";
        int process = request.getParameter("process") != null ? Integer.parseInt(request.getParameter("process")) : 0;
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int offset = (page - 1) * 3;

        //this query for search and filter
        String query1 = "SELECT * FROM hr_system_v2.timesheet Where user_id= " + user.getId();
        if (!fromDate.isEmpty()) {
            query1 += " and date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            query1 += " and date <= " + "'" + toDate + "'";
        }
        if (!project_code.isEmpty()) {
            query1 += " and project_code =  " + "'" + project_code + "'";
        }
        if (!title.isEmpty()) {
            query1 += " and title like  " + "'%" + title + "%'";
        }
        if (process != 0) {
            query1 += " and process = " + "'" + process + "'";
        }
        query1 += " limit 3 offset " + offset;

        //this query for  total timesheet
        String query2 = "SELECT count(id) FROM hr_system_v2.timesheet Where user_id= " + user.getId();
        if (!fromDate.isEmpty()) {
            query2 += " and date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            query2 += " and date <= " + "'" + toDate + "'";
        }
        if (!project_code.isEmpty()) {
            query2 += " and project_code =  " + "'" + project_code + "'";
        }
        if (!title.isEmpty()) {
            query2 += " and title like  " + "'%" + title + "%'";
        }
        if (process != 0) {
            query2 += " and process = " + "'" + process + "'";
        }

        int timesheetCount = timesheetDAO.getTotalTimesheet(query2);
        int total = timesheetCount / 3 + (timesheetCount % 3 == 0 ? 0 : 1);
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
        request.setAttribute("timesheetList", timesheetDAO.getTimesheetList(query1));
        request.setAttribute("projects", projectDAO.getAllProjectCode(user.getGroup_code()));
        request.setAttribute("timesheetProcess", settingDAO.getTimesheetProcess());
        request.setAttribute("timesheetStatus", settingDAO.getTimesheetStatus());
        request.getRequestDispatcher("/Views/TimesheetListView.jsp").forward(request, response);
    }

    private void newTimesheet(HttpServletRequest request, HttpServletResponse response, String method)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        if (method.equalsIgnoreCase("GET")) {
            showNewTimesheetView(request, response);
        } else {
            newTimesheetImplement(request, response);
        }
    }

    private void editTimesheet(HttpServletRequest request, HttpServletResponse response, String method)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        if (method.equalsIgnoreCase("GET")) {
            showNewTimesheetView(request, response);
        } else {
            editTimesheetImplement(request, response);
        }
    }

    private void showNewTimesheetView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("account");
        if (request.getParameter("id") != null) {
            Timesheet timesheet = timesheetDAO.getTimesheetById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("viDate", myFormatDate(timesheet.getDate()));

            request.setAttribute("timesheet", timesheet);
        }
        request.setAttribute("projects", projectDAO.getAllProjectCode(user.getGroup_code()));
        request.setAttribute("timesheetProcess", settingDAO.getTimesheetProcess());
        request.getRequestDispatcher("/Views/NewTimesheetView.jsp").forward(request, response);
    }

    private String myFormatDate(String date) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new SimpleDateFormat("dd-MM-yyyy").parse(date));
    }

    private void editTimesheetImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        String duration = request.getParameter("duration");
        int process = Integer.parseInt(request.getParameter("process"));
        String project = request.getParameter("project");
        String workResult = request.getParameter("work-result");
        timesheetDAO.updateTimesheet(new Timesheet(id, title, date, process, duration, workResult, project));
        request.getSession().setAttribute("successMessage", "Editted");
        response.sendRedirect("/HR_Management/Timesheet/EditTimesheet?id=" + id);

    }

    private void newTimesheetImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        String duration = request.getParameter("duration");
        int process = Integer.parseInt(request.getParameter("process"));
        String project = request.getParameter("project");
        String work_result = request.getParameter("work-result");
        int status = 1;
        User user = (User) request.getSession().getAttribute("account");
        timesheetDAO.addNewTimesheet(new Timesheet(title, date, process, duration, status, user.getId(), project, work_result));
        request.getSession().setAttribute("successMessage", "Add new timesheet success");
        response.sendRedirect("/HR_Management/Timesheet/NewTimesheet");
    }

    private void deleteTimesheet(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        timesheetDAO.deleteTimesheetById(id);
        User user = (User) request.getSession().getAttribute("account");
        String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
        String project_code = request.getParameter("project") != null ? request.getParameter("project") : "";
        String title = request.getParameter("title") != null ? request.getParameter("title") : "";
        int process = request.getParameter("process") != null ? Integer.parseInt(request.getParameter("process")) : 0;
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        String query2 = "SELECT count(id) FROM hr_system_v2.timesheet Where user_id= " + user.getId();
        if (!fromDate.isEmpty()) {
            query2 += " and date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            query2 += " and date <= " + "'" + toDate + "'";
        }
        if (!project_code.isEmpty()) {
            query2 += " and project_code =  " + "'" + project_code + "'";
        }
        if (!title.isEmpty()) {
            query2 += " and title like  " + "'%" + title + "%'";
        }
        if (process != 0) {
            query2 += " and process = " + "'" + process + "'";
        }

        int timesheetCount = timesheetDAO.getTotalTimesheet(query2);
        int total = timesheetCount / 3 + (timesheetCount % 3 == 0 ? 0 : 1);
        response.getWriter().print(Math.min(total, page));

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

    private void showTimesheetReviewList(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("account");
        request.setAttribute("projects", projectDAO.getAllProjectCode(user.getGroup_code()));
        request.setAttribute("timesheetProcess", settingDAO.getTimesheetProcess());
        request.setAttribute("users", userDAO.getUsersByGroupCode(user.getGroup_code()));
        request.getRequestDispatcher("/Views/TimesheetReviewView.jsp").forward(request, response);
    }

    private void getAllTimesheetInGroup(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        Gson gson = new Gson();
        String condition = "";
        String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
        String project_code = request.getParameter("project") != null ? request.getParameter("project") : "";
        String title = request.getParameter("title") != null ? request.getParameter("title") : "";
        String username = request.getParameter("username") != null ? request.getParameter("username") : "";
        int process = request.getParameter("process") != null ? Integer.parseInt(request.getParameter("process")) : 0;
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int offset = (page - 1) * 3;
        if (!fromDate.isEmpty()) {
            condition += " and date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            condition += " and date <= " + "'" + toDate + "'";
        }
        if (!project_code.isEmpty()) {
            condition += " and project_code =  " + "'" + project_code + "'";
        }
        if (!title.isEmpty()) {
            condition += " and title like  " + "'%" + title + "%'";
        }
        if (!username.isEmpty()) {
            condition += " and username like  " + "'%" + username + "%'";
        }
        if (process != 0) {
            condition += " and process = " + "'" + process + "'";
        }
        JsonElement element = gson.toJsonTree(timesheetDAO.getAllTimesheet(condition,"G6"), new TypeToken<ArrayList<Timesheet>>() {
        }.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        response.getWriter().println(jsonArray);

    }

}
