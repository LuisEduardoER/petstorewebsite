/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import entity.Transcation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeff
 */
public class transcationadmin extends HttpServlet {
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
        out.println("<meta http-equiv=\"refresh\" content = \"3; URL=http://" + request.getHeader("Host") + "/PetStore/transcationmanager.jsp\">");
        out.println("</head>");
        out.println("<body>");

        //get request mode to determine function
        String mode = request.getParameter("mode");
        Transcation tran = new Transcation();

        if (mode != null && mode.equals(addnewmode))//add store operation
        {
            boolean available = false;
            tran.setCustomerid(Integer.valueOf(request.getParameter("cid")));
            tran.setStoreid(Integer.valueOf(request.getParameter("sid")));
            tran.setSalesmanid(Integer.valueOf(request.getParameter("salesmanid")));
            tran.setOrderdate(Date.valueOf(request.getParameter("odate")));
            // work on this, now only one product at one time
            String pid[] = request.getParameterValues("pid");
            String quantity[] = request.getParameterValues("quantity");
            for (int i = 0; i < pid.length; i++) {
                if (!pid[i].isEmpty() && !quantity[i].isEmpty()) {
                    if (tran.addProduct2Orderlist(Integer.valueOf(pid[i]), Integer.valueOf(Integer.valueOf(quantity[i])))) {
                        available = true;
                    } else {
                        out.println("Product " + pid[i] + " Inventory Quantity is not enough for this order");
                        available = false;
                        break;
                    }
                }
            }
            if (available) {
                if (tran.addNewRecord()) {
                    out.println("Add Inventory Record Succeed");
                } else {
                    out.println("Add Inventory Record Failed");
                }
            } else {
                out.println("Add Inventory Record Failed");
            }

        }
        //edit transcation information
        if (mode != null && mode.equals(editmode)) {
            tran.setOrderid(Integer.valueOf(request.getParameter("oid")));
            tran.setCustomerid(Integer.valueOf(request.getParameter("cid")));
            tran.setStoreid(Integer.valueOf(request.getParameter("sid")));
            tran.setSalesmanid(Integer.valueOf(request.getParameter("salesmanid")));
            tran.setOrderdate(Date.valueOf(request.getParameter("orderdate")));
            String pid[] = request.getParameterValues("pid");
            String quantity[] = request.getParameterValues("quantity");
            for (int i = 0; i < pid.length; i++) {
                tran.changePurchaselist(Integer.valueOf(pid[i]), Integer.valueOf(quantity[i]));
            }
            if (tran.updateRecord()) {
                out.println("Edit Transcation Succeed");
            } else {
                out.println("Edit Transcation Failed");
            }
        }

        //delete transcation info
        if (mode != null && mode.equals(deletemode)) {
            tran.setOrderid(Integer.valueOf(request.getParameter("oid")));
            if (tran.deleteRecord()) {
                out.println("Delete Transcation Succeed");
            } else {
                out.println("Delete Transcation Failed");
            }
        }

        //view inventory info
        if (mode != null && mode.equals(viewmode)) {
            Boolean edit = false;
            if (request.getParameter("edit") != null && request.getParameter("edit").equals("true")) {
                edit = true;
            }
            ArrayList<Transcation> tran_list = new ArrayList<Transcation>();
            String condition = request.getParameter("viewcondition");
            if (condition != null) {
                if (condition.equals(viewall)) {
                    tran_list = tran.getAllRecord();
                }
                if (condition.equals(viewbyCondition)) {
                    tran.setOrderid(Integer.valueOf(request.getParameter("oid")));
                    tran_list = tran.getRecord();
                }

            } //add other view condition
            else {
                out.println("error in View Condition!\n" + condition);
            }

            request.setAttribute("tran_list", tran_list);

            if (edit) {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/transcationmanager.jsp?mode=" + editmode).forward(request, response);
            } else {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/transcationmanager.jsp?mode=" + viewmode).forward(request, response);
            }
            tran_list.clear();
        }

        try {
            out.println("\nYou will be redirect to Inventory Manager in 3 seconds");
            out.println("<a href = \"http://" + request.getHeader("Host") + "/PetStore/transcationmanager.jsp\">Click here to redirect</a>");
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
