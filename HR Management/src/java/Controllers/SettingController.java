/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.SettingDAO;
import Models.Setting;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hide
 */
public class SettingController extends HttpServlet {

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
                case "/Setting":
                    settingListImplement(request, response);
                    break;
                case "/AddView":
                    request.getRequestDispatcher("../Views/SettingAdd.jsp").forward(request, response);
                    break;
                case "/Add":
                    settingAdd(request, response);
//                    settingListImplement(request, response);
                    break;
                case "/Status":
                    changeStatus(request, response);
                    break;
                case "/Filter":
                    filterSetting(request, response);
                    break;
                case "/Search":
                    searchSetting(request, response);
                    break;
                case "/EditView":
                    settingEditView(request, response);
                    break;
                case "/Edit":
                    settingEdit(request, response);
                    break;
                case "/Delete":
                    settingDelete(request, response);
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

    private void settingListImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            HttpSession ses = request.getSession();
            String input = (String) ses.getAttribute("session");
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("page", page);
            SettingDAO sDAO = new SettingDAO();

            Vector<String> t = sDAO.getAllType();
            request.setAttribute("listT", t);
            boolean check = true;
            for (String type : t) {
                if (type.equals(input)) {
                    check = false;
                    break;
                }
            }
            int count;
            Vector<Setting> s;
            if (input == null || input.isEmpty()) {
                s = sDAO.getSettingList(Integer.parseInt(page));
                count = sDAO.getTotalSetting(null, null);
            } else {
                if ("0".equals(input) || "1".equals(input)) {
                    s = sDAO.filterSettingList(input, 2, Integer.parseInt(page));
                    count = sDAO.getTotalSetting(input, null);
                } else {
                    if (check) {
                        s = sDAO.searchSetting(input, Integer.parseInt(page));
                        count = sDAO.getTotalSetting(null, input);
                    } else {
                        s = sDAO.filterSettingList(input, 1, Integer.parseInt(page));
                        count = sDAO.getTotalSetting(input, null);
                    }
                }
            }
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            request.setAttribute("listS", s);
            request.getRequestDispatcher("../Views/SettingList.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("ádfasdfasdfasd" + e.getMessage());
        }
    }

    private void searchSetting(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String search = request.getParameter("input");
            HttpSession ses = request.getSession();
            if (null != search) {
                ses.setAttribute("session", search);
                ses.setMaxInactiveInterval(-1);
            } else {
                ses.removeAttribute("session");
            }
            response.sendRedirect("../SettingController/Setting");
        } catch (Exception e) {
            System.out.println("ádfasdfasdfasd" + e.getMessage());
        }
    }

    //Add
    private void settingAdd(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String type = request.getParameter("type");
            String value = request.getParameter("value");
            String order = request.getParameter("order");
            String status = request.getParameter("foo");
            String note = request.getParameter("note");
            int o = Integer.parseInt(order);
            boolean sta = Boolean.parseBoolean(status);
            Setting st = new Setting(1, type, value, sta, o, note);

            SettingDAO sDAO = new SettingDAO();
            boolean check = sDAO.addNewSetting(st);

            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("page", page);
            int count = sDAO.getTotalSetting(null, null);
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            Vector<Setting> s;
            s = sDAO.getSettingList(Integer.parseInt(page));
            request.setAttribute("listS", s);
            request.getRequestDispatcher("../Views/SettingList.jsp").forward(request, response);
        }
    }

    private void changeStatus(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String status = request.getParameter("status");
            String employee_id = request.getParameter("id");
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            int st = Integer.parseInt(status);
            int i = Integer.parseInt(employee_id);
            SettingDAO s = new SettingDAO();
            s.editStatus(st, i);
            response.sendRedirect("../SettingController/Setting?page=" + page);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private void filterSetting(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            String filter = request.getParameter("input");
            HttpSession ses = request.getSession();
            if (!"All".equals(filter)) {
                ses.setAttribute("session", filter);
                ses.setMaxInactiveInterval(-1);
            } else {
                ses.removeAttribute("session");
            }
            response.sendRedirect("../SettingController/Setting");
        }
    }
    //editview

    private void settingEditView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String id = request.getParameter("id");
            String type = request.getParameter("type");
            String value = request.getParameter("value");
            String order = request.getParameter("order");
            String status = request.getParameter("status");
            String note = request.getParameter("note");
            int i = Integer.parseInt(id);
            int o = Integer.parseInt(order);
            boolean sta = Boolean.parseBoolean(status);
            Setting s = new Setting(i, type, value, sta, o, note);
            request.setAttribute("listS", s);
            request.getRequestDispatcher("../Views/SettingEdit.jsp").forward(request, response);
        }
    }

    //editview
    private void settingEdit(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String id = request.getParameter("spid");
            String type = request.getParameter("type");
            String value = request.getParameter("value");
            String order = request.getParameter("order");
            String status = request.getParameter("foo");
            String note = request.getParameter("note");
            int i = Integer.parseInt(id);
            int o = Integer.parseInt(order);
            boolean sta = status.contains("1") ? true : false;
            Setting st = new Setting(i, type, value, sta, o, note);
            SettingDAO sDAO = new SettingDAO();
            boolean check = sDAO.updateSettingList(st, i);

            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("page", page);
            int count = sDAO.getTotalSetting(null, null);
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            Vector<Setting> s;
            s = sDAO.getSettingList(Integer.parseInt(page));
            request.setAttribute("listS", s);
            request.getRequestDispatcher("../Views/SettingList.jsp").forward(request, response);
        }
    }

    //delete
    private void settingDelete(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String id = request.getParameter("id");
            int i = Integer.parseInt(id);
            SettingDAO sDAO = new SettingDAO();
            sDAO.deleteByID(i);

            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("page", page);
            int count = sDAO.getTotalSetting(null, null);
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            Vector<Setting> s;
            s = sDAO.getSettingList(Integer.parseInt(page));
            request.setAttribute("listS", s);
            request.getRequestDispatcher("../Views/SettingList.jsp").forward(request, response);
        }
    }
}
