package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Client {

    private String login;
    private String pass;
    private String status;
    private final String IP = "127.0.0.1";
    private static final int LOGINISRIGHT = 202;
    private List<Message> messageList;

    public Client(String login, String pass) {
        this.login = login;
        this.pass = pass;
        this.status = "Online";
        messageList = new LinkedList<>();
    }

    private String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public boolean checkLogIn() throws IOException {
        URL obj = new URL(Utils.getURL() + "/login");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String json = toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));
        }

        return conn.getResponseCode() == LOGINISRIGHT;
    }

    public static void logOut() throws IOException {
        URL obj = new URL(Utils.getURL() + "/login?exit=1");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("GET");
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getIP() {
        return IP;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
     }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
