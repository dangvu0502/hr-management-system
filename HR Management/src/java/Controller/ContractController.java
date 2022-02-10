/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ContractDAO;
import DAO.EmployeeDAO;
import Models.Contract;
import Models.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Egamorft
 */
public class ContractController extends HttpServlet {

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
                case "/Details":
                    ContractDetails(request, response, method);
                    break;
                default:
                    out.println(pageNotFound);
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

    // <editor-fold defaultstate="collapsed" desc="ContractDetails">
    private void ContractDetails(HttpServletRequest request, HttpServletResponse response, String method) {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                contractImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                showContractView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void contractImplement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String setting_type = request.getParameter("type");
        String txtSearch = request.getParameter("txt");
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        request.setAttribute("page", page);
        ContractDAO cDAO = new ContractDAO();
        int count = cDAO.getTotalContract();
        int endPage = count / 2;
        if (endPage % 2 != 0) {
            endPage++;
        }
        request.setAttribute("endP", endPage);
        List<Contract> c = new ArrayList<>();
        if (setting_type == null || txtSearch == null) {
            c = cDAO.getContractList(Integer.parseInt(page));
        } else {
            c = cDAO.getContractBySearch(setting_type, txtSearch, Integer.parseInt(page));
            request.setAttribute("setting_type", setting_type);
            request.setAttribute("txtSearch", txtSearch);
        }
        request.setAttribute("listC", c);
        request.getRequestDispatcher("../Views/Contract.jsp").forward(request, response);
    }

    private void showContractView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            String setting_type = request.getParameter("type");
            String txtSearch = request.getParameter("txt");
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("page", page);
            ContractDAO cDAO = new ContractDAO();
            int count = cDAO.getTotalContract();
            int endPage = count / 2;
            if (endPage % 2 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            List<Contract> c = new ArrayList<>();
            c = cDAO.getContractList(Integer.parseInt(page));
            request.setAttribute("listC", c);
//            List<Contract> c = new ArrayList<>();
//            if (setting_type == null || input == null) {
//                c = cDAO.getContractList(Integer.parseInt(page));
//            } else {
//                c = cDAO.getContractBySearch(setting_type, input);
//            }
//            request.setAttribute("listC", c);
            request.getRequestDispatcher("../Views/Contract.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="pageNotFound">
    private String pageNotFound = "\n"
            + "<!DOCTYPE html>\n"
            + "<html>\n"
            + "    <head>\n"
            + "        <link href=\"https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@600;900&display=swap\" rel=\"stylesheet\">\n"
            + "        <script src=\"https://kit.fontawesome.com/4b9ba14b0f.js\" crossorigin=\"anonymous\"></script>\n"
            + "        <style>\n"
            + "            body {\n"
            + "                background-color: #95c2de;\n"
            + "            }\n"
            + "\n"
            + "            .mainbox {\n"
            + "                background-color: #95c2de;\n"
            + "                margin: auto;\n"
            + "                height: 600px;\n"
            + "                width: 600px;\n"
            + "                position: relative;\n"
            + "            }\n"
            + "\n"
            + "            .err {\n"
            + "                color: #ffffff;\n"
            + "                font-family: 'Nunito Sans', sans-serif;\n"
            + "                font-size: 11rem;\n"
            + "                position:absolute;\n"
            + "                left: 20%;\n"
            + "                top: 8%;\n"
            + "            }\n"
            + "\n"
            + "            .far {\n"
            + "                position: absolute;\n"
            + "                font-size: 8.5rem;\n"
            + "                left: 42%;\n"
            + "                top: 15%;\n"
            + "                color: #ffffff;\n"
            + "            }\n"
            + "\n"
            + "            .err2 {\n"
            + "                color: #ffffff;\n"
            + "                font-family: 'Nunito Sans', sans-serif;\n"
            + "                font-size: 11rem;\n"
            + "                position:absolute;\n"
            + "                left: 68%;\n"
            + "                top: 8%;\n"
            + "            }\n"
            + "\n"
            + "            .msg {\n"
            + "                text-align: center;\n"
            + "                font-family: 'Nunito Sans', sans-serif;\n"
            + "                font-size: 1.6rem;\n"
            + "                position:absolute;\n"
            + "                left: 16%;\n"
            + "                top: 45%;\n"
            + "                width: 75%;\n"
            + "            }\n"
            + "\n"
            + "            a {\n"
            + "                text-decoration: none;\n"
            + "                color: white;\n"
            + "            }\n"
            + "\n"
            + "            a:hover {\n"
            + "                text-decoration: underline;\n"
            + "            }\n"
            + "\n"
            + "        </style>\n"
            + "    </head>\n"
            + "    <body>\n"
            + "        <div class=\"mainbox\">\n"
            + "            <div class=\"err\">4</div>\n"
            + "            <i class=\"far fa-question-circle fa-spin\"></i>\n"
            + "            <div class=\"err2\">4</div>\n"
            + "            <div class=\"msg\">Maybe this page moved? Got deleted? Is hiding out in quarantine? Never existed in the first place?<p>Let's go <a href=\"#\">home</a> and try from there.</p></div>\n"
            + "        </div>\n"
            + "    </body>\n"
            + "</html>\n"
            + "\n"
            + "";
    //</editor-fold>

}
