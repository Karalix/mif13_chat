/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.chat;

import static java.lang.reflect.Array.set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author emilie
 */
public class GestionMessages {
    
    public GestionMessages() {
    }
    
    public ArrayList<Message> getMessagesByRoom(ServletContext context, String room){
        Map<String,ArrayList<Message>> map = (HashMap<String,ArrayList<Message>>)context.getAttribute("rooms");
        if(map == null)
        {
            map = initializeRooms(context);
        }
        ArrayList<Message> list = map.get(room);
        if(list == null){
            list = new ArrayList<Message>();
            map.put(room, list);
        }
        return list;
    } 
    
    public void addMessageInRoom(ServletContext context, String room, Message msg){
        Map map = (HashMap)context.getAttribute("rooms");
        ArrayList<Message> list = getMessagesByRoom(context,room);
        list.add(msg);
    }
    
    public int numbreMessageInARoom(ServletContext context, String room){
        Map map = (HashMap)context.getAttribute("rooms");
        ArrayList<Message> list = getMessagesByRoom(context,room);
        return list.size();
    }
    public Set<String> getAllRooms(ServletContext context){
        Map map = (HashMap)context.getAttribute("rooms");
        return map.keySet();
    }

    private Map<String, ArrayList<Message>> initializeRooms(ServletContext context) {
        Map <String, ArrayList<Message>> map = new HashMap<String,ArrayList<Message>>();
        context.setAttribute("rooms",map);
        return map ;
    }
  
}
