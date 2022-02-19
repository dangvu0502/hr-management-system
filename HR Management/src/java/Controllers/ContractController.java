/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.ContractDAO;
import Dao.EmployeeDAO;
import Dao.SettingDAO;
import Dao.UserDAO;
import Models.Contract;
import Models.Employee;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
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

    private ContractDAO contractDAO;
    private SettingDAO settingDAO;

    public void init() {
        contractDAO = new ContractDAO();
        settingDAO = new SettingDAO();

    }

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
        request.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter();) {
            String action = request.getPathInfo() == null ? "" : request.getPathInfo();
            String method = request.getMethod();
            switch (action) {
                case "/Details":
                    showContractView(request, response);
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
    private void showContractView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("account");
        String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
        String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
        int status = request.getParameter("status") != null ? Integer.parseInt(request.getParameter("status")) : -1;
        String fullname = request.getParameter("fullname") != null ? request.getParameter("fullname") : "";
        int type = request.getParameter("type") != null ? Integer.parseInt(request.getParameter("type")) : -1;
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int offset = (page - 1) * 3;

        //this query for search and filter
        String query1 = "SELECT c.id, u.fullname, u.email, c.start_date, c.end_date, c.status, c.type \n"
                + "FROM hr_system_v2.contract c inner join hr_system_v2.user u \n"
                + "where c.user_id = u.id ";
        if (!fromDate.isEmpty()) {
            query1 += " and start_date >= " + "'" + fromDate + "'";
        }
        if (!toDate.isEmpty()) {
            query1 += " and end_date <= " + "'" + toDate + "'";
        }
        if (status != -1) {
            query1 += " and c.status =  " + "'" + status + "'";
        }
        if (!fullname.isEmpty()) {
            query1 += " and fullname like  " + "'%" + fullname + "%'";
        }
        if (type != -1) {
            query1 += " and type = " + "'" + type + "'";
        }
        query1 += " limit 3 offset " + offset;

        //this query for  total contract
        String query2 = "SELECT count(*) FROM (" + query1+") c";
//        if (!fromDate.isEmpty()) {
//            query2 += " and start_date >= " + "'" + fromDate + "'";
//        }
//        if (!toDate.isEmpty()) {
//            query2 += " and end_date <= " + "'" + toDate + "'";
//        }
//        if (status != -1) {
//            query2 += " and c.status =  " + "'" + status + "'";
//        }
//        if (!fullname.isEmpty()) {
//            query2 += " and fullname like  " + "'%" + fullname + "%'";
//        }
//        if (type != -1) {
//            query2 += " and type = " + "'" + type + "'";
//        }
        List<Contract> c = new ArrayList<>();
        c = contractDAO.getContractList(query1);
        LocalDateTime now = LocalDateTime.now();
            for (int i = 0; i < c.size(); i++) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate endDate = LocalDate.parse(c.get(i).getEndDate(), formatter);
                LocalDateTime ldt = LocalDateTime.of(endDate, LocalDateTime.now().toLocalTime());
                if (ldt.isAfter(now)) {
                    c.get(i).setStatus('1');
                    contractDAO.setStatus(c.get(i));
                }
            }

        int contractCount = contractDAO.getTotalContract(query2);
        int total = contractCount / 3 + (contractCount % 3 == 0 ? 0 : 1);
        int begin = 1;
        int end = 3;
        while (page > end) {
            end += 3;
            begin += 3;
        }
        end = Math.min(end, total);
        begin = Math.min(end, begin);
        request.setAttribute("total", total);
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
        request.setAttribute("currentNumber", page);
        request.setAttribute("contractList", contractDAO.getContractList(query1));
//        request.setAttribute("contractProcess", settingDAO.getTimesheetProcess());
//        request.setAttribute("contractStatus", settingDAO.getTimesheetStatus());
        request.getRequestDispatcher("/Views/Contract.jsp").forward(request, response);
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

    private void contractAddImplement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        String idStr = request.getParameter("id");
        String idc = request.getParameter("idc");
        String StartDate = request.getParameter("StartDate");
        String EndDate = request.getParameter("EndDate");
        if (idStr == null) {
            idStr = "-1";
        }
        int id = Integer.parseInt(idStr);
        UserDAO userDAO = new UserDAO();
        ContractDAO contractDAO = new ContractDAO();
        if (idc == null) {
            contractDAO.addNewContract(Integer.parseInt(idStr));
        }
        if (StartDate != null && EndDate != null) {
            contractDAO.addContract(StartDate, EndDate, Integer.parseInt(idc));
        }
        List<User> listUser = userDAO.getUserById(Integer.parseInt(idStr));
        List<User> listU = userDAO.getUserList();
        List<Contract> listContract = contractDAO.getContractByUserId(Integer.parseInt(idStr));
        request.setAttribute("listU", listU);
        request.setAttribute("listUser", listUser);
        request.setAttribute("listContract", listContract);
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
