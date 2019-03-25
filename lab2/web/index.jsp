<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Lab 2 SOA AGH</title>
  </head>
  <body>
  Some not so random text in here.
  <%
    Date temp = new Date();
    out.print("<h2> Scripted date: " + temp.toString() +"</h2>");
  %>
  </body>
</html>
