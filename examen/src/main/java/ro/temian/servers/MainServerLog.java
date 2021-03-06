/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.temian.servers;

import java.sql.Timestamp;

/**
 *
 * @author Antoniu
 */
public class MainServerLog {
    private final String message;
    private final long time;

    public MainServerLog(String message, long time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }
    
    @Override
    public String toString() {
        Timestamp stamp = new Timestamp(time);
        
        return stamp + ": " + message + "\n";
    }
}
