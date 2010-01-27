<%--
    Document   : transcationmanager
    Created on : Dec 9, 2009, 10:59:56 PM
    Author     : Jeff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="entity.Transcation"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*" %>
<%@page import="entity.Product" %>

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
        <title>Transcation Admin</title>
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
                        <h2>Pet Store Transcation Admin Manager</h2>
                        <a href="index.jsp">Back to Index</a><BR>
                        <%
            String usertype = (String) session.getAttribute("usertype");
            if (usertype == null) {
                usertype = "";
            }
            if (usertype.equals(entity.UserCred.adminuser)) {
                        %>

                        <form action="/PetStore/transcationadmin" name="viewalltranscation" method="POST">
                            <input type="hidden" name="mode" value="<%=viewmode%>" />
                            <input type="hidden" name="viewcondition" value="<%=viewall%>" />
                            <input type="submit" value="View All Transcation" name="viewall" />&nbsp;&nbsp;
                        </form>

                        <form action="transcationmanager.jsp" name="addnew" method="post">
                            <input type="hidden" name="mode" value="<%=addnewmode%>" />
                            <input type="submit" value="Add Transcation" name="addnew" />&nbsp;&nbsp;
                        </form>

                        <form action="/PetStore/transcationadmin" name="edittranscation" method="POST">
                            <input type="hidden" name="mode" value="<%=viewmode%>" />
                            <input type="hidden" name="edit" value="true" />
                            <input type="hidden" name="viewcondition" value="<%=viewbyCondition%>" />
                            Order ID:<input type="text" name="oid" value="" size="5" />
                            <input type="submit" value="Edit" name="edittranscation" />&nbsp;&nbsp;
                        </form

                        <%
            String mode = request.getParameter("mode");
            if (mode != null && mode.equals(editmode)) {// return one record to edit
                ArrayList<Transcation> tran_list = (ArrayList<Transcation>) request.getAttribute("tran_list");
                for (Iterator iter = tran_list.iterator(); iter.hasNext();) {
                    Transcation tran = (Transcation) iter.next();
                        %>

                        <form action="/PetStore/transcationadmin" name="edititem" method="POST">
                            <input type="hidden" name="mode" value="<%=editmode%>" />
                            Transcation Information<BR>
                            Order ID:<input type="text" name="oid" value="<%=tran.getOrderid()%>" size="20" /><BR>
                            Store ID:<input type="text" name="sid" value="<%=tran.getStoreid()%>" size="20" /><BR>
                            Customer ID:<input type="text" name="cid" value="<%=tran.getCustomerid()%>" size="20" /><BR>
                            Salesman ID:<input type="text" name="salesmanid" value="<%=tran.getSalesmanid()%>" /><BR>
                            Order Date:<input type="text" name="orderdate" value="<%=tran.getOrderdate()%>" size="20" /><BR>
                            <TABLE>
                                <TR>
                                    <TH>ProductID</TH>
                                    <TH>Quantity</TH></TR>
                                    <%
                                        HashMap<Product, Integer> purchaselist = tran.getPurchaselist();
                                        Iterator itp = purchaselist.keySet().iterator();
                                        while (itp.hasNext()) {
                                            Product pro1 = (Product) itp.next();
                                            int quantity = purchaselist.get(pro1);
                                    %>
                                <TR>
                                    <TD><input type="text" name="pid" value="<%=pro1.getPid()%>" size="20" /></TD>
                                    <TD><input type="text" name="quantity" value="<%=quantity%>" size="20"/></TD>
                                </TR>
                                <%
                                        }
                                %>
                            </TABLE>
                            <input type="submit" value="Edit Record" name="submit" />
                        </form>

                        <form action="/PetStore/transcationadmin" name="deleteitem" method="POST">
                            <input type="hidden" name="mode" value="<%=deletemode%>" />
                            <input type="hidden" name="oid" value="<%=tran.getOrderid()%>" />
                            <input type="submit" value="Delete Record" name="submit" />
                        </form>

                        <%
                }//end of for
            }//end of edit mode
            if (mode != null && mode.equals(addnewmode)) {
                        %>

                        <Br><BR>
                        Enter New Transcation Record:
                        <form action="/PetStore/transcationadmin" name="newrecord" method="POST">
                            <input type="hidden" name="mode" value="<%=addnewmode%>" />
                            Customer ID:<input type="text" name="cid" value="" size="20" /><BR>
                            Store ID:<input type="text" name="sid" value="" size="20" /><BR>
                            Salesman ID:<input type="text" name="salesmanid" value="" size="20" /><BR>
                            Order Date:<input type="text" name="odate" value="" size="20" /><BR>
                            <% for (int i = 0; i < 5; i++) {%>
                            ProductID:<input type="text" name="pid" value="" size="20" />
                            Quantity: <input type="text" name="quantity" value="" size="20" /><BR>
                            <% }%>
                            <input type="submit" value="Add" name="addnew" />
                        </form>

                        <% }
            if (mode != null && mode.equals(viewmode)) {
                ArrayList<Transcation> tran_list = (ArrayList<Transcation>) request.getAttribute("tran_list");
                        %>
                        <TABLE>
                            <TR>
                                <TH>Order ID</TH>
                                <TH>Customer ID</TH>
                                <TH>Store ID</TH>
                                <TH>Salesman ID</TH>
                                <TH>Order Date</TH>
                                <TH>Order Total</TH>
                                <TH>Order Details</TH>
                            </TR>
                            <%
                                    for (Iterator iter = tran_list.iterator(); iter.hasNext();) {
                                        Transcation tran = (Transcation) iter.next();
                            %>
                            <TR>
                                <TD><%= tran.getOrderid()%></TD>
                                <TD><%= tran.getCustomerid()%></TD>
                                <TD><%= tran.getStoreid()%></TD>
                                <TD><%= tran.getSalesmanid()%></TD>
                                <TD><%= tran.getOrderdate()%></TD>
                                <TD><%= tran.getOrdertotal()%></TD>
                                <TD>
                                    <TABLE>
                                        <TR>
                                            <TH>ProductID</TH>
                                            <TH>Quantity</TH>
                                        </TR>
                                        <%
                                        HashMap<Product, Integer> purchaselist = tran.getPurchaselist();
                                        Iterator itp = purchaselist.keySet().iterator();
                                        while (itp.hasNext()) {
                                            Product pro1 = (Product) itp.next();
                                            int quantity = purchaselist.get(pro1);
                                        %>
                                        <TR>
                                            <TD><%=pro1.getPid()%></TD>
                                            <TD><%=quantity%></TD>
                                        </TR>
                                        <%
                                        }
                                        %>
                                    </TABLE>
                                </TD>
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