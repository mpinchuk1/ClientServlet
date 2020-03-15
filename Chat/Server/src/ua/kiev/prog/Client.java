package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;



public class Client {
    private String login;
    private String pass;
    private String status;
    private List<Message> messageList;
    private final String IP = "127.0.0.1";
    private static final int LOGINISRIGHT = 202;

    public Client(String login, String pass) {
        this.login = login;
        this.pass = pass;
        this.status = "Online";
        this.messageList = new LinkedList<>();
    }

    private String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static Client fromJSON(String bufStr) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(bufStr, Client.class);
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getIP() {
        return IP;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
