package Challenge;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Contact> contacts;//?
    private static Scanner scan;
    private static int id = 0;

    public static void main(String[] args) {
        contacts = new ArrayList<>();
        System.out.println("Welcome to my humble world of programming");
        showInitialOptions();

    }

    private static void showInitialOptions() {
        System.out.println("Please select one: " + "\n\t1.Manage Contats" +
                "\n\t2. Message" + "\n\t3. Quit");
        scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                manageContact();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;
        }


    }

    private static void manageMessages() {
        System.out.println("please select one: " +
                "\n\t1.Show all messages" +
                "\n\t2.Send a new message" +
                "\n\t3.Go Back");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                showAllMessage();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void sendNewMessage() {
        System.out.println("Whom you going to send a message?");
        String name = scan.next();
        if (name.equals("")) {
            System.out.println("please enter the contact");
            sendNewMessage();
        } else {
            boolean doesExit = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExit = true;
                }
            }
            if (doesExit) {
                System.out.println("What are you going to say?");
                String text = scan.next();
                if (text.equals("")) {
                    System.out.println("Please enter some message");
                    sendNewMessage();
                } else {
                    id++;
                    Message newMessage = new Message(text, name, id);
                    for (Contact c : contacts) {
                        if (c.getName().equals(name)) {
                            ArrayList<Message> newMessages = c.getMessage();
                            newMessages.add(newMessage);
                            c.setMessage(newMessages);
                        }
                    }

                }
            } else {
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void showAllMessage() {
        ArrayList<Message> allMessages = new ArrayList<>();
        for (Contact c : contacts) {
            allMessages.addAll(c.getMessage());
        }
        if (allMessages.size() > 0) {
            for (Message m : allMessages) {
                m.getDetails();
                System.out.println("**********");
            }


        } else {
            System.out.println("You don't have any message");
        }
        showInitialOptions();
    }

    private static void manageContact() {
        System.out.println("please select one: " +
                "\n\t1.Show all contact" +
                "\n\t2.Add a new contact" +
                "\n\t3.Search for a contact" +
                "\n\t4.Delete a contact" +
                "\n\t5. Go back");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                showAllContact();
                break;
            case 2:
                addNewcontact();
                break;
            case 3:
                seachForContact();
                break;
            case 4:
                deteleContact();
                break;
            default:
                showInitialOptions();
                break;


        }
    }

    private static void deteleContact() {
        System.out.println("please enter the contact's name: ");
        String name = scan.next();
        if (name.equals("")) {
            deteleContact();
        } else {
            boolean doesExit = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExit = true;
                    contacts.remove(c);
                }

            }
            if (!doesExit) {
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void seachForContact() {
        System.out.println("Please enter the contact name: ");
        String name = scan.next();
        if (name.equals("")) {
            System.out.println("Please enter the name: ");
            seachForContact();
        } else {
            boolean doesExit = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExit = true;
                    c.getDetails();
                }
            }
            if (!doesExit) {
                System.out.println("There is no such contact in your phone");
            }
        }
        showInitialOptions();
    }

    private static void addNewcontact() {
        System.out.println("Adding new contact" + "\nPlease enter contact's name: ");
        String name = scan.next();
        System.out.println("Please Enter contact's number: ");
        String number = scan.next();
        System.out.println("Enter email: ");
        String email = scan.next();

        if (name.equals("") || number.equals("") || email.equals("")) {
            System.out.println("Please enter all of the information");
            addNewcontact();
        } else {
            boolean doesExit = false;
            for (Contact c : contacts) {
                if (c.getName().equals(name)) {
                    doesExit = true;
                }
            }
            if (doesExit) {
                System.out.println("We have contact with this name");
            } else {
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
                System.out.println(name + "added successfully");

            }

        }
        showInitialOptions();
    }

    private static void showAllContact() {
        if (contacts.size() > 0) {
            for (Contact c : contacts) {
                c.getDetails();
                System.out.println("*************");
            }
            showInitialOptions();


        } else {
            System.out.println("You don't have any contact");
            showInitialOptions();
        }
    }
}



