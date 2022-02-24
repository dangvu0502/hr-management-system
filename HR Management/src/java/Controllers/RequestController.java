/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.RequestDAO;
import Models.Request;
import Models.Timesheet;
import Models.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author quocb
 */
@WebServlet(name = "RequestController", urlPatterns = {"/Request/*"})
public class RequestController extends HttpServlet {

    private RequestDAO requestDAO ;
    public void init() {
       requestDAO = new RequestDAO();

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
        try (PrintWriter out = response.getWriter();) {
            String action = request.getPathInfo() == null ? "" : request.getPathInfo();
            String method = request.getMethod();
            switch (action) {
                case "/RequestList":
                    showRequestListView(request, response);
                    break;
//                case "/NewTimesheet":
//                    newRequest(request, response, method);
//                    break;
//                case "/TimesheetDetail":
//                    showRequestDetailView(request, response);
//                    break;
//                case "/DeleteTimesheet":
//                    deleteRequest(request, response);
//                    break;
//                case "/EditTimesheet":
//                    editRequest(request, response, method);
//                    break;
                case "/GetAllRequest":
                    getAllRequest(request, response);
                    break;
//                default:
//                    response.sendError(404);
//                    break;
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

    private void showRequestListView(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("account");
        String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
        String title = request.getParameter("title") != null ? request.getParameter("title") : "";
        String name = request.getParameter("name") != null ? request.getParameter("name") : "";
        int status = request.getParameter("status") != null ? Integer.parseInt(request.getParameter("status")) : 0;
//      int support_type_id = Integer.parseInt(request.getParameter("support_type_id") != null ? request.getParameter("support_type_id") : "");
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int offset = (page - 1) * 3;
        //
        String sql1 = "SELECT r.request_date, r.title, (s.name) as RequestName, (u.fullname) as 'Incharge Staff', r.status, r.update_date FROM ((hr_system_v2.request r \n"
                + "join hr_system_v2.`support type` s on r.support_type_id = s.id)\n"
                + "join hr_system_v2.user u on r.in_charge_staff = u.id)\n"
                + "where r.support_type_id = " + 106;
        if (!fromDate.isEmpty()) {
            sql1 += " and r.request_date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            sql1 += "r.request_date <= " + "'" + toDate + "'";
        }
        if (!name.isEmpty()) {
            sql1 += " s.name like  " + "'%" + name + "%'";
        }
        if (!title.isEmpty()) {
            sql1 += " and r.title like  " + "'%" + title + "%'";
        }
        if (status != 0) {
            sql1 += " r.status = " + "'" + status + "'";
        }
        sql1 += " limit 3 offset " + offset;
        //
        String sql2 = "SELECT count(r.support_type_id) FROM ((hr_system_v2.request r \n"
                + "join hr_system_v2.`support type` s on r.support_type_id = s.id)\n"
                + "join hr_system_v2.user u on r.in_charge_staff = u.id)\n"
                + "where r.support_type_id = " + 106;
        if (!fromDate.isEmpty()) {
            sql2 += " and r.request_date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            sql2 += "r.request_date <= " + "'" + toDate + "'";
        }
        if (!name.isEmpty()) {
            sql2 += " s.name like  " + "'%" + name + "%'";
        }
        if (!title.isEmpty()) {
            sql2 += " and r.title like  " + "'%" + title + "%'";
        }
        if (status != 0) {
            sql2 += " r.status = " + "'" + status + "'";
        }
        //
        int count = requestDAO.getTotalRequest(sql2);
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
        request.setAttribute("requestList", requestDAO.getRequestList(sql1));
        request.getRequestDispatcher("/Views/RequestView.jsp").forward(request, response);
      
    }

    private void getAllRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(requestDAO.getAllRequest(), new TypeToken<ArrayList<Timesheet>>() {
        }.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        response.getWriter().println(jsonArray);

    }
// </editor-fold>
}
