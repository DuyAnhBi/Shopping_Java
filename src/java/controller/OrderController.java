/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.OrderDAO;

/**
 *
 * @author TRANG
 */
@WebServlet(name="OrderController", urlPatterns={"/OrderControllerURL"})
public class OrderController extends HttpServlet {
   
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
            OrderDAO oDAO = new OrderDAO();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of Orders</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println(" <form action=\"OrderControllerURL\" method=\"get\">\n"
                    + "        <p>Search Staff_id: <input type=\"text\" name=\"staff_id\"></p>\n"
                    + "        <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                    + "        <input type=\"reset\" value=\"Clear\">\n"
                    + "    </form>");
            String submit = request.getParameter("submit");
            out.println("<table border : 1px >\n"
                    + "        <caption>List of Orders</caption>\n"
                    + "        <tr>\n"
                    + "            <th>order_id</th>\n"
                    + "            <th>customer_id</th>\n"
                    + "            <th>order_status</th>\n"
                    + "            <th>order_date</th>\n"
                    + "            <th>required_date</th>\n"
                    + "            <th>shipped_date</th>\n"
                    + "            <th>store_id</th>\n"
                    + "            <th>staff_id</th>\n"
                    + "            <th>Update</th>\n"
                    + "            <th>Delete</th>\n"
                    + "        </tr>");
            Vector<Order> vector;
            if(submit != null){
                String staff_id = request.getParameter("staff_id");
                vector = oDAO.getOrder("select * from orders where staff_id =" + staff_id);
            }else{
                vector = oDAO.getOrder("select * from orders");
            }
            for (Order o : vector) {
                out.println("<tr>\n"
                        + "            <td>"+o.getOrder_id()+"</td>\n"
                        + "            <td>"+o.getCustomer_id()+"</td>\n"
                        + "            <td>"+o.getOrder_status()+"</td>\n"
                        + "            <td>"+o.getOrder_date()+"</td>\n"
                        + "            <td>"+o.getRequired_date()+"</td>\n"
                        + "            <td>"+o.getShipped_date()+"</td>\n"
                        + "            <td>"+o.getStore_id()+"</td>\n"
                        + "            <td>"+o.getStaff_id()+"</td>\n"
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
