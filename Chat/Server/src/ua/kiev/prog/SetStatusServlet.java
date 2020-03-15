package ua.kiev.prog;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SetStatusServlet extends HttpServlet {

    private UsersInfoFullList users = UsersInfoFullList.getInstanse();
    private List<Client> list = users.getList();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status =  req.getParameter("status");

        ServletContext context = req.getServletContext();
        String str = (String) context.getAttribute("user_login");

        //System.out.println(list.toString());
        for (Client user: users.getList()) {
            if(user.getLogin().equals(str)){
                user.setStatus(status);
                //System.out.println(user.toString());
                resp.setStatus(300);
            }
        }

    }
}
