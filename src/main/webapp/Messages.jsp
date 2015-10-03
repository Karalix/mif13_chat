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


<%if(request.getMethod()=="GET"){%>
<jsp:include page="affichage.jsp"/>   
<%} else {%>
<jsp:include page="Init"/>
<jsp:include page="affichage.jsp"/> 
<% } %>