/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.chat;

/**
 *
 * @author Alix
 */
public class Message {
    
    private String texte ;
    
    private String sender ;
    
    private int id;
    
    private String room ;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Message(String sender, String texte, String room) {
        this.texte = texte;
        this.sender = sender;
        this.room = room ;
        this.id = 0 ;
    }
    
    public String toXml() {
        StringBuilder sb = new StringBuilder() ;
        
        sb.append("<message>");
        sb.append("<id>");
        sb.append(String.valueOf(this.id));
        sb.append("</id>");
        sb.append("<sender>");
        sb.append(this.sender);
        sb.append("</sender>");
        sb.append("<text>");
        sb.append(this.texte);
        sb.append("</text>");
        //Hypermedia links
        sb.append("<modify href=\"/Conversations/"+this.room+"/Messages/"+this.id+"\" />");
        sb.append("</message>");
        
        return sb.toString();
    }
    
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":\"");
        sb.append(String.valueOf(this.id));
        sb.append("\",\n");
        sb.append("\"sender\":\"");
        sb.append(this.sender);
        sb.append("\",\n");
        sb.append("\"text\":\"");
        sb.append(this.texte);
        //Hypermedia
        sb.append("\",\n");
        sb.append("\"links\": [{\"modify\":\"");
        sb.append("/Conversations/"+this.room+"/Messages/"+this.id);
        sb.append("\"}]");
        sb.append("}");
        
        return sb.toString();
    }
    
    
}
