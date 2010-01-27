/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import database.dbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeff
 */
public class loadingroutineadmin extends HttpServlet {

    final String facttoday = "LoadingProductForToday";

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
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Data WareHouse Loading Admin</title>");
        out.println("<meta http-equiv=\"refresh\" content = \"3; URL=http://" + request.getHeader("Host") + "/PetStore/loadingroutine.jsp\">");
        out.println("</head>");
        out.println("<body>");

        String mode = request.getParameter("mode");

        if(mode.equals(facttoday)){
             try {
//                Date now = new Date();
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                String today = format.parse(now.toString()).toString();
                String date = request.getParameter("date");
                String loadingfordate = "INSERT factsale (customerid,productid,storeid,timeid,sale) select fact.cid, fact.pid, fact.sid,fact.timedimid, sum(fact.tot) FROM (SELECT o.oid,o.cid,od.pid,od.quantity,o.sid,pro.uniprice,td.timedimid,quantity*uniprice as tot FROM orders o,orderdetail od, time_dim td, products pro where o.oid=od.oid and  o.odate=td.date and o.odate='"+date+"' AND pro.pid = od.pid group by o.cid,od.pid,o.sid,o.odate order by o.cid, od.pid) AS fact group by cid,pid,sid";
                PreparedStatement stmt = dbConnection.getConnection().prepareStatement(loadingfordate);
                stmt.execute(loadingfordate);
                out.println("Update Succeessful");
            } catch (SQLException ex) {
                Logger.getLogger(loadingroutineadmin.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                out.println("Error Occured");
            }

        }
            try {
                out.println("\nYou will be redirect to Customer Manager in 3 seconds");
                out.println("<a href = \"http://" + request.getHeader("Host") + "/PetStore/loadingroutine.jsp\">Click here to redirect</a>");
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
