package bingoserializables;

import java.io.Serializable;

public class CreateRoom implements Serializable{
    public String id;
    public String host;
    public int maxPlayer;
    public boolean normalMode;
    public int bingoSize;
}
