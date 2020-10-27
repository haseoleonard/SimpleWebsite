/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nghiadh.users.UsersDAO;
import nghiadh.users.UsersUpdateError;

/**
 *
 * @author haseo
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

    private final String SEARCH_CONTROLLER = "search";

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
//        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("RESOURCE_MAP");

        String username = request.getParameter("txtUsername").trim();
        String newPassword = request.getParameter("txtPassword").trim();
        String newFullname = request.getParameter("txtFullname").trim();
        String role = request.getParameter("chkAdmin");
        boolean admin = false;
        UsersUpdateError error = new UsersUpdateError();
        error.setUpdatedUsername(username);
        String lastSearchValue = request.getParameter("lastSearchValue");
        String url = map.get(SEARCH_CONTROLLER)
                + "?txtSearchValue=" + lastSearchValue;
        if (role != null) {
            admin = true;
        }
        boolean foundError = false;
        if (newPassword.trim().length() < 6 || newPassword.trim().length() > 22) {
            foundError = true;
            error.setPasswordLengthErr("Password Must be between 6-22 Characters");
        }
        if (newFullname.trim().length() < 6 || newFullname.trim().length() > 22) {
            foundError = true;
            error.setFullNameLengthErr("Full Name Must be between 6-22 Characters");
        }
        try {
            if (!foundError) {
                UsersDAO dao = new UsersDAO();
                if (dao.updateAccount(username, newPassword, newFullname, admin)) {

                }
            } else {
                request.setAttribute("UPDATEERROR", error);
            }
        } catch (SQLException ex) {
            log("UpdateAccountServlet_SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateAccountServlet_NamingException:" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
