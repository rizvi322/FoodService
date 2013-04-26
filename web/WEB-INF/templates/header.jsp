<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%


    if(request.getAttribute("error") == "" || request.getAttribute("error") == null )
    {
        request.setAttribute("error","");
    }
%>
<html>
    <head>
        <title>${title}</title>
        <!-- <link href="../css/styles.css" type="text/css" media="screen" rel="stylesheet"/> -->
        <style type="text/css">
            *{
                margin: 0;
                padding: 0;
            }

            th,td{
                text-align: left;
                padding-left: 5px;
                padding: 5px;
            }

            a{
                margin-bottom: 10px;
                text-decoration: underline;
                color: #6495ed;
            }

            a:hover{

                color: #dc143c;
            }

            select{
                padding: 2px;
            }

            div.error{

                background-color: #ffb6c1;
                width: 60%;
                padding: 5px;
                margin-bottom: 10px;
            }

            div.comment{

                background-color: #d3d3d3;
                padding: 5px;
                border: 5px;
                width: 60%;
                margin-bottom: 5px;
            }
            h3{
                margin-top: 10px;
                margin-bottom: 10px;
            }

            div.cbody{

                margin-left: 10px;
                margin-top: 10px;
            }

        </style>
    </head>
    <body>
        <div class="cbody">
            <c:if test="${error != ''}">
                <div class="error">
                    <p>${error}</p>
                </div>
            </c:if>