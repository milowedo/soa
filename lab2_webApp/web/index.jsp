<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Millosz
  Date: 19.03.2019
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>laboratorium numer 2</title>
  </head>
  <body>
  <h3>To jest moja pierwsza aplikacja jee</h3>
  <%
    Date temp = new Date();
    out.print("<h2>" + temp.toString() + "</h2>");
  %>
  <hr>
  <a href="any_number_post.html">dowolna ilość liczb metodą POST</a><br>
  <a href="pierwszy.html">strona "pierwszy"</a>
  <br>
  <hr>
  <br>
  <a href="adultness.html">Doradca piwny</a>
  </body>
</html>
