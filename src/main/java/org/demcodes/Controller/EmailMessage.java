package org.demcodes.Controller;

import org.demcodes.Controller.Interface.MessageI;
import org.demcodes.Model.Message;

public class EmailMessage  extends Message implements MessageI {
    public EmailMessage(String messageId, String message, String msgTitle, String senderId, String receiverId) {
        super(messageId, message, msgTitle, senderId, receiverId);
    }

    @Override
    public void send() {

    }
}
