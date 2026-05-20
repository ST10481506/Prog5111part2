package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message();
        assertEquals("Message ready to send.", msg.validateMessageLength("Hi"));
    }

    @Test
    public void testMessageLengthFail() {
        Message msg = new Message();
        String longMsg = "a".repeat(300);
        assertTrue(msg.validateMessageLength(longMsg).contains("exceeds"));
    }

    @Test
    public void testRecipientCorrect() {
        Message msg = new Message();
        assertEquals("Cell phone number successfully captured.",
                msg.checkRecipientCell("+27718693002"));
    }

    @Test
    public void testRecipientIncorrect() {
        Message msg = new Message();
        assertTrue(msg.checkRecipientCell("08575975889").contains("incorrectly"));
    }

    @Test
    public void testHash() {
        Message msg = new Message();
        msg.generateMessageID();
        String hash = msg.createMessageHash(1, "Hi tonight");
        assertTrue(hash.contains(":1:"));
    }
}