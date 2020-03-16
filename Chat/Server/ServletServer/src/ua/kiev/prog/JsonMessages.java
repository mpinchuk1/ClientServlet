package ua.kiev.prog;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonMessages {
    private final List<Message> list;

    public JsonMessages(List<Message> sourceList, int fromIndex, String login) {
        this.list = new ArrayList<>();
        for (int i = fromIndex; i < sourceList.size(); i++) {
            if(!sourceList.get(i).getTo().equals("@ALL")){
                if(sourceList.get(i).getTo().equals(login)){
                    list.add(sourceList.get(i));
                }
            }else {
                list.add(sourceList.get(i));
            }


        }

    }

}
