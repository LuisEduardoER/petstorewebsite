<%-- 
    Document   : login
    Created on : Nov 30, 2009, 10:34:24 PM
    Author     : Jeff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="user" class="entity.UserCred" scope="session"/>
<jsp:setProperty name="user" property="*"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta http-equiv="refresh" content = "3; URL=/PetStore/index.jsp">
    </head>
    <body>
        <h1>Login Process</h1>
        <%
            String mode = request.getParameter("mode");
            if (mode.equals("login")) {
                String name = user.getUsername();
                String password = user.getPassword();
                if (user.getUsertype() == null) {
                    if (name.equals("admin") && password.equals("adminadmin")) {
                        user.setUsertype(entity.UserCred.adminuser);
                        session.setAttribute("usertype", user.getUsertype());
                        out.println("login as Admin");
                    }
                    if (name.equals("user") && password.equals("useruser")) {
                        user.setUsertype(entity.UserCred.normaluser);
                        session.setAttribute("usertype", user.getUsertype());
                        out.println("login as Normal User");
                    }
                }
            } else if (mode.equals("logoff")) {
                user.setUsertype(null);
                session.setAttribute("usertype",null);
                out.println("Log Off Successful");
            }


        %>
        You Login<br>
        Type: <%=user.getUsertype()%> <BR>
        <%
            if (user.getUsertype() != null) {
        %>
        <a href="index.jsp">Go to Index Page</a>
        <a href="login.jsp?mode=logoff">LogOff</a>
        <%        } else {
        %>
        <a href="index.jsp">Login Again</a>
        <%            }

        %>
    </body>
</html>
