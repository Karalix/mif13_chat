/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emilie
 */
public class MessagesServlet extends EnhancedHttpServlet {

      private static GestionMessages gm;

    @Override
    public void init() throws ServletException{
        super.init();
        ServletContext context = this.getServletContext();
        gm = (GestionMessages) context.getAttribute("gestionmessage");
        if(gm == null)
        {
            gm = new GestionMessages();
            context.setAttribute("gestionmessage", gm);
        }
    }
   
    
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
           String room = (String)request.getAttribute("room");
           String idmsg = (String)request.getAttribute("idmsg");
           Boolean after = (Boolean)request.getAttribute("after");
           String format = defineOutputFormat(request, response);
           ServletContext context = request.getServletContext();
           GestionMessages gm = (GestionMessages)context.getAttribute("gestionmessage");
           ArrayList<Message> list = gm.getMessagesByRoom(context,room);
           PrintWriter out = response.getWriter();
           if(idmsg != null && after != null)
           {
               int entier = Integer.parseInt(idmsg);
               serveListMessages(out, format, list, entier);
           }
           else if(idmsg != null)
           {
               int entier = Integer.parseInt(idmsg);
               if(list.size()+1 >= entier ) {
                    Message m = list.get(entier);
                    serveSingleMessage(out, format, m);
               }
           }
           else
           {
               serveListMessages(out, format, list, 0);

           }
    }

    public void serveSingleMessage(PrintWriter out, String format, Message m) {
        switch(format) {
            case "xml" : out.println(m.toXml());
                break;
            default : out.println(m.toJson());
        }
    }
    
    public void serveListMessages(PrintWriter out, String format, List<Message> list, int beginIndex) {
        switch(format) {
            case "xml" :
                out.println("<messages>");
                for(int i = beginIndex; i < list.size(); i++)
                {
                    Message m = list.get(i);
                    out.println(m.toXml());
                }
                out.println("</messages>");
                break ;
            default :
                out.println("{\"messages\":[");
                boolean firstIteration = true ;
                for(int i = beginIndex; i < list.size(); i++)
                {
                    if(!firstIteration) {
                        out.print(",");
                    }
                    Message m = list.get(i);
                    out.println(m.toJson());
                    if(firstIteration) {
                        firstIteration = false ;
                    }
                }
                out.println("]}");
                break ;
        }
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override/*mais comment utiliser la methode put??????????????*/
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String text = request.getParameter("texte");
        String user = request.getParameter("login");
        String room = request.getParameter("room");
        Message m = new Message(user,text);
        ServletContext context = request.getServletContext();
        GestionMessages gm = (GestionMessages)context.getAttribute("gestionmessage");
        gm.addMessageInRoom(context, room, m);
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
