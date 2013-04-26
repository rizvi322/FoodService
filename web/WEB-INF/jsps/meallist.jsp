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
<a href="foodservlet?type=show">show foodlist</a>
&nbsp;&nbsp;
<a href="welcomeservlet">todays meals</a>
&nbsp;&nbsp;
<a href="logoutservlet">logout</a>

<h3>List of available meals : </h3>
<table width="60%" border="1">
    <tr>
        <th>Serial</th>
        <th>Meal Id</th>
        <th>Meal Items</th>
        <th>Options</th>
    </tr>

    <c:forEach var="meal" items="${meals}" varStatus="serial">
        <tr>
            <td>${serial.count}</td>
            <td>${meal.id}</td>
            <td>${meal.items}</td>
            <td><a href="mealservlet?type=remove&id=${meal.id}">remove</a></td>
        </tr>
    </c:forEach>
</table>

<%@include file="../templates/footer.jsp"%>