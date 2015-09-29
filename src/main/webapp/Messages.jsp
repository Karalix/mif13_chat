<%-- 
    Document   : Messages
    Created on : 28 sept. 2015, 21:11:51
    Author     : Alix
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="fr.univlyon1.chat.Message"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! 
private List messages = new ArrayList();
%>
    <%
        String texte = request.getParameter("texte");
        String user = "Alix";
        user = request.getParameter("login");
        if(texte != null)
        {
            messages.add(new Message(user,texte));
        }
        response.setHeader("Refresh", "5");
        pageContext.setAttribute("messages", messages);
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
