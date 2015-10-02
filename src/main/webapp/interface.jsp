<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    
    <head>
        <title>Chat</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1><%= request.getSession().getAttribute("room") %></h1>
        <iframe style="width: 100%;height: 300px;" src="Messages.jsp" name="messages"></iframe>
        <form action="Messages.jsp" target="messages" method="post">
            <input type="text" name="texte"/>
            <input type="submit" value="Envoyer"/>
        </form>
        <br>
        <form action="Init" method="get">
            <input type="submit" value="deconnexion"/>
        </form>
    </body>
</html>

