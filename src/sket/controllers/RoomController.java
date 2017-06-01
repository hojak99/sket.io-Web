package sket.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import sket.model.action.PlayerAction;
import sket.model.data.Player;
import sket.model.data.Room;

public class RoomController {

    public RoomController() {
        super();
    }

    /* 방 생성하는 메소드 */
    public static Room createRoom(String name, boolean isLock, String pwd, String masterId) {
        // 방 생성 코드. Room 생성자 안에 roomList 에 방 추가하는 코드 작성되있음.
        Room room = new Room(name, PlayerAction.getEqualPlayerId(masterId), Room.getCountRoomId(), isLock, pwd);
        Player player = PlayerAction.getEqualPlayerId(masterId);
        player.setMaster(true);

        return room;
    }

    /* 방 정보 json 으로 반환하는 메소드 */
    public static String getRoomInfoToJSON(Room targetRoom) {
        JSONObject message = new JSONObject();
        message.put("type", "ROOM_INFO");

        JSONObject data = new JSONObject();
        data.put("roomId", targetRoom.getRoomId());
        data.put("name", targetRoom.getRoomName());
        data.put("lock", targetRoom.isLocked());
        data.put("playerNumber", targetRoom.getTotalUserNumber());
        data.put("roomMaster", targetRoom.getRoomId());
        JSONArray jsonArray = new JSONArray();

        for (Player player : Room.getRoomIntoPlayer(targetRoom)) {
            JSONObject temp = new JSONObject();
            temp.put("nick", player.getId());
            jsonArray.put(temp);
        }

        data.put("playerList", jsonArray);
        message.put("data", data);
        return message.toString();
    }

    /* 방 목록을 json 으로 보내는 메소드 */
    public static String getRoomListAsJSON() {
        JSONObject message = new JSONObject();
        message.put("type", "ROOM_LIST");

        JSONObject data = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        System.out.println("log : 방 목록 리스트 사이즈 : " + Room.getRoomList().size());

        if (Room.getRoomList().size() == 0) {
            data.put("size", 0);
            message.put("data", data);
            return message.toString();
        }

        for (Room room : Room.getRoomList()) {
            JSONObject object = new JSONObject();
            object.put("roomId", room.getRoomId());
            object.put("name", room.getRoomName());
            object.put("lock", room.isLocked());
            object.put("password", room.getRoomPwd());
            object.put("playerNumber", room.getTotalUserNumber());

            jsonArray.put(object);
        }

        data.put("roomList", jsonArray);
        message.put("data", data);
        return message.toString();

        /*
            다음과 같이 json 보낸다.

            {
	        "type": "ROOM_LIST",
	        "roomList": [{
		        "playerNumber": 0,
		        "password": "null",
		        "name": "방제목",
		        "lock": false,
		        "roomId": 0
	        }, {
		        "playerNumber": 0,
		        "password": "123123",
		        "name": "방제목1",
		        "lock": true,
		        "roomId": 1
	        }]
        }

        */
    }
}