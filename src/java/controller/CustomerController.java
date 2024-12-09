/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.CustomerDAO;

/**
 *
 * @author TRANG
 */
@WebServlet(name="CustomerController", urlPatterns={"/CustomerControllerURL"})
public class CustomerController extends HttpServlet {
   
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
            CustomerDAO cDAO = new CustomerDAO();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of Customers</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println(" <form action=\"CustomerControllerURL\" method=\"get\">\n"
                    + "        <p>Search customer first_name: <input type=\"text\" name=\"customer_name\"></p>\n"
                    + "        <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                    + "        <input type=\"reset\" value=\"Clear\">\n"
                    + "    </form>");
            String submit = request.getParameter("submit");
            out.print("<table border : 1px>\n"
                    + "        <caption>List of Customer</caption>\n"
                    + "        <tr>\n"
                    + "            <th>customer_id</th>\n"
                    + "            <th>first_name</th>\n"
                    + "            <th>last_name</th>\n"
                    + "            <th>phone</th>\n"
                    + "            <th>email</th>\n"
                    + "            <th>street</th>\n"
                    + "            <th>city</th>\n"
                    + "            <th>state</th>\n"
                    + "            <th>zip_code</th>\n"
                    + "            <th>Update</th>\n"
                    + "            <th>Delete</th>\n"
                    + "        </tr>");
            Vector<Customer> vector = null;
            if(submit == null){
                vector = cDAO.getCustomer("select * from customers");
            }else{
                String name = request.getParameter("customer_name");
                vector = cDAO.getCustomer("select * from customers where first_name like '%"+name+"%'");
            }
            for (Customer c : vector) {
                out.println("<tr>\n"
                        + "            <td>"+c.getCustomer_id()+"</td>\n"
                        + "            <td>"+c.getFirst_name()+"</td>\n"
                        + "            <td>"+c.getLast_name()+"</td>\n"
                        + "            <td>"+c.getPhone()+"</td>\n"
                        + "            <td>"+c.getEmail()+"</td>\n"
                        + "            <td>"+c.getStreet()+"</td>\n"
                        + "            <td>"+c.getCity()+"</td>\n"
                        + "            <td>"+c.getState()+"</td>\n"
                        + "            <td>"+c.getZip_code()+"</td>\n"
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
