/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ContractDAO;
import DAO.EmployeeDAO;
import DAO.SettingDAO;
import DAO.UserDAO;
import Models.Contract;
import Models.Employee;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
                case "/Add":
                    ContractAdd(request, response, method);
                    break;
                case "/EditContract":
                    EditContract(request, response, method);
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

    // <editor-fold defaultstate="collapsed" desc="ContractDetails">
    private void ContractDetails(HttpServletRequest request, HttpServletResponse response, String method) {
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
            HttpSession session = request.getSession();
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

            LocalDateTime now = LocalDateTime.now();
            for (int i = 0; i < c.size(); i++) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate endDate = LocalDate.parse(c.get(i).getEndDate(), formatter);
                LocalDateTime ldt = LocalDateTime.of(endDate, LocalDateTime.now().toLocalTime());
                if (ldt.isAfter(now)) {
                    c.get(i).setStatus('1');
                    cDAO.setStatus(c.get(i));
                }
            }
            request.setAttribute("listC", c);
            request.getRequestDispatcher("../Views/Contract.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ContractAdd">
    private void ContractAdd(HttpServletRequest request, HttpServletResponse response, String method) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                contractAddImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                addContractView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void contractAddImplement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            idStr = "-1";
        }
        int id = Integer.parseInt(idStr);
        UserDAO userDAO = new UserDAO();
        List<User> listUser = userDAO.getUserById(Integer.parseInt(idStr));
        List<User> listU = userDAO.getUserList();
        request.setAttribute("listU", listU);
        request.setAttribute("listUser", listUser);
        request.setAttribute("idSelected", id);
        request.getRequestDispatcher("../Views/ContractAdd.jsp").forward(request, response);

    }

    private void addContractView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = new UserDAO().getUserList();
        request.setAttribute("listU", listUser);
        request.getRequestDispatcher("../Views/ContractAdd.jsp").forward(request, response);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="EditContract">
    private void EditContract(HttpServletRequest request, HttpServletResponse response, String method) {
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                contractEditImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                editContractView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void editContractView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Contract> contract = new ContractDAO().getOne(Integer.parseInt(id));
        request.setAttribute("contract", contract);
        request.getRequestDispatcher("../Views/ContractEdit.jsp").forward(request, response);
    }

    private void contractEditImplement(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        String id = request.getParameter("id");
        String StartDate = request.getParameter("StartDate");
        String EndDate = request.getParameter("EndDate");
        ContractDAO contractDAO = new ContractDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (sdf.parse(StartDate).before(sdf.parse(EndDate))) {
            contractDAO.updateContract(EndDate, Integer.parseInt(id));
            request.getSession().setAttribute("message", "Edit Contract Successfully!!");
            response.sendRedirect("../Contract/EditContract?id=" + id);
        } else {
            request.getSession().setAttribute("message", "End Date must after Start Date!!");

            response.sendRedirect("../Contract/EditContract?id=" + id);
        }
    }
    // </editor-fold>
}
