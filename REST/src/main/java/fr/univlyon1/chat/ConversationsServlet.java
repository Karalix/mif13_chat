/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emilie
 */
public class ConversationsServlet extends EnhancedHttpServlet {

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
        if(Pattern.matches("/Chat/Conversations/.*/Messages.*", request.getRequestURI()) ) {
            return ;
        }

        response.setContentType("text/html");
        String uri = request.getRequestURI();
        String[] parts = uri.split("/");
        if(parts.length > 4){
            String room = parts[3];
            String objectif = parts[4];
            switch (objectif){
                case "interface":
                    String user = parts[5];
                    if(user == null)
                    {
                        user = "default";
                    }
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/interface.jsp?room="+room+"&login="+user);
                    requestDispatcher.include(request, response);
                    break;
                case "nombre":
                    PrintWriter out = response.getWriter();
                    out.println("Nombre de messages dans la salle "+room+"  : ");
                    ServletContext context = request.getServletContext();
                    GestionMessages gm = (GestionMessages)context.getAttribute("gestionmessage");
                    out.println( gm.numbreMessageInARoom(context, room));
                    break;
                 default:
                     break;
            }
                          
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
        if(Pattern.matches("/Chat/Conversations/.*/Messages.*", request.getRequestURI()) ) {
            return ;
        }
        ServletContext context = this.getServletContext();
        String uri = request.getRequestURI();
        String[] parts = uri.split("/");
        String targetRoom = parts[3] ;
        gm.getMessagesByRoom(context, targetRoom);
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(Pattern.matches("/Chat/Conversations/.*/Messages.*", request.getRequestURI()) ) {
            return ;
        }
        String uri = request.getRequestURI();
        String[] parts = uri.split("/");
        String room = parts[3];
        ServletContext context = request.getServletContext();
        
        updateLastModified(context);
        
        gm.removeRoom(context, room);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(Pattern.matches("/Chat/Conversations/.*/Messages.*", req.getRequestURI()) ) {
            return ;
        }
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
