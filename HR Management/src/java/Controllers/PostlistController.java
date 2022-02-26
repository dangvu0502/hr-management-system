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
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lehun
 */
@WebServlet(name = "PostlistController", urlPatterns = {"/PostlistController"})
public class PostlistController extends HttpServlet {

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
            String page = request.getParameter("page");
            String SearchPost = request.getParameter("SearchPost") != null ? request.getParameter("SearchPost") : "";

            if (page == null) {
                page = "1";
            }
            init();
            request.setAttribute("page", page);
            BlogDAO eDAO = new BlogDAO();
            int count = 0;
            Vector<BLog> e = new Vector();
            if (!SearchPost.isEmpty()) {
                e = eDAO.SearchPostbyTittle(Integer.parseInt(page), SearchPost);
                count = eDAO.GetTotalPostByTittle(SearchPost);
                request.setAttribute("SearchPost", SearchPost);

            } else {
                count = eDAO.GetTotalBlog();
                e = eDAO.GetBlogList(Integer.parseInt(page));
            }
            int endPage = count / 3;
            if (endPage % 3 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            request.setAttribute("listE", e);
            request.getRequestDispatcher("Views/PostList.jsp").forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
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
