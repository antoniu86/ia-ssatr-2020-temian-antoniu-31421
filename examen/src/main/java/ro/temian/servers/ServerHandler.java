/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.temian.servers;

import ro.temian.common.RoomEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.temian.common.UserEntity;

/**
 *
 * @author Antoniu
 */
public class ServerHandler extends Thread {
    private int id;
    private int port;
    
    public ServerHandler(int id, int port) {
        this.id = id;
        this.port = port;
    }
    
    @Override
    public void run() {
        ServerSocket ss;
        
        try {
            ss = new ServerSocket(port);
            
            while(true){
                try (Socket s = ss.accept()) {
                    BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

                    String status = fluxIn.readLine();

                    Socket s1 = new Socket("127.0.0.1", 4050);
                    PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s1.getOutputStream()),true);
                    
                    if (status.equals("Unlocking")) {
                        fluxOut.println("SRV " + id + " unlocking");
                    } else {
                        fluxOut.println("SRV " + id + " locking");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
