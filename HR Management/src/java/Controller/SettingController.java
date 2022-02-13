/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SettingDAO;
import Models.Setting;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hide
 */
public class SettingController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String action = request.getPathInfo() == null ? "" : request.getPathInfo();
            String method = request.getMethod();
            switch (action) {
                case "/Setting":
                    settingListImplement(request, response);
                    break;
                case "/Add":
//                    settingListImplement(request, response);
                    break;
                case "/Status":
                    changeStatus(request, response);
                    break;
                case "/EditView":
                    settingEditView(request, response);
                    break;
                case "/Edit":
                    settingEditView(request, response);
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

    private void settingListImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("page", page);
            SettingDAO sDAO = new SettingDAO();
            int count = sDAO.getTotalSetting();
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            Vector<Setting> s;
            s = sDAO.getSettingList(Integer.parseInt(page));
            request.setAttribute("listS", s);
            request.getRequestDispatcher("../Views/SettingList.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("ádfasdfasdfasd" + e.getMessage());
        }
    }

    private void changeStatus(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String status = request.getParameter("status");
            String employee_id = request.getParameter("id");
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            int st = Integer.parseInt(status);
            int i = Integer.parseInt(employee_id);
            SettingDAO s = new SettingDAO();
            s.editStatus(st, i);
            response.sendRedirect("../SettingController/Setting?page=" + page);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    //editview

    private void settingEditView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String id = request.getParameter("id");
            String type = request.getParameter("type");
            String value = request.getParameter("value");
            String order = request.getParameter("order");
            String status = request.getParameter("status");
            String note = request.getParameter("note");
            int i = Integer.parseInt(id);
            int o = Integer.parseInt(order);
            boolean sta = Boolean.parseBoolean(status);
            Setting s = new Setting(i, type, value, sta, o, note);
            request.setAttribute("listS", s);
            request.getRequestDispatcher("../Views/SettingEdit.jsp").forward(request, response);
        }
    }
}
