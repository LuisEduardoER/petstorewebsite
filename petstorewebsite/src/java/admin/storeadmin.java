/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import database.dbConnection;
import entity.Store;
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
public class storeadmin extends HttpServlet {
    //define mode string

    final String addstoremode = "AddNewStore";//
    final String editmode = "EditMode";//edit the store info
    final String viewmode = "ViewMode"; //all record match return
    final String deletemode = "DeleteMode";//no record return
    final String viewall = "ViewAll";
    final String viewbyStoreid = "ViewBySID";
    final String viewbyRegionid = "ViewByRID";
    final String viewbyName = "ViewByName";
    final String viewbyCity = "ViewByCity";
    final String viewbyState = "ViewByCity";
    final String viewbyZip = "ViewByCity";
    final String viewbyPhone = "ViewByCity";

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
        out.println("<meta http-equiv=\"refresh\" content = \"3; URL=http://" + request.getHeader("Host") + "/PetStore/storemanager.jsp\">");
        out.println("</head>");
        out.println("<body>");

        //get request mode to determine function
        String mode = request.getParameter("mode");
        dbConnection dbconn = new dbConnection();
        if (mode != null && mode.equals(addstoremode))//add store operation
        {
            Store sto = new Store();
            sto.setName(request.getParameter("name"));
            sto.setStreet(request.getParameter("street"));
            sto.setCity(request.getParameter("city"));
            sto.setState(request.getParameter("state"));
            sto.setZip(request.getParameter("zip"));
            sto.setPhone(request.getParameter("phone"));
            String sto_region = request.getParameter("region");

            try {
                int regionid = Integer.valueOf(sto_region);
                sto.setRegionid(regionid);
            } catch (Exception ex) {
                ex.printStackTrace();
                //error to translate ,set default;
                sto.setRegionid(1);
            }

            String sto_manager = request.getParameter("manager");
            try {
                int managerid = Integer.valueOf(sto_manager);
                sto.setManager(managerid);
            } catch (Exception ex) {
                ex.printStackTrace();
                sto.setManager(1);
            }

            boolean sve = sto.addNewRecord();

            if (sve) {
                out.println("Add Store Succeed");
            } else {
                out.println("Add Store Failed");
            }
        }
        //edit store information
        if (mode != null && mode.equals(editmode)) {

            String sto_id = request.getParameter("sid");
            String sto_name = request.getParameter("name");
            String sto_street = request.getParameter("street");
            String sto_city = request.getParameter("city");
            String sto_state = request.getParameter("state");
            String sto_zipcode = request.getParameter("zip");
            String sto_phone = request.getParameter("phone");
            String sto_region = request.getParameter("regionid");
            String sto_manager = request.getParameter("managerid");

            Store sto = new Store(Integer.valueOf(sto_id), Integer.valueOf(sto_region), Integer.valueOf(sto_manager), sto_name, sto_street, sto_city, sto_state, sto_zipcode, sto_phone);

            if (sto.updateRecord()) {
                out.println("Edit Store Succeed");
            } else {
                out.println("Edit Store Failed");
            }
        }

        //delete store info
        if (mode != null && mode.equals(deletemode)) {
            String sto_id = request.getParameter("sid");
            Store sto = new Store();
            sto.setSid(Integer.valueOf(sto_id));
            if (sto.deleteRecord()) {
                out.println("Delete Store Succeed");
            } else {
                out.println("Delete Store Failed");
            }
        }

        //view store info
        if (mode != null && mode.equals(viewmode)) {

            Boolean edit = false;
            if (request.getParameter("edit") != null && request.getParameter("edit").equals("true")) {
                edit = true;
            }
            Store sto = new Store();
            ArrayList<Store> storelist = new ArrayList<Store>();
            String condition = request.getParameter("viewcondition");
            if (condition != null) {
                if (condition.equals(viewall)) {
                    storelist = sto.getAllRecord();
                }
                if (condition.equals(viewbyStoreid)) {
                    String sid = request.getParameter("storeid");
                    if (!sid.isEmpty()) {
                        sto.setSid(Integer.valueOf(sid));
                        storelist = sto.getRecord();
                    } else {
                        out.println("Please enter StoreID in order to edit Store Info ");
                    }
                } //add other view condition
                else {
                    out.println("error in View Condition!\n" + condition);
                }
            }

            request.setAttribute("storelist", storelist);
            if (edit) {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/storemanager.jsp?mode=" + editmode).forward(request, response);
            } else {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/storemanager.jsp?mode=" + viewmode).forward(request, response);
            }

        }

        try {
            out.println("\nYou will be redirect to Store Manager in 3 seconds");
            out.println("<a href = \"http://" + request.getHeader("Host") + "/PetStore/storemanager.jsp;\">Click here to redirect</a>");
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
