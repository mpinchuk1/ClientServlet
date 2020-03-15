package ua.kiev.prog;


import java.util.ArrayList;
import java.util.List;

public class UsersOnline  {

    private final List<String> list;

    public UsersOnline(List<Client> clientList){
        list = new ArrayList<>();
        for (Client c: clientList) {
            list.add(c.getLogin() + " (" + c.getStatus() + ")");
        }
    }
}
