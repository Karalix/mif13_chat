/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;

/**
 *
 * @author emilie
 */
public class GestionMessages {
    
    public GestionMessages() {
    }
    
    /**
     * Gets the list of messages of the room, if the rooms doesn't exist, it is then created
     * @param context
     * @param room
     * @return 
     */
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
        if (map == null) {
            map = new HashMap<String,List>();
        }
        return map.keySet();
    }

    private Map<String, ArrayList<Message>> initializeRooms(ServletContext context) {
        Map <String, ArrayList<Message>> map = new HashMap<String,ArrayList<Message>>();
        context.setAttribute("rooms",map);
        return map ;
    }
    
    public void removeRoom(ServletContext context, String room){
        Map map = (HashMap)context.getAttribute("rooms");
        getMessagesByRoom(context, room);
        map.remove(room);
    }
}
