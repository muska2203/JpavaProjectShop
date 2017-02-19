<%-- 
    Document   : index
    Created on : 19.02.2017, 21:54:06
    Author     : admin
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="folder.Items"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            
        </title>
    </head>
    <body>
        <h1>Item list!</h1>
        <% 
                Items it = new Items();
                String name = (String)request.getParameter("name");
                //String name  = "glasses";
                ResultSet res = it.findByName(name);
                int i =0;
                while(res.next())
                {
                String tmp = res.getString("name");%>
                <p>
                   <%=tmp%>
                </p>
                <%}
            %>
    </body>
</html>
