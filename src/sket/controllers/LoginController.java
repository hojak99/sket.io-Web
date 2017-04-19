package sket.controllers;

import sket.model.action.FBConnection;
import sket.model.action.FBGraph;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String code = "";

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String act = req.getParameter("loginBtn");

        if (act == null) {
            //no button has been selected
        } else if (act.equals("fb")) {
            FBConnection fbConnection = new FBConnection();
            code = fbConnection.getFBAuthUrl();
            System.out.println(code);

            if (code == null || code.equals("")) {
                throw new RuntimeException(
                        "ERROR: Didn't get code parameter in callback.");
            }

            String accessToken = fbConnection.getAccessToken(code);
            System.out.println(accessToken);

            FBGraph FBGraph = new FBGraph(accessToken);
            String graph = FBGraph.getFBGraph();
            Map<String, String> fbProfileData = FBGraph.getGraphData(graph);
            ServletOutputStream out = res.getOutputStream();

            out.println("<h1>Facebook Login using Java</h1>");
            out.println("<h2>Application Main Menu</h2>");
            out.println("<div>Welcome " + fbProfileData.get("first_name"));
            out.println("<div>Your Email: " + fbProfileData.get("email"));
            out.println("<div>Your Token: " + accessToken);
        } else if (act.equals("google")) {
            System.out.println("google");
//            LoginGoogle.loginGoogle = new LoginGoogle();
        } else {
            //someone has altered the HTML and sent a different value!
        }

        /*
        success -->
            request.getSession().setAttribute("user", user);
            response.sendRedirect("home");
         */
        /*
        Authenticator authenticator = new Authenticator();
        String result = authenticator.authenticate(username, password);
        if (result.equals("success")) {
//            rd = request.getRequestDispatcher("/success.jsp");
            System.out.println("success login");
            User user = new User(username, password, nick);
            request.setAttribute("user", user);
        } else {
            System.out.println("success fail");
//            rd = request.getRequestDispatcher("/error.jsp");
        }*/
    }
}