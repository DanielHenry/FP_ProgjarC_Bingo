
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
                    if (ClientGlobals.gameFrame == null) {
                        playerListModel = new DefaultListModel();
                        playerListModel.addElement(ClientGlobals.id);
                        for (String s : pl.list) {
                            playerListModel.addElement(s);
                        }
                        lobbyFrame.setPlayerListModel(playerListModel);
                    }
                    else {
                        DefaultListModel roomPlayerModel = new DefaultListModel();
                        roomPlayerModel.addElement(ClientGlobals.id);
                        for (String s : pl.list) {
                            roomPlayerModel.addElement(s);
                        }
                        ClientGlobals.gameFrame.setPlayerListModel(roomPlayerModel);
                    }
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
                
                else if (o.getClass() == Chat.class) {
                    Chat c = (Chat) o;
                    lobbyFrame.addChat(c.sender + ": " + c.message);
                }
                
                else if (o.getClass() == Whisper.class) {
                    Whisper w = (Whisper) o;
                    lobbyFrame.addChat("(Whisper)" + w.sender + ": " + w.message);
                }
                
                else if (o.getClass() == CreateRoom.class) {
                    CreateRoom cr = (CreateRoom) o;
                    roomListModel.addElement(cr.id);
                    ClientGlobals.roomMap.put(cr.id, new Room(cr.normalMode, cr.bingoSize));
                    lobbyFrame.setRoomListModel(roomListModel);
                }
                
                else if (o.getClass() == JoinRoom.class && ClientGlobals.gameFrame != null) {
                    JoinRoom jr = (JoinRoom) o;
                    ClientGlobals.gameFrame.addPlayer(jr.playerID);
                }
            }
        }
        catch (Exception ex) {
            Logger.getLogger(ClientListenerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
