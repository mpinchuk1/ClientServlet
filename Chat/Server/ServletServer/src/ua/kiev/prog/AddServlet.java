package ua.kiev.prog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();
    private UsersInfoFullList users = UsersInfoFullList.getInstanse();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        ServletContext context = req.getServletContext();
        String curLogin = (String) context.getAttribute("user_login");
//        List<Message> userMsgList = new LinkedList<>();
//        for (Client client: users.getList()){
//            if(client.getLogin().equals(curLogin))
//                userMsgList = client.getMessageList();
//        }
		Message msg = Message.fromJSON(bufStr);
		if (msg != null){
//            if(curLogin.equals(msg.getTo())){
//                userMsgList.add(msg);
//            }
            msgList.add(msg);

            //System.out.println(msgList.toString());
        }
		else
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	public static byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
