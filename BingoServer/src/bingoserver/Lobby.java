package bingoserver;

import bingoserializables.*;
import java.util.HashMap;

public class Lobby {
    public PlayerList playerStringList;
    public HashMap<String, Room> roomMap;
    public RoomList roomStringList;
    public Lobby() {
        roomMap = new HashMap<>();
        roomStringList = new RoomList();
        playerStringList = new PlayerList();
    }
}
