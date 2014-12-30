package bingoserializables;

import java.io.Serializable;

public class Whisper implements Serializable {
    public String message;
    public String sender;
    public String receiver;
    
    /**
     * 
     * @param m Message: isi pesan
     * @param s Sender: id pengirim
     * @param r Receiver: id penerima
     */
    public Whisper(String m, String s, String r) {
        message = m;
        sender = s;
        receiver = r;
    }
}
