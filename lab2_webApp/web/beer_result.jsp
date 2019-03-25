<%@ page import="com.beer.model.BeerExpert" %><%--
  Created by IntelliJ IDEA.
  User: Millosz
  Date: 19.03.2019
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Polecana marka</title>
</head>
<body style="text-align: center">
Polecamy piwo: <%
    String brand = BeerExpert.getBeerBrand();
    out.println(brand);
%>
<br><hr><br>
<a href="index.jsp">Back to index</a>
</body>
</html>
