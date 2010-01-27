<%--
    Document   : customermanager.jsp
    Created on : Dec 9, 2009, 10:57:34 PM
    Author     : Jeff
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="entity.Employee"%>
<%@page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="employee" class="entity.Employee" scope="request" />

<%
            //define mode string
            final String addnewmode = "AddNewMode";//
            final String editmode = "EditMode";//one record returned
            final String viewmode = "ViewMode"; //all record return
            final String deletemode = "DeleteMode";//no record return
            //view condition
            final String viewall = "ViewAll";
            final String viewbyCondition = "ViewByCondition";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Manager</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>

    <body>

        <div id="wrap">

            <div class="header">
                <div class="logo"><a href="index.jsp"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>
                <div id="menu">
                    <ul>
                        <li class="selected"><a href="index.html">home</a></li>
                        <li><a href="about.html">about us</a></li>
                        <li><a href="category.html">pets</a></li>
                        <li><a href="specials.html">specials pets</a></li>
                        <li><a href="myaccount.html">my accout</a></li>
                        <li><a href="register.html">register</a></li>
                        <li><a href="details.html">prices</a></li>
                        <li><a href="contact.html">contact</a></li>
                    </ul>
                </div>


            </div>


            <div class="center_content">
                <div class="left_content">
                    <div class="display">
                          <h2>Pet Store Employee Admin Manager</h2>
<a href="index.jsp">Back to Index</a><BR>
                            <%
                                String usertype = (String) session.getAttribute("usertype");
                                if (usertype == null) {
                                    usertype = "";
                                }
                                if (usertype.equals(entity.UserCred.adminuser)) {
                            %>
                            <form action="/PetStore/employeeadmin" name="viewallemployee" method="POST">
                                <input type="hidden" name="mode" value="<%=viewmode%>" />
                                <input type="hidden" name="viewcondition" value="<%=viewall%>" />
                                <input type="submit" value="View All Employee" name="viewall" />&nbsp;&nbsp;
                            </form>

                            <form action="employeemanager.jsp" name="addnew" method="post">
                                <input type="hidden" name="mode" value="<%=addnewmode%>" />
                                <input type="submit" value="Add Employee" name="addnew" />&nbsp;&nbsp;
                            </form>

                            <form action="/PetStore/employeeadmin" name="editemployee" method="POST">
                                <input type="hidden" name="mode" value="<%=viewmode%>" />
                                <input type="hidden" name="edit" value="true" />
                                <input type="hidden" name="viewcondition" value="<%=viewbyCondition%>" />
                                Employee ID: <input type="text" name="eid" value="" size="5" />
                                <input type="submit" value="Edit" name="editinventory" />&nbsp;&nbsp;
                            </form>
                            <%
                                    String mode = request.getParameter("mode");
                                    if (mode != null && mode.equals(editmode)) {// return one record to edit
                                        ArrayList<Employee> emp_list = (ArrayList<Employee>) request.getAttribute("emp_list");
                                        for (Iterator iter = emp_list.iterator(); iter.hasNext();) {
                                            Employee emp = (Employee) iter.next();
                            %>

                            <form action="/PetStore/employeeadmin" name="edititem" method="POST">
                                <input type="hidden" name="mode" value="<%=editmode%>" />
                                Employee Information<BR>
                                Employee ID:<input type="text" name="eid" size="20" value="<%=emp.getEid()%>" /><BR>
                                Store ID:<input type="text" name="storeid" value="<%=emp.getStoreid()%>" size="20" /><BR>
                                First Name:<input type="text" name="firstname" value="<%=emp.getFirstname()%>" size="20" /><BR>
                                Last Name:<input type="text" name="lastname" value="<%=emp.getLastname()%>" size="20" /><BR>
                                Street Addr:<input type="text" name="streetaddr" value="<%=emp.getStreetaddr()%>" size="20" /><BR>
                                City: <input type="text" name="city" value="<%=emp.getCity()%>" size="20" /><BR>
                                State:<input type="text" name="state" value="<%=emp.getState()%>" size="20" /><BR>
                                ZipCode:<input type="text" name="zip" value="<%=emp.getZip()%>" size="20" /><BR>
                                Title: <input type="text" name="title" value="<%=emp.getTitle()%>" size="20" /><BR>
                                Email:<input type="text" name="email" value="<%=emp.getEmail()%>" size="20" /><BR>
                                Salary:<input type="text" name="salary" value="<%=emp.getSalary()%>" size="20" /><BR>
                                StartDate:<input type="text" name="startdate" value="<%=emp.getStartdate()%>" size="20" /><BR>
                                <input type="submit" value="Edit Record" name="submit" />
                            </form>

                            <form action="/PetStore/employeeadmin" name="deleteitem" method="POST">
                                <input type="hidden" name="mode" value="<%=deletemode%>" />
                                <input type="hidden" name="eid" value="<%=emp.getEid()%>" />
                                <input type="submit" value="Delete Record" name="submit" />
                            </form>

                            <%
                                                }//end of for
                                            }//end of edit mode
                                            if (mode != null && mode.equals(addnewmode)) {
                            %>
                             <Br><BR>
                            Enter New Employee Record:
                            <form action="/PetStore/employeeadmin" name="newinventoryrecord" method="POST">
                                <input type="hidden" name="mode" value="<%=addnewmode%>" />
                                Store ID:<input type="text" name="storeid" value="" size="20" /><BR>
                                First Name:<input type="text" name="firstname" value="" size="20" /><BR>
                                Last Name:<input type="text" name="lastname" value="" size="20" /><BR>
                                Street Addr:<input type="text" name="streetaddr" value="" size="20" /><BR>
                                City: <input type="text" name="city" value="" size="20" /><BR>
                                State:<input type="text" name="state" value="" size="20" /><BR>
                                ZipCode:<input type="text" name="zip" value="" size="20" /><BR>
                                Title: <input type="text" name="title" value="" size="20" /><BR>
                                Email:<input type="text" name="email" value="" size="20" /><BR>
                                Salary:<input type="text" name="salary" value="" size="20" /><BR>
                                StartDate(yyyy-mm-dd):<input type="text" name="startdate" value="" size="20" /><BR>
                                <input type="submit" value="Add" name="addnew" />
                            </form>

                                <% }
                                    if (mode != null && mode.equals(viewmode)) {
                                        ArrayList<Employee> emp_list = (ArrayList<Employee>) request.getAttribute("emp_list");
                            %>
                            <TABLE>
                                <TR>
                                    <TH>Employee ID</TH>
                                    <TH>Store ID</TH>
                                    <TH>First Name</TH>
                                    <TH>Last Name</TH>
                                    <TH>Street Addr</TH>
                                    <TH>City</TH>
                                    <TH>State</TH>
                                    <TH>ZipCode</TH>
                                    <TH>Title</TH>
                                    <TH>Email</TH>
                                    <TH>Salary</TH>
                                    <TH>Start Date</TH>
                                </TR>
                                <%
                                    for (Iterator iter = emp_list.iterator(); iter.hasNext();) {
                                        Employee emp = (Employee) iter.next();
                                %>
                                <TR>
                                    <TD><%= emp.getEid()%></TD>
                                    <TD><%= emp.getStoreid()%></TD>
                                    <TD><%= emp.getFirstname()%></TD>
                                    <TD><%= emp.getLastname()%></TD>
                                    <TD><%= emp.getStreetaddr()%></TD>
                                    <TD><%= emp.getCity()%></TD>
                                    <TD><%= emp.getState()%></TD>
                                    <TD><%= emp.getZip()%></TD>
                                    <TD><%= emp.getTitle()%></TD>
                                    <TD><%= emp.getEmail()%></TD>
                                    <TD><%= emp.getSalary()%></TD>
                                    <TD><%= emp.getStartdate()%></TD>
                                </TR>

                                <%
                                    }
                                %>
                            </TABLE>
                            <%
                                    }
                                } else {
                            %>
                            You have not login yet.
                            Click <a href="index.jsp">here </a>to login.
                            <%            }
                            %>
                    </div>
                    <div class="clear"></div>
                </div><!--end of left content-->

                <div class="right_content">
                    <div class="languages_box">
                        <span class="red">Languages:</span>
                        <a href="#" class="selected"><img src="images/gb.gif" alt="" title="" border="0" /></a>
                        <a href="#"><img src="images/fr.gif" alt="" title="" border="0" /></a>
                        <a href="#"><img src="images/de.gif" alt="" title="" border="0" /></a>
                    </div>
                    <div class="currency">
                        <span class="red">Currency: </span>
                        <a href="#">GBP</a>
                        <a href="#">EUR</a>
                        <a href="#" class="selected">USD</a>
                    </div>


                    <div class="login">

                        <form name="login" action="login.jsp" method="POST">
                            <div class="username">Login Name: <input type="text" name="username" value="" size="20" /></div>
                            <div class="password">&nbsp;&nbsp;&nbsp;&nbsp;Password: <input type="password" name="password" value="" size="20" /></div>
                            <input type="hidden" name="mode" value="login" />
                            <div class="submit"><input type="submit" value="Login" name="login" /></div>
                        </form>

                    </div>
<!--



                    <div class="title"><span class="title_icon"><img src="images/bullet3.gif" alt="" title="" /></span>About Our Shop</div>
                    <div class="about">
                        <p>
                            <img src="images/about.gif" alt="" title="" class="right" />
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud.
                        </p>

                    </div>

                    <div class="right_box">

                        <div class="title"><span class="title_icon"><img src="images/bullet4.gif" alt="" title="" /></span>Promotions</div>
                        <div class="new_prod_box">
                            <a href="details.html">product name</a>
                            <div class="new_prod_bg">
                                <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                                <a href="details.html"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                            </div>
                        </div>

                        <div class="new_prod_box">
                            <a href="details.html">product name</a>
                            <div class="new_prod_bg">
                                <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                                <a href="details.html"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                            </div>
                        </div>

                        <div class="new_prod_box">
                            <a href="details.html">product name</a>
                            <div class="new_prod_bg">
                                <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                                <a href="details.html"><img src="images/thumb3.gif" alt="" title="" class="thumb" border="0" /></a>
                            </div>
                        </div>

                    </div>

                    <div class="right_box">

                        <div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>Categories</div>

                        <ul class="list">
                            <li><a href="#">accesories</a></li>
                            <li><a href="#">pets gifts</a></li>
                            <li><a href="#">specials</a></li>
                            <li><a href="#">hollidays gifts</a></li>
                            <li><a href="#">accesories</a></li>
                            <li><a href="#">pets gifts</a></li>
                            <li><a href="#">specials</a></li>
                            <li><a href="#">hollidays gifts</a></li>
                            <li><a href="#">accesories</a></li>
                            <li><a href="#">pets gifts</a></li>
                            <li><a href="#">specials</a></li>
                        </ul>

                        <div class="title"><span class="title_icon"><img src="images/bullet6.gif" alt="" title="" /></span>Partners</div>

                        <ul class="list">
                            <li><a href="#">accesories</a></li>
                            <li><a href="#">pets gifts</a></li>
                            <li><a href="#">specials</a></li>
                            <li><a href="#">hollidays gifts</a></li>
                            <li><a href="#">accesories</a></li>
                            <li><a href="#">pets gifts</a></li>
                            <li><a href="#">specials</a></li>
                            <li><a href="#">hollidays gifts</a></li>
                            <li><a href="#">accesories</a></li>
                        </ul>

                    </div>
-->

                </div><!--end of right content-->




                <div class="clear"></div>
            </div><!--end of center content-->


            <div class="footer">
                <div class="left_footer"><img src="images/footer_logo.gif" alt="" title="" /><br /> <a href="http://csscreme.com/freecsstemplates/" title="free css templates"><img src="images/csscreme.gif" alt="free css templates" border="0" /></a></div>
                <div class="right_footer">
                    <a href="#">home</a>
                    <a href="#">about us</a>
                    <a href="#">services</a>
                    <a href="#">privacy policy</a>
                    <a href="#">contact us</a>

                </div>


            </div>

        </div>
    </body>
</html>