/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.chat;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alix
 */
public class EnhancedHttpServlet extends HttpServlet{
    
    /**
     * Choose the format we are going to respond with, depending on the Accept Header from Request
     * It also sets the MIMETYPE
     * @param req
     * @return a string describing the format type : html, xml or json
     */
    public String defineOutputFormat(HttpServletRequest req, HttpServletResponse res) {
        String format ;
        /*if(req.getHeader("Accept").contains("text/html")) {
            format = "html" ;
            res.setHeader("Content-Type", "text/html;charset=UTF-8");
        } else */if( req.getHeader("Accept").contains("application/xml")) {
            format = "xml" ;
            res.setHeader("Content-Type", "application/xml");
        } else {
            format = "json" ;
            res.setHeader("Content-Type", "application/json");
        }
        return format;
    }
    
    /**
     * Update the 'lastModified' Attribute in the context, allowing the client to know the page changed
     * @param context 
     */
    public void updateLastModified(ServletContext context) {
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        String dateString = format.format(new Date());
        context.setAttribute("lastModified", dateString );
    }
    
}
