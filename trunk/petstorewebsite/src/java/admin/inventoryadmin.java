/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import entity.Inventory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeff
 */
public class inventoryadmin extends HttpServlet {
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
        out.println("<title>Inventory Admin</title>");
        out.println("<meta http-equiv=\"refresh\" content = \"3; URL=http://" + request.getHeader("Host") + "/PetStore/inventorymanager.jsp\">");
        out.println("</head>");
        out.println("<body>");
        //get request mode to determine function
        String mode = request.getParameter("mode");
        if (mode != null && mode.equals(addnewmode))//add store operation
        {

            String inv_store = request.getParameter("storeid");
            String inv_product = request.getParameter("productid");
            String inv_productAmount = request.getParameter("productamount");
            Inventory inv = new Inventory();
            inv.setStoreId(Integer.valueOf(inv_store));
            inv.setProductId(Integer.valueOf(inv_product));
            inv.setProductAmount(Integer.valueOf(inv_productAmount));
            if (inv.addNewRecord()) {
                out.println("Add Inventory Record Succeed");
            } else {
                out.println("Add Inventory Record Failed");
            }
        }
        //edit inventory information
        if (mode != null && mode.equals(editmode)) {
            Inventory inv = new Inventory();

            String inv_store = request.getParameter("storeid");
            String inv_product = request.getParameter("productid");
            String inv_productAmount = request.getParameter("productamount");

            inv.setStoreId(Integer.valueOf(inv_store));
            inv.setProductId(Integer.valueOf(inv_product));
            inv.setProductAmount(Integer.valueOf(inv_productAmount));

            if (inv.updateRecord()) {
                out.println("Edit Inventory Succeed");
            } else {
                out.println("Edit Inventory Failed");
            }
        }

        //delete inventory info
        if (mode != null && mode.equals(deletemode)) {

            String inv_store = request.getParameter("storeid");
            String inv_product = request.getParameter("productid");
            Inventory inv = new Inventory();
            inv.setStoreId(Integer.valueOf(inv_store));
            inv.setProductId(Integer.valueOf(inv_product));

            if (inv.deleteRecord()) {
                out.println("Delete Inventory Succeed");
            } else {
                out.println("Delete Inventory Failed");
            }

        }

        //view inventory info
        if (mode != null && mode.equals(viewmode)) {
            Boolean edit = false;
            if (request.getParameter("edit") != null && request.getParameter("edit").equals("true")) {
                edit = true;
            }
            ArrayList<Inventory> inv_list = new ArrayList<Inventory>();
            String condition = request.getParameter("viewcondition");
            if (condition != null) {
                if (condition.equals(viewall)) {
                    Inventory inv = new Inventory();
                    inv_list = inv.getAllRecord();
                }
                if (condition.equals(viewbyCondition)) {
                    String storeid = request.getParameter("storeid");
                    String productid = request.getParameter("productid");
                    if ((!storeid.isEmpty()) && (!productid.isEmpty())) {
                        Inventory inv = new Inventory();
                        inv.setStoreId(Integer.valueOf(storeid));
                        inv.setProductId(Integer.valueOf(productid));
                        inv_list = inv.getRecord();
                    } else {
                        out.println("Please enter Store ID & Product ID to edit Inventory Info ");
                    }
                }

            } //add other view condition
            else {
                out.println("error in View Condition!\n" + condition);
            }

            request.setAttribute("inv_list", inv_list);
            if (edit) {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/inventorymanager.jsp?mode=" + editmode).forward(request, response);
            } else {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/inventorymanager.jsp?mode=" + viewmode).forward(request, response);
            }

        }
        try {
            out.println("\nYou will be redirect to Inventory Manager in 3 seconds");
            out.println("<a href = \"http://" + request.getHeader("Host") + "/PetStore/inventorymanager.jsp\">Click here to redirect</a>");
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

