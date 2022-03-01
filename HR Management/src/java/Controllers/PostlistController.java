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
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "PostlistController", urlPatterns = {"/PostlistController/*"})
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getPathInfo() == null ? "" : request.getPathInfo();
        String method = request.getMethod();
        switch (action) {
            case "/Add":
                request.getRequestDispatcher("Views/PostAdd.jsp").forward(request, response);
                break;
            case "/Status":
                String Slug = request.getParameter("Slug");
                int Flag = Integer.parseInt(request.getParameter("Flag"));
                BlogDAO EDAO = new BlogDAO();
                EDAO.UpdateStatus(Flag, Slug);
                response.sendRedirect("../PostlistController");
                break;
            default:
                response.sendError(404);
                break;
        }
        ViewPostList(request, response);
    }

//    public void ADD(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("utf-8");
//        
//
//    }
    public void ViewPostList(HttpServletRequest request, HttpServletResponse response) {
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
                e = eDAO.GetPostLIst(Integer.parseInt(page));
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

    public void AddSubMit(HttpServletRequest request, HttpServletResponse response) {
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
            Boolean check = eDAO.CreatePost(b);
            if (check == false) {
                return;
            }
            response.sendRedirect("../PostlistController");
        } catch (Exception ex) {
            Logger.getLogger(PostDetailsController.class.getName()).log(Level.SEVERE, null, ex);
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
        ViewPostList(request, response);
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
        if ("/Addc".equals(request.getPathInfo())) {
            AddSubMit(request, response);
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
