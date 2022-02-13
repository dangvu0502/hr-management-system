/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.BlogDAO;
import Models.BLog;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lehun
 */
public class BlogSearchController extends HttpServlet {

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
        String page = request.getParameter("page");
        String Search = request.getParameter("Search");
        String Type = request.getParameter("Type");
        if (page == null) {
            page = "1";
        }
        request.setAttribute("page", page);
        BlogDAO eDAO = new BlogDAO();
        int count = eDAO.GetTotalBlogBySearch(Type, Search);
        int endPage = count / 3;
        if (endPage % 5 != 0) {
            endPage++;
        }
        request.setAttribute("endP", endPage);
        request.setAttribute("Search", Search);
        request.setAttribute("Type", Type);
        try {
            Vector<BLog> e = new Vector();

            e = eDAO.SearchBlogByType(Integer.parseInt(page), Type, Search);
            request.setAttribute("listE", e);
            request.getRequestDispatcher("Views/BlogSearch.jsp").forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
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
        String page = request.getParameter("page");
        String Search = request.getParameter("inputSearch");
        String Type = request.getParameter("type");
        if (page == null) {
            page = "1";
        }
        request.setAttribute("page", page);
        BlogDAO eDAO = new BlogDAO();
        int count = eDAO.GetTotalBlogBySearch(Type, Search);
        int endPage = count / 3;
        if (endPage % 5 != 0) {
            endPage++;
        }
        request.setAttribute("endP", endPage);
        request.setAttribute("Search", Search);
        request.setAttribute("Type", Type);

        try {
            Vector<BLog> e = new Vector();

            e = eDAO.SearchBlogByType(Integer.parseInt(page), Type, Search);
            if (e.isEmpty()) {
                String Error = "Search Not Found";
                request.setAttribute("IsEmpty", Error);
                request.setAttribute("SearchResult", Search);
                request.getRequestDispatcher("Views/BlogSearch.jsp").forward(request, response);
                return;
            }
            request.setAttribute("listE", e);
            request.getRequestDispatcher("Views/BlogSearch.jsp").forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(BlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
