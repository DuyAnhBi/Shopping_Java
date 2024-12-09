/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import entity.Staff;
import entity.Store;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.StoreDAO;

/**
 *
 * @author TRANG
 */
@WebServlet(name="StoreController", urlPatterns={"/StoreControllerURL"})
public class StoreController extends HttpServlet {
   
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
            StoreDAO sDAO = new StoreDAO();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of Stores</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println(" <form action=\"StoreControllerURL\" method=\"get\">\n"
                    + "        <p>Search store_name: <input type=\"text\" name=\"store_name\"></p>\n"
                    + "        <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                    + "        <input type=\"reset\" value=\"Clear\">\n"
                    + "    </form>");
            String submit = request.getParameter("submit");
            
            out.println("<table border : 1px>\n"
                    + "        <caption>List of Store</caption>\n"
                    + "        <tr>\n"
                    + "            <th>store_name</th>\n"
                    + "            <th>phone</th>\n"
                    + "            <th>email</th>\n"
                    + "            <th>street</th>\n"
                    + "            <th>city</th>\n"
                    + "            <th>state</th>\n"
                    + "            <th>zip_code</th>\n"
                    + "            <th>Update</th>\n"
                    + "            <th>Delete</th>\n"
                    + "        </tr>");
            Vector<Store> vector = null;
            if(submit == null){
                vector = sDAO.getStore("select * from stores");
            }else{
                String Sname = request.getParameter("store_name");
                vector = sDAO.getStore("select * from stores where store_name like '%" +Sname+"%'");
            }
            for (Store s : vector) {
                out.println("<tr>\n"
                        + "            <td>"+s.getStore_name()+"</td>\n"
                        + "            <td>"+s.getPhone()+"</td>\n"
                        + "            <td>"+s.getEmail()+"</td>\n"
                        + "            <td>"+s.getStreet()+"</td>\n"
                        + "            <td>"+s.getCity()+"</td>\n"
                        + "            <td>"+s.getState()+"</td>\n"
                        + "            <td>"+s.getZip_code()+"</td>\n"
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
