package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Loginer {

    private Client client;
    private String login;
    private String pass;

    public void login() throws IOException {
        Scanner scanner = new Scanner(System.in);


            while(true){
                System.out.println("Enter your login: ");
                login = scanner.nextLine();
                System.out.println("Enter your password: ");
                pass = scanner.nextLine();

                client = new Client(login, pass);


                boolean res = client.checkLogIn();

                if (res) {
                    System.out.println("Welcome to Chat " + login);
                    break;
                }
            }
    }

    public Client getClient() {
        return client;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}
