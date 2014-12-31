/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoclient2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


/**
 *
 * @author Wik
 */
public class ClientGlobals {
    public static String id;
    public static ObjectOutputStream os;
    public static ObjectInputStream is;
    public static String activeRoomID;
    public static BingoFrame2 gameFrame;
    public static HashMap<String, Room> roomMap;
}

class UserException extends Exception{
    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}