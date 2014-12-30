package bingoserializables;

import java.io.Serializable;

public class Chat implements Serializable {
    public String message;
    public String sender;
    public boolean global;
    /**
     * 
     * @param i ID: id room
     * @param m Message: isi pesan
     * @param s Sender: pengirim
     */
    public Chat(String m, String s, boolean g) {
        message = m;
        sender = s;
        global = g;
    }
}
