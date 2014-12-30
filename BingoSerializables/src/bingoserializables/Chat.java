package bingoserializables;

import java.io.Serializable;

public class Chat implements Serializable {
    public int roomID;
    public String message;
    public String sender;
    
    /**
     * 
     * @param i ID: id room
     * @param m Message: isi pesan
     * @param s Sender: pengirim
     */
    public Chat(int i, String m, String s) {
        roomID = i;
        message = m;
        sender = s;
    }
}
