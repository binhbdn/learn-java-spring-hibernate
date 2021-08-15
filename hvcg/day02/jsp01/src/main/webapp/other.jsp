<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="vn.hvcg.Student" %>
<html>
    <head>
        <title>A Simple JSP Example</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <p>Current date is : <%= new java.util.Date() %></p>
        <p>Current date is : <% out.println("\"" + new java.util.Date().toString() + "\""); %></p>

        <c:if test="${not empty name}">
            <h1>Hello ${name} !!! from other.jsp</h1>
        </c:if>

        <c:if test="${empty name}">
            <h1>Hello World from other.jsp</h1>
        </c:if>

        <h2>Get data from ArrayList - Solution 1</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${students}" var="student">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

        <h2>Get data from ArrayList - Solution 2</h2>
<%
        java.util.ArrayList<Student> students =
            (java.util.ArrayList<Student>)request.getAttribute("students");

        out.println("<table class=\"table\"><thead><tr><th>Id</th><th>Name</th></tr></thead><tbody>");
        for (Student student:students) {
            out.println(String.format("<tr><td>%d</td><td>%s</td></tr>",student.getId(),student.getName()));
        };
        out.println("</tbody></table>");
%>
    </body>
</html>