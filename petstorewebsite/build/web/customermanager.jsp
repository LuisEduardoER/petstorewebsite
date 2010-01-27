<%--
    Document   : customermanager.jsp
    Created on : Dec 9, 2009, 10:57:34 PM
    Author     : Jeff
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="entity.Customer"%>
<%@page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="customer" class="entity.Customer" scope="request" />

<%
            //define mode string
            final String addnewmode = "AddNewMode";//
            final String editmode = "EditMode";//one record returned
            final String viewmode = "ViewMode"; //all record return
            final String deletemode = "DeleteMode";//no record return
            final String home = "HOME";
            final String business = "BUSINESS";
            //view condition
            final String viewall = "ViewAll";
            final String viewbyCondition = "ViewByCondition";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Manager</title>
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
                         <h2>Pet Store Customer Admin Manager</h2>

                        <%
                            String usertype = (String) session.getAttribute("usertype");
                            if (usertype == null) {
                                usertype = "";
                            }
                            if (usertype.equals(entity.UserCred.adminuser)) {
                        %>
                        <a href="index.jsp">Back to Index</a><BR>
                        <form action="/PetStore/customeradmin" name="viewallcustomer" method="POST">
                        <input type="hidden" name="mode" value="<%=viewmode%>" />
                        <input type="hidden" name="viewcondition" value="<%=viewall%>" />
                        <input type="hidden" name="customertype" value="<%=home%>" />
                        <input type="submit" value="View All HOME Customer" name="viewall" />&nbsp;&nbsp;
                    </form>
                    <form action="/PetStore/customeradmin" name="viewallcustomer" method="POST">
                        <input type="hidden" name="mode" value="<%=viewmode%>" />
                        <input type="hidden" name="viewcondition" value="<%=viewall%>" />
                        <input type="hidden" name="customertype" value="<%=business%>" />
                        <input type="submit" value="View All BUSINESS Customer" name="viewall" />&nbsp;&nbsp;
                    </form>

                    <form action="customermanager.jsp" name="addnew" method="post">
                        <input type="hidden" name="mode" value="<%=addnewmode%>" />
                        <input type="submit" value="Add Customer" name="addnew" />&nbsp;&nbsp;
                    </form>

                    <form action="/PetStore/customeradmin" name="editcustomer" method="POST">
                        <input type="hidden" name="mode" value="<%=viewmode%>" />
                        <input type="hidden" name="edit" value="true" />
                        <input type="hidden" name="viewcondition" value="<%=viewbyCondition%>" />
                        <input type="text" name="customerid" value="" size="5" />

                        <input type="submit" value="Edit" name="Edit Customer" />&nbsp;&nbsp;
                    </form>

                    <%
                            String mode = request.getParameter("mode");
                            if (mode != null && mode.equals(editmode)) {// return one record to edit
                                ArrayList<Customer> cus_list = (ArrayList<Customer>) request.getAttribute("cus_list");
                                for (Iterator iter = cus_list.iterator(); iter.hasNext();) {
                                    Customer cus = (Customer) iter.next();
                    %>

                    <form action="/PetStore/customeradmin" name="edititem" method="POST">
                        <input type="hidden" name="mode" value="<%=editmode%>" />
                        Customer Information<BR>
                        Customer Type:<input type="radio" name="customertype" value="<%=home%>" />HOME<input type="radio" name="customertype" value="<%=business%>" />Business
                        Customer ID:<input type="text" name="customerid" size="20" value="<%=cus.getCustomerid()%>" /><BR>
                        First Name:<input type="text" name="firstname" value="<%=cus.getFirstname()%>" size="20" /><BR>
                        Last Name:<input type="text" name="lastname" value="<%=cus.getLastname()%>" size="20" /><BR>
                        Street Addr:<input type="text" name="streetaddr" value="<%=cus.getStreetaddr()%>" size="20" /><BR>
                        City: <input type="text" name="city" value="<%=cus.getCity()%>" size="20" /><BR>
                        State: <input type="text" name="state" value="<%=cus.getState()%>" size="20" /><BR>
                        Zip: <input type="text" name="zip" value="<%=cus.getZip()%>" size="20" /><BR>
                        Phone:<input type="text" name="phone" value="<%=cus.getPhone()%>" size="20" /><BR>
                        For Home Customer:<BR>
                        Gender:
                        <input type="radio" name="gender" value="male" />Male<input type="radio" name="gender" value="female" />Female<BR>
                        Marriage:
                        <input type="radio" name="marriage" value="married" />Married<input type="radio" name="marriage" value="single" />Single<BR>
                        Age: <input type="text" name="age" value="<%=cus.getAge()%>" size="20" /><BR>
                        Preference:<input type="text" name="preference" value="<%=cus.getPreference()%>" size="20" /><BR>
                        For Business Customer:<BR>
                        Company Name:<input type="text" name="companyname" value="<%=cus.getCompanyname()%>" size="20" /><BR>
                        Income:<input type="text" name="income" value="<%=cus.getIncome()%>" size="20" /><BR>
                        Industry: <input type="text" name="industry" value="<%=cus.getIndustry()%>" size="20" /><BR>
                        Company Descrption:<textarea name="company_desc" rows="4" cols="20"><%=cus.getCompany_desc()%></textarea><BR>
                        <input type="submit" value="Edit Record" name="submit" />
                    </form>

                    <form action="/PetStore/customeradmin" name="deleteitem" method="POST">
                        <input type="hidden" name="mode" value="<%=deletemode%>" />
                        <input type="hidden" name="customerid" value="<%=cus.getCustomerid()%>" />
                        <input type="submit" value="Delete Record" name="submit" />
                    </form>


                    <%
                                }//end of for
                            }//end of edit mode
                            if (mode != null && mode.equals(addnewmode)) {
                    %>

                    <Br><BR>
                    Enter New Customer Record:
                    <form action="/PetStore/customeradmin" name="newcustomerrecord" method="POST">
                        <input type="hidden" name="mode" value="<%=addnewmode%>" />
                        Customer Type:<BR>
                        <input type="radio" name="customertype" value="<%=home%>" />Home Customer
                        <input type="radio" name="customertype" value="<%=business%>" />Business Customer<BR>
                        First Name:<input type="text" name="firstname" value="" size="20" /><BR>
                        Last Name:<input type="text" name="lastname" value="" size="20" /><BR>
                        Street Addr:<input type="text" name="streetaddr" value="" size="20" /><BR>
                        City: <input type="text" name="city" value="" size="20" /><BR>
                        State: <input type="text" name="state" value="" size="20" /><BR>
                        Zip: <input type="text" name="zip" value="" size="20" /><BR><BR>
                        Phone:<input type="text" name="phone" value="" size="20" /><BR>
                        For Home Customer:<BR>
                        Gender:
                        <input type="radio" name="gender" value="male" />Male<input type="radio" name="gender" value="female" />Female<BR>
                        Marriage:
                        <input type="radio" name="marriage" value="married" />Married<input type="radio" name="marriage" value="single" />Single<BR>
                        Age: <input type="text" name="age" value="" size="20" /><BR>
                        Income:<input type="text" name="income" value="" size="20" /><BR>
                        Preference:<input type="text" name="preference" value="" size="20" /><BR><BR>
                        For Business Customer:<BR>
                        Company Name:<input type="text" name="companyname" value="" size="20" /><BR>
                        Industry: <input type="text" name="industry" value="" size="20" /><BR>
                        Company Descrption:<textarea name="company_desc" rows="4" cols="20">
                        </textarea>
                        <input type="submit" value="Add" name="addnew" />
                    </form>

                    <% }
                            String type =(String) request.getAttribute("customertype");
                            if (mode != null && mode.equals(viewmode) && type.equals("HOME")) {
                                ArrayList<Customer> cus_list = (ArrayList<Customer>) request.getAttribute("cus_list");
                    %>
                    <TABLE>
                        <TR>
                            <TH>Customer ID</TH>
                            <TH>First Name</TH>
                            <TH>Last Name</TH>
                            <TH>Street Addr</TH>
                            <TH>City</TH>
                            <TH>State</TH>
                            <TH>ZipCode</TH>
                            <TH>Phone<TH>
                            <TH>Gender</TH>
                            <TH>Marriage</TH>
                            <TH>Age</TH>
                            <TH>Preference</TH>
                        </TR>
                        <%
                                for (Iterator iter = cus_list.iterator(); iter.hasNext();) {
                                    Customer cus = (Customer) iter.next();
                        %>
                        <TR>
                            <TD><%= cus.getCustomerid()%></TD>
                            <TD><%= cus.getFirstname()%></TD>
                            <TD><%= cus.getLastname()%></TD>
                            <TD><%= cus.getStreetaddr()%></TD>
                            <TD><%= cus.getCity()%></TD>
                            <TD><%= cus.getState()%></TD>
                            <TD><%= cus.getZip()%></TD>
                            <TD><%= cus.getPhone()%></TD>
                            <TD><%= cus.getGender()%></TD>
                            <TD><%= cus.isMarriage()%></TD>
                            <TD><%= cus.getAge()%></TD>
                            <TD><%= cus.getPreference()%></TD>
                        </TR>
                        <%
                                }
                        %>
                    </TABLE>
                    <%
                            }
                            if (mode != null && mode.equals(viewmode) && type.equals("BUSINESS")) {
                                ArrayList<Customer> cus_list = (ArrayList<Customer>) request.getAttribute("cus_list");
                    %>
                    <TABLE>
                        <TR>
                            <TH>Customer ID</TH>
                            <TH>First Name</TH>
                            <TH>Last Name</TH>
                            <TH>Street Addr</TH>
                            <TH>City</TH>
                            <TH>State</TH>
                            <TH>ZipCode</TH>
                            <TH>Phone<TH>
                            <TH>Industry</TH>
                            <TH>Company Name</TH>
                            <TH>Company Description</TH>
                            <TH>Annual Income</TH>
                        </TR>
                        <%
                                for (Iterator iter = cus_list.iterator(); iter.hasNext();) {
                                    Customer cus = (Customer) iter.next();
                        %>
                        <TR>
                            <TD><%= cus.getCustomerid()%></TD>
                            <TD><%= cus.getFirstname()%></TD>
                            <TD><%= cus.getLastname()%></TD>
                            <TD><%= cus.getStreetaddr()%></TD>
                            <TD><%= cus.getCity()%></TD>
                            <TD><%= cus.getState()%></TD>
                            <TD><%= cus.getZip()%></TD>
                            <TD><%= cus.getPhone()%></TD>
                            <TD><%= cus.getIndustry()%></TD>
                            <TD><%= cus.getCompanyname()%></TD>
                            <TD><%= cus.getCompany_desc()%></TD>
                            <TD><%= cus.getIncome()%></TD>
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
                            PetShop is the nation’s leading supplier in pet supplies ranging from pet food, feeders, medicine, kennels, toy’s to pet furniture.
                        </p>

                    </div>

                    <div class="right_box">

                        <div class="title"><span class="title_icon"><img src="images/bullet4.gif" alt="" title="" /></span>Promotions</div>
                        <div class="new_prod_box">
                            <a href="details.html">Kennel</a>
                            <div class="new_prod_bg">
                                <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                                <a href="details.html"><img src="images/prod2.gif" alt="" title="" class="thumb" border="0" /></a>
                            </div>
                        </div>

                        <div class="new_prod_box">
                            <a href="details.html">Critter Trail</a>
                            <div class="new_prod_bg">
                                <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                                <a href="details.html"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                            </div>
                        </div>

                        <div class="new_prod_box">
                            <a href="details.html">Rabbit Food</a>
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
                            <li><a href="#">canned Food</a></li>
                            <li><a href="#">dry Food</a></li>
                            <li><a href="#">treats</a></li>
                            <li><a href="#">beds</a></li>
                            <li><a href="#">kennels</a></li>

                        </ul>

                        <div class="title"><span class="title_icon"><img src="images/bullet6.gif" alt="" title="" /></span>Partners</div>

                        <ul class="list">
                            <li><a href="#">Petco</a></li>
                            <li><a href="#">Petsmart</a></li>
                            <li><a href="#">Dogswell</a></li>
                            <li><a href="#">Dog Rocks</a></li>
                            <li><a href="#">Fetch Pet Care</a></li>
                            <li><a href="#">The Dog House</a></li>
                            <li><a href="#">The Pawkit</a></li>

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