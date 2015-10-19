/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;

/**
 *
 * @author Alix
 */
public class Init extends HttpServlet {
    
    
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
        ServletContext context = request.getServletContext();      
        if(request.getParameter("but").equals("out") )
        {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("index.html");
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
        ServletContext context = request.getServletContext();      
        
         if(request.getParameter("but").equals("msg"))
        {
            String room = request.getParameter("room");
            if(room == null)
            {
                room = "default" ;
            }
            String user = request.getParameter("login");
            if(user == null)
            {
                user = "Bob";
            }
            String texte = request.getParameter("texte");
            if(texte != null)
            {
                gm.addMessageInRoom(context,room,new Message(user,texte, room));
            }
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
            String dateString = format.format(new Date());
            context.setAttribute("lastModified", dateString );
            request.getRequestDispatcher("interface.jsp?login="+user+"&room="+room).forward(request, response);
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
