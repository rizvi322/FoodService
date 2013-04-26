<%
    String username = (String)session.getAttribute("username");
    if(!username.equals("admin"))
    {
        response.sendRedirect("loginservlet");
    }
%>
<%@include file="../templates/header.jsp"%>

<a href="foodservlet?type=show">show foodlist</a>
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

<h3>Add food form : </h3>
<form action="foodservlet" method="post">
<table border="0">
    <tr>
        <td>Food Name* : </td>
        <td><input type="text" name="name"/></td>
    </tr>
    <tr>
        <td>Food Description : </td>
        <td><textarea rows="3" cols="50" name="description"></textarea></td>
    </tr>
</table>
<input type="hidden" name="type" value="add"/>
<input type="submit" value="add food">
</form>

<%@include file="../templates/footer.jsp"%>