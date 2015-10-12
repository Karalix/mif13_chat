/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emilie
 */
public class ChatServlet extends EnhancedHttpServlet {
    
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
        /*ici affichage des salles à voir*/
        String format = defineOutputFormat(request, response);
        PrintWriter out = response.getWriter();
        ServletContext context = request.getServletContext();
        Set<String> set = gm.getAllRooms(context);
        Iterator<String> it = set.iterator();
        switch (format) {
            case "xml" :
                out.println("<salles>");
                while (it.hasNext()){
                    out.println("<salle>"+it.next()+"</salle>");
                }
                out.println("</salles>");
                break ;
            default:
                boolean firstIteration = true ;
                out.println("{\"salles\":[");
                while (it.hasNext()){
                    if(!firstIteration) {
                        out.print(",");
                    }
                    out.println("{ \"nom\":\""+it.next()+"\"}");
                    if(firstIteration) {
                        firstIteration = false ;
                    }
                }
                out.println("]}");
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
        ServletContext context = this.getServletContext();
        String uri = request.getRequestURI();
        String[] parts = uri.split("/");
        String targetRoom = parts[3] ;
        gm.getMessagesByRoom(context, targetRoom);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String room = request.getParameter("room");
        ServletContext context = request.getServletContext();
        gm.getMessagesByRoom(context, room);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
