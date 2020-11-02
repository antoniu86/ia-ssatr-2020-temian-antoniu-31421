/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.curs2.ssatr.ia;

/**
 *
 * @author mihai.hulea
 */
public class Car {
    //atribute
    private String name;
    public  int speed;
    public  String plate;
    
    //constructori
    Car(String name, int speed, String plate){
        this.name = name;
        this.speed = speed;
        this.plate = plate;
    }
    
    void accelerate(){
        speed++;
        System.out.println("Vehicle speed is "+speed);
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }
}
