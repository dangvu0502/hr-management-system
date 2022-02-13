/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.GroupDAO;
import dao.SettingDAO;
import dao.SupportTypeDAO;
import models.Setting;
import models.SupportType;
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
@WebServlet(name = "SupportTypeController", urlPatterns = {"/SupportTypeController/*"})
public class SupportTypeController extends HttpServlet {

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
                case "/SupportType":
                    supportTypeListImplement(request, response);
                    break;
                case "/SupportTypeEdit":
                    supportTypeEditView(request, response);
                    break;
                case "/AddView":
                    request.getRequestDispatcher("../Views/SupportTypeAdd.jsp").forward(request, response);
                    break;
                case "/Add":
                    supportTypeAdd(request, response);
//                    settingListImplement(request, response);
                    break;
                case "/Edit":
                    supportTypeEdit(request, response);
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

    private void supportTypeListImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String group_type = request.getParameter("type");
            String input = request.getParameter("input");
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("page", page);
            SupportTypeDAO sDAO = new SupportTypeDAO();
            int count = sDAO.totalSupportType();
            int endPage = count / 3;
            if (endPage % 3 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            Vector<SupportType> s = new Vector();
            if (group_type == null || input == null || input.isEmpty()) {
                s = sDAO.getSupportTypeList(Integer.parseInt(page));
            } else {
                s = sDAO.getSupportTypeList(Integer.parseInt(page));//sai
            }
            request.setAttribute("listS", s);
            request.getRequestDispatcher("../Views/SupportTypeView.jsp").forward(request, response);
        }
    }

     //Add
    private void supportTypeAdd(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String name = request.getParameter("name");
            String incharge = request.getParameter("incharge");
            String email = request.getParameter("email");
            String status = request.getParameter("foo");
            String description = request.getParameter("description");
            boolean sta = Boolean.parseBoolean(status);
            SupportType st = new SupportType(1, name, email, sta, false, incharge, description);
            
            SupportTypeDAO sDAO = new SupportTypeDAO();
            boolean check = sDAO.addNewSupportType(st);

            String group_type = request.getParameter("type");
            String input = request.getParameter("input");
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("page", page);
            GroupDAO gDAO = new GroupDAO();
            int count = sDAO.totalSupportType();
            int endPage = count / 3;
            if (endPage % 3 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            Vector<SupportType> s = new Vector();
            if (group_type == null || input == null || input.isEmpty()) {
                s = sDAO.getSupportTypeList(Integer.parseInt(page));
            } else {
                s = sDAO.getSupportTypeList(Integer.parseInt(page));//sai
            }
            request.setAttribute("listS", s);
            request.getRequestDispatcher("../Views/SupportTypeView.jsp").forward(request, response);
        }
    }

    //EditView
    private void supportTypeEditView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String incharge = request.getParameter("incharge");
            String email = request.getParameter("email");
            String status = request.getParameter("status");
            String description = request.getParameter("description");
            int i = Integer.parseInt(id);
            boolean s = Boolean.parseBoolean(status);
            SupportType sDAO = new SupportType(i, name, email, s, false, incharge, description);
            request.setAttribute("listS", sDAO);
            request.getRequestDispatcher("../Views/SupportTypeEdit.jsp").forward(request, response);
        }
    }

    //Edit
    private void supportTypeEdit(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String id = request.getParameter("spid");
            String name = request.getParameter("name");
            String incharge = request.getParameter("incharge");
            String email = request.getParameter("email");
            String status = request.getParameter("foo");
            String description = request.getParameter("description");
            int i = Integer.parseInt(id);
//            boolean sta = Boolean.parseBoolean(status);
            boolean sta = status.contains("1") ? true : false;
            SupportType st = new SupportType(i, name, email, sta, false, incharge, description);
            SupportTypeDAO sDAO = new SupportTypeDAO();
            boolean check = sDAO.updateSupportType(st, i);

            String group_type = request.getParameter("type");
            String input = request.getParameter("input");
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("page", page);
            GroupDAO gDAO = new GroupDAO();
            int count = sDAO.totalSupportType();
            int endPage = count / 3;
            if (endPage % 3 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            Vector<SupportType> s = new Vector();
            if (group_type == null || input == null || input.isEmpty()) {
                s = sDAO.getSupportTypeList(Integer.parseInt(page));
            } else {
                s = sDAO.getSupportTypeList(Integer.parseInt(page));//sai
            }
            request.setAttribute("listS", s);
            request.getRequestDispatcher("../Views/SupportTypeView.jsp").forward(request, response);
        }
    }
}
