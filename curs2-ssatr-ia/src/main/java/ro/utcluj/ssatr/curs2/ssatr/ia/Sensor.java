/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.curs2.ssatr.ia;

/**
 *
 * @author antoniu
 */
public class Sensor {
    //atribute
    private int value;
    private String location;
    
    //constructori
    Sensor(int value, String location){
        this.value = value;
        this.location = location;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public boolean equals(Object obj) {
        if(obj instanceof Sensor){
            Sensor s = (Sensor)obj;
            
            if (location.equals(s.getLocation())) {
                return true;
            }
        }

        
        return false;
    }
    
    public static void main(String[] args) {
        Sensor s1 = new Sensor(1, "location1");
	Sensor s2 = new Sensor(2, "location2");
        Sensor s3 = new Sensor(3, "location1");
        
        System.out.println(s1 + " and " + s2 + " => " + s1.equals(s2));
        System.out.println(s1 + " and " + s3 + " => " + s1.equals(s3));
        System.out.println(s2 + " and " + s3 + " => " + s2.equals(s3));
    }
}
