package sket.model.data;

import java.util.ArrayList;

/**
 * Created by KwonJH on 2017-04-13.
 */
public class Player {
    public static ArrayList<Player> playerArrayList = new ArrayList<>();

    private static int guestID = 100;
    private String id; // oauthID or guest는 100부터
    private String sessionId;

    private int score = 0;
    
    private boolean isMaster = false;
    private boolean isExaminer = false;
    private boolean isReady = false;
    private boolean isGuest = false;

    public Player(String id, String sessionId, boolean isGuest) {
        this.id = id;
        this.sessionId = sessionId;
        this.isGuest = isGuest;

        playerArrayList.add(this);
    }

    public Player(String sessionId, boolean isGuest) {
        this.id = guestID + "";
        this.sessionId = sessionId;
        this.isGuest = isGuest;
        playerArrayList.add(this);

        guestID += 1;
    }

    public static ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    public static void setPlayerArrayList(ArrayList<Player> playerArrayList) {
        Player.playerArrayList = playerArrayList;
    }

    public static int getGuestID() {
        return guestID;
    }

    public static void setGuestID(int guestID) {
        Player.guestID = guestID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public void setMaster(boolean master) {
        isMaster = master;
    }

    public boolean isExaminer() {
        return isExaminer;
    }

    public void setExaminer(boolean examiner) {
        isExaminer = examiner;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }
}
