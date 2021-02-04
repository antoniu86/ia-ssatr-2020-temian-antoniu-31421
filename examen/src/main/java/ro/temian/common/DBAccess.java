/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.temian.common;

import ro.temian.servers.MainServerLog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Antoniu
 */
public class DBAccess {
    // connection
    private Connection conn;

    // connection credentials
    private String db = "examen";
    private String user = "examen";
    private String pass = "examen";

    public DBAccess() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/" + db + ";create=false", user, pass);
    }
    
    // adds

    public void insertUser(UserEntity user) throws SQLException {
        Statement s = conn.createStatement();
        int id = 1;
        
        ResultSet rs = s.executeQuery("SELECT ID FROM USERS ORDER BY ID DESC FETCH FIRST ROW ONLY");
        
        if (rs.next()) {
            id = Integer.parseInt(rs.getString("ID")) + 1;
        }
        
        s.executeUpdate("INSERT INTO USERS (ID, FIRSTNAME, LASTNAME, RFID, SRV_1, SRV_2, SRV_3, SRV_4) VALUES (" + id + ", '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getRfid() + "', " + user.isSrv_1() + ", " + user.isSrv_2() + ", " + user.isSrv_3() + ", " + user.isSrv_4() + ")");        
        s.close();
    }
    
    public void insertLog(String log) throws SQLException {
        Statement s = conn.createStatement();
        int id = 1;
        
        ResultSet rs = s.executeQuery("SELECT ID FROM LOGS ORDER BY ID DESC FETCH FIRST ROW ONLY");
        
        if (rs.next()) {
            id = Integer.parseInt(rs.getString("ID")) + 1;
        }
        
        s.executeUpdate("INSERT INTO LOGS (ID, ENTRY, TIME) VALUES (" + id + ", '" + log + "', " + System.currentTimeMillis() + ")");
    }
    
    // get
    
    public ArrayList getLogs() throws SQLException {
        Statement s = conn.createStatement();
        ArrayList<MainServerLog> logs = new ArrayList<>();
        
        ResultSet rs = s.executeQuery("SELECT * FROM LOGS");
        
        while (rs.next()) {
            logs.add(new MainServerLog(rs.getString("entry"), Long.parseLong(rs.getString("time"))));
        }
        
        return logs;
    }
    
    // finds
    
    public ArrayList findAll() throws SQLException {
        Statement s = conn.createStatement();
        ArrayList<UserEntity> users = new ArrayList<>();
        
        ResultSet rs = s.executeQuery("SELECT * FROM USERS");
        
        while (rs.next()) {
            users.add(new UserEntity(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("rfid"), rs.getBoolean("srv_1"), rs.getBoolean("srv_2"), rs.getBoolean("srv_3"), rs.getBoolean("srv_4")));
        }
        
        return users;
    }

    public UserEntity findByRfid(String rfid) throws SQLException {
        Statement s = conn.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM USERS WHERE RFID = '" + rfid + "'");

        if (rs.next()) {
            return new UserEntity(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("rfid"), rs.getBoolean("srv_1"), rs.getBoolean("srv_2"), rs.getBoolean("srv_3"), rs.getBoolean("srv_4"));
        } else {
            return null;
        }
    }
    
    public UserEntity findById(int id) throws SQLException {
        Statement s = conn.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM USERS WHERE ID = " + id);

        if (rs.next()) {
            return new UserEntity(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("rfid"), rs.getBoolean("srv_1"), rs.getBoolean("srv_2"), rs.getBoolean("srv_3"), rs.getBoolean("srv_4"));
        } else {
            return null;
        }
    }
    
    // updates
    
    public void updateUser(int id, String firstName, String lastName, String rfid, boolean srv_1, boolean srv_2, boolean srv_3, boolean srv_4) throws SQLException{
        Statement s = conn.createStatement();
        s.executeUpdate("UPDATE USERS SET FIRSTNAME = '" + firstName + "', LASTNAME = '" + lastName + "', RFID = '" + rfid + "', srv_1 = " + srv_1 + ", srv_2 = " + srv_2 + ", srv_3 = " + srv_3 + ", srv_4 = " + srv_4 + " WHERE ID = " + id);
    }
    
    // deletes
    
    public void deleteByRfid(String rfid) throws SQLException{
        Statement s = conn.createStatement();
        s.executeUpdate("DELETE FROM USERS WHERE RFID = '" + rfid + "'");        
    }
    
    public void deleteById(int id) throws SQLException{
        Statement s = conn.createStatement();
        s.executeUpdate("DELETE FROM USERS WHERE ID = " + id);        
    }
    
    public void deleteLogs() throws SQLException {
        Statement s = conn.createStatement();
        s.executeUpdate("DELETE FROM LOGS");   
    }
    
    // main

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBAccess db = new DBAccess();

        UserEntity user1 = new UserEntity("Antoniu", "Temian", "1362006190", true, true, false, true);
        UserEntity user2 = new UserEntity("Mos", "Craciun", "5568894112", false, false, true, true);

        db.insertUser(user1);
        db.insertUser(user2);

        UserEntity result1 = db.findByRfid("1362006190");
        System.out.println(result1);
        
        if (result1 != null) {
            //db.deleteByRfid(result.getRfid());

            System.out.println("User deleted!");
        } else {
            System.out.println("User not found!");
        }
        
        UserEntity result2 = db.findById(562);
        System.out.println(result2);

        if (result2 != null) {
            //db.deleteByRfid(result.getRfid());

            System.out.println("User deleted!");
        } else {
            System.out.println("User not found!");
        }

    }
}
