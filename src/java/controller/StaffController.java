/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.StaffDAO;

/**
 *
 * @author TRANG
 */
@WebServlet(name="StaffController", urlPatterns={"/StaffControllerURL"})
public class StaffController extends HttpServlet {
   
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
            /* TODO output your page here. You may use following sample code. */
            StaffDAO sDAO = new StaffDAO();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>:List of Staffs</title>");  
            out.println("</head>");
            out.println("<body>");
            String service = request.getParameter("service");
            if(service==null){//run direct servlet
                service = "viewAllAStaff";
            }
            if(service.equals("removeStaff")){
                int id = Integer.parseInt(request.getParameter("staff_id"));
                sDAO.removeStaff(id);
                response.sendRedirect("StaffControllerURL");
            }
            if(service.equals("insertStaff")){
                String staff_id_raw = request.getParameter("staff_id");
                String first_name_raw = request.getParameter("fname");
                String last_name_raw = request.getParameter("lname");
                String email_raw = request.getParameter("email");
                String phone_raw = request.getParameter("phone");
                String active_raw = request.getParameter("active");
                String store_id_raw = request.getParameter("store");
                String manager_id_raw = request.getParameter("manager_id");
                // check du lieu : empty, lenght
                String sql = "select * from staffs where staff_id =" +Integer.parseInt(staff_id_raw);
                Vector<Staff> vector = sDAO.getStaff(sql);
                if(vector.size()>0){
                    //duplicate
                    //dua ra thong bao
                    response.sendRedirect("StaffControllerURL?service=viewAllAStaff");
                }
                //convert
                int staff_id = Integer.parseInt(staff_id_raw);
                int active = Integer.parseInt( active_raw);
                int store_id = Integer.parseInt(store_id_raw );
                int manager_id = Integer.parseInt(manager_id_raw);
                Staff s = new Staff(staff_id, first_name_raw, last_name_raw,
                        email_raw, phone_raw, active, store_id, manager_id);
                sDAO.addStaff(s);
                response.sendRedirect("StaffControllerURL?service=viewAllAStaff");
            }
            if(service.equals("viewAllAStaff")){
                out.println("<form action=\"StaffControllerURL\" method=\"get\">\n"
                        + "        <p>Search first_name: <input type=\"text\" name=\"first_name\"></p>\n"
                        + "        <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                        + "        <input type=\"reset\" value=\"Clear\">\n"
                        + "    </form>");
                String submit = request.getParameter("submit");
                out.println("<a href=\"insertStaff.html\">Insert Staff</a>");
                out.print("<table border : 1px>\n"
                        + "        <caption>List of Staff</caption>\n"
                        + "        <tr>\n"
                        + "            <th>staff_id</th>\n"
                        + "            <th>first_name</th>\n"
                        + "            <th>last_name</th>\n"
                        + "            <th>email</th>\n"
                        + "            <th>phone</th>\n"
                        + "            <th>active</th>\n"
                        + "            <th>store_id</th>\n"
                        + "            <th>manager_id</th>\n"
                        + "            <th>Update</th>\n"
                        + "            <th>Delete</th>\n"
                        + "        </tr>");
                Vector<Staff> vector = null;
                if(submit == null){
                    vector = sDAO.getStaff("select * from staffs");
                }else{
                    String fname = request.getParameter("first_name");
                    vector = sDAO.getStaff("select * from staffs where first_name like '%" +fname+"%'");
                }
                for( Staff o : vector){
                    out.println("<tr>\n"
                            + "            <td>"+o.getStaff_id()+"</td>\n"
                            + "            <td>"+o.getFirst_name()+"</td>\n"
                            + "            <td>"+o.getLast_name()+"</td>\n"
                            + "            <td>"+o.getEmail()+"</td>\n"
                            + "            <td>"+o.getPhone()+"</td>\n"
                            + "            <td>"+o.getActive()+"</td>\n"
                            + "            <td>"+o.getStore_id()+"</td>\n"
                            + "            <td>"+o.getManager_id()+"</td>\n"
                            + "            <td><a href=\"StaffControllerURL?service=updateStaff&staff_id="+o.getStaff_id()+"\">Update Staff</a></td>\n"
                            + "            <td><a href=\"StaffControllerURL?service=removeStaff&staff_id="+o.getStaff_id()+"\" "
                                    + "onclick =\"return confirm('are you sure')\">Delete Staff</a></td>\n"
                            + "        </tr>");
                }
                out.println("</table>");
            }
    
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
