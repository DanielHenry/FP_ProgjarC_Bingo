package bingoserver;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import bingoserializables.*;
import java.util.Map.Entry;

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
                    CreateRoom cr;
                    for (Entry<String, Room> es : BingoServer.mainLobby.roomMap.entrySet()) {
                        cr = new CreateRoom();
                        cr.id = es.getValue().id;
                        cr.host = es.getValue().host;
                        cr.bingoSize = es.getValue().bingoSize;
                        cr.normalMode = es.getValue().normalMode;
                        cr.maxPlayer = es.getValue().maxPlayer;
                        os.writeObject(cr);
                        os.flush();
                    }
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
                        System.out.println("activeRoom: " + activeRoom.playerStringList.list.size());
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
                    CreateRoom cr = (CreateRoom)obj;
                    Room r = new Room();
                    r.host = cr.host;
                    r.id = cr.id;
                    r.bingoSize = cr.bingoSize;
                    r.maxPlayer = cr.maxPlayer;
                    r.normalMode = cr.normalMode;
                    r.playerStringList = new PlayerList();
                    r.playerStringList.list.add(id);
                    activeRoom = r;
                    BingoServer.mainLobby.roomMap.put(r.id, r);
                    BingoServer.mainLobby.roomStringList.list.add(r.id);
                    BingoServer.mainLobby.playerStringList.list.remove(id);
                    PlayerDisconnected pd = new PlayerDisconnected();
                    pd.playerName = id;
                    sendToList(BingoServer.mainLobby.playerStringList, pd);
                    sendToList(BingoServer.mainLobby.playerStringList, cr);
                    //for (String s : BingoServer.mainLobby.playerStringList.list) {
                   //     PlayerThread p = BingoServer.playerThreadMap.get(s);
                   //     p.os.writeObject(cr);
                   //     p.os.flush();
                   // }
                }
                
                else if (obj.getClass() == JoinRoom.class) {
                    JoinRoom jr = (JoinRoom) obj;
                    BingoServer.mainLobby.playerStringList.list.remove(id);
                    PlayerDisconnected pd = new PlayerDisconnected();
                    pd.playerName = id;
                    sendToList(BingoServer.mainLobby.playerStringList, pd);
                    Room r = BingoServer.mainLobby.roomMap.get(jr.roomID);
                    os.writeObject(r.playerStringList);
                    sendToList(r.playerStringList, jr);
                    r.playerStringList.list.add(id);
                    activeRoom = r;
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
