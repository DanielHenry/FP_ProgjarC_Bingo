package bingoserializables;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wik
 */
public class PlayerList implements Serializable {
    public ArrayList<String> list;
    
    public PlayerList() {
        list = new ArrayList<>();
    }
}
