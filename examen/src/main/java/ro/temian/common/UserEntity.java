/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.temian.common;

import java.util.ArrayList;

/**
 *
 * @author Antoniu
 */
public class UserEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String rfid;
    private boolean srv_1;
    private boolean srv_2;
    private boolean srv_3;
    private boolean srv_4;
    
    public UserEntity(String firstName, String lastName, String rfid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rfid = rfid;
    }
    
    public UserEntity(String firstName, String lastName, String rfid, boolean srv_1, boolean srv_2, boolean srv_3, boolean srv_4) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rfid = rfid;
        this.srv_1 = srv_1;
        this.srv_2 = srv_2;
        this.srv_3 = srv_3;
        this.srv_4 = srv_4;
    }
    
    public UserEntity(int id, String firstName, String lastName, String rfid, boolean srv_1, boolean srv_2, boolean srv_3, boolean srv_4) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rfid = rfid;
        this.srv_1 = srv_1;
        this.srv_2 = srv_2;
        this.srv_3 = srv_3;
        this.srv_4 = srv_4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public boolean isSrv_1() {
        return srv_1;
    }

    public boolean isSrv_2() {
        return srv_2;
    }

    public boolean isSrv_3() {
        return srv_3;
    }

    public boolean isSrv_4() {
        return srv_4;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public String showServerAccess() {
        String s = "";
        ArrayList<Integer> list = new ArrayList<>();
        
        if (srv_1 == true) {
            list.add(1);
        }
        
        if (srv_2 == true) {
            list.add(2);
        }
        
        if (srv_3 == true) {
            list.add(3);
        }
        
        if (srv_4 == true) {
            list.add(4);
        }
        
        if (list.size() > 0) {
            for (Integer i: list) {
                s = s + i + " ";
            }
        } else {
            return "NO SERVER ACCESS";
        }
        
        return s;
    }
}
