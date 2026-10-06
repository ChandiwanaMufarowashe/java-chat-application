package com.mycompany.chatapp_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class Message {

    // ===================== VARIABLES =====================

    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String message;
    private String messageHash;

    private static int totalMessagesSent = 0;
    private static String allMessages = "";

    // Sender is used for Part 3 option A.
    private static String sender = "Logged-in user";

    // ===================== PART 3 ARRAYS =====================

    private static final int SIZE = 100;

    private static String[] sentMessages = new String[SIZE];
    private static String[] disregardedMessages = new String[SIZE];
    private static String[] storedMessages = new String[SIZE];
    private static String[] messageHashes = new String[SIZE];
    private static String[] messageIDs = new String[SIZE];

   
    private static int sentCount = 0;
    private static int disregardedCount = 0;
    private static int storedCount = 0;
    private static int hashIDCount = 0;

    private static boolean jsonLoaded = false;

    // ===================== CONSTRUCTOR =====================

    public Message() {
        Random rand = new Random();

        long number = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        this.messageID = String.valueOf(number);

        this.numMessagesSent = totalMessagesSent;
        this.recipient = "";
        this.message = "";
        this.messageHash = "";
    }

    // ===================== SETTERS =====================

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public void setNumMessagesSent(int numMessagesSent) {
        this.numMessagesSent = numMessagesSent;
    }

    public static void setSender(String senderName) {
        sender = senderName;
    }

    // ===================== GETTERS =====================

    public String getMessageID() {
        return messageID;
    }

    public int getNumMessagesSent() {
        return numMessagesSent;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public static String getSender() {
        return sender;
    }

    // ===================== ARRAY GETTERS FOR JUNIT TESTING =====================

    public static int getSentCount() {
        return sentCount;
    }

    public static int getDisregardedCount() {
        return disregardedCount;
    }

    public static int getStoredCount() {
        return storedCount;
    }

    public static String getSentMessage(int index) {
        return sentMessages[index];
    }

    public static String getDisregardedMessage(int index) {
        return disregardedMessages[index];
    }

    public static String getStoredMessage(int index) {
        return storedMessages[index];
    }

    public static String getMessageIDFromArray(int index) {
        return messageIDs[index];
    }

    public static String getMessageHashFromArray(int index) {
        return messageHashes[index];
    }

    // ===================== PART 2 METHODS =====================

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        return recipient != null && recipient.matches("^\\+\\d{1,3}\\d{1,10}$");
    }

    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extraCharacters = message.length() - 250;
            return "Message exceeds 250 characters by " + extraCharacters
                    + ", please reduce the size.";
        }
    }

    public String createMessageHash() {

        String firstTwoNumbers = messageID.substring(0, 2);
        String firstWord;
        String lastWord;

        message = message.trim();

        int firstSpace = message.indexOf(" ");
        int lastSpace = message.lastIndexOf(" ");

        if (firstSpace == -1) {
            firstWord = message;
            lastWord = message;
        } else {
            firstWord = message.substring(0, firstSpace);
            lastWord = message.substring(lastSpace + 1);
        }

        messageHash = firstTwoNumbers + ":" + numMessagesSent + ":" + firstWord + lastWord;
        messageHash = messageHash.toUpperCase();

        return messageHash;
    }

    public String SentMessage() {
        Scanner MyK = new Scanner(System.in);

        System.out.println("Choose what you want to do with the message:"
                + "\n1. Send Message"
                + "\n2. Disregard Message"
                + "\n3. Store Message to send later"
                + "\nPlease Enter your choice: ");

        int choice = MyK.nextInt();
        MyK.nextLine();

        return sentMessage(choice);
    }

    public String sentMessage(int choice) {

        if (choice == 1) {

            totalMessagesSent = totalMessagesSent + 1;
            numMessagesSent = totalMessagesSent;

            createMessageHash();

            addSentMessage(messageID, messageHash, recipient, message);

            allMessages += "Message ID: " + messageID
                    + "\nMessage Hash: " + messageHash
                    + "\nSender: " + sender
                    + "\nRecipient: " + recipient
                    + "\nMessage: " + message
                    + "\n-----------------------------\n";

            return "Message successfully sent.";

        } else if (choice == 2) {

            addDisregardedMessage(message);

            return "Message deleted.";

        } else if (choice == 3) {

            createMessageHash();

            addStoredMessage(messageID, messageHash, recipient, message);

            storeMessage();

            return "Message successfully stored.";

        } else {

            return "Invalid option selected.";
        }
    }

    public String printMessages() {

        if (allMessages.equals("")) {
            return "No messages have been sent yet.";
        } else {
            return allMessages;
        }
    }

    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    public void storeMessage() {
        createMessageHash();

        try {
            FileWriter writer = new FileWriter("storedMessages.json", true);

            writer.write("{\n");
            writer.write("\"MessageID\": \"" + messageID + "\",\n");
            writer.write("\"MessageHash\": \"" + messageHash + "\",\n");
            writer.write("\"Recipient\": \"" + recipient + "\",\n");
            writer.write("\"Message\": \"" + message + "\"\n");
            writer.write("}\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error storing message.");
        }
    }

    // ===================== PART 3 ARRAY METHODS =====================

    public static void addSentMessage(String id, String hash, String recipient, String message) {

        String fullMessage = "Message ID: " + id
                + "\nMessage Hash: " + hash
                + "\nSender: " + sender
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;

        sentMessages[sentCount] = fullMessage;
        sentCount++;

        messageIDs[hashIDCount] = id;
        messageHashes[hashIDCount] = hash;
        hashIDCount++;
    }

    public static void addDisregardedMessage(String message) {
        disregardedMessages[disregardedCount] = message;
        disregardedCount++;
    }

    public static void addStoredMessage(String id, String hash, String recipient, String message) {

        String fullMessage = "Message ID: " + id
                + "\nMessage Hash: " + hash
                + "\nSender: " + sender
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;

        storedMessages[storedCount] = fullMessage;
        storedCount++;

        messageIDs[hashIDCount] = id;
        messageHashes[hashIDCount] = hash;
        hashIDCount++;
    }

    // Option A: Display sender and recipient of all stored messages.
    public static String displaySenderAndRecipient() {

        loadStoredMessagesFromJSON();//*

        if (storedCount == 0) { //*
            return "No stored messages found.";
        }

        StringBuilder output = new StringBuilder();///*

        output.append("Stored Message Sender and Recipient Details:\n");
        output.append("-----------------------------\n");//What is output?

        for (int i = 0; i < storedCount; i++) {//*
            if (storedMessages[i] != null) {//*
                output.append("Sender: ");
                output.append(getLineFromRecord(storedMessages[i], "Sender: "));
                output.append("\n");

                output.append("Recipient: ");//What is append
                output.append(getLineFromRecord(storedMessages[i], "Recipient: "));
                output.append("\n");

                output.append("-----------------------------\n");
            }
        }

        return output.toString(); //
    }

    // Option B: Display the longest stored message.
    public static String displayLongestMessage() {

        loadStoredMessagesFromJSON(); //What is this

        if (storedCount == 0) {
            return "No stored messages found.";
        }

        String longestRecord = "";
        String longestMessage = "";

        for (int i = 0; i < storedCount; i++) {

            if (storedMessages[i] != null) {

                String currentMessage = getLineFromRecord(storedMessages[i], "Message: ");

                if (currentMessage.length() > longestMessage.length()) {
                    longestMessage = currentMessage;
                    longestRecord = storedMessages[i];
                }
            }
        }

        return "Longest Stored Message:\n" + longestRecord;
    }

    // Option C: Search for a message ID and display the corresponding recipient and message.
    public static String searchByMessageID(String searchID) {

        loadStoredMessagesFromJSON();

        searchID = searchID.trim();

        for (int i = 0; i < storedCount; i++) {

            if (storedMessages[i] != null) {

                String id = getLineFromRecord(storedMessages[i], "Message ID: ");

                if (id.equals(searchID)) {
                    String recipientFound = getLineFromRecord(storedMessages[i], "Recipient: ");
                    String messageFound = getLineFromRecord(storedMessages[i], "Message: ");

                    return "Message Found:"
                            + "\nRecipient: " + recipientFound
                            + "\nMessage: " + messageFound;
                }
            }
        }

        return "Message ID not found.";
    }

    // Option D: Search for all stored messages for a particular recipient.
    public static String searchByRecipient(String searchRecipient) {

        loadStoredMessagesFromJSON();

        searchRecipient = searchRecipient.trim();

        StringBuilder output = new StringBuilder();

        output.append("Stored messages for recipient: ").append(searchRecipient).append("\n");
        output.append("-----------------------------\n");

        for (int i = 0; i < storedCount; i++) {

            if (storedMessages[i] != null) {

                String currentRecipient = getLineFromRecord(storedMessages[i], "Recipient: ");

                if (currentRecipient.equals(searchRecipient)) {
                    output.append(storedMessages[i]);
                    output.append("\n-----------------------------\n");
                }
            }
        }

        String onlyHeading = "Stored messages for recipient: " + searchRecipient
                + "\n-----------------------------\n";

        if (output.toString().equals(onlyHeading)) {
            return "No stored messages found for this recipient.";
        }

        return output.toString();
    }

    // Option E: Delete a stored message using the message hash.
    public static String deleteByMessageHash(String searchHash) {

        loadStoredMessagesFromJSON();

        searchHash = searchHash.trim();

        for (int i = 0; i < storedCount; i++) {

            if (storedMessages[i] != null) {

                String currentHash = getLineFromRecord(storedMessages[i], "Message Hash: ");

                if (currentHash.equals(searchHash)) {
                    storedMessages[i] = null;
                    return "Message deleted successfully.";
                }
            }
        }

        return "Message hash not found.";
    }

    // Option F: Display a report with full details of all stored messages.
    public static String displayReport() {

        loadStoredMessagesFromJSON();

        if (storedCount == 0) {
            return "No stored messages found.";
        }

        StringBuilder report = new StringBuilder();

        report.append("Stored Messages Report:\n");
        report.append("-----------------------------\n");

        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i] != null) {
                report.append(storedMessages[i]);
                report.append("\n-----------------------------\n");
            }
        }

        return report.toString();
    }

    public static String displayDisregardedMessages() {

        if (disregardedCount == 0) {
            return "No disregarded messages found.";
        }

        StringBuilder output = new StringBuilder();

        output.append("Disregarded Messages:\n");
        output.append("-----------------------------\n");

        for (int i = 0; i < disregardedCount; i++) {
            output.append(disregardedMessages[i]);
            output.append("\n");
        }

        return output.toString();
    }

    public static String displayStoredMessages() {

        loadStoredMessagesFromJSON();

        if (storedCount == 0) {
            return "No stored messages found.";
        }

        StringBuilder output = new StringBuilder();

        output.append("Stored Messages:\n");
        output.append("-----------------------------\n");

        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i] != null) {
                output.append(storedMessages[i]);
                output.append("\n-----------------------------\n");
            }
        }

        return output.toString();
    }

    // ===================== JSON READING METHOD =====================
//START HERE
    public static void loadStoredMessagesFromJSON() {

        if (jsonLoaded) {
            return;
        }

        File file = new File("storedMessages.json");

        if (!file.exists()) {
            jsonLoaded = true;
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            String id = "";
            String hash = "";
            String recipient = "";
            String storedMessage = "";

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (line.startsWith("\"MessageID\"")) {
                    id = getJSONValue(line);
                } else if (line.startsWith("\"MessageHash\"")) {
                    hash = getJSONValue(line);
                } else if (line.startsWith("\"Recipient\"")) {
                    recipient = getJSONValue(line);
                } else if (line.startsWith("\"Message\"")) {
                    storedMessage = getJSONValue(line);
                } else if (line.startsWith("}")) {

                    String fullMessage = "Message ID: " + id
                            + "\nMessage Hash: " + hash
                            + "\nSender: " + sender
                            + "\nRecipient: " + recipient
                            + "\nMessage: " + storedMessage;

                    boolean alreadyExists = false;

                    for (int i = 0; i < storedCount; i++) {
                        if (storedMessages[i] != null
                                && storedMessages[i].contains("Message ID: " + id)) {
                            alreadyExists = true;
                        }
                    }

                    if (!alreadyExists) {
                        storedMessages[storedCount] = fullMessage;
                        storedCount++;

                        messageIDs[hashIDCount] = id;
                        messageHashes[hashIDCount] = hash;
                        hashIDCount++;
                    }

                    id = "";
                    hash = "";
                    recipient = "";
                    storedMessage = "";
                }
            }

            reader.close();
            jsonLoaded = true;

        } catch (IOException e) {
            System.out.println("Error reading stored messages from JSON file.");
        }
    }

    // ===================== HELPER METHODS =====================

    private static String getLineFromRecord(String record, String label) {

        if (record == null) {
            return "";
        }

        String[] lines = record.split("\n");

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].startsWith(label)) {
                return lines[i].replace(label, "").trim();
            }
        }

        return "";
    }

    private static String getJSONValue(String line) {

        int colonPosition = line.indexOf(":");

        String value = line.substring(colonPosition + 1).trim();

        value = value.replace("\"", "");
        value = value.replace(",", "");

        return value;
    }

    // ===================== JUNIT TESTING METHOD =====================
// It clears all Part 3 arrays and resets all counters before each test runs.
// This prevents data from one test affecting another test.
    
    public static void resetArrays() {

        sentMessages = new String[SIZE];
        disregardedMessages = new String[SIZE];
        storedMessages = new String[SIZE];
        messageHashes = new String[SIZE];
        messageIDs = new String[SIZE];

        sentCount = 0;
        disregardedCount = 0;
        storedCount = 0;
        hashIDCount = 0;
        totalMessagesSent = 0;
        allMessages = "";
        sender = "Logged-in user";
        jsonLoaded = false;
    }
}