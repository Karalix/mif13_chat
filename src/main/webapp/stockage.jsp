<%-- 
    Document   : stockage
    Created on : 29 sept. 2015, 15:13:57
    Author     : emilie
--%>
<jsp:useBean id="unbean" class="fr.univlyon1.chat.GestionMessages" scope="application"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="fr.univlyon1.chat.Message"%>
<%  String room = ((String)session.getAttribute("room"));
        
        if(room == null)
        {
            room = "default" ;
        }
        
        String user = ((String)session.getAttribute("login"));
        if(user == null)
        {
            user = "Bob";
        }
        String texte = request.getParameter("texte");
        
        if(texte != null)
        {
            unbean.addMessageInRoom(application,room,new Message(user,texte));
        }
       
%>