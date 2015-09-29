<%-- 
    Document   : Messages
    Created on : 28 sept. 2015, 21:11:51
    Author     : Alix
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="fr.univlyon1.chat.Message"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! 
//private List messages = new ArrayList();
private Map rooms = new HashMap() ;
%>
    <%
        String room = ((String)session.getAttribute("room"));
        if(room == null)
        {
            room = "default" ;
        }
        if(rooms.get(room) == null)
        {
            rooms.put(room, new ArrayList());
        }
        //String user = "Alix" ;
        
        String user = ((String)session.getAttribute("login"));
        if(user == null)
        {
            user = "Bob";
        }
        String texte = request.getParameter("texte");
        //user = request.getParameter("login");
        if(texte != null)
        {
            ((List)rooms.get(room)).add(new Message(user,texte));
        }
        response.setHeader("Refresh", "5");
        pageContext.setAttribute("messages", ((List)rooms.get(room)));
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
