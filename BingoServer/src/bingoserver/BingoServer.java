
package bingoserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import bingoserializables.PlayerList;
import java.util.HashMap;

public class BingoServer {
    
    public static HashMap<String, PlayerThread> playerThreadMap;
    public static Lobby mainLobby;
    
    public static void main(String[] args) {
        playerThreadMap = new HashMap<>();
        mainLobby = new Lobby();
        try {
            ConnectionListener cl = new ConnectionListener(4545);
            cl.start();
        } catch (IOException ex) {
            Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
