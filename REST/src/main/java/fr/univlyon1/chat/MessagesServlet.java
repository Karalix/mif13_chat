/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emilie
 */
public class MessagesServlet extends HttpServlet {

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
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
           ServletContext context = request.getServletContext();
           GestionMessages gm = (GestionMessages)context.getAttribute("gestionmessage");
           ArrayList<Message> list = gm.getMessagesByRoom(context,room);
           PrintWriter out = response.getWriter();
           out.println("Liste des message de la salle "+room+" : ");
            for(int i = 0; i < list.size(); i++)
            {
                Message m = list.get(i);
                out.println(m.getSender()+" : "+m.getTexte());
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
