/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EmployeeDAO;
import DAO.GroupDAO;
import Models.Group;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author quocb
 */
@WebServlet(name = "GroupListController", urlPatterns = {"/grouplist"})
public class GroupListController extends HttpServlet {

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
        try {
            String setting_type = request.getParameter("type");
            String input = request.getParameter("input");
            String page = request.getParameter("page");
            if (page == null) page = "1";
            request.setAttribute("page", page);
            GroupDAO gDAO = new GroupDAO();
            int count = gDAO.totalGroup();
            int endPage = count/5;
            if (endPage % 5 != 0) endPage++;
            request.setAttribute("endP", endPage);
            Vector<Group> g = new Vector();
            if (setting_type == null || input == null) {
                g = gDAO.getGroupList(Integer.parseInt(page));
            } else {
                g = gDAO.getGroupBySearch(setting_type, input);
            }
            request.setAttribute("listAG", g);
            request.getRequestDispatcher("Views/GroupView.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("ádfasdfasdfasd" + e.getMessage());
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

}
