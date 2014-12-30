package bingoserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import bingoserializables.*;
import java.util.Map.Entry;
import java.util.Set;

public class PlayerThread extends Thread {
    private String id;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private Room activeRoom;
    
    public PlayerThread (ObjectOutputStream os, ObjectInputStream is) {
        this.os = os;
        this.is /*spartaaaaa*/ = is;
        activeRoom = null;
        id = null;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    private void sendToList(PlayerList p, Object o) {
        for (String id : p.list) {
            try {
                BingoServer.playerThreadMap.get(id).os.writeObject(o);
                BingoServer.playerThreadMap.get(id).os.flush();
            }
            catch (Exception e) {
                Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    @Override
    public void run() {
        Object obj;
        while (true) {
            try {
                obj = is.readObject();
                if (obj.getClass() == PlayerConnected.class) {
                    System.out.println("Got PlayerConnected Object");
                    PlayerConnected pc = (PlayerConnected)obj;
                    //kalau baru pertama kali konek (bukan dari room)
                    if (id == null) {
                        setId(pc.playerName);
                        BingoServer.playerThreadMap.put(id, this);
                    }
                    //kirim daftar player ke player yg baru konek
                    os.writeObject(BingoServer.mainLobby.playerStringList);
                    os.flush();
                    //kirim daftar room
                    os.writeObject(BingoServer.mainLobby.roomStringList);
                    os.flush();
                    //kirim objek playerconnected ke player-player lain di lobby
                    sendToList(BingoServer.mainLobby.playerStringList, pc);
                    BingoServer.mainLobby.playerStringList.list.add(id);
                }
                
                else if (obj.getClass() == Chat.class) {
                    Chat c = (Chat) obj;
                    if (activeRoom == null) {
                        sendToList(BingoServer.mainLobby.playerStringList, c);
                    }
                    else {
                        sendToList(activeRoom.playerStringList, c);
                    }
                }
                
                else if (obj.getClass() == Whisper.class) {
                    Whisper w = (Whisper) obj;
                    if (BingoServer.playerThreadMap.containsKey(w.receiver)) {
                        try {
                            os.writeObject(w);
                            os.flush();
                            BingoServer.playerThreadMap.get(w.receiver).os.writeObject(w);
                            BingoServer.playerThreadMap.get(w.receiver).os.flush();
                        }
                        catch (Exception ex) {
                            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                else if (obj.getClass() == CreateRoom.class) {
                    CreateRoom cr = new CreateRoom();
                    Room r = new Room();
                    r.host = cr.host;
                    r.id = cr.id;
                    r.bingoSize = cr.bingoSize;
                    r.maxPlayer = cr.maxPlayer;
                    r.normalMode = cr.normalMode;
                    r.playerStringList = new PlayerList();
                    r.playerStringList.list.add(id);
                    activeRoom = r;
                    BingoServer.mainLobby.roomMap.put(id, r);
                    BingoServer.mainLobby.roomStringList.list.add(id);
                    BingoServer.mainLobby.playerStringList.list.remove(id);
                    for (String s : BingoServer.mainLobby.playerStringList.list) {
                        PlayerThread p = BingoServer.playerThreadMap.get(s);
                        p.os.writeObject(cr);
                        p.os.flush();
                    }
                }
                
                
            } catch (Exception ex) {
                Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
                PlayerDisconnected pd = new PlayerDisconnected();
                pd.playerName = id;
                if (activeRoom != null) {
                    activeRoom.playerStringList.list.remove(id);
                    sendToList(activeRoom.playerStringList, pd);
                }
                else {
                    BingoServer.mainLobby.playerStringList.list.remove(id);
                    sendToList(BingoServer.mainLobby.playerStringList, pd);
                }
                BingoServer.playerThreadMap.remove(id);
                break;
            }
        }
    }
}
