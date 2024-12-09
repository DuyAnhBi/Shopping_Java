/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import entity.OrderItem;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.OrderItemsDAO;

/**
 *
 * @author TRANG
 */
@WebServlet(name="OrderItemController", urlPatterns={"/OrderItemControllerURL"})
public class OrderItemController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            OrderItemsDAO oDAO = new OrderItemsDAO();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of OrderItems</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"OrderItemControllerURL\" method=\"get\">\n"
                    + "        <p>Search quantity of product with id: <input type=\"text\" name=\"product_id\"></p>\n"
                    + "        <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                    + "        <input type=\"reset\" value=\"Clear\">\n"
                    + "    </form>");
            out.println("<table border : 1px>\n"
                    + "        <caption>List of OrderItems</caption>\n"
                    + "        <tr>\n"
                    + "            <th>order_id</th>\n"
                    + "            <th>item_id</th>\n"
                    + "            <th>product_id</th>\n"
                    + "            <th>quantity</th>\n"
                    + "            <th>list_price</th>\n"
                    + "            <th>discount</th>\n"
                    + "            <th>Update</th>\n"
                    + "            <th>Delete</th>\n"
                    + "        </tr>");
            String submit = request.getParameter("submit");
            Vector<OrderItem> vector;
            if(submit!=null){
                String product_id = request.getParameter("product_id");
                vector = oDAO.getOrderItem("select * from order_items where product_id = " + product_id);
            }else{
                vector = oDAO.getOrderItem("select * from order_items");
            }
            for (OrderItem o : vector){
                out.println("<tr>\n"
                        + "            <td>"+o.getOrder_id()+"</td>\n"
                        + "            <td>"+o.getItem_id()+"</td>\n"
                        + "            <td>"+o.getProduct_id()+"</td>\n"
                        + "            <td>"+o.getQuantity()+"</td>\n"
                        + "            <td>"+o.getList_price()+"</td>\n"
                        + "            <td>"+o.getDiscount()+"</td>\n"
                        + "            <td></td>\n"
                        + "            <td></td>\n"
                        + "        </tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
