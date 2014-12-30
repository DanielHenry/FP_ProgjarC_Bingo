package bingoserver;

import bingoserializables.*;

public class Room {
    public PlayerList playerStringList;
    public String host;
    public String id;
    public int maxPlayer;
    public boolean normalMode;
    public int bingoSize;
    
    public Room() {
        playerStringList = new PlayerList();
    }
}
