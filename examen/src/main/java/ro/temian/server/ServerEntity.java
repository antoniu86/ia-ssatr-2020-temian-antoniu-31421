/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.temian.server;

import ro.temian.common.UserEntity;
import ro.temian.common.DBAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import ro.temian.common.RoomEntry;

/**
 *
 * @author Antoniu
 */
public class ServerEntity {
    private DBAccess db;
    private ArrayList<RoomEntry> room_entries;

    public ServerEntity() throws ClassNotFoundException, SQLException {
        this.db = new DBAccess();
        this.room_entries = new ArrayList<>();
    }

    public ArrayList<UserEntity> getUsers() throws SQLException {
        return db.findAll();
    }
    
    public void addUser(UserEntity user) throws SQLException {
        db.insertUser(user);
    }
    
    public UserEntity getUserById(int id) throws SQLException {
        return db.findById(id);
    }
    
    public UserEntity getUserByRfid(String rfid) throws SQLException {
        return db.findByRfid(rfid);
    }
    
    public void updateUser(int id, String firstName, String lastName, String rfid, boolean srv_1, boolean srv_2, boolean srv_3, boolean srv_4) throws SQLException {
        db.updateUser(id, firstName, lastName, rfid, srv_1, srv_2, srv_3, srv_4);
    }
    
    public void deleteUserById(int id) throws SQLException {
        db.deleteById(id);
    }
    
    // logs
    
    public void insertLog(String log) throws SQLException {
        db.insertLog(log);
    }
    
    public ArrayList<ServerLog> getLogs() throws SQLException {
        return db.getLogs();
    }
    
    // entries
    
    public ArrayList getRoomEntries() {
        return room_entries;
    }
    
    public void addEntry(RoomEntry entry) {
        this.room_entries.add(entry);
    }
    
    public void removeEntry(RoomEntry entry) {
        this.room_entries.remove(entry);
    }
}
