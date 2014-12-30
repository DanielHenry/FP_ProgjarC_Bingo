package bingoserver;

import bingoserializables.*;
import java.util.ArrayList;

public class Lobby {
    public PlayerList playerStringList;
    public ArrayList<Room> roomList;
    public Lobby() {
        roomList = new ArrayList<>();
        playerStringList = new PlayerList();
    }
}
