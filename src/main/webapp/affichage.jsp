<%-- 
    Document   : affichage
    Created on : 29 sept. 2015, 15:14:12
    Author     : emilie
--%>
<jsp:useBean id="unbean" class="fr.univlyon1.chat.GestionMessages" scope="application"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="fr.univlyon1.chat.Message"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
        String room = ((String)session.getAttribute("room"));
        if(room == null)
        {
            room = "default" ;
        }
        
        response.setHeader("Refresh", "5");
        pageContext.setAttribute("messages", unbean.getMessagesByRoom(room));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <c:forEach items="${messages}" var="message">
        <p><span style="font-weight: bold;">${message.sender} : </span>${message.texte}</p>
    </c:forEach>
    </body>
</html>