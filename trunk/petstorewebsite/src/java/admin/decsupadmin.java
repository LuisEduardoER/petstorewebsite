/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.Connection;
import database.dbConnection;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


/**
 *
 * @author lorie0779
 */
public class decsupadmin extends HttpServlet {
/*
    final String query1mode = "Query1Mode";//
    final String query2mode = "Query2Mode";//
    final String query3mode = "Query3Mode"; //
    final String query4mode = "Query4Mode";//
    final String query5mode = "Query5Mode";
    final String query6mode = "Query6Mode";
    final String query7mode = "Query7Mode";
    final String query8mode = "Query8Mode";
    final String query9mode = "Query9Mode";
  */

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
        try {
            //TODO output your page here
            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            out.println("<head>");
            out.println("<title>Decision Support Result Page</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />");
            //out.println("<meta http-equiv=\"refresh\" content = \"3; URL=http://" + request.getHeader("Host") + "/PetStore/decsupresult.jsp\">");

            out.println("</head>");
            out.println("<body>");
            
            out.println("<div id=\"wrap\"><div class=\"header\"><div class=\"logo\"><a href=\"index.html\">" +
                    "<img src=\"images/logo.gif\" alt=\"\" title=\"\" border=\"0\" /></a></div><div id=\"menu\"><ul>" +
                    "<li class=\"selected\"><a href=\"index.html\">home</a></li><li><a href=\"about.html\">about us</a></li>" +
                    "<li><a href=\"category.html\">pets</a></li><li><a href=\"specials.html\">specials pets</a></li><li>" +
                    "<a href=\"myaccount.html\">my accout</a></li><li><a href=\"register.html\">register</a></li><li>" +
                    "<a href=\"details.html\">prices</a></li><li><a href=\"contact.html\">contact</a></li></ul></div></div>" +
                    "<div class=\"center_content\"><div class=\"left_content\"><div class=\"display\"><table border=\"1\"><h2>Decision Support Result" +
                    " Page</h2>");
      

            PreparedStatement stmt;
            ResultSet rs;
            Connection conn = dbConnection.getConnection();
            ResultSetMetaData rsmd;


            //query1
            String mode = request.getParameter("mode");
            System.out.println("mode=" + mode);
            if (mode != null && mode.equals("query1mode"))//add employee operation
            {
                System.out.println("in...query1mode");
                stmt = conn.prepareStatement("SELECT customer_dim.customertype, SUM(clist.salebycustomer) AS salebycategory FROM " +
                        "(SELECT fs.customerid,SUM(fs.sale) AS salebycustomer FROM factsale fs " +
                        "GROUP BY fs.customerid) AS clist, customer_dim WHERE clist.customerid = customer_dim.customerid " +
                        "GROUP BY customer_dim.customertype ORDER BY salebycategory DESC");
                //stmt.setInt(1, this.storeid);
                rs = stmt.executeQuery();
                //rsmd = rs.getMetaData();
                System.out.println("rs="+rs.toString());
                BigDecimal noOfBusCusts = new BigDecimal(0), noOfHomeCusts = new BigDecimal(0), ratio = new BigDecimal(0);
                while(rs.next()){
                    
                    if (rs.getBoolean("customertype")) {
                        noOfBusCusts = rs.getBigDecimal("salebycategory");
                    } else{
                        noOfHomeCusts = rs.getBigDecimal("salebycategory");
                    }
                }
                System.out.println("noOfBusCusts="+noOfBusCusts+",noOfHomeCusts="+noOfHomeCusts);
                ratio = noOfBusCusts.divide(noOfHomeCusts, 2, RoundingMode.HALF_UP);
                System.out.println("ratio="+ratio);
                out.println("<TD><H2>The ratio of business to home customers(sales)</H2></TD>");
                out.println("<TD><H2>"+ratio+"</H2></TD></TR>");
            }//query2
            else if (mode != null && mode.equals("query2mode"))//add employee operation
            {
                System.out.println("...in query2mode");
                String startDate = request.getParameter("query2start");
                String overDate = request.getParameter("query2over");
                System.out.println("startDate="+startDate+",overDate="+overDate);
                PreparedStatement stmtstarttimeid, stmtovertimeid;
                ResultSet rsstarttimeid, rsovertimeid;

                stmtstarttimeid = conn.prepareStatement("select timedimid from time_dim where date='" + startDate+"'");
                rsstarttimeid = stmtstarttimeid.executeQuery();
                rsstarttimeid.last();
                int starttimeid=0;
                if(rsstarttimeid.getRow()==1)
                    starttimeid=rsstarttimeid.getInt("timedimid");

                System.out.println("starttimeid="+starttimeid);
                stmtovertimeid = conn.prepareStatement("select timedimid from time_dim where date='" + overDate+"'");
                rsovertimeid = stmtovertimeid.executeQuery();
                rsovertimeid.last();
                int overtimeid=0;
                if(rsovertimeid.getRow()==1)
                    overtimeid=rsovertimeid.getInt("timedimid");
                System.out.println("overtimeid="+overtimeid);
                stmt = conn.prepareStatement("SELECT time1.storeid, (time2.salebystore-time1.salebystore) AS saleincrease FROM" +
                        " (SELECT fs.storeid, SUM(fs.sale) AS salebystore FROM factsale fs WHERE timeid = " + starttimeid +
                        " GROUP BY fs.storeid) AS time1,(SELECT fs.storeid, SUM(fs.sale) AS salebystore FROM " +
                        "factsale fs WHERE timeid = " + overtimeid + " GROUP BY fs.storeid) AS time2 WHERE " +
                        "time1.storeid = time2.storeid AND time1.salebystore < time2.salebystore ORDER BY saleincrease DESC");

                //stmt.setInt(1, this.storeid);
                //System.out.println("stmt="+stmt);
                rs = stmt.executeQuery();
//                rs.last();
//                int rowcount=rs.getRow();
//                System.out.println("rowcount="+rowcount);
                
                rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();
                System.out.println("numberOfColumns="+numberOfColumns);
                out.println("<TR>");
                for (int i = 1; i <= numberOfColumns; i++) 
                    out.println("<TH>"+rsmd.getColumnLabel(i)+"</TH>");
                out.println("</TR>");

                while (rs.next()) {
                    out.println("<TR><TD>"+rs.getInt(1)+"</TD>");
                    out.println("<TD>"+rs.getBigDecimal(2)+"</TD></TR>");
                }


            }//query3
            else if (mode != null && mode.equals("query3mode"))//add employee operation
            {
                System.out.println("...in query3mode");
                String date = request.getParameter("query3date");
                System.out.println("date="+date);

                PreparedStatement stmttimeid;
                ResultSet rstimeid;
                stmttimeid = conn.prepareStatement("select timedimid from time_dim where date='" + date+"'");
                rstimeid = stmttimeid.executeQuery();
                rstimeid.last();
                int timeid=0;
                if(rstimeid.getRow()==1)
                    timeid=rstimeid.getInt("timedimid");

                System.out.println("timeid="+timeid);
                stmt = conn.prepareStatement("SELECT fs.productid, SUM(fs.sale) AS totalSale FROM factsale fs WHERE " +
                        "fs.timeid = " + timeid + " GROUP BY fs.productid ORDER BY totalSale DESC");
                //stmt.setInt(1, this.storeid);
                rs = stmt.executeQuery();
                rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();

                System.out.println("numberOfColumns="+numberOfColumns);
                out.println("<TR>");
                for (int i = 1; i <= numberOfColumns; i++)
                    out.println("<TH>"+rsmd.getColumnLabel(i)+"</TH>");
                out.println("</TR>");

                while (rs.next()) {
                    out.println("<TR><TD>"+rs.getInt(1)+"</TD>");
                    out.println("<TD>"+rs.getBigDecimal(2)+"</TD></TR>");
                }


            }//query4
            else if (mode != null && mode.equals("query4mode"))//add employee operation
            {
                System.out.println("...in query4mode");
                String date = request.getParameter("query4date");
                System.out.println("date="+date);

                PreparedStatement stmttimeid;
                ResultSet rstimeid;
                
                stmttimeid = conn.prepareStatement("select timedimid from time_dim where date='" + date+"'");
                rstimeid = stmttimeid.executeQuery();
                rstimeid.last();
                int timeid=0;
                if(rstimeid.getRow()==1)
                    timeid=rstimeid.getInt("timedimid");

                System.out.println("timeid="+timeid);
                stmt = conn.prepareStatement("SELECT fs.productid, SUM(fs.sale) AS totalSale FROM factsale fs " +
                        "WHERE fs.timeid = " + timeid + " GROUP BY fs.productid ORDER BY totalSale ASC");
                //stmt.setInt(1, this.storeid);
                rs = stmt.executeQuery();
                rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();

                System.out.println("numberOfColumns="+numberOfColumns);
                out.println("<TR>");
                for (int i = 1; i <= numberOfColumns; i++)
                    out.println("<TH>"+rsmd.getColumnLabel(i)+"</TH>");
                out.println("</TR>");

                while (rs.next()) {
                    out.println("<TR><TD>"+rs.getInt(1)+"</TD>");
                    out.println("<TD>"+rs.getBigDecimal(2)+"</TD></TR>");
                }


            }//query5
            else if (mode != null && mode.equals("query5mode"))//add employee operation
            {
                System.out.println("...in query5mode");
                String date = request.getParameter("query5date");
                System.out.println("date="+date);

                PreparedStatement stmttimeid;
                ResultSet rstimeid;

                stmttimeid = conn.prepareStatement("select timedimid from time_dim where date='" + date+"'");
                rstimeid = stmttimeid.executeQuery();
                rstimeid.last();
                int timeid=0;
                if(rstimeid.getRow()==1)
                    timeid=rstimeid.getInt("timedimid");
                System.out.println("timeid="+timeid);

                stmt = conn.prepareStatement("SELECT customer_dim.category, SUM(clist.totalSale) AS salebycategory FROM " +
                        "(SELECT fs.customerid, SUM(fs.sale) AS totalSale FROM factsale fs WHERE fs.timeid = " + timeid +
                        " GROUP BY fs.customerid) AS clist, customer_dim WHERE clist.customerid = customer_dim.customerid" +
                        " GROUP BY category ORDER by salebycategory DESC");
                ////stmt.setInt(1, this.storeid);
                //System.out.println("stmt="+stmt);
                rs = stmt.executeQuery();
                rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();

                System.out.println("numberOfColumns="+numberOfColumns);
                out.println("<TR>");
                for (int i = 1; i <= numberOfColumns; i++)
                    out.println("<TH>"+rsmd.getColumnLabel(i)+"</TH>");
                out.println("</TR>");
                int top2=1;
                while (rs.next()) {
                    if(top2<=2){
                        out.println("<TR><TD>"+rs.getString(1)+"</TD>");
                        out.println("<TD>"+rs.getBigDecimal(2)+"</TD></TR>");
                        top2+=1;
                    }
                }


            }//query6
            else if (mode != null && mode.equals("query6mode"))//add employee operation
            {
                System.out.println("...in query6mode");
                String date = request.getParameter("query6date");
                System.out.println("date="+date);

                PreparedStatement stmttimeid;
                ResultSet rstimeid;

                stmttimeid = conn.prepareStatement("select timedimid from time_dim where date='" + date+"'");
                rstimeid = stmttimeid.executeQuery();
                rstimeid.last();
                int timeid=0;
                if(rstimeid.getRow()==1)
                    timeid=rstimeid.getInt("timedimid");
                System.out.println("timeid="+timeid);

                stmt = conn.prepareStatement("SELECT pd.category, SUM(salebyproduct.totalSale) AS salebycategory FROM " +
                        "(SELECT fs.productid, SUM(fs.sale) AS totalSale FROM factsale fs WHERE fs.timeid = " + timeid +
                        " GROUP BY fs.productid ORDER BY totalSale DESC) AS salebyproduct,product_dim pd WHERE " +
                        " pd.productid = salebyproduct.productid GROUP BY category ORDER BY salebycategory DESC");
                //stmt.setInt(1, this.storeid);
                rs = stmt.executeQuery();
                rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();

                System.out.println("numberOfColumns="+numberOfColumns);
                out.println("<TR>");
                for (int i = 1; i <= numberOfColumns; i++)
                    out.println("<TH>"+rsmd.getColumnLabel(i)+"</TH>");
                out.println("</TR>");
                int top2=1;
                while (rs.next()) {
                    if(top2<=2){
                        out.println("<TR><TD>"+rs.getString(1)+"</TD>");
                        out.println("<TD>"+rs.getBigDecimal(2)+"</TD></TR>");
                        top2+=1;
                    }
                }


            }//query7
            else if (mode != null && mode.equals("query7mode"))//add employee operation
            {
                System.out.println("in...query7mode");
                stmt = conn.prepareStatement("SELECT store_dim.regionid, SUM(slist.salebystore) AS salebyregion FROM " +
                        "(SELECT fs.storeid,SUM(fs.sale) AS salebystore FROM factsale fs GROUP BY fs.storeid) AS slist, store_dim " +
                        "WHERE slist.storeid = store_dim.storeid GROUP BY regionid ORDER BY salebyregion DESC");
                //stmt.setInt(1, this.storeid);
                rs = stmt.executeQuery();
                rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();

                System.out.println("numberOfColumns="+numberOfColumns);
                out.println("<TR>");
                for (int i = 1; i <= numberOfColumns; i++)
                    out.println("<TH>"+rsmd.getColumnLabel(i)+"</TH>");
                out.println("</TR>");

                while (rs.next()) {
                    out.println("<TR><TD>"+rs.getInt(1)+"</TD>");
                    out.println("<TD>"+rs.getBigDecimal(2)+"</TD></TR>");
                }


            }//query8
            else if (mode != null && mode.equals("query8mode"))//add employee operation
            {
                System.out.println("in...query8mode");
                int productid = Integer.valueOf(request.getParameter("query8pid"));
                System.out.println("productid="+productid);

                stmt = conn.prepareStatement("SELECT fs.customerid, sum(fs.sale) as sales FROM factsale fs, " +
                        "customer_dim c WHERE c.customerid=fs.customerid and c.customertype=0 and fs.productid =" +productid+
                        " group by fs.customerid ORDER BY sales DESC");
                //stmt.setInt(1, this.storeid);
                rs = stmt.executeQuery();
                rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();

                System.out.println("numberOfColumns="+numberOfColumns);
                out.println("<TR>");
                for (int i = 1; i <= numberOfColumns; i++)
                    out.println("<TH>"+rsmd.getColumnLabel(i)+"</TH>");
                out.println("</TR>");

                while (rs.next()) {
                    out.println("<TR><TD>"+rs.getInt(1)+"</TD>");
                    out.println("<TD>"+rs.getBigDecimal(2)+"</TD></TR>");
                }


            }//query9
            else if (mode != null && mode.equals("query9mode"))//add employee operation
            {
                System.out.println("in...query9mode");
                stmt = conn.prepareStatement("SELECT f.customerid,f.productid,count(f.productid) as nooftrans,fd.weekinyear," +
                        "sum(f.sale) as monthlyspend FROM factsale f,time_dim fd where f.timeid=fd.timedimid" +
                        " group by f.customerid,f.productid,fd.weekinyear having nooftrans>=2 order by f.customerid,f.productid," +
                        " fd.weekinyear");
                //stmt.setInt(1, this.storeid);
                rs = stmt.executeQuery();
                rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();

                System.out.println("numberOfColumns="+numberOfColumns);
                out.println("<TR>");
                for (int i = 1; i <= numberOfColumns; i++)
                    out.println("<TH>"+rsmd.getColumnLabel(i)+"</TH>");
                out.println("</TR>");

                while (rs.next()) {
                    out.println("<TR><TD>"+rs.getInt(1)+"</TD>");
                    out.println("<TD>"+rs.getInt(2)+"</TD>");
                    out.println("<TD>"+rs.getBigDecimal(3)+"</TD>");
                    out.println("<TD>"+rs.getInt(4)+"</TD>");
                    out.println("<TD>"+rs.getBigDecimal(5)+"</TD></TR>");
                }


            }//query10
            else if (mode != null && mode.equals("query10mode"))//add employee operation
            {
                System.out.println("in...query10mode");
                stmt = conn.prepareStatement("select p.pname as Product_Name, s.name as Store_Name, i.quantity from " +
                        "inventory i, stores s, products p where i.quantity < 5 and i.storeid = s.sid" +
                        " and i.productid = p.pid group by i.quantity");
                //stmt.setInt(1, this.storeid);
                System.out.println("stmt="+stmt);
                rs = stmt.executeQuery();
                rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();

                System.out.println("numberOfColumns="+numberOfColumns);
                out.println("<TR>");
                for (int i = 1; i <= numberOfColumns; i++)
                    out.println("<TH>"+rsmd.getColumnLabel(i)+"</TH>");
                out.println("</TR>");

                while (rs.next()) {
                    out.println("<TR><TD>"+rs.getString(1)+"</TD>");
                    out.println("<TD>"+rs.getString(2)+"</TD>");
                    out.println("<TD>"+rs.getInt(3)+"</TD></TR>");
                }
                
                


            }//query11
            else if (mode != null && mode.equals("query11mode"))//add employee operation
            {
                System.out.println("in...query11mode");
                stmt = conn.prepareStatement("SELECT c.cid, c.firstname, c.lastname FROM factsale f, customers c where " +
                        "f.timeid > ((to_days(now()) - to_days('2009-01-01'))-100) and c.cid = f.customerid group by c.cid");
                //stmt.setInt(1, this.storeid);
                rs = stmt.executeQuery();
                rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();

                System.out.println("numberOfColumns="+numberOfColumns);
                out.println("<TR>");
                for (int i = 1; i <= numberOfColumns; i++)
                    out.println("<TH>"+rsmd.getColumnLabel(i)+"</TH>");
                out.println("</TR>");

                while (rs.next()) {
                    out.println("<TR><TD>"+rs.getInt(1)+"</TD>");
                    out.println("<TD>"+rs.getString(2)+"</TD>");
                    out.println("<TD>"+rs.getString(3)+"</TD></TR>");
                }


            }//query12
            else if (mode != null && mode.equals("query12mode"))//add employee operation
            {
                System.out.println("in...query12mode");
                stmt = conn.prepareStatement("SELECT c.cid as Customerid, c.firstname, c.lastname FROM factsale f, customers c where " +
                        "f.timeid > ((to_days(now()) - to_days('2009-01-01'))-100) and c.cid = f.customerid group by c.cid");
                //stmt.setInt(1, this.storeid);
                rs = stmt.executeQuery();
                rsmd = rs.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();

                System.out.println("numberOfColumns="+numberOfColumns);
                out.println("<TR>");
                for (int i = 1; i <= numberOfColumns; i++)
                    out.println("<TH>"+rsmd.getColumnLabel(i)+"</TH>");
                out.println("</TR>");

                while (rs.next()) {
                    out.println("<TR><TD>"+rs.getInt(1)+"</TD>");
                    out.println("<TD>"+rs.getString(2)+"</TD>");
                    out.println("<TD>"+rs.getString(3)+"</TD></TR>");
                }


            }
            out.println("<a href=\"decisionsupport.jsp\">Back to previous page</a></table>");
            out.println("</div><div class=\"clear\"></div></div><div class=\"right_content\"><div class=\"languages_box\">" +
                    "<span class=\"red\">Languages:</span><a href=\"#\" class=\"selected\"><img src=\"images/gb.gif\" alt=" +
                    "\"\" title=\"\" border=\"0\" /></a><a href=\"#\"><img src=\"images/fr.gif\" alt=\"\" title=\"\" border=" +
                    "\"0\" /></a><a href=\"#\"><img src=\"images/de.gif\" alt=\"\" title=\"\" border=\"0\" /></a></div>" +
                    "<div class=\"currency\"><span class=\"red\">Currency: </span><a href=\"#\">GBP</a><a href=\"#\">EUR</a>" +
                    "<a href=\"#\" class=\"selected\">USD</a></div><div class=\"login\"><form name=\"login\" action=" +
                    "\"login.jsp\" method=\"POST\"><div class=\"username\">Login Name: <input type=\"text\" name=\"username\""+
                    " value=\"\" size=\"20\" /></div><div class=\"password\">&nbsp;&nbsp;&nbsp;&nbsp;Password: <input type=" +
                    "\"password\" name=\"password\" value=\"\" size=\"20\" /></div><input type=\"hidden\" name=\"mode\" value=" +
                    "\"login\" /><div class=\"submit\"><input type=\"submit\" value=\"Login\" name=\"login\" /></div></form>" +
                    "</div></div><div class=\"clear\"></div></div><div class=\"footer\"><div class=\"left_footer\"><img src=" +
                    "\"images/footer_logo.gif\" alt=\"\" title=\"\" /><br /> <a href=\"http://csscreme.com/freecsstemplates/\""+
                    " title=\"free css templates\"><img src=\"images/csscreme.gif\" alt=\"free css templates\" border=\"0\" />" +
                    "</a></div><div class=\"right_footer\"><a href=\"#\">home</a><a href=\"#\">about us</a><a href=\"#\">services" +
                    "</a><a href=\"#\">privacy policy</a><a href=\"#\">contact us</a></div></div></div>");
            out.println("</BODY></HTML>");
            dbConnection.closeConnection();
        } catch (SQLException ex) {
            //Logger.getLogger(query1mode).log(Level.SEVERE, null, ex);
            dbConnection.closeConnection();
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
