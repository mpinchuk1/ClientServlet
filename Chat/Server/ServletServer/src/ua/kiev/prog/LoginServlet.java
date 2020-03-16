package ua.kiev.prog;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    static final Map<String, String> users = new HashMap<>();
    private UsersInfoFullList usersOnline = UsersInfoFullList.getInstanse();
    public Client client;

    static {
        users.put("student", "123");
        users.put("max", "123");
        users.put("sava", "123");
        users.put("alex", "123");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = AddServlet.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        client = Client.fromJSON(bufStr);
        if (client != null){

            String tmpPass = users.get(client.getLogin());
            if(client.getPass().equals(tmpPass)){
                ServletContext context = req.getServletContext();
                context.setAttribute("user_login", client.getLogin());
                HttpSession session = req.getSession(true);
                session.setAttribute("user_login", client.getLogin());
                System.out.println("OK. " + client.getLogin() + " is online!");
                usersOnline.add(client);
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);

            }else {
                System.out.println("Wrong pass or/and login");
            }

        }
        else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String exitPar = req.getParameter("exit");
        HttpSession session = req.getSession(false);

        if("1".equals(exitPar) && session != null){
            session.removeAttribute("user_login");
        }
    }

    //public Client getClient(){ return client;}
}
