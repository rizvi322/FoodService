<%@include file="../templates/header.jsp"%>
<%

    String username = (String)session.getAttribute("username");
    if(username == null || username == "")
    {
%>
<a href="loginservlet">login</a>
&nbsp;&nbsp;
<%
    }
    else
    {
%>
<a href="foodservlet?type=add">add food</a>
&nbsp;&nbsp;
<a href="foodservlet?type=show">show foodlist</a>
&nbsp;&nbsp;
<a href="mealservlet?type=add">add meal</a>
&nbsp;&nbsp;
<a href="mealservlet?type=assign">assign meal</a>
&nbsp;&nbsp;
<a href="mealservlet?type=show_meallist">show meallist</a>
&nbsp;&nbsp;
<a href="logoutservlet">logout</a>
<%
    }
%>

<h3>Meals for the day : </h3>
<table width="60%" border="1">
    <tr>
        <th>Serial</th>
        <th>Meal Items</th>
        <th>Meal Type</th>
        <th>Meal Description</th>
        <c:if test="${username == 'admin'}">
            <th>Options</th>
        </c:if>
    </tr>
    <c:forEach var="meal" items="${meals}" varStatus="serial">
        <tr>
            <td>${serial.count}</td>
            <td>${meal.meal_items}</td>
            <td>${meal.meal_time}</td>
            <td>${meal.description}</td>
            <c:if test="${username == 'admin'}">
                <td><a href="mealservlet?type=remove_assign&id=${meal.id}">remove</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<br/>
<h3>Comments : </h3>
<form action="commentservlet" method="post">
    <table border="0">
        <tr>
            <td>Your Name* : </td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Your Comment* : </td>
            <td><textarea rows="3" cols="50" name="comment"></textarea> </td>
        </tr>
    </table>
    <input type="hidden" name="type" value="add_comment"/>
    <input type="submit" value="add comment"/>
</form>
<br/>
<div>
    <c:forEach var="comment" items="${commentList}">
        <div class="comment">
                &nbsp;&nbsp;&nbsp;Name : ${comment.name}<br>
                &nbsp;&nbsp;&nbsp;Time : ${comment.time}<br>
                &nbsp;&nbsp;&nbsp;Comment : ${comment.comment}<br>
        </div>
    </c:forEach>
</div>
<%@include file="../templates/footer.jsp"%>