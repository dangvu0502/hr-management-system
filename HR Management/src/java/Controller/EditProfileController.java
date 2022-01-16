/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.EmployeeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.Employee;
import java.io.File;
import java.io.InputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Egamorft
 */
@MultipartConfig()
public class EditProfileController extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            request.getRequestDispatcher("Views/EditProfile.jsp").forward(request, response);
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
//            EmployeeDAO dao = new EmployeeDAO();
//            String username = "admin";
//            request.setAttribute("employee", dao.checkUsernameExist(username));
//            request.getRequestDispatcher("Views/EditProfile.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
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

        try {
            EmployeeDAO dao = new EmployeeDAO();

            String fullname = request.getParameter("fullname");
            String avatar = request.getParameter("avatar");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
//            InputStream inputStream = null; // input stream of the upload file
//
//            // obtains the upload file part in this multipart request
//            Part filePart = request.getPart("image");
//            if (filePart != null) {
//                // prints out some information for debugging
//                System.out.println(filePart.getName());
//                System.out.println(filePart.getSize());
//                System.out.println(filePart.getContentType());
//
//                // obtains input stream of the upload file
//                inputStream = filePart.getInputStream();
//            }

//            Part part = request.getPart("image");
//            String fileName = part.getSubmittedFileName();
            //String path = getServletContext().getRealPath("../"+"img"+File.separator+fileName);
            HttpSession session = request.getSession();

            Employee employee = (Employee) session.getAttribute("account"); //EMPLOYEE!!!!
            if (employee.getPassword().equals(password)) {
                if (fullname != null) {

                    employee.setFullname(fullname);
                }
                session.setAttribute("account", employee);

                dao.UpdateProfile(fullname, "", username);
                request.setAttribute("error", "success");
                request.getRequestDispatcher("Views/EditProfile.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "danger");
                request.getRequestDispatcher("Views/EditProfile.jsp").forward(request, response);
            }

//            response.sendRedirect("EditProfile");
        } catch (Exception ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
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
