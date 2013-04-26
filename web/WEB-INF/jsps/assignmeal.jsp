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
<a href="foodservlet?type=show">show foodlist</a>
&nbsp;&nbsp;
<a href="mealservlet?type=add">add meal</a>
&nbsp;&nbsp;
<a href="mealservlet?type=show_meallist">show meallist</a>
&nbsp;&nbsp;
<a href="welcomeservlet">todays meals</a>
&nbsp;&nbsp;
<a href="logoutservlet">logout</a>

<h3>Assign meal form : </h3>
<form action="mealservlet" method="post">
    <table border="0">
        <tr>
            <td>Meal Package* : </td>
            <td>
                <select name="meal_id">
                    <option value="0">Select Package</option>
                    <c:forEach var="meal" items="${meals}">
                        <option value="${meal.id}">${meal.items}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Meal Type* : </td>
            <td>
                <select name="meal_time">
                    <option value="">Select Option</option>
                    <option value="Breakfast">Breakfast</option>
                    <option value="Lunch">Lunch</option>
                    <option value="Dinner">Dinner</option>
                    <option value="Other">Other</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Meal Description : </td>
            <td><textarea rows="3" cols="50" name="description"></textarea></td>
        </tr>
    </table>
    <input type="hidden" name="type" value="assign"/>
    <input type="submit" value="assign meal">
</form>

<%@include file="../templates/footer.jsp"%>