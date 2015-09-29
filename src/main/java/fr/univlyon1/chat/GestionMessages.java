/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author emilie
 */
public class GestionMessages {

    private static Map<String, ArrayList<Message>> map = new HashMap();

    
    public GestionMessages() {
    }
    
    public ArrayList<Message> getMessagesByRoom(String room){
        ArrayList<Message> list = map.get(room);
        if(list == null){
            list = new ArrayList<Message>();
            map.put(room, list);
        }
        return list;
    } 
    
    public void addMessageInRoom(String room, Message msg){
        ArrayList<Message> list = getMessagesByRoom(room);
        list.add(msg);
    }
    
    public int numbreMessageInARoom(String room){
        ArrayList<Message> list = getMessagesByRoom(room);
        return list.size();
    }
}
