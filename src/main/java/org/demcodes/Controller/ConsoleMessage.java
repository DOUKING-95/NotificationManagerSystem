package org.demcodes.Controller;

import org.demcodes.Controller.Interface.MessageI;
import org.demcodes.Model.Message;


import java.util.Scanner;
import java.util.UUID;


/**
 * Class ConsoleMessage qui gère les toutes méthodes sur  les services de Massage
 * */
public class ConsoleMessage extends Message implements MessageI {
    public ConsoleMessage(String messageId, String message, String msgTitle, String senderId, String receiverId) {
        super(messageId, message, msgTitle, senderId, receiverId);
    }


    /**
     * Méthode send implementer depuis le interface MessageI
     * */
    @Override
    public void send() {
        //TODO: Appler la classe EmailSender ici

    }


/**
 *Méthode pour initier les Messages
 * @Param: senderId: pour l'id de sender
 * @Param: receiverId: pour l'id  du receiver
 * */
    public  static Message initMessage(String senderId, String receiverId){
        String messageId =  UUID.randomUUID().toString();
        Scanner sc = new Scanner(System.in);
        System.out.println("Donner un titre a votre Message");
        String messageTitle = sc.nextLine();
        System.out.println("Donner me corps de votre message");
        String messageBody = sc.nextLine();

        return new Message(
                messageId,
                messageTitle,
                messageBody,
                senderId,
                receiverId
        );

    }
}
