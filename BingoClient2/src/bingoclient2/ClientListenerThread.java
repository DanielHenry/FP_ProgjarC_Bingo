
package bingoclient2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import bingoserializables.*;
import javax.swing.DefaultListModel;

public class ClientListenerThread extends Thread{
    DefaultListModel playerListModel;
    DefaultListModel roomListModel;
    
    LobbyFrame lobbyFrame;
    public ClientListenerThread (LobbyFrame lf) {
        lobbyFrame = lf;
    }
    
    @Override
    public void run () {
        ObjectOutputStream os = ClientGlobals.os;
        ObjectInputStream is = ClientGlobals.is;
        try {
            while (true) {
                Object o = is.readObject();
                System.out.println("Got object of type");
                System.out.println(o.getClass());
                if (o.getClass() == PlayerList.class) {
                    PlayerList pl = (PlayerList) o;
                    playerListModel = new DefaultListModel();
                    playerListModel.addElement(ClientGlobals.id);
                    for (String s : pl.list) {
                        playerListModel.addElement(s);
                    }
                    lobbyFrame.setPlayerListModel(playerListModel);
                }
                
                else if (o.getClass() == RoomList.class) {
                    RoomList rl = (RoomList) o;
                    roomListModel = new DefaultListModel();
                    for (String s : rl.list) {
                        roomListModel.addElement(s);
                    }
                    lobbyFrame.setRoomListModel(roomListModel);
                }
                
                else if (o.getClass() == PlayerConnected.class) {
                    PlayerConnected pc = (PlayerConnected) o;
                    playerListModel.addElement(pc.playerName);
                    lobbyFrame.setPlayerListModel(playerListModel);
                }
                
                else if (o.getClass() == PlayerDisconnected.class) {
                    PlayerDisconnected pd = (PlayerDisconnected) o;
                    playerListModel.removeElement(pd.playerName);
                    lobbyFrame.setPlayerListModel(playerListModel);
                }
            }
        }
        catch (Exception ex) {
            Logger.getLogger(ClientListenerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
