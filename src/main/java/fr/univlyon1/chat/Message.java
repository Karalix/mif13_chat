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

    public Message(String texte, String sender) {
        this.texte = texte;
        this.sender = sender;
    }
    
    
    
}
