/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.temian.server;

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
    private final ServerEntity server;
    
    public ServerHandler(ServerEntity server) {
        this.server = server;
    }
    
    @Override
    public void run() {
        ServerSocket ss;
        
        try {
            ss = new ServerSocket(4050);
            server.insertLog("Server started");
            
            while(true){
                try (Socket s = ss.accept()) {
                    ArrayList<RoomEntry> room_entries = server.getRoomEntries();
                    
                    BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
                    
                    String rfid = fluxIn.readLine();
                    boolean found = false;
                    
                    System.out.println(rfid);
                    
                    if (rfid == null) {
                        break;
                    }
                    
                    long milli = System.currentTimeMillis();
                    Timestamp stamp = new Timestamp(milli);
                    
                    UserEntity user = null;
                    RoomEntry entry = null;
                    
                    for (RoomEntry re: room_entries) {
                        UserEntity entry_user = re.getUser();
                        
                        if (entry_user.getRfid().equals(rfid)) {
                            found = true;
                            user = entry_user;
                            entry = re;
                            
                            break;
                        }
                    }
                    
                    if (!found) {
                        user = server.getUserByRfid(rfid);
                        
                        if (user != null) {
                            if (user.isSrv_1() || user.isSrv_2() || user.isSrv_3() || user.isSrv_4()) {
                                server.insertLog(user.getFullName() + " entered the servers room at " + stamp);
                                server.addEntry(new RoomEntry(user, milli));
                                fluxOut.println("Entering - ACCESS GRANTED");
                            } else {
                                server.insertLog(user.getFullName() + " trying to enter servers room at " + stamp + " !!! ACCESS DENIED !!!");
                                fluxOut.println("Entering - ACCESS DENIED");
                            }
                        } else {
                            server.insertLog("Unknown user trying to enter servers room with RFID: " + rfid + " at " + stamp + " !!! ALERT !!!");
                            fluxOut.println("Entering - ACCESS DENIED");
                        }
                    } else {
                        server.removeEntry(entry);
                        server.insertLog(user.getFullName() + " left the servers room at " + stamp);
                        fluxOut.println("Exiting - ACCESS GRANTED");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
