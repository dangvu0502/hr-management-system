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

    private RequestDAO requestDAO;

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
      ArrayList<Request> r = new ArrayList<Request>();
      
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
