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
        <% String user = request.getParameter("login");%>
        <% String room = request.getParameter("room");%>
        <h1><%= room %></h1>
        <iframe style="width: 100%;height: 300px;" name="messages" src="Messages.jsp?login=<%=user%>&room=<%=room%>" ></iframe>
        <form action="Init" target="messages" method="post">
            <input type="text" name="texte"/>
            <input type="hidden" name="login" value="<%=user%>"/>
            <input type="hidden" name="room" value="<%=room%>"/>
            <input type="hidden" name="but" value="msg"/>
            <input type="submit" value="Envoyer"/>
        </form>
        <br>
        <form action="Init" method="get">
            <input type="hidden" name="but" value="out"/>
            <input type="submit" value="deconnexion"/>
        </form>
    </body>
</html>

