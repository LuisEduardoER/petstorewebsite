/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import database.dbConnection;
import entity.Region;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeff
 */
public class regionadmin extends HttpServlet {

    //define mode string
    final String addnewmode = "AddNewMode";//
    final String editmode = "EditMode";//one record returned
    final String viewmode = "ViewMode"; //all record return
    final String deletemode = "DeleteMode";//no record return
    //view condition
    final String viewall = "ViewAll";
    final String viewbyCondition = "ViewByCondition";

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
        PrintWriter out = response.getWriter();

        //print out html head
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Store Admin</title>");
        out.println("<meta http-equiv=\"refresh\" content = \"3; URL=http://" + request.getHeader("Host") + "/PetStore/regionmanager.jsp\">");
        out.println("</head>");
        out.println("<body>");
        //get request mode to determine function
        String mode = request.getParameter("mode");
        dbConnection dbconn = new dbConnection();

        if (mode != null && mode.equals(addnewmode))//add store operation
        {
            String name = request.getParameter("name");
            String managerid = request.getParameter("managerid");
            Region reg = new Region(name, Integer.valueOf(managerid));
            if (reg.addNewRecord()) {
                out.println("Add Region Record Succeed");
            } else {
                out.println("Add Region Record Failed");
            }
        }

        //edit inventory information
        if (mode != null && mode.equals(editmode)) {
            Region reg = new Region();
            String regionid = request.getParameter("regionid");
            String name = request.getParameter("name");
            String managerid = request.getParameter("managerid");

            reg.setId(Integer.valueOf(regionid));
            reg.setName(name);
            reg.setManagerid(Integer.valueOf(managerid));

            if (reg.updateRecord()) {
                out.println("Record Update Succeed");
            } else {
                out.println("Record Update Failed");
            }
        }

        //delete inventory info
        if (mode != null && mode.equals(deletemode)) {
            String regionid = request.getParameter("regionid");
            Region reg = new Region();
            reg.setId(Integer.valueOf(regionid));
            if (reg.deleteRecord()) {
                out.println("Delete Region Succeed");
            } else {
                out.println("Delete Region Failed");
            }

        }

        //view region info
        if (mode != null && mode.equals(viewmode)) {
            try {
                Connection conn = dbconn.getConnection();
                Boolean edit = false;
                if (request.getParameter("edit") != null && request.getParameter("edit").equals("true")) {
                    edit = true;
                }
                ArrayList<Region> reg_list = new ArrayList<Region>();
                String condition = request.getParameter("viewcondition");
                if (condition != null) {
                    if (condition.equals(viewall)) {
                        Region reg = new Region();
                        reg_list = reg.getAllRecord();
                    }
                    if (condition.equals(viewbyCondition)) {
                        String regionid = request.getParameter("regionid");
                        Region reg = new Region();
                        reg.setId(Integer.valueOf(regionid));
                        reg_list = reg.getRecord();
                    }
                } //add other view condition
                else {
                    out.println("error in View Condition!\n" + condition);
                }

                request.setAttribute("regionlist", reg_list);
                if (edit) {
                    getServletConfig().getServletContext().getRequestDispatcher(
                            "/regionmanager.jsp?mode=" + editmode).forward(request, response);
                } else {
                    getServletConfig().getServletContext().getRequestDispatcher(
                            "/regionmanager.jsp?mode=" + viewmode).forward(request, response);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        try {
            out.println("\nYou will be redirect to Region Manager in 3 seconds");
            out.println("<a href = \"http://" + request.getHeader("Host") + "/PetStore/regionmanager.jsp\">Click here to redirect</a>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
