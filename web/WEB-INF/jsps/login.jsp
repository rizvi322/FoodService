<%@include file="../templates/header.jsp"%>

<a href="welcomeservlet">todays meals</a>
&nbsp;&nbsp;
<h3>Login Form : </h3>
<form action="loginservlet" method="post">
    <table border="0">
        <tr>
            <td>Username* : </td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>Password*  : </td>
            <td><input type="password" name="password"/></td>
        </tr>
    </table>
    <input type="submit" value="login">
</form>

<%@include file="../templates/footer.jsp"%>

