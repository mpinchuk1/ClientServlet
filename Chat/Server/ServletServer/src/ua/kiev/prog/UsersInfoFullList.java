package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class UsersInfoFullList {
    private static final UsersInfoFullList usersList = new UsersInfoFullList();


    private final List<Client> list = new ArrayList<>();

    public static UsersInfoFullList getInstanse(){
        return usersList;
    }

    private UsersInfoFullList(){

    }

    public synchronized void add(Client c) {
        list.add(c);
    }

    public synchronized String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(new UsersOnline(list));
    }

    public List<Client> getList(){
        return list;
    }
}
