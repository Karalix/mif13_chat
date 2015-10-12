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

    public Message(String sender, String texte) {
        this.texte = texte;
        this.sender = sender;
    }
    
    public String toXml() {
        StringBuilder sb = new StringBuilder() ;
        sb.append("<message>");
        sb.append("<sender>");
        sb.append(this.sender);
        sb.append("</sender>");
        sb.append("<text>");
        sb.append(this.texte);
        sb.append("</text>");
        sb.append("</message>");
        
        return sb.toString();
    }
    
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"sender\":\"");
        sb.append(this.sender);
        sb.append("\",\n");
        sb.append("\"text\":\"");
        sb.append(this.texte);
        sb.append("\"");
        sb.append("}");
        
        return sb.toString();
    }
    
}
