/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.AbsenceDAO;
import Dao.SettingDAO;
import Models.Absence;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kha Chinh
 */
public class AbsencesController extends HttpServlet {

    private AbsenceDAO aDAO;
    private SettingDAO sDAO;

    @Override
    public void init() {
        aDAO = new AbsenceDAO();
        sDAO = new SettingDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String action = request.getPathInfo() == null ? "" : request.getPathInfo();
            String method = request.getMethod();
            switch (action) {
                case "/Absence":
                    listAbsence(request, response);
                    break;
                case "/Delete":
                    deleteAbsence(request, response);
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

    private void listAbsence(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("account");
        String title = request.getParameter("title") != null ? request.getParameter("title") : "";
        if (title.contains("_")) {
            title = title.replaceAll("_", " ");
        }
        String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
        String type = request.getParameter("type") != null ? request.getParameter("type") : "";
        String status = request.getParameter("status") != null ? request.getParameter("status") : "";
        String page = request.getParameter("page") != null ? request.getParameter("page") : "1";

        String sql = "SELECT * FROM hr_system_v2.absence a WHERE user_id = " + user.getId();
        if (!title.isEmpty()) {
            sql += " and title like '%" + title + "%'";
        }
        if (!fromDate.isEmpty()) {
            sql += " and (a.from >= '" + fromDate + "' or a.to >= '" + fromDate + "')";
        }
        if (!toDate.isEmpty()) {
            sql += " and (a.from <= '" + toDate + "' or a.to <= '" + toDate + "')";
        }
        if (!type.isEmpty()) {
            sql += " and absence_type = " + type;
        }
        if (!status.isEmpty()) {
            sql += " and status = " + status;
        }

        String sql2 = "SELECT count(*) FROM hr_system_v2.absence a WHERE user_id = " + user.getId();
        if (!title.isEmpty()) {
            sql2 += " and title like '%" + title + "%'";
        }
        if (!fromDate.isEmpty()) {
            sql2 += " and (a.from >= '" + fromDate + "' or a.to >= '" + fromDate + "')";
        }
        if (!toDate.isEmpty()) {
            sql2 += " and (a.from <= '" + toDate + "' or a.to <= '" + toDate + "')";
        }
        if (!type.isEmpty()) {
            sql2 += " and absence_type = " + type;
        }
        if (!status.isEmpty()) {
            sql2 += " and status = " + status;
        }

        int count = aDAO.getTotalAbsenceOfStaff(sql2);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        Vector<Absence> a = aDAO.getListAbsenceByStaff(sql, Integer.parseInt(page));
        request.setAttribute("listA", a);
        request.setAttribute("endP", endPage);
        request.setAttribute("AbsenceType", sDAO.getAbsenceSetting(1));
        request.setAttribute("AbsenceStatus", sDAO.getAbsenceSetting(2));
        request.getRequestDispatcher("/Views/Absence.jsp").forward(request, response);
    }

    public void deleteAbsence(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        aDAO.deleteTimesheetById(id);
        User user = (User) request.getSession().getAttribute("account");
        String title = request.getParameter("title") != null ? request.getParameter("title") : "";
        if (title.contains("_")) {
            title = title.replaceAll("_", " ");
        }
        String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
        String type = request.getParameter("type") != null ? request.getParameter("type") : "";
        String status = request.getParameter("status") != null ? request.getParameter("status") : "";
        String page = request.getParameter("page") != null ? request.getParameter("page") : "1";

        String sql2 = "SELECT count(*) FROM hr_system_v2.absence a WHERE user_id = " + user.getId();
        if (!title.isEmpty()) {
            sql2 += " and title like '%" + title + "%'";
        }
        if (!fromDate.isEmpty()) {
            sql2 += " and (a.from >= '" + fromDate + "' or a.to >= '" + fromDate + "')";
        }
        if (!toDate.isEmpty()) {
            sql2 += " and (a.from <= '" + toDate + "' or a.to <= '" + toDate + "')";
        }
        if (!type.isEmpty()) {
            sql2 += " and absence_type = " + type;
        }
        if (!status.isEmpty()) {
            sql2 += " and status = " + status;
        }
        int count = aDAO.getTotalAbsenceOfStaff(sql2);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        response.getWriter().print(endPage);
    }
}
