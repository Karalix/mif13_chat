<%-- 
    Document   : affichage
    Created on : 29 sept. 2015, 15:14:12
    Author     : emilie
--%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.univlyon1.chat.GestionMessages"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="fr.univlyon1.chat.Message"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
        String room = request.getParameter("room");
        String user = request.getParameter("login");
        if(room == null)
        {
            room = "default" ;
        }  
        ServletContext context = request.getServletContext();
        GestionMessages gm = (GestionMessages)context.getAttribute("gestionmessage");
        ArrayList<Message> messages = gm.getMessagesByRoom(context,room);
        pageContext.setAttribute("messages", messages);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="5 URL=affichage.jsp?login=<%=user%>&room=<%=room%>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <c:forEach items="${messages}" var="message">
        <p><span style="font-weight: bold;">${message.sender} : </span>${message.texte}</p>
    </c:forEach>  
    </body>
</html>