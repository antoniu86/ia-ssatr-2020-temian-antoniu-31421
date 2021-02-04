/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.temian.client;

import java.sql.SQLException;
import ro.temian.common.UserEntity;
import ro.temian.common.DBAccess;

/**
 *
 * @author Antoniu
 */
public class ClientEntity {
    private DBAccess db;

    public ClientEntity() throws ClassNotFoundException, SQLException {
        this.db = new DBAccess();
    }
    
    public UserEntity getUserByRfid(String rfid) throws SQLException {
        return db.findByRfid(rfid);
    }
}
