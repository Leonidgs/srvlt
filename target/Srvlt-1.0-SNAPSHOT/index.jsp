<%@ page import="ru.appline.logic.Model" %>

<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1><%= "Home Page For Working with Users" %></h1>
        Enter User ID(0 for print full list of users)
        <br/>
        Available: <%
        Model model = Model.getInstance();
            out.print(model.getFromList().size());
        %>
    <form method="get" action="get">
        <label>
            ID:<input type="text", name="id"><br/>
        </label>
        <button type="submit">Search</button>
    </form>
        <a href="addUser.html">Create a new User</a>
    </body>
</html>