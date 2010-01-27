/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import entity.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
public class employeeadmin extends HttpServlet {
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

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Employee Admin</title>");
        out.println("<meta http-equiv=\"refresh\" content = \"3; URL=http://" + request.getHeader("Host") + "/PetStore/employeemanager.jsp\">");
        out.println("</head>");
        out.println("<body>");

        //get request mode to determine function
        String mode = request.getParameter("mode");
        Employee emp = new Employee();
        if (mode != null && mode.equals(addnewmode))//add employee operation
        {
            emp.setFirstname(request.getParameter("firstname"));
            emp.setLastname(request.getParameter("lastname"));
            emp.setStreetaddr(request.getParameter("streetaddr"));
            emp.setCity(request.getParameter("city"));
            emp.setState(request.getParameter("state"));
            emp.setZip(request.getParameter("zip"));
            emp.setStoreid(Integer.valueOf(request.getParameter("storeid")));
            emp.setEmail(request.getParameter("email"));
            emp.setTitle(request.getParameter("title"));
            emp.setStartdate(Date.valueOf(request.getParameter("startdate")));
            emp.setSalary(new BigDecimal(request.getParameter("salary")));

            if (emp.addNewRecord()) {
                out.println("Add Employee Record Succeed");
            } else {
                out.println("Add Employee Record Failed");
            }
        }

        //edit customer information
        if (mode != null && mode.equals(editmode)) {
            emp.setEid(Integer.valueOf(request.getParameter("eid")));
            emp.setFirstname(request.getParameter("firstname"));
            emp.setLastname(request.getParameter("lastname"));
            emp.setStreetaddr(request.getParameter("streetaddr"));
            emp.setCity(request.getParameter("city"));
            emp.setState(request.getParameter("state"));
            emp.setZip(request.getParameter("zip"));
            emp.setStoreid(Integer.valueOf(request.getParameter("storeid")));
            emp.setTitle(request.getParameter("title"));
            emp.setEmail(request.getParameter("email"));
            emp.setStartdate(Date.valueOf(request.getParameter("startdate")));
            emp.setSalary(new BigDecimal(request.getParameter("salary")));

            if (emp.updateRecord()) {
                out.println("Edit Employee Succeed");
            } else {
                out.println("Edit Employee Failed");
            }
        }

        //delete customer info
        if (mode != null && mode.equals(deletemode)) {
            emp.setEid(Integer.valueOf(request.getParameter("eid")));
            if (emp.deleteRecord()) {
                out.println("Delete Employee Succeed");
            } else {
                out.println("Delete Employee Failed");
            }
        }

        //view inventory info
        if (mode != null && mode.equals(viewmode)) {
            Boolean edit = false;
            if (request.getParameter("edit") != null && request.getParameter("edit").equals("true")) {
                edit = true;
            }
            ArrayList<Employee> emp_list = new ArrayList<Employee>();
            String condition = request.getParameter("viewcondition");
            if (condition != null) {
                if (condition.equals(viewall)) {
                    emp_list = emp.getAllRecord();
                }
                if (condition.equals(viewbyCondition)) {
                    emp.setEid(Integer.valueOf(request.getParameter("eid")));
                    emp_list = emp.getRecord();
                }

            } //add other view condition
            else {
                out.println("error in View Condition!\n" + condition);
            }
            request.setAttribute("emp_list", emp_list);
            if (edit) {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/employeemanager.jsp?mode=" + editmode).forward(request, response);
            } else {
                getServletConfig().getServletContext().getRequestDispatcher(
                        "/employeemanager.jsp?mode=" + viewmode).forward(request, response);
            }

        }

        try {
            out.println("\nYou will be redirect to Inventory Manager in 3 seconds");
            out.println("<a href = \"http://" + request.getHeader("Host") + "/PetStore/employeemanager.jsp\">Click here to redirect</a>");
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
