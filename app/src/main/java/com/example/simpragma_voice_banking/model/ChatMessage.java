package com.example.simpragma_voice_banking.model;


/**
 * Chat Message model used when ChatMessages are required, either to be sent or received,
 * all messages that are to be shown in the chat-ui must be contained in this model.
 */
public class ChatMessage {
    private String message;
    private String timestamp;
    private String type;
    private String sender;

    public ChatMessage(String message, String timestamp, String type) {
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
    }

    public ChatMessage(String message, String timestamp, String type, String sender) {
        this(message, timestamp, type);
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
