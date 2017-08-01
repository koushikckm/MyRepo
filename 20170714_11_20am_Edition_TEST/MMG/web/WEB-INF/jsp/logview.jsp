<%--
    Document   : logview
    Created on : Jun 15, 2011, 11:29:54 AM
    Author     : PankajB
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.mazdausa.mmg.db.vo.AccessLogVO,java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hi, You are able to successfully connect to the database.</h1><br/>

        <h2>Records.</h2><br/>
        <table border="1">


        <%
        List<AccessLogVO> list=(List<AccessLogVO>)request.getAttribute("list");
        int i=1;
        if(list!=null)
            for(AccessLogVO accessLogVO: list)
                {
                    out.print("<tr>");
                    out.print("<td>"+i+".</td> <td>" +  accessLogVO.getRequestedTime()  +  "</td> <td>Path = "+ accessLogVO.getRelativeApplicationURI()+"</td>");
                    out.print("</tr>");
                    i++;
                }

        %>
        </table>



    </body>
</html>
