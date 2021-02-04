/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.temian.common;

/**
 *
 * @author Antoniu
 */
public class RoomEntry {
    private UserEntity user;
    private long entry;

    public RoomEntry(UserEntity user, long entry) {
        this.user = user;
        this.entry = entry;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public long getEntry() {
        return entry;
    }

    public void setEntry(long entry) {
        this.entry = entry;
    }
}
