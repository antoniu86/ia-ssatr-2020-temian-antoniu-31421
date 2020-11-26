/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.temalab3;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author antoniu
 */
public class test {
    
    public static void main(String[] args) throws InterruptedException {
        
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp1);
        
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        System.out.println(formatter.format(timestamp1));
        
        
        Thread.sleep(5000);
        
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp2);
        
        System.out.println(((timestamp2.getTime() - timestamp1.getTime()) / 1000));
    }
    
}
