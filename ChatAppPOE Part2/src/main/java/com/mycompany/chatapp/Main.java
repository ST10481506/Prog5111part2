package com.mycompany.chatapp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();

        // ===== REGISTER =====
        System.out.println("=== Registration ===");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cell number: ");
        String cell = input.nextLine();

        System.out.println(login.registerUser(username, password, cell, firstName, lastName));

        // ===== LOGIN =====
        System.out.println("\n=== Login ===");

        System.out.print("Enter username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter password: ");
        String loginPass = input.nextLine();

        boolean isLoggedIn = login.loginUser(loginUser, loginPass);
        System.out.println(login.returnLoginStatus(isLoggedIn));

        if (isLoggedIn) {

            System.out.println("\nWelcome to QuickChat");

            int choice = 0;

            while (choice != 3) {

                System.out.println("\nMenu:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");

                choice = input.nextInt();
                input.nextLine();

                if (choice == 1) {

                    System.out.print("How many messages: ");
                    int total = input.nextInt();
                    input.nextLine();

                    for (int i = 1; i <= total; i++) {

                        Message msg = new Message();

                        System.out.print("Recipient: ");
                        String recipient = input.nextLine();
                        System.out.println(msg.checkRecipientCell(recipient));

                        System.out.print("Message: ");
                        String text = input.nextLine();
                        System.out.println(msg.validateMessageLength(text));

                        msg.setRecipient(recipient);
                        msg.setMessage(text);

                        msg.generateMessageID();
                        String hash = msg.createMessageHash(i, text);

                        System.out.println("Message Hash: " + hash);

                        System.out.println("1 Send | 2 Discard | 3 Store");
                        int opt = input.nextInt();
                        input.nextLine();

                        System.out.println(msg.sendMessage(opt));
                    }

                } else if (choice == 2) {
                    System.out.println("Coming Soon.");
                }
            }

            System.out.println("Total messages: " + Message.returnTotalMessages());
        }

        input.close();
    }
}