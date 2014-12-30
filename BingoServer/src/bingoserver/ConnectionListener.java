package bingoserver;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionListener extends Thread{
    ServerSocket servSock;
    int port;
    Exception ex;
    public ConnectionListener(int port) throws IOException {
        servSock = new ServerSocket(port);
        
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Socket client = servSock.accept();
                ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(client.getInputStream());
                PlayerThread p = new PlayerThread(os, is);
                p.start();
            } catch (IOException ex) {
                Logger.getLogger(ConnectionListener.class.getName()).log(Level.SEVERE, null, ex);
                this.ex = ex;
                break;
            }
        }
    }
}
