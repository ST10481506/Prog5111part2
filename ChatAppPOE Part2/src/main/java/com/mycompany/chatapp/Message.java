package com.mycompany.chatapp;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private static int messageCount = 0;

    
    // MESSAGE ID CHECK
    
    public boolean checkMessageID(String id) {
        return id.length() <= 10;
    }

    
    // RECIPIENT VALIDATION
    
    public String checkRecipientCell(String number) {
        if (number.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    
    // CREATE MESSAGE HASH
    
    public String createMessageHash(int messageNumber, String message) {

        String[] words = message.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String idPart = messageID.substring(0, 2);

        messageHash = idPart + ":" + messageNumber + ":" + firstWord + lastWord;

        return messageHash.toUpperCase();
    }

    
    // MESSAGE LENGTH VALIDATION
    
    public String validateMessageLength(String msg) {
        if (msg.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message exceeds 250 characters by " + (msg.length() - 250) + ", please reduce the size.";
        }
    }

    
    // GENERATE MESSAGE ID
    
    public String generateMessageID() {
        Random rand = new Random();
        messageID = String.valueOf(1000000000L + (long)(rand.nextDouble() * 8999999999L));
        return messageID;
    }

    
    // STORE MESSAGE IN JSON
    
    public void storeMessageToJSON() {
        try {
            FileWriter file = new FileWriter("messages.json", true);

            file.write("{\n");
            file.write("  \"MessageID\": \"" + messageID + "\",\n");
            file.write("  \"Recipient\": \"" + recipient + "\",\n");
            file.write("  \"Message\": \"" + message + "\",\n");
            file.write("  \"Hash\": \"" + messageHash + "\"\n");
            file.write("}\n\n");

            file.close();

        } catch (IOException e) {
            System.out.println("Error writing message to file.");
        }
    }

    
    // SEND / STORE / DISCARD
   
    public String sendMessage(int option) {
        if (option == 1) {
            messageCount++;
            return "Message successfully sent.";
        } else if (option == 2) {
            return "Press 0 to delete the message.";
        } else if (option == 3) {
            storeMessageToJSON();
            return "Message successfully stored.";
        }
        return "Invalid option.";
    }

    
    // TOTAL MESSAGES
    
    public static int returnTotalMessages() {
        return messageCount;
    }

    
    // SETTERS
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }
}