package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MessageCommands {

    private final Gson gson = new GsonBuilder().create();

    public int checkCommands(String login, String text) throws IOException {

        String[] words = text.split(" ");
        String command = "";
        if(words.length > 0){
            command = words[0];
        }

        StringBuilder sb = new StringBuilder();
        int res = 200;

        switch (command) {
            case "/private": {

                String toWho = words[1];
                System.out.println(toWho);

                for (int i = 2; i < words.length; i++) {
                    sb.append(words[i]).append(' ');
                }
                String message = sb.append("    #PRIVATE").toString();
                Message m = new Message(login, message, toWho);
                res = m.sendMesg(Utils.getURL() + "/add");

                break;
            }
            case "/createRoom":

                System.out.println("Room");

                break;
            case "/users": {

                URL obj = new URL(Utils.getURL() + "/getUsers");
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
                conn.setRequestMethod("GET");

                try (InputStream is = conn.getInputStream()) {
                    byte[] buf = GetThread.responseBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    UsersOnline list = gson.fromJson(strBuf, UsersOnline.class);
                    if (list != null) {
                        System.out.println("Users online: ");
                        int i = 1;
                        for (String str : list.getList()) {
                            System.out.println(i + ". " + str);
                            i++;
                        }
                    }
                }

                break;
            }
            case "/setStatus": {
                String status = words[1].equals("1") ? "online" : "away";
                URL obj = new URL(Utils.getURL() + "/setStatus?status=" + status);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
                conn.setRequestMethod("GET");

                if(conn.getResponseCode() == 300){
                    System.out.println("OK. Your status is " + status);
                }
                break;
            }
            default: {

                Message m = new Message(login, text);
                res = m.sendMesg(Utils.getURL() + "/add");
                break;
            }
        }


        return res;
    }
}
