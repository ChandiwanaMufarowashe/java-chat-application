package com.mycompany.chatapp_1;

import java.util.Scanner;

/**
 * QuickChat – Part 1, Part 2 and Part 3.
 */
public class ChatApp_1 {

    public static void main(String[] args) {

        Login obj = new Login();
        Scanner MyK = new Scanner(System.in);

        // ===================== REGISTRATION SECTION =====================

        System.out.println("USER REGISTRATION");

        boolean registered = false;
        String registerMessage = "";

        for (int i = 1; i <= 3; i++) {

            System.out.println("\nAttempt " + i + " of 3");

            System.out.print("Enter Name and Surname: ");
            obj.setName_Surname(MyK.nextLine());

            System.out.print("Enter username: ");
            obj.setUsername(MyK.nextLine());

            System.out.print("Enter password: ");
            obj.setPassword(MyK.nextLine());

            System.out.print("Enter phone number: ");
            obj.setPhone_Num(MyK.nextLine());

            registerMessage = obj.registerUser();
            System.out.println(registerMessage);

            if (registerMessage.contains("User successfully registered")) {
                registered = true;
                break;
            } else {
                System.out.println("Registration failed. Attempts left: " + (3 - i));
            }
        }

        if (!registered) {
            System.out.println("\nToo many failed registration attempts. Exiting system.");
            MyK.close();
            return;
        }

        // ===================== LOGIN SECTION =====================

        System.out.println("\nLOGIN");

        boolean accessGranted = false;

        for (int i = 1; i <= 3; i++) {

            System.out.print("Enter username: ");
            String username = MyK.nextLine();

            System.out.print("Enter password: ");
            String password = MyK.nextLine();

            if (obj.loginUser(username, password)) {
                System.out.println(obj.returnLoginStatus(username, password));
                accessGranted = true;
                break;
            } else {
                System.out.println(obj.returnLoginStatus(username, password));
                System.out.println("Attempts left: " + (3 - i));
            }
        }

        if (!accessGranted) {
            System.out.println("\nToo many failed login attempts. Exiting system.");
            MyK.close();
            return;
        }

        // Sets the logged-in user as the sender for stored message reports.
        Message.setSender(obj.getName_Surname());

        // ===================== QUICKCHAT MAIN MENU =====================

        System.out.println("\n-----------------------------");
        System.out.println("Welcome to QuickChat.");
        System.out.println("-----------------------------");

        int menuChoice = 0;

        while (menuChoice != 3) {

            System.out.println("\nPlease choose an option:");
            System.out.println("1. Send Messages");
            System.out.println("2. Stored Messages Menu");
            System.out.println("3. Quit");
            System.out.print("\nEnter your choice: ");

            menuChoice = MyK.nextInt();
            MyK.nextLine();

            if (menuChoice == 1) {

                // ===================== SEND MESSAGES =====================

                System.out.print("How many messages do you want to send? ");
                int numberOfMessages = MyK.nextInt();
                MyK.nextLine();

                int count = 1;

                while (count <= numberOfMessages) {

                    Message msg = new Message();

                    System.out.println("\nMessage " + count + " of " + numberOfMessages);

                    System.out.print("Enter recipient number: ");
                    msg.setRecipient(MyK.nextLine());

                    while (!msg.checkRecipientCell()) {
                        System.out.println("Cell phone number is incorrectly formatted or does not contain an international code.");
                        System.out.print("Enter recipient number: ");
                        msg.setRecipient(MyK.nextLine());
                    }

                    System.out.print("Enter your message: ");
                    msg.setMessage(MyK.nextLine());

                    while (!msg.checkMessageLength().equals("Message ready to send.")) {
                        System.out.println(msg.checkMessageLength());
                        System.out.print("Enter your message: ");
                        msg.setMessage(MyK.nextLine());
                    }

                    System.out.println("\nMessage ready to send.\n");

                    String result = msg.SentMessage();
                    System.out.println(result);

                    if (result.equals("Message successfully sent.")
                            || result.equals("Message successfully stored.")) {

                        System.out.println("\nMessage Details:");
                        System.out.println("Message ID: " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.getMessageHash());
                        System.out.println("Sender: " + Message.getSender());
                        System.out.println("Recipient: " + msg.getRecipient());
                        System.out.println("Message: " + msg.getMessage());
                    }

                    count++;
                }

                Message temp = new Message();
                System.out.println("\nTotal messages sent: " + temp.returnTotalMessages());

            } else if (menuChoice == 2) {

                // ===================== PART 3 STORED MESSAGES SUB-MENU =====================

                char subChoice = ' '; //char means it stores one character only (a,b.c)

                while (subChoice != 'g') { //Keep showing the sub-menu while the user has not chosen g.

                    System.out.println("\nSTORED MESSAGES MENU");
                    System.out.println("a. Display sender and recipient of all stored messages");
                    System.out.println("b. Display the longest stored message");
                    System.out.println("c. Search for a message ID and display the recipient and message");
                    System.out.println("d. Search for all stored messages for a particular recipient");
                    System.out.println("e. Delete a stored message using the message hash");
                    System.out.println("f. Display a report with full details of all stored messages");
                    System.out.println("g. Return to main menu");
                    System.out.print("Enter your option: ");

                    String optionInput = MyK.nextLine().toLowerCase();

                    if (optionInput.isEmpty()) {
                        System.out.println("Invalid option. Please enter a letter from a to g.");
                        continue;
                    }

                    subChoice = optionInput.charAt(0);

                    if (subChoice == 'a') {

                        System.out.println("\nOPTION A: Sender and Recipient of Stored Messages");
                        System.out.println(Message.displaySenderAndRecipient());

                    } else if (subChoice == 'b') {

                        System.out.println("\nOPTION B: Longest Stored Message");
                        System.out.println(Message.displayLongestMessage());

                    } else if (subChoice == 'c') {

                        System.out.println("\nOPTION C: Search Stored Message by Message ID");
                        System.out.print("Enter message ID only: ");
                        String searchID = MyK.nextLine();

                        System.out.println(Message.searchByMessageID(searchID));

                    } else if (subChoice == 'd') {

                        System.out.println("\nOPTION D: Search Stored Messages by Recipient");
                        System.out.print("Enter recipient number: ");
                        String searchRecipient = MyK.nextLine();

                        System.out.println(Message.searchByRecipient(searchRecipient));

                    } else if (subChoice == 'e') {

                        System.out.println("\nOPTION E: Delete Stored Message by Message Hash");
                        System.out.print("Enter message hash: ");
                        String searchHash = MyK.nextLine();

                        System.out.println(Message.deleteByMessageHash(searchHash));

                    } else if (subChoice == 'f') {

                        System.out.println("\nOPTION F: Stored Messages Report");
                        System.out.println(Message.displayReport());

                    } else if (subChoice == 'g') {

                        System.out.println("Returning to main menu...");

                    } else {

                        System.out.println("Invalid option. Please choose a, b, c, d, e, f or g.");
                    }
                }

            } else if (menuChoice == 3) {

                System.out.println("Goodbye.");

            } else {

                System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }

        MyK.close();
    }
}