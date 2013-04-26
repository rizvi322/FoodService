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
<a href="mealservlet?type=assign">assign meal</a>
&nbsp;&nbsp;
<a href="foodservlet?type=show">show foodlist</a>
&nbsp;&nbsp;
<a href="mealservlet?type=show_meallist">show meallist</a>
&nbsp;&nbsp;
<a href="welcomeservlet">todays meals</a>
&nbsp;&nbsp;
<a href="logoutservlet">logout</a>

<h3>Add food form : </h3>
<form action="mealservlet" method="post">
    <table border="0">
        <tr>
            <td>Food Items* : </td>
            <td>
                <select name="items" multiple="multiple">
                    <c:forEach var="food" items="${foods}">
                        <option value="${food.name}">${food.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="hidden" name="type" value="add"/>
    <input type="submit" value="add meal">
</form>

<%@include file="../templates/footer.jsp"%>