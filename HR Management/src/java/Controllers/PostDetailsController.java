/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.BlogDAO;
import Models.BLog;
import Models.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lehun
 */
@WebServlet(name = "PostDetailsController", urlPatterns = {"/PostDetailsController"})
public class PostDetailsController extends HttpServlet {

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
            throws  IOException, ParseException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String Slug = request.getParameter("Slug");
            BlogDAO eDAO = new BlogDAO();
            BLog b = eDAO.GetBlogBySlug(Slug);
            Vector<Category> e = new Vector<>();
            e = eDAO.GetCategory();
            request.setAttribute("CCS", e);
            request.setAttribute("postDetails", b);
            
            request.getRequestDispatcher("Views/PostDetails.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        String Slug = request.getParameter("Slug");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String publishDate = dtf.format(now);

        String thumnails = request.getParameter("ThumnailIMG");
        String category = request.getParameter("Category");
        String Author = request.getParameter("Author");
        String Tittle = request.getParameter("Tittle");
        String Brieft = request.getParameter("Brieft");
        String Content = request.getParameter("Content");
        int Flag = Integer.parseInt(request.getParameter("Flag"));
        BlogDAO eDAO = new BlogDAO();
        BLog b = new BLog(thumnails, Tittle, Brieft, category, publishDate, Slug, Content, Author, Flag);
        try {
            Boolean check = eDAO.UpdatePost(b, Slug);
            if (check == false) {
                return;
            }
            response.sendRedirect("PostController/Views");
        } catch (Exception ex) {
            Logger.getLogger(PostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
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
