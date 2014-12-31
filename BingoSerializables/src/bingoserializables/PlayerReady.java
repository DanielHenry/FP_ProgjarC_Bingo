package bingoserializables;

import java.io.Serializable;


public class PlayerReady implements Serializable {
    public String playerID;
    public String roomID;
    public PlayerReady(String pID, String rID) {
        playerID = pID;
        roomID = rID;
    }
}
