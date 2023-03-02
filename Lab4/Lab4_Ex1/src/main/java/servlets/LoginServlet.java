package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.System.out;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String, String> accounts = new HashMap<>();
//    public LoginServlet() {
//        // Add pre-built accounts to the HashMap
//        accounts.put("admin", "123");
//        accounts.put("user0", "123");
//        accounts.put("user1", "321");
//    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, String> accounts = new HashMap<>();
        accounts.put("admin", "admin123");
        accounts.put("user", "user123");



        //if username not in hashmap, get_pwd == null;
        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            response.setContentType("text/html");
            response.getWriter().println("<html><body><h2>Name/Password match</h2></body></html>");
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<html><body><h2>Name/Password does not match</h2></body></html>");
        }

        // check if the content-type is set to "text/html", and correct it if necessary
        if (!response.getContentType().startsWith("text/html")) {
            response.setContentType("text/html");
        }
    }
}
