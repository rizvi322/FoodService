<%
    String username = (String)session.getAttribute("username");
    if(!username.equals("admin"))
    {
        response.sendRedirect("loginservlet");
    }
%>
<%@include file="../templates/header.jsp"%>

<a href="foodservlet?type=add">add food</a>
&nbsp;&nbsp;
<a href="mealservlet?type=add">add meal</a>
&nbsp;&nbsp;
<a href="mealservlet?type=assign">assign meal</a>
&nbsp;&nbsp;
<a href="mealservlet?type=show_meallist">show meallist</a>
&nbsp;&nbsp;
<a href="welcomeservlet">todays meals</a>
&nbsp;&nbsp;
<a href="logoutservlet">logout</a>


<h3>List of available foods : </h3>
<table width="60%" border="1">
    <tr>
        <th>Serial</th>
        <th>Food ID</th>
        <th>Food Name</th>
        <th>Food Description</th>
        <th>Options</th>
    </tr>
        <c:forEach var="food" items="${foods}" varStatus="serial">
            <tr>
                <td>${serial.count}</td>
                <td>${food.id}</td>
                <td>${food.name}</td>
                <td>${food.description}</td>
                <td><a href="foodservlet?type=remove&id=${food.id}">remove</a></td>
            </tr>
        </c:forEach>
</table>

<%@include file="../templates/footer.jsp"%>