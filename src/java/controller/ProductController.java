/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.ProductDAO;

/**
 *
 * @author TRANG
 */
@WebServlet(name="ProductController", urlPatterns={"/ProductControllerURL"})
public class ProductController extends HttpServlet {
   
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
            ProductDAO pDAO = new ProductDAO();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lists of Products</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"ProductControllerURL\" method=\"get\">\n"
                    + "        <p>Search Brand_name: <input type=\"text\" name=\"brand_name\"></p>\n"
                    + "        <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                    + "        <input type=\"reset\" value=\"Clear\">\n"
                    + "    </form>");
            out.println("<table border : 1px >\n"
                    + "        <caption>Stock of Products</caption>\n"
                    + "        <tr>\n"
                    + "            <th>product_id</th>\n"
                    + "            <th>product_name</th>\n"
                    + "            <th>model_year</th>\n"
                    + "            <th>list_price</th>\n"
                    + "            <th>brand_name</th>\n"
                    + "            <th>category_name</th>\n"
                    + "            <th>Update</th>\n"
                    + "            <th>Delete</th>\n"
                    + "        </tr>");
            String submit = request.getParameter("submit");
            Vector<Product> vector;
            if(submit!= null){
                String brand_name = request.getParameter("brand_name");
                vector = pDAO.getProduct("select * from products where brand_name like'%"+brand_name+"%'");
            }else{
                vector = pDAO.getProduct("select * from products");
            }
            for(Product p : vector){
                out.println("<tr>\n"
                        + "            <td>"+p.getProduct_id()+"</td>\n"
                        + "            <td>"+p.getProduct_name()+"</td>\n"
                        + "            <td>"+p.getModel_year()+"</td>\n"
                        + "            <td>"+p.getList_price()+"</td>\n"
                        + "            <td>"+p.getBrand_name()+"</td>\n"
                        + "            <td>"+p.getCategory_name()+"</td>\n"
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
