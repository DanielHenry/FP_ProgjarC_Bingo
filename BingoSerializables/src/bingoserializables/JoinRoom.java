/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserializables;

import java.io.Serializable;

/**
 *
 * @author Wik
 */
public class JoinRoom implements Serializable {
    public String roomID;
    public String playerID;
    public JoinRoom(String rID, String pID) {
        roomID = rID;
        playerID = pID;
    }
}
