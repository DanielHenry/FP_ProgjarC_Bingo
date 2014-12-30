package bingoserializables;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomList implements Serializable {
    public ArrayList<String> list;
    public RoomList() {
        list = new ArrayList();
    }
}
