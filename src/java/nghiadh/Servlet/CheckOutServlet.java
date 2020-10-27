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
import javax.servlet.http.HttpSession;
import nghiadh.cart.CartObject;
import nghiadh.cart.CheckOutError;
import nghiadh.orderDetails.OrderDetailsDAO;
import nghiadh.orders.OrdersDAO;

/**
 *
 * @author haseo
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String CHECK_OUT_PAGE = "checkOutPage";
    private final String CHECK_OUT_SUCCESS_PAGE = "checkOutSuccessPage";
    private final String CHECK_OUT_FAILED_PAGE = "checkOutFailedPage";

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
        //
        ServletContext context = request.getServletContext();
        Map<String, String> resourceMap = (Map<String, String>) context.getAttribute("RESOURCE_MAP");
        //
        String url = CHECK_OUT_FAILED_PAGE;
        String customerName = request.getParameter("txtCustomerName");
        String customerAddress = request.getParameter("txtCustomerAddress");
        CheckOutError error = new CheckOutError();
        boolean foundError = false;
        if (customerName.trim().isEmpty()) {
            foundError = true;
            error.setCustomerNameEmptyErr("Customer Name Must Not Be Empty");
        }
        if (customerAddress.trim().isEmpty()) {
            foundError = true;
            error.setCustomerAddressEmptyErr("Customer Address Must Not Be Empty");
        }
        try {
            if (!foundError) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    CartObject cart = (CartObject) session.getAttribute("CART");
                    if (cart != null) {
                        Map<String, Integer> items = cart.getItems();

                        OrdersDAO dao = new OrdersDAO();

                        if (dao.insertOrder(customerName, customerAddress, items)) {
                            int ordID = dao.getOrderID(customerName, customerAddress);
                            if (ordID > 0) {
                                OrderDetailsDAO detailDAO = new OrderDetailsDAO();
                                boolean rs = detailDAO.insertOrderDetails(ordID, items);
                                if (rs) {
                                    url = CHECK_OUT_SUCCESS_PAGE;
                                    session.setAttribute("CART", null);
                                }
                            }
                        }
                    }
                }
            } else {
                url = CHECK_OUT_PAGE;
                request.setAttribute("CHECKOUTERROR", error);
            }
        } catch (SQLException ex) {
            log("CheckOutServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOutServlet_NamingException: " + ex.getMessage());
        } finally {
            url = resourceMap.get(url);
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
