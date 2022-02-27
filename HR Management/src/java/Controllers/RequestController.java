/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.RequestDAO;
import Dao.SupportTypeDAO;
import Dao.UserDAO;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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

    private RequestDAO requestDAO;
    private SupportTypeDAO supporttypeDAO;
    private UserDAO userDAO;

    public void init() {
        requestDAO = new RequestDAO();
        supporttypeDAO = new SupportTypeDAO();
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
        try (PrintWriter out = response.getWriter();) {
            String action = request.getPathInfo() == null ? "" : request.getPathInfo();
            String method = request.getMethod();
            switch (action) {
                case "/RequestList":
                    showRequestListView(request, response);
                    break;
                case "/AddRequest":
                    addRequest(request, response, method);
                    break;
//                case "/TimesheetDetail":
//                    showRequestDetailView(request, response);
//                    break;
                case "/DeleteRequest":
                    deleteRequest(request, response);
                    break;
                case "/EditRequest":
                    editRequest(request, response, method);
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

    private void showRequestListView(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("account");
        String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
        String title = request.getParameter("title") != null ? request.getParameter("title") : "";
        String name = request.getParameter("name") != null ? request.getParameter("name") : "";
        int status = request.getParameter("status") != null ? Integer.parseInt(request.getParameter("status")) : 0;
        int support_type_id = request.getParameter("support_type_id") != null ? Integer.parseInt(request.getParameter("support_type_id")) : 0;
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int offset = (page - 1) * 3;
        //
        String sql1 = "SELECT r.id, r.request_date, r.title, (s.name) as RequestName, (u.fullname) as 'Incharge Staff', r.status, r.update_date FROM ((hr_system_v2.request r \n"
                + "                join hr_system_v2.`support type` s on r.support_type_id = s.id)\n"
                + "                join hr_system_v2.user u on r.in_charge_staff = u.id)\n"
                + "                where r.support_type_id = s.id";
        if (!fromDate.isEmpty()) {
            sql1 += " and r.request_date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            sql1 += " and r.request_date <= " + "'" + toDate + "'";
        }
        if (!title.isEmpty()) {
            sql1 += " and r.title like  " + "'%" + title + "%'";
        }
        if (!name.isEmpty()) {
            sql1 += " and s.name like  " + "'%" + name + "%'";
        }
        if (status != 0) {
            sql1 += " and r.status = " + "'" + status + "'";
        }
        sql1 += " limit 3 offset " + offset;
        //
        String sql2 = "SELECT count(r.support_type_id) FROM ((hr_system_v2.request r \n"
                + "join hr_system_v2.`support type` s on r.support_type_id = s.id)\n"
                + "join hr_system_v2.user u on r.in_charge_staff = u.id)\n"
                + "where r.support_type_id = s.id";
        if (!fromDate.isEmpty()) {
            sql2 += " and r.request_date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            sql2 += " and r.request_date <= " + "'" + toDate + "'";
        }

        if (!title.isEmpty()) {
            sql2 += " and r.title like  " + "'%" + title + "%'";
        }
        if (!name.isEmpty()) {
            sql2 += " and s.name like  " + "'%" + name + "%'";
        }
        if (status != 0) {
            sql2 += " and r.status = " + "'" + status + "'";
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
        request.setAttribute("supportName", supporttypeDAO.getAllSpName());
        request.setAttribute("requestStatus", requestDAO.getAllStatus());
        request.getRequestDispatcher("/Views/RequestView.jsp").forward(request, response);

    }

// </editor-fold>
    private void deleteRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        requestDAO.deleteGroupById(id);
        String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
        String title = request.getParameter("title") != null ? request.getParameter("title") : "";
        String name = request.getParameter("name") != null ? request.getParameter("name") : "";
        int status = request.getParameter("status") != null ? Integer.parseInt(request.getParameter("status")) : 0;
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        String sql2 = "SELECT count(r.support_type_id) FROM ((hr_system_v2.request r \n"
                + "join hr_system_v2.`support type` s on r.support_type_id = s.id)\n"
                + "join hr_system_v2.user u on r.in_charge_staff = u.id)\n"
                + "where r.support_type_id = s.id";
        if (!fromDate.isEmpty()) {
            sql2 += " and r.request_date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            sql2 += " and r.request_date <= " + "'" + toDate + "'";
        }

        if (!title.isEmpty()) {
            sql2 += " and r.title like  " + "'%" + title + "%'";
        }
        if (!name.isEmpty()) {
            sql2 += " and s.name like  " + "'%" + name + "%'";
        }
        if (status != 0) {
            sql2 += " and r.status = " + "'" + status + "'";
        }

        int count = requestDAO.getTotalRequest(sql2);
        int total = count / 3 + (count % 3 == 0 ? 0 : 1);
        response.getWriter().print(Math.min(total, page));

    }

    private void addRequest(HttpServletRequest request, HttpServletResponse response, String method) {
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                groupAddImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                addGroupView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void groupAddImplement(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
        String title = request.getParameter("title");
        String update_date = request.getParameter("update_date");
        String request_date = request.getParameter("request_date");
        String support_type_id = request.getParameter("support_type_id");
        String in_charge_staff = request.getParameter("in_charge_staff"); //null
        String status = request.getParameter("status");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (sdf.parse(update_date).before(sdf.parse(request_date))) {
           requestDAO.addnewrequest(title, request_date, update_date, Integer.parseInt(support_type_id), Integer.parseInt(in_charge_staff), Integer.parseInt(status));
        request.getSession().setAttribute("message", "Add Project Successfully!!");
        response.sendRedirect("../Request/AddRequest");
        } else {
            request.getSession().setAttribute("message", "End Date must after Start Date !!");
             response.sendRedirect("../Request/AddRequest");
        }
        
    }

    private void addGroupView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setAttribute("listSP", supporttypeDAO.getAllSpName());
        request.setAttribute("listU", userDAO.getManagerFullname());
        request.getRequestDispatcher("../Views/RequestViewAdd.jsp").forward(request, response);
    }

    private void editRequest(HttpServletRequest request, HttpServletResponse response, String method) {
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                requestEditImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                editRequestView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void requestEditImplement(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        String title = request.getParameter("title");
        String update_date = request.getParameter("update_date");
        String request_date = request.getParameter("request_date");
        String support_type_id = request.getParameter("support_type_id");
        String in_charge_staff = request.getParameter("in_charge_staff");
        String status = request.getParameter("status");
        int id = Integer.parseInt(request.getParameter("id"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (sdf.parse(update_date).before(sdf.parse(request_date))) {
        requestDAO.updateRequest(title, request_date, update_date, Integer.parseInt(support_type_id), Integer.parseInt(in_charge_staff),Integer.parseInt(status),id);
        request.getSession().setAttribute("message", "Add Project Successfully!!");
        response.sendRedirect("../Request/EditRequest");
        } else {
            request.getSession().setAttribute("message", "End Date must after Start Date !!");
             response.sendRedirect("../Request/EditRequest");
        }
    }

    private void editRequestView(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Request> requestlist = new RequestDAO().getOne(id);
        request.setAttribute("listSP", supporttypeDAO.getAllSpName());
        request.setAttribute("listU", userDAO.getManagerFullname());
        request.getRequestDispatcher("../Views/RequestViewEdit.jsp").forward(request, response);
        request.setAttribute("requestlist", requestlist);
    }

}
