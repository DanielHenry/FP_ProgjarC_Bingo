package bingoserializables;

import java.io.Serializable;

public class PlayerConnected implements Serializable{
    public String playerName;
    /**
     * @param p id player
     */
    public PlayerConnected(String p) {
        playerName = p;
    }
}
