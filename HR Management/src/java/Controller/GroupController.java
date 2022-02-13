/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GroupDAO;
import Models.Group;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "GroupController", urlPatterns = {"/Group/*"})
public class GroupController extends HttpServlet {

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
                case "/GroupList":
                    groupListImplement(request, response);
                    break;
                case "/GroupEdit":
                    GroupEdit(request, response, method);
                    break;
                case "/GroupAdd":
                    GroupAdd(request, response, method);
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

        // <editor-fold defaultstate="collapsed" desc="GroupList">
//     private void groupList(HttpServletRequest request, HttpServletResponse response, String method)
//            throws Exception {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter();) {
//            if (method.equalsIgnoreCase("post")) {
//                groupListImplement(request, response);
//            } else if (method.equalsIgnoreCase("get")) {
//                showGroupView(request, response);
//            }
//        } catch (Exception ex) {
//            log(ex.getMessage());
//        }
//    }
    private void groupListImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String group_type = request.getParameter("type");
            String input = request.getParameter("input");
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }
            request.setAttribute("page", page);
            GroupDAO gDAO = new GroupDAO();
            int count = gDAO.totalGroup();
            int endPage = count / 6;
            if (endPage % 5 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            request.setAttribute("gr", group_type);
            request.setAttribute("txtS", input);
            Vector<Group> g = new Vector();
            if (input == null || input.isEmpty()) {
                g = gDAO.getGroupList(Integer.parseInt(page));
            } else {
                g = gDAO.getGroupBySearch(input);
            }
            request.setAttribute("listG", g);
            request.getRequestDispatcher("../Views/GroupView.jsp").forward(request, response);
        }
    }

    private void showGroupView(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        //out.println(request.getContextPath());
        request.getRequestDispatcher("../Views/GroupView.jsp").forward(request, response);
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="GroupEdit">
    private void GroupEdit(HttpServletRequest request, HttpServletResponse response, String method) {
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                groupEditImplement(request, response);
            } else if (method.equalsIgnoreCase("get")) {
                editGroupView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void editGroupView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Vector<Group> group = new GroupDAO().getOne(Integer.parseInt(id));
        request.setAttribute("group", group);
        request.getRequestDispatcher("../Views/GroupViewEdit.jsp").forward(request, response);
    }

    private void groupEditImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String code = request.getParameter("code");
            String manager = request.getParameter("manager_id");
            String name = request.getParameter("name");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String description = request.getParameter("description");
            String parent_group_code = request.getParameter("parent_group_code");
            String update_date = request.getParameter("update_date");
            String id = request.getParameter("id");

            GroupDAO group = new GroupDAO();
            group.editGroup(code, manager, name, status, description, parent_group_code, update_date, Integer.parseInt(id));
            response.sendRedirect("../Contract/GroupEdit?id=" + id);
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GroupAdd">
    private void GroupAdd(HttpServletRequest request, HttpServletResponse response, String method) {
        try (PrintWriter out = response.getWriter();) {
            if (method.equalsIgnoreCase("post")) {
                GroupAdd(request, response, method);
            } else if (method.equalsIgnoreCase("get")) {
                editGroupView(request, response);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private void addGroupView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Vector<Group> group = new GroupDAO().getGroup();
        request.setAttribute("group", group);
        request.getRequestDispatcher("../Views/GroupViewAdd.jsp").forward(request, response);
    }

    private void groupAddImplement(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter();) {
            String idStr = request.getParameter("id");
            if (idStr == null) {
                idStr = "-1";
            }
            int id = Integer.parseInt(idStr);
            GroupDAO groupDAO = new GroupDAO();
            List<Group> Vgroup = groupDAO.getOne(Integer.parseInt(idStr));
            List<Group> group = groupDAO.getGroup();
            request.setAttribute("group", group);
            request.setAttribute("Vgroup", Vgroup);
            request.setAttribute("idSelected", id);
            request.getRequestDispatcher("../Views/GroupViewAdd.jsp").forward(request, response);
        }
    }
    //</editor-fold>
    
}
