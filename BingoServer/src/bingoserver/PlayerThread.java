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
    
    public PlayerThread (ObjectOutputStream os, ObjectInputStream is) {
        this.os = os;
        this.is /*spartaaaaa*/ = is;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public void run() {
        Object obj;
        while (true) {
            try {
                obj = is.readObject();
                if (obj.getClass() == PlayerConnected.class) {
                    PlayerConnected pc = (PlayerConnected)obj;
                    //kirim daftar player ke player yg baru konek
                    os.writeObject(BingoServer.mainLobby.playerStringList);
                    setId(pc.playerName);
                    BingoServer.playerThreadMap.put(id, this);
                    BingoServer.mainLobby.playerStringList.list.add(id);
                    Set<Entry<String, PlayerThread>> entryset = BingoServer.playerThreadMap.entrySet();
                    for (Entry<String, PlayerThread> e : entryset) 
                    {
                        //kirim objek playerconnected ke player2 lain
                        e.getValue().os.writeObject(pc);
                    }
                }
                
                else if (obj.getClass() == Chat.class) {
                    Chat c = (Chat) obj;
                    if (c.roomID == -1) {
                        for (String id : BingoServer.mainLobby.playerStringList.list) {
                            try {
                                BingoServer.playerThreadMap.get(id).os.writeObject(c);
                            }
                            catch (Exception ex) {
                                Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    else {
                        Room r = BingoServer.mainLobby.roomMap.get(c.roomID);
                        if (r != null) {
                            for (String id : r.playerStringList.list) {
                                try {
                                    BingoServer.playerThreadMap.get(id).os.writeObject(c);
                                }
                                catch (Exception ex) {
                                    Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                }
                
                else if (obj.getClass() == Whisper.class) {
                    Whisper w = (Whisper) obj;
                    if (BingoServer.playerThreadMap.containsKey(w.receiver)) {
                        try {
                            BingoServer.playerThreadMap.get(w.receiver).os.writeObject(w);
                        }
                        catch (Exception ex) {
                            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                else if (obj.getClass() == CreateRoom.class) {
                    CreateRoom cr = new CreateRoom();
                    /**
                     * belum
                     */
                }
                
            } catch (Exception ex) {
                Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
                PlayerDisconnected pd = new PlayerDisconnected();
                pd.playerName = id;
                BingoServer.playerThreadMap.remove(id);
                BingoServer.mainLobby.playerStringList.list.remove(id);
                Set<Entry<String, PlayerThread>> entryset = BingoServer.playerThreadMap.entrySet();
                for (Entry<String, PlayerThread> e : entryset) 
                {
                    try {
                        //kirim objek playerdisconnected ke player2 lain
                        e.getValue().os.writeObject(pd);
                    } catch (IOException ex1) {
                        Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                break;
            }
        }
    }
}
