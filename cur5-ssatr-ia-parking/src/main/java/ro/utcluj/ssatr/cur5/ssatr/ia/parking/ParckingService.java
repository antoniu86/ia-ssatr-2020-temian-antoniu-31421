/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.cur5.ssatr.ia.parking;

import java.sql.SQLException;

/**
 *
 * @author mihai.hulea
 */
public class ParckingService {
    
    private DBAccess db;
    public static final int UNIT_PRICE = 2; 

    public ParckingService() throws ClassNotFoundException, SQLException {
        db = new DBAccess();
    }
  
    public synchronized String handlePlateNumber(String plateNumber) throws SQLException{
        CarAccessEntity c = db.findByPlateNumber(plateNumber);
        if(c==null){
            CarAccessEntity x = new CarAccessEntity(plateNumber, System.currentTimeMillis());
            db.insertCar(x);
            return "Car entry: "+x.getPlateNumber()+ " tariff: "+UNIT_PRICE+" RON";
        }else{
            long current = System.currentTimeMillis();
            int p = computeParkingStayPrice(c.getEntryTime(), current);
            db.addCarToHistory(c, current);
            db.deleteByPlateNumber(plateNumber);
            return "Car exit: "+c.getPlateNumber()+" price = "+p+" RON";
        } 
    }
    
    private int computeParkingStayPrice(long entryTime, long current){
        //1 LEU / secunda        
        return (int)(((current - entryTime)/1000)* UNIT_PRICE);
        
    }
    
    public static void main(String[] args) throws Exception {
        ParckingService p = new ParckingService();
        System.out.println(p.handlePlateNumber("CJ 99 ABC"));
        Thread.sleep(2000);
        System.out.println(p.handlePlateNumber("CJ 99 ABC"));
    }
    
}
