package org.demcodes.Model;

public class Message {
    public  Message(){};
    public Message(String messageId, String message, String msgTitle, String senderId, String receiverId) {
        this.messageId = messageId;
        this.message = message;
        this.msgTitle = msgTitle;
        this.senderId = senderId;
        this.receverId = receiverId;
    }


    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", message='" + message + '\'' +
                ", msgTitle='" + msgTitle + '\'' +
                ", senderId=" + senderId +
                ", receverId=" + receverId +
                '}';
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceverId() {
        return receverId;
    }

    public void setReceverId(String receverId) {
        this.receverId = receverId;
    }

    private String messageId;


    private  String message;
    private  String msgTitle;
    private String senderId;
    private String receverId;
}
