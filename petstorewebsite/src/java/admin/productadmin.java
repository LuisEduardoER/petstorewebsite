/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeff
 */
public class productadmin extends HttpServlet {

    //define mode string
    final String addnewmode = "AddNewMode";//
    final String editmode = "EditMode";//one record returned
    final String viewmode = "ViewMode"; //all record return
    final String deletemode = "DeleteMode";//no record return
    //view condition
    final String viewall = "ViewAll";            
    final String viewallbySearch = "ViewAllbySearch";
    final String viewbyCondition = "ViewByCondition";
    final String viewallinorder = "ViewAllInOrder";

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
        out.println("<title>Product Admin</title>");
        out.println("<meta http-equiv=\"refresh\" content = \"3; URL=http://" + request.getHeader("Host") + "/PetStore/productmanager.jsp\">");
        out.println("</head>");
        out.println("<body>");
        //get request mode to determine function
        String mode = request.getParameter("mode");
        Product pro = new Product();
        if (mode != null && mode.equals(addnewmode))//add store operation
        {
            pro.setPname(request.getParameter("pname"));
            pro.setUniprice(new BigDecimal(request.getParameter("uniprice")));
            pro.setCategory(request.getParameter("category"));

            if (pro.addNewRecord()) {
                out.println("Add Product Record Succeed");
            } else {
                out.println("Add Product Record Failed");
            }
        }

        //edit Product information
        if (mode != null && mode.equals(editmode)) {
            pro.setPid(Integer.valueOf(request.getParameter("pid")));
            pro.setPname(request.getParameter("pname"));
            pro.setUniprice(new BigDecimal(request.getParameter("uniprice")));
            pro.setCategory(request.getParameter("category"));

            if (pro.updateRecord()) {
                out.println("Edit Product Succeed");
            } else {
                out.println("Edit Product Failed");
            }
        }

        //delete product info
        if (mode != null && mode.equals(deletemode)) {
            pro.setPid(Integer.valueOf(request.getParameter("pid")));
            if (pro.deleteRecord()) {
                out.println("Delete product Succeed");
            } else {
                out.println("Delete product Failed");
            }
        }

        //view inventory info
        if (mode != null && mode.equals(viewmode)) {
            Boolean edit = false;
            if (request.getParameter("edit") != null && request.getParameter("edit").equals("true")) {
                edit = true;
            }
            ArrayList<Product> pro_list = new ArrayList<Product>();
            String condition = request.getParameter("viewcondition");
            if (condition != null) {
                if (condition.equals(viewall)) {
                    pro_list = pro.getAllRecord();
                }
                if (condition.equals(viewallinorder)){
                    String column = request.getParameter("column");
                    if(column.equals("Name")){column ="pname";}
                    if(column.equals("Price")){column="uniprice";}
                    if(column.equals("Category")){column="category";}
                    String direction = request.getParameter("direction");
                    if(!column.isEmpty()&&!direction.isEmpty()){
                    pro_list = pro.getAllRecordbyOrder(column, direction);}
                    else{pro_list = pro.getAllRecord();}
                }

                if  (condition.equals(viewallbySearch)){
                    String key = request.getParameter("keyword");
                    pro_list = pro.getAllRecordbySearch(key);
                }
                if (condition.equals(viewbyCondition)) {
                    pro.setPid(Integer.valueOf(request.getParameter("pid")));
                    pro_list = pro.getRecord();
                }
            } //add other view condition
            else {
                out.println("error in View Condition!\n" + condition);
            }

            request.setAttribute("pro_list", pro_list);
            if (edit) {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/productmanager.jsp?mode=" + editmode).forward(request, response);
            } else {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/productmanager.jsp?mode=" + viewmode).forward(request, response);
            }

        }
        try {
            out.println("\nYou will be redirect to Inventory Manager in 3 seconds");
            out.println("<a href = \"http://" + request.getHeader("Host") + "/PetStore/productmanager.jsp\">Click here to redirect</a>");
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
