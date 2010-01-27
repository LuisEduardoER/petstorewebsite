/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import entity.Customer;
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
public class customeradmin extends HttpServlet {
    //define mode string

    final String addnewmode = "AddNewMode";//
    final String editmode = "EditMode";//one record returned
    final String viewmode = "ViewMode"; //all record return
    final String deletemode = "DeleteMode";//no record return
    //view condition
    final String viewall = "ViewAll";
    final String viewbyCondition = "ViewByCondition";
    final String home = "HOME";
    final String business = "BUSINESS";

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
        out.println("<title>Customer Admin</title>");
        out.println("<meta http-equiv=\"refresh\" content = \"3; URL=http://" + request.getHeader("Host") + "/PetStore/customermanager.jsp\">");
        out.println("</head>");
        out.println("<body>");

        //get request mode to determine function
        String mode = request.getParameter("mode");
        String customer = request.getParameter("customertype");
        Customer cus = new Customer();
        if (mode != null && mode.equals(addnewmode))//add store operation
        {
            cus.setFirstname(request.getParameter("firstname"));
            cus.setLastname(request.getParameter("lastname"));
            cus.setStreetaddr(request.getParameter("streetaddr"));
            cus.setCity(request.getParameter("city"));
            cus.setState(request.getParameter("state"));
            cus.setZip(request.getParameter("zip"));
            cus.setPhone(request.getParameter("phone"));

            if (customer.equals(home)) {
                cus.setCtype(Customer.homecustomer);
                String gender = request.getParameter("gender");
                if (gender.equals("male")) {
                    cus.setGender(Customer.male);
                } else {
                    cus.setGender(Customer.female);
                }
                String marriage = request.getParameter("marriage");
                if (marriage.equals("married")) {
                    cus.setMarriage(true);
                } else {
                    cus.setMarriage(false);
                }
                cus.setAge(Integer.valueOf(request.getParameter("age")));
                cus.setPreference(request.getParameter("preference"));
                cus.setIncome(new BigDecimal(request.getParameter("income")));
            } else {
                cus.setCtype(Customer.buscustomer);
                cus.setCompanyname(request.getParameter("companyname"));
                cus.setIndustry(request.getParameter("industry"));
                cus.setCompany_desc(request.getParameter("company_desc"));
            }

            if (cus.addNewRecord()) {
                out.println("Add Customer Record Succeed\n");
            } else {
                out.println("Add Customer Record Failed\n");
            }

        }

        //edit customer information
        if (mode != null && mode.equals(editmode)) {
            cus.setCustomerid(Integer.valueOf(request.getParameter("customerid")));
            cus.setFirstname(request.getParameter("firstname"));
            cus.setLastname(request.getParameter("lastname"));
            cus.setStreetaddr(request.getParameter("streetaddr"));
            cus.setCity(request.getParameter("city"));
            cus.setState(request.getParameter("state"));
            cus.setZip(request.getParameter("zip"));
            cus.setPhone(request.getParameter("phone"));

            if (customer.equals(home)) {
                cus.setCtype(Customer.homecustomer);
                String gender = request.getParameter("gender");
                if (gender.equals("male")) {
                    cus.setGender(Customer.male);
                } else {
                    cus.setGender(Customer.female);
                }
                String marriage = request.getParameter("marriage");
                if (marriage.equals("married")) {
                    cus.setMarriage(true);
                } else {
                    cus.setMarriage(false);
                }
                cus.setAge(Integer.valueOf(request.getParameter("age")));
                cus.setPreference(request.getParameter("preference"));
                cus.setIncome(new BigDecimal(request.getParameter("income")));
            } else {
                cus.setCtype(Customer.buscustomer);
                cus.setCompanyname(request.getParameter("companyname"));
                cus.setIndustry(request.getParameter("industry"));
                cus.setCompany_desc(request.getParameter("company_desc"));
            }
            if (cus.updateRecord()) {
                out.println("Edit Customer Succeed\n");
            } else {
                out.println("Edit Customer Failed\n");
            }
        }

        //delete customer info
        if (mode != null && mode.equals(deletemode)) {
            cus.setCustomerid(Integer.valueOf(request.getParameter("customerid")));
            if (cus.deleteRecord()) {
                out.println("Delete Customer Succeed\n");
            } else {
                out.println("Delete Customer Failed\n");
            }
        }

        //view customer info
        if (mode != null && mode.equals(viewmode)) {
            Boolean edit = false;
            if (request.getParameter("edit") != null && request.getParameter("edit").equals("true")) {
                edit = true;
            }
            ArrayList<Customer> cus_list = new ArrayList<Customer>();
            String condition = request.getParameter("viewcondition");
            if (condition != null) {
                if (condition.equals(viewall)) {
                    if (customer.equals(home)) {
                        cus.setCtype(Customer.homecustomer);
                        cus_list = cus.getAllRecord();
                        request.setAttribute("customertype", "HOME");
                    } else {
                        cus.setCtype(Customer.buscustomer);
                        cus_list = cus.getAllRecord();
                        request.setAttribute("customertype", "BUSINESS");
                    }
                }
                if (condition.equals(viewbyCondition)) {
                    cus.setCustomerid(Integer.valueOf(request.getParameter("customerid")));
                    cus_list = cus.getRecord();
                } else {
                    out.println("Please enter Customer ID in order to edit Customer Info ");
                }
            } //add other view condition
            else {
                out.println("error in View Condition!\n");
            }

            request.setAttribute("cus_list", cus_list);
            if (edit) {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/customermanager.jsp?mode=" + editmode).forward(request, response);
            } else {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/customermanager.jsp?mode=" + viewmode).forward(request, response);
            }
        }
        try {
            out.println("\nYou will be redirect to Customer Manager in 3 seconds");
            out.println("<a href = \"http://" + request.getHeader("Host") + "/PetStore/customermanager.jsp\">Click here to redirect</a>");
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
