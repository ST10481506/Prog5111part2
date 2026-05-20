package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testUsernameCorrect() {
        Login login = new Login();
        assertTrue(login.checkUserName("ky_1"));
    }

    @Test
    public void testUsernameIncorrect() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordCorrect() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordIncorrect() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testLoginSuccess() {
        Login login = new Login();
        login.registerUser("ky_1", "Ch&&sec@ke99!", "+27838968976", "Jacky", "Serumula");

        boolean result = login.loginUser("ky_1", "Ch&&sec@ke99!");
        assertTrue(result);
    }

    @Test
    public void testLoginFail() {
        Login login = new Login();
        login.registerUser("ky_1", "Ch&&sec@ke99!", "+27838968976", "Jacky", "Serumula");

        boolean result = login.loginUser("wrong", "wrong");
        assertFalse(result);
    }
}