package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        MessageCommands commandsChecker = new MessageCommands();
        Loginer loginer = new Loginer();
        loginer.login();

        try (Scanner scanner = new Scanner(System.in)) {
            Thread th = new Thread(new GetThread());
            th.setDaemon(true);
            th.start();

            //System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                if (text.equals("/q")) {
                    Client.logOut();
                    System.out.println("Goodbye!");
                    break;
                }
                if(text.equals("")) continue;


                int res = commandsChecker.checkCommands(loginer.getLogin(), text);
                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
