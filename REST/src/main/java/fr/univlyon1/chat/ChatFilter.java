/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alix
 */
public class ChatFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request ;
        HttpServletResponse resp = (HttpServletResponse)response ;
        
        
        if(req.getMethod().equalsIgnoreCase("POST") && req.getParameter("but").equals("in") && req.getParameter("login") != "")
        {
             ServletContext context = request.getServletContext();  
             String user = request.getParameter("login");
             String room = request.getParameter("room");
             String chemin = "/Chat/Conversations/"+room+"/interface/"+user;
             //request.getRequestDispatcher("interface.jsp?login="+user+"&room="+room).forward(request, response);
             resp.sendRedirect(chemin);
        }
        else if(req.getMethod().equalsIgnoreCase("POST") && req.getParameter("but").equals("in"))
        {
            req.getRequestDispatcher("index.html").forward(req, resp);
        }
        else if(Pattern.matches("/Chat/Conversations/.*/Messages.*", req.getRequestURI()) )
        {
            String uri = req.getRequestURI();
            String[] parts = uri.split("/");
            req.setAttribute("room", parts[3]);
            if(parts[4].equals("MessagesAfter"))
            {
                req.setAttribute("after", true);
            }
            if(parts.length >= 6 && parts[5]!=null)
            {
                req.setAttribute("idmsg",parts[5]);
            }
            req.getRequestDispatcher("/Messages").forward(req, resp);
        }
        
        chain.doFilter(req , resp);
    }

    @Override
    public void destroy() {
    }
    
}
