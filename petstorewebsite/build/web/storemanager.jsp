<%--
    Document   : storemanager
    Created on : Nov 30, 2009, 9:43:18 AM
    Author     : Jeff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="entity.Store"%>
<%@page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="store" class="entity.Store" scope="request" />

<%
            //define mode string
            final String addstoremode = "AddNewStore";//
            final String editmode = "EditMode";//one record returned
            final String viewmode = "ViewMode"; //all record return
            final String deletemode = "DeleteMode";//no record return
            //view condition
            final String viewall = "ViewAll";
            final String viewbyStoreid = "ViewBySID";
            final String viewbyRegionid = "ViewByRID";
            final String viewbyName = "ViewByName";
            final String viewbyCity = "ViewByCity";
            final String viewbyState = "ViewByCity";
            final String viewbyZip = "ViewByCity";
            final String viewbyPhone = "ViewByCity";


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Manager</title>
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
                    <h2>Pet Store Manager</h2>
<a href="index.jsp">Back to Index</a><BR>
                            <form action="/PetStore/storeadmin" name="viewallstore" method="POST">
                                <input type="hidden" name="mode" value="<%=viewmode%>" />
                                <input type="hidden" name="viewcondition" value="<%=viewall%>" />
                                <input type="submit" value="View All Store" name="viewallstore" />&nbsp;&nbsp;
                            </form>

                            <form action="storemanager.jsp" name="addstore" method="post">
                                <input type="hidden" name="mode" value="<%=addstoremode%>" />
                                <input type="submit" value="Add Store" name="addstore" />&nbsp;&nbsp;
                            </form>

                            <form action="/PetStore/storeadmin" name="editstore" method="POST">
                                <input type="hidden" name="mode" value="<%=viewmode%>" />
                                <input type="hidden" name="edit" value="true" />
                                <input type="hidden" name="viewcondition" value="<%=viewbyStoreid%>" />
                                Store ID: <input type="text" name="storeid" value="" size="5" />
                                <input type="submit" value="Edit" name="editstore" />&nbsp;&nbsp;
                            </form>
                            <%
                                String mode = request.getParameter("mode");
                                if (mode != null && mode.equals(editmode)) {// return one record to edit
                                    ArrayList<Store> storelist = (ArrayList<Store>) request.getAttribute("storelist");
                                     for (Iterator iter = storelist.iterator(); iter.hasNext();) {
                                        Store sto = (Store) iter.next();
                            %>
                            <form action="/PetStore/storeadmin?mode=<%=editmode%>" name="editstore" method="POST">
                                Store Information<BR>
                                Store ID:<input type="text" name="sid" size="20" value="<%=sto.getSid()%>" /><BR>
                                Region ID:<input type="text" name="regionid" value="<%=sto.getRegionid()%>" size="20" /><BR>
                                Name: <input type="text" name="name" size="20" value="<%=sto.getName()%>" /><BR>
                                Street Addr: <input type="text" name="street" size="20" value="<%=sto.getStreet()%>" /><BR>
                                City:<input type="text" name="city" size="20" value="<%=sto.getCity()%>" /><BR>
                                State:<input type="text" name="state" value="<%=sto.getState()%>" size="20" /><BR>
                                ZipCode:<input type="text" name="zip" value="<%=sto.getZip()%>" size="20"/><BR>
                                Phone: <input type="text" name="phone" value="<%=sto.getPhone()%>" size="20" /><BR>
                                Manager ID:<input type="text" name="managerid" value="<%=sto.getManager()%>" size="20" /><BR>
                                <input type="submit" value="Edit Store" name="submit" />
                            </form>

                            <form action="/PetStore/storeadmin" name="deletestore" method="POST">
                                <input type="hidden" name="mode" value="<%=deletemode%>" />
                                <input type="hidden" name="sid" value="<%=sto.getSid()%>" />
                                <input type="submit" value="Delete" name="submit" />
                            </form>
                            <%
                                    }//end of for
                                }//end of edit mode
                                if (mode != null && mode.equals(addstoremode)) {
                            %>
                            <Br><BR>
                            Enter New Store Information:
                            <form action="/PetStore/storeadmin" method="post" name="addstore">
                                <input type="hidden" name="mode" value="<%=addstoremode%>" />
                                Store Name: <input type="text" name="name" value="petsmart" size="20" /> <br>
                                Street Address:<input type="text" name="street" value="" size="20" /> <br>
                                City :<input type="text" name="city" value="" size="10" /> <br>
                                State: <input type="text" name="state" value="" size="5" /> <br>
                                Zip Code:<input type="text" name="zip" value="" size="5" /> <br>
                                Phone:<input type="text" name="phone" value="" size="12" /> <br>
                                Region :<select name="region">
                                    <option>NorthEast</option>
                                    <option>SouthEast</option>
                                    <option>Center</option>
                                    <option>NorthWest</option>
                                    <option>SouthWest</option>
                                    <option>International</option>
                                </select> <br>
                                Manager: <input type="text" name="manager" value="" size="10" /> <br>
                                <input type="submit" value="Submit" name="submit" />
                            </form>
                            <% }
                                if (mode != null && mode.equals(viewmode)) {
                                    ArrayList<Store> storelist = (ArrayList<Store>) request.getAttribute("storelist");
                            %>
                            <TABLE>
                                <TR>
                                    <TH>Store ID</TH>
                                    <TH>Region ID</TH>
                                    <TH>Name</TH>
                                    <TH>Street Addr</TH>
                                    <TH>City</TH>
                                    <TH>State</TH>
                                    <TH>Zip</TH>
                                    <TH>Phone</TH>
                                    <TH>Manager</TH>
                                </TR>
                                <%

                                    for (Iterator iter = storelist.iterator(); iter.hasNext();) {
                                        Store sto = (Store) iter.next();
                                %>
                                <TR>
                                    <TD><%= sto.getSid()%></TD>
                                    <TD><%= sto.getRegionid()%></TD>
                                    <TD><%=sto.getName()%></TD>
                                    <TD><%=sto.getStreet()%></TD>
                                    <TD><%=sto.getCity()%></TD>
                                    <TD><%=sto.getState()%></TD>
                                    <TD><%=sto.getZip()%></TD>
                                    <TD><%=sto.getPhone()%></TD>
                                    <TD><%=sto.getManager()%></TD>
                                </TR>

                                <%
                                    }
                                %>
                            </TABLE>
                            <%
                                }
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