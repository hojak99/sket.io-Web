package sket.model.action;

import sket.model.data.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by KwonJH on 2017-05-12.
 */
public class SessionManager {
    private static HashSet<HttpSession> sessionList = new HashSet<>();

    public static HashSet<HttpSession> getSessionList() {
        return sessionList;
    }

    public static void addSession(HttpSession session) {
        sessionList.add(session);
    }

    public static String getUserIdEqualSession(HttpSession rcvsession) {
        for (HttpSession httpSession : sessionList) {
            if (httpSession.equals(rcvsession)) {
                return ((User) httpSession.getAttribute("user")).getId();
            }
        }
        return null;
    }

    public static String getUserEqualSession(HttpSession session) {
        for (HttpSession httpSession : sessionList) {

            if (httpSession.equals(session)) {
                return ((User) (httpSession.getAttribute("user"))).getId();
            }
        }
        return null;
    }
}