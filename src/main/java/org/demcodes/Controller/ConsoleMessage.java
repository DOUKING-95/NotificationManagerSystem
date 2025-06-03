package org.demcodes.Controller;

import org.demcodes.Controller.Interface.MessageI;
import org.demcodes.Model.Message;


import java.util.Scanner;
import java.util.UUID;

public class ConsoleMessage extends Message implements MessageI {
    public ConsoleMessage(String messageId, String message, String msgTitle, String senderId, String receiverId) {
        super(messageId, message, msgTitle, senderId, receiverId);
    }

    @Override
    public void send() {

    }

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
