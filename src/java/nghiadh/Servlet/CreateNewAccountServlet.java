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
import nghiadh.users.UsersCreateError;
import nghiadh.users.UsersDAO;

/**
 *
 * @author haseo
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
    private final String ERROR_PAGE = "createAccountPage";
    private final String LOGIN_PAGE = "loginPage";
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
        Map<String,String> resourceMap = (Map<String,String>) context.getAttribute("RESOURCE_MAP");
        //
        String url = resourceMap.get(ERROR_PAGE);
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");

        UsersCreateError error = new UsersCreateError();
        try{
            boolean foundError = false;
            if(username.trim().length()<6||username.trim().length()>20){
                foundError=true;
                error.setUsernameLengthErr("Username must be between 6-20 characters");
            }if(!username.trim().matches("[A-Za-z0-9._]+")){
                foundError=true;
                error.setUsernameContainsWrongCharsErr("Username must not contains special characters or space");
            }if(password.trim().length()<6||password.trim().length()>20){
                foundError=true;
                error.setPasswordLengthErr("Password must be between 6-20 characters");
            }if(!password.trim().matches("[0-9A-Za-z._]+")){
                foundError=true;
                error.setPasswordContainsWrongCharsErr("Password must not contains special characters or space");
            }else if(!confirm.trim().equals(password.trim())){
                foundError=true;
                error.setConfirmNotMatchedErr("Password and Confirm Must Matched");
            }if(fullName.trim().length()<6||fullName.trim().length()>20){
                foundError=true;
                error.setFullNameLengthErr("Fullname must be between 6-20 characters");
            }
            if(foundError){
                request.setAttribute("CREATE_ERROR", error);
            }else{
                UsersDAO dao = new UsersDAO();
                if(dao.createNewAccount(username, password, fullName, false)){
                    url =resourceMap.get(LOGIN_PAGE);
                }
            }
            
        } catch (SQLException ex) {
            if(ex.getMessage().contains("duplicate")){
                error.setUsernameIsExisted("Username is Existed");
                request.setAttribute("CREATE_ERROR", error);
            }
            log(ex.getMessage());
        } catch (NamingException ex) {
            log(ex.getMessage());
        }finally{
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
