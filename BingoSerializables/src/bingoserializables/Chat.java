package bingoserializables;

import java.io.Serializable;

public class Chat implements Serializable {
    public String message;
    public String sender;
    /**
     * 
     * @param m Message: isi pesan
     * @param s Sender: pengirim
     */
    public Chat(String m, String s) {
        message = m;
        sender = s;
    }
}
