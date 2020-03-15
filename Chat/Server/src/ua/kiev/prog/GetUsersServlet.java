package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetUsersServlet extends HttpServlet {

    private UsersInfoFullList users = UsersInfoFullList.getInstanse();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String json = users.toJSON();
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}
